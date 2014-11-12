package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.util;

import java.util.HashMap;
import java.util.Map;

import com.qunar.hotel.sa.common.util.*;
import com.qunar.hotel.sa.product.price.bean.OtaPriceParam;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by he.chen on 14-10-27.
 * @version $Id$
 */
public class KaiyuanHttpUtil {
    protected static Logger logger = LoggerFactory.getLogger("com.qunar.hotel.oas.spi.kaiyuan");
    /**
     * 调用开元除登录以外的其他接口时，需要传递此参数，作为凭证
     */
    public static volatile String sessionId = "";
    private static final String XMLNS = "\"http://www.shands.com.cn/kaiyuan/1.1/\"";
    private static final String SOAP_BEGIN = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
            + "            <soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"> ";
    private static final String SOAP_END = "</soap:Envelope>";

    private static final String SOAP_HEAD_BEGIN = "<soap:Header>\n"
            + "   <ShandsSoapHeader xmlns=\"http://www.shands.com.cn/kaiyuan/1.1/\">";
    private static final String SOAP_HEAD_END = "</ShandsSoapHeader>\n" + "  </soap:Header>";
    private static final String SOAP_BODY_BEGIN = "<soap:Body>";
    private static final String SOAP_BODY_END = "</soap:Body>";
    public static final String SESSION_INVALID_CODE = "6004";
    private static final String CORP_CODE = "corpCode";

    /**
     * 登录的函数名
     */
    public static final String LOGIN_ACTION = "ChannelLogin";
    /**
     * 酒店可用房数据查询函数名
     */
    public static final String HOTEL_AVAILABILITY_ACTION = "HotelRateDailyDetail";

    /**
     * 生成请求体xml
     * 
     * @param functionname 函数名
     * @param params soapBody中的参数
     * @param requireSessionId 是否需要有效的sessionId,除登录函数外，其余需要
     * @return
     */
    public static String makeRequestXml(String functionname, Map<String, String> params, boolean requireSessionId) {
        if (requireSessionId && StringUtils.isBlank(sessionId)) {
            sessionId = fetchSessionId();
        }
        StringBuilder sb = new StringBuilder(SOAP_BEGIN);
        sb.append(makeSoadHead(sessionId));
        sb.append(makeSoapBody(functionname, params));
        sb.append(SOAP_END);
        return sb.toString();
    }

    /**
     * 生成soap Head中的内容
     * 
     * @param sessionId 调用接口的凭证
     * @return
     */
    private static StringBuilder makeSoadHead(String sessionId) {
        StringBuilder sb = new StringBuilder(SOAP_HEAD_BEGIN);
        sb.append("<SessionId>").append(sessionId).append("</SessionId>");
        sb.append("<ResultCode>").append("string").append("</ResultCode>");// 此参数虽然传递，但是无用
        sb.append("<ErrorReason>").append("string").append("</ErrorReason>"); // 此参数虽然传递，但是无用
        sb.append("<Channel>").append(OasConfigUtils.getKaiyuanChannel()).append("</Channel>");
        sb.append(SOAP_HEAD_END);
        return sb;
    }

    /**
     * 生成soap body中的内容
     * 
     * @param functionName
     * @param params 请求参数
     * @return
     */
    private static StringBuilder makeSoapBody(String functionName, Map<String, String> params) {
        StringBuilder sb = new StringBuilder(SOAP_BODY_BEGIN);
        sb.append("<").append(functionName).append(" xmlns=").append(XMLNS).append(">");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append("<").append(entry.getKey()).append(">").append(entry.getValue()).append("</")
                    .append(entry.getKey()).append(">");
        }
        sb.append("</").append(functionName).append(">");
        sb.append(SOAP_BODY_END);
        return sb;
    }

    /**
     * 调用登录接口以获取登录凭证sessionId
     * 
     * @return
     */
    private static String fetchSessionId() {
        long beginTime = System.currentTimeMillis();
        Map<String, String> initialParams = new HashMap<String, String>();
        initialParams.put("username", OasConfigUtils.getKaiyuanUsername());
        initialParams.put("password", OasConfigUtils.getKaiyuanPassword());

        String requestXml = "";
        String loginResultXml = "";
        try {
            requestXml = makeRequestXml(LOGIN_ACTION, initialParams, false);
            loginResultXml = innergetResponseXml(OasConfigUtils.getKaiyuanLoginUrl(), "KAIYUAN_LOGIN", requestXml);
            String sessionId = KaiyuanParseUtil.parseSessionId(loginResultXml);
            OasQMonitorUtils.recordOne(QmonitorNameEnum.OAS_REMOTE_LOGIN, System.currentTimeMillis() - beginTime);
            return sessionId;
        } catch (Exception e) {
            logger.error(QmonitorNameEnum.OAS_REMOTE_LOGIN_ERR.name() + "：login kaiyuan exception ，requestXml{}", requestXml,
                    e);
            OasQMonitorUtils.recordOne(QmonitorNameEnum.OAS_REMOTE_LOGIN_ERR);
            return null;
        }
    }


    public static String innergetResponseXml(String url, String monitorName, String requestXml) throws Exception {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "text/xml");
        return HttpUtil.getContentByPost(monitorName, post, requestXml);
    }


    /**
     * 调用开元接口获取响应的xml字符串
     * 
     * @param kaiyuanRequestBean
     * @return
     * @throws Exception
     */

    /**
     * 根据下单时部分参数调用接口HotelRateDailyDetail获取实时报价
     * 
     * @return
     * @throws Exception
     */
    public static String getOrderPriceResponseXml(String requestXml) throws Exception {
        return innergetResponseXml(OasConfigUtils.getKaiyuanHotelAvailability(), "KAIYUAN_ORDER_PRICE", requestXml);

    }


    public static String getOrderPriceRequestXml(OtaPriceParam opp) {
        String[] roomIdArr = StringUtils.split(opp.getOtaRoomId(), "|");
        if (roomIdArr.length != 2) {
            return null;
        }
        Map<String, String> initialParams = new HashMap<String, String>();
        initialParams.put("hotelCode", opp.getHotelInfoParam().getOtaHotelId());
        initialParams.put("ratePlanCode", roomIdArr[1]);
        initialParams.put("roomTypeCode", roomIdArr[0]);
        initialParams.put("arrivalDate", DateFormatUtils.format4y2M2d(opp.getCheckIn()));
        initialParams.put("departureDate", DateFormatUtils.format4y2M2d(opp.getCheckOut()));
        initialParams.put("numofRooms", opp.getBookRoomNum() + "");
        initialParams.put("numofAdults", "2");// 写死为2
        initialParams.put("numOfChildren", "0");// 写死为0
        initialParams.put("corpCode", CORP_CODE);
        return makeRequestXml(KaiyuanHttpUtil.HOTEL_AVAILABILITY_ACTION, initialParams, true);
    }
}
