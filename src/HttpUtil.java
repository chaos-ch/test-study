package com.qunar.hotel.sa.common.util;

import com.qunar.base.meerkat.monitor.QMonitor;
import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import qunar.QunarServer;
import qunar.hc.QunarClient;
import qunar.hc.tools.Interceptors;
import qunar.hc.tools.Strategies;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class HttpUtil {
    private static final int TIME_OUT_IN_MILLSECONDS = 10000;
    private static final int CONN_TIMEOUT_IN_MILLISECONDS = 3000;
    private static final int RETRY_TIMES = 2;
    private static final int ORDER_TIME_OUT_IN_MILLSECONDS = 30000;
    private static final int LONG_TIME_OUT_IN_MILLSECONDS = 100000; // 100s

    private static final QunarClient httpClient;
    private static final QunarClient orderHttpClient;
    private static final QunarClient longHttpClient;
    private static Logger log = Logger.getLogger(HttpUtil.class);

    static {
        HttpParams params = new BasicHttpParams();

        HttpConnectionParams.setConnectionTimeout(params, CONN_TIMEOUT_IN_MILLISECONDS);
        HttpConnectionParams.setSoTimeout(params, TIME_OUT_IN_MILLSECONDS);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(params, "Chrome/5.0.342.9 Safari/533.2");

        HttpClientParams.setCookiePolicy(params, CookiePolicy.BROWSER_COMPATIBILITY);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(getHttpsSupportScheme());
        ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(schemeRegistry);

        cm.setMaxTotal(1200);
        cm.setDefaultMaxPerRoute(300);

        httpClient = new QunarClient(cm, params);
        Strategies.keepAlive(httpClient, 5000);
        Interceptors.gzip(httpClient, true);
    }

    static {
        HttpParams params = new BasicHttpParams();

        HttpConnectionParams.setConnectionTimeout(params, CONN_TIMEOUT_IN_MILLISECONDS);
        HttpConnectionParams.setSoTimeout(params, ORDER_TIME_OUT_IN_MILLSECONDS);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(params, "Chrome/5.0.342.9 Safari/533.2");

        HttpClientParams.setCookiePolicy(params, CookiePolicy.BROWSER_COMPATIBILITY);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(getHttpsSupportScheme());
        ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(schemeRegistry);

        cm.setMaxTotal(600);
        cm.setDefaultMaxPerRoute(100);

        orderHttpClient = new QunarClient(cm, params);
        Strategies.keepAlive(orderHttpClient, 5000);
        Interceptors.gzip(orderHttpClient, true);
    }

    static {
        HttpParams params = new BasicHttpParams();

        HttpConnectionParams.setConnectionTimeout(params, CONN_TIMEOUT_IN_MILLISECONDS);
        HttpConnectionParams.setSoTimeout(params, LONG_TIME_OUT_IN_MILLSECONDS);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(params, "Chrome/5.0.342.9 Safari/533.2");

        HttpClientParams.setCookiePolicy(params, CookiePolicy.BROWSER_COMPATIBILITY);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        schemeRegistry.register(getHttpsSupportScheme());
        ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(schemeRegistry);

        cm.setMaxTotal(600);
        cm.setDefaultMaxPerRoute(100);

        longHttpClient = new QunarClient(cm, params);
        Strategies.keepAlive(longHttpClient, 5000);
        Interceptors.gzip(longHttpClient, true);
    }

    private static Scheme getHttpsSupportScheme() {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            return new Scheme("https", 443, ssf);
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (KeyManagementException e) {
            return null;
        }
    }

    public static String getContent(final String url) throws Exception {
        return getContent(url, RETRY_TIMES);
    }

    @SuppressWarnings("deprecation")
    public static String getContent(final String url, final int retryTimes) throws Exception {
        FutureTask<String> fu = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String resultString = StringUtils.EMPTY;
                for (int i = 0; i < retryTimes; i++) {
                    HttpGet request = new HttpGet(url);
                    HttpResponse response = null;
                    HttpEntity entity = null;
                    try {
                        response = httpClient.execute(request);
                        entity = response.getEntity();
                        StatusLine status = response.getStatusLine();
                        if (status != null && status.getStatusCode() == 200) {
                            resultString = EntityUtils.toString(entity, "UTF-8");
                            return resultString;
                        } else {
                            QMonitor.recordOne("http_fetch_error");
                        }
                    } catch (Exception e) {
                        if (e instanceof org.apache.http.conn.ConnectionPoolTimeoutException) {
                            QMonitor.recordOne("ConnectionPoolTimeoutException");
                        }
                        QMonitor.recordOne("http_fetch_error");
                        request.releaseConnection();
                        log.error("access " + url + " exception," + e.getMessage());
                        throw e;
                    } finally {
                        EntityUtils.consume(entity);
                        entity = null;
                    }
                }
                return resultString;
            }
        });
        QunarServer.getExecutor().execute(fu);
        String content = fu.get(TIME_OUT_IN_MILLSECONDS, TimeUnit.MILLISECONDS);
        return content;
    }

    @SuppressWarnings("deprecation")
    public static String getContentInLongTime(final String url) throws Exception {
        FutureTask<String> fu = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String resultString = StringUtils.EMPTY;
                for (int i = 0; i < RETRY_TIMES; i++) {
                    HttpGet request = new HttpGet(url);
                    HttpResponse response;
                    HttpEntity entity = null;
                    try {
                        response = longHttpClient.execute(request);
                        entity = response.getEntity();
                        StatusLine status = response.getStatusLine();
                        if (status != null && status.getStatusCode() == 200) {
                            resultString = EntityUtils.toString(entity, "UTF-8");
                            return resultString;
                        } else {
                            QMonitor.recordOne("http_fetch_error");
                        }
                    } catch (Exception e) {
                        if (e instanceof org.apache.http.conn.ConnectionPoolTimeoutException) {
                            QMonitor.recordOne("ConnectionPoolTimeoutException");
                        }
                        QMonitor.recordOne("http_fetch_error");
                        request.releaseConnection();
                        log.error("access " + url + " exception," + e.getMessage());
                        throw e;
                    } finally {
                        EntityUtils.consume(entity);
                        entity = null;
                    }
                }
                return resultString;
            }
        });
        QunarServer.getExecutor().execute(fu);
        String content = fu.get(LONG_TIME_OUT_IN_MILLSECONDS, TimeUnit.MILLISECONDS);
        return content;
    }

    @SuppressWarnings("deprecation")
    public static String getContentByPost(final String url, final List<NameValuePair> formParams) throws Exception {
        FutureTask<String> fu = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String resultString = StringUtils.EMPTY;
                for (int i = 0; i < RETRY_TIMES; i++) {
                    HttpPost request = new HttpPost(url);
                    request.setEntity(new UrlEncodedFormEntity(formParams, "utf-8"));
                    HttpEntity entity = null;
                    try {
                        Interceptors.gzip(httpClient, true);
                        HttpResponse response = httpClient.execute(request);
                        entity = response.getEntity();
                        StatusLine status = response.getStatusLine();
                        if (status != null && status.getStatusCode() == 200) {
                            resultString = EntityUtils.toString(entity, "UTF-8");
                            return resultString;
                        } else {
                            request.abort();
                            QMonitor.recordOne("http_fetch_error");
                        }
                    } catch (IOException ex) {
                        if (ex instanceof org.apache.http.conn.ConnectionPoolTimeoutException) {
                            QMonitor.recordOne("ConnectionPoolTimeoutException");
                            log.error("ConnectionPoolTimeoutException=" + (ex == null ? null : ex.getMessage()));
                            request.releaseConnection();
                        }
                        request.releaseConnection();
                        log.error("access " + url + " exception," + ex.getMessage());
                        throw ex;
                    } catch (Exception e) {
                        QMonitor.recordOne("http_fetch_error");
                        request.releaseConnection();
                        log.error("access " + url + " exception," + e.getMessage());
                        throw e;
                    } finally {
                        EntityUtils.consume(entity);
                        entity = null;
                    }
                }
                return resultString;
            }
        });
        QunarServer.getExecutor().execute(fu);
        String content = null;
        try {
            content = fu.get(TIME_OUT_IN_MILLSECONDS, TimeUnit.MILLISECONDS);
        } catch (java.util.concurrent.ExecutionException e) {
            QMonitor.recordOne("ExecutionException");
            throw e;
        }
        return content;
    }

    @SuppressWarnings("deprecation")
    public static String getContentByLongPost(final String url, final List<NameValuePair> formParams) throws Exception {
        FutureTask<String> fu = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String resultString = StringUtils.EMPTY;
                for (int i = 0; i < RETRY_TIMES; i++) {
                    HttpPost request = new HttpPost(url);
                    request.setEntity(new UrlEncodedFormEntity(formParams, "utf-8"));
                    HttpEntity entity = null;
                    try {
                        Interceptors.gzip(longHttpClient, true);
                        HttpResponse response = longHttpClient.execute(request);
                        entity = response.getEntity();
                        StatusLine status = response.getStatusLine();
                        if (status != null && status.getStatusCode() == 200) {
                            resultString = EntityUtils.toString(entity, "UTF-8");
                            return resultString;
                        } else {
                            request.abort();
                            QMonitor.recordOne("http_fetch_error");
                        }
                    } catch (IOException ex) {
                        if (ex instanceof org.apache.http.conn.ConnectionPoolTimeoutException) {
                            QMonitor.recordOne("ConnectionPoolTimeoutException");
                            log.error("ConnectionPoolTimeoutException=" + (ex == null ? null : ex.getMessage()));
                            request.releaseConnection();
                        }
                        request.releaseConnection();
                        log.error("access " + url + " exception," + ex.getMessage());
                        throw ex;
                    } catch (Exception e) {
                        QMonitor.recordOne("http_fetch_error");
                        request.releaseConnection();
                        log.error("access " + url + " exception," + e.getMessage());
                        throw e;
                    } finally {
                        EntityUtils.consume(entity);
                        entity = null;
                    }
                }
                return resultString;
            }
        });
        QunarServer.getExecutor().execute(fu);
        String content = null;
        try {
            content = fu.get(LONG_TIME_OUT_IN_MILLSECONDS, TimeUnit.MILLISECONDS);
        } catch (java.util.concurrent.ExecutionException e) {
            QMonitor.recordOne("ExecutionException");
            throw e;
        }
        return content;
    }
    
    @SuppressWarnings("deprecation")
    public static String getContentByPost(final String monitorName, final HttpPost post, final String requestXml)
            throws Exception {
        long start = System.currentTimeMillis();
        FutureTask<String> fu = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String resultString = StringUtils.EMPTY;
                for (int i = 0; i < RETRY_TIMES; i++) {
                    HttpEntity entityReq = new StringEntity(requestXml, "UTF-8");
                    post.setEntity(entityReq);
                    HttpEntity responseEntity = null;
                    try {
                        Interceptors.gzip(httpClient, true);
                        HttpResponse response = httpClient.execute(post);
                        responseEntity = response.getEntity();
                        StatusLine status = response.getStatusLine();
                        if (status != null && status.getStatusCode() == 200) {
                            resultString = EntityUtils.toString(responseEntity);
                            return resultString;
                        } else {
                            post.abort();
                            QMonitor.recordOne("http_fetch_error");
                        }
                    } catch (IOException ex) {
                        if (ex instanceof org.apache.http.conn.ConnectionPoolTimeoutException) {
                            QMonitor.recordOne("ConnectionPoolTimeoutException");
                            QMonitor.recordOne(monitorName + "ConnectionPoolTimeoutException");
                            log.error("ConnectionPoolTimeoutException=" + (ex == null ? null : ex.getMessage()));
                            post.releaseConnection();
                        }
                        post.releaseConnection();
                        log.warn(ex.getMessage());
                        throw ex;
                    } catch (Exception e) {
                        QMonitor.recordOne("http_fetch_error");
                        log.warn(e.getMessage());
                        post.releaseConnection();
                        throw e;
                    } finally {
                        EntityUtils.consume(responseEntity);
                        responseEntity = null;
                    }
                }
                return resultString;
            }
        });
        QunarServer.getExecutor().execute(fu);
        String content = null;
        try {
            content = fu.get(TIME_OUT_IN_MILLSECONDS, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            QMonitor.recordOne("TimeoutException");
            log.error("TimeoutException=" + (e == null ? null : e.getMessage()));
            post.releaseConnection();
            throw e;
        }
        QMonitor.recordOne(monitorName, System.currentTimeMillis() - start);
        if (StringUtils.isBlank(content)) {
            QMonitor.recordOne(monitorName + "Failed");
        }
        return content;
    }

    @SuppressWarnings("deprecation")
    public static String getContentByPost4SubmitOrder(final String monitorName, final HttpPost post,
            final String requestXml) throws Exception {
        long start = System.currentTimeMillis();
        FutureTask<String> fu = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String resultString = StringUtils.EMPTY;
                HttpEntity entityReq = new StringEntity(requestXml, "UTF-8");
                post.setEntity(entityReq);
                try {
                    Interceptors.gzip(orderHttpClient, true);
                    HttpResponse response = orderHttpClient.execute(post);
                    entityReq = response.getEntity();
                    StatusLine status = response.getStatusLine();
                    if (status != null && status.getStatusCode() == 200) {
                        resultString = EntityUtils.toString(entityReq);
                        return resultString;
                    } else {
                        post.abort();
                        QMonitor.recordOne("http_fetch_error");
                    }
                } catch (IOException ex) {
                    if (ex instanceof org.apache.http.conn.ConnectionPoolTimeoutException) {
                        QMonitor.recordOne("ConnectionPoolTimeoutException");
                        QMonitor.recordOne(monitorName + "ConnectionPoolTimeoutException");
                        log.error("ConnectionPoolTimeoutException=" + (ex == null ? null : ex.getMessage()));
                        post.releaseConnection();
                    }
                    post.releaseConnection();
                    log.warn(ex.getMessage());
                    throw ex;
                } catch (Exception e) {
                    QMonitor.recordOne("http_fetch_error");
                    log.warn(e.getMessage());
                    post.releaseConnection();
                    throw e;
                } finally {
                    EntityUtils.consume(entityReq);
                    entityReq = null;
                }
                return resultString;
            }
        });
        QunarServer.getExecutor().execute(fu);
        String content = null;
        try {
            content = fu.get(ORDER_TIME_OUT_IN_MILLSECONDS, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            QMonitor.recordOne("TimeoutException");
            log.error("TimeoutException=" + (e == null ? null : e.getMessage()));
            post.releaseConnection();
            throw e;
        }
        QMonitor.recordOne(monitorName, System.currentTimeMillis() - start);
        if (StringUtils.isBlank(content)) {
            QMonitor.recordOne(monitorName + "Failed");
        }
        return content;
    }

    /**
     * @param request
     * @return
     */
    public static String getUserIPString(HttpServletRequest request) {
        String ip = request.getHeader("x-real-ip");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getParameter("__testip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        int pos = ip.indexOf(',');
        if (pos >= 0) {
            ip = ip.substring(0, pos);
        }
        return ip;
    }

    public static Set<String> getUserIPStrings(HttpServletRequest request) {
        String ip = request.getHeader("x-real-ip");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getParameter("__testip");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        int pos = ip.indexOf(',');
        if (pos >= 0) {
            ip = ip.substring(0, pos);
        }
        String[] strings = ip.split(" ");
        return new HashSet<String>(Arrays.asList(strings));
    }
}
