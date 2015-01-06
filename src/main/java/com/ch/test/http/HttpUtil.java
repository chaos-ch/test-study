package com.ch.test.http;

/**
 * Created with IntelliJ IDEA.
 * @author yong.chen
 * Date: 14-8-8
 * Time: 下午6:18
 * http工具处理类
 */

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import qunar.QunarServer;
import qunar.hc.QunarClient;
import qunar.hc.tools.Interceptors;
import qunar.hc.tools.Strategies;

import com.qunar.base.meerkat.monitor.QMonitor;

@SuppressWarnings("deprecation")
public class HttpUtil {
    private static final int TIME_OUT_IN_MILLSECONDS = 10000;
    private static final int CONN_TIMEOUT_IN_MILLISECONDS = 3000;
    private static final int RETRY_TIMES = 6;
    private static final QunarClient httpClient;
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    static {
        HttpParams params = new BasicHttpParams();

        HttpConnectionParams.setConnectionTimeout(params, CONN_TIMEOUT_IN_MILLISECONDS);
        HttpConnectionParams.setSoTimeout(params, TIME_OUT_IN_MILLSECONDS);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(params, "Chrome/5.0.342.9 Safari/533.2");

        HttpClientParams.setCookiePolicy(params, CookiePolicy.BROWSER_COMPATIBILITY);

        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        //schemeRegistry.register(getHttpsSupportScheme());
        ThreadSafeClientConnManager cm = new ThreadSafeClientConnManager(schemeRegistry);

        cm.setMaxTotal(600);
        cm.setDefaultMaxPerRoute(100);

        httpClient = new QunarClient(cm, params);
        Strategies.keepAlive(httpClient, 5000);
        Interceptors.gzip(httpClient, true);
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
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            return new Scheme("https", 443, ssf);
        } catch (NoSuchAlgorithmException e) {
            return null;
        } catch (KeyManagementException e) {
            return null;
        }
    }

    @SuppressWarnings("deprecation")
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
    public static String getContent(final String url, final int retryTimes, final String returnType) throws Exception {
        FutureTask<String> fu = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String resultString = StringUtils.EMPTY;
                for (int i = 0; i < retryTimes; i++) {
                    HttpGet request = new HttpGet(url);
                    request.addHeader("Accept", returnType);
                    HttpResponse response = httpClient.execute(request);
                    HttpEntity entity = null;
                    try {
                        entity = response.getEntity();
                        StatusLine status = response.getStatusLine();
                        if (status != null && status.getStatusCode() == 200) {
                            resultString = EntityUtils.toString(entity, "UTF-8");
                            return resultString;
                        } else {
                            QMonitor.recordOne("http_fetch_error");
                        }
                    } catch (Exception e) {
                        QMonitor.recordOne("http_fetch_error");
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

    public static String getContentByGZIP(final String url) throws Exception {
        FutureTask<String> fu = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String resultString = StringUtils.EMPTY;
                for (int i = 0; i < RETRY_TIMES; i++) {
                    HttpGet request = new HttpGet(url);
                    request.addHeader("Accept-Encoding", "gzip");
                    HttpResponse response = null;
                    HttpEntity entity = null;
                    try {
                        response = httpClient.execute(request);
                        entity = response.getEntity();
                        if (entity != null) {
                            Header ceheader = entity.getContentEncoding();
                            if (ceheader != null) {
                                HeaderElement[] codecs = ceheader.getElements();
                                for (int j = 0; j < codecs.length; j++) {
                                    if (codecs[j].getName().equalsIgnoreCase("gzip")) {
                                        response.setEntity(new GzipDecompressingEntity(response.getEntity()));
                                    }
                                }
                            }
                        }
                        StatusLine status = response.getStatusLine();
                        if (status != null && status.getStatusCode() == 200) {
                            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
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

    public static String getHttpsContentByPost(final String url, Map<String, String> params)
            throws Exception {
        List<NameValuePair> httpParam = mapToNameValuePair(params);
        return getContentByPost(url, httpParam);
    }

    private static List<NameValuePair> mapToNameValuePair(Map<String, String> params) {
        List<NameValuePair> httpParam = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            httpParam.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return httpParam;
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



}
