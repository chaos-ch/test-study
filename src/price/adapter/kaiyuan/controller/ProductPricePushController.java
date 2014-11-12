package com.qunar.hotel.sa.product.price.adapter.kaiyuan.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qunar.hotel.sa.common.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Maps;

/**
 * 
 * 产品报价对外接口，供代理商推送报价信息
 * 
 * Created by he.chen on 14-10-27.
 * 
 */
@Controller
@RequestMapping("/api/price/push/")
public class ProductPricePushController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 推送房价标志
     */
    private static final String PUSH_ROOM_PRICE_ACTION = "pushrateplan";

    /**
     * 推送房态标志
     */
    private static final String PUSH_ROOM_STATUS_ACTION = "pushroomratestatus";

    private Set<String> safeIps = new HashSet<String>(Arrays.asList(OasConfigUtils.getKaiyuanIpWhiteList()));

    /**
     * 推送产品报价信息，支持房态和房价
     * 
     * @param data
     * @param req
     * @param resp
     * @throws java.io.IOException
     */
    @RequestMapping(value = "pushProductPrice", method = RequestMethod.POST)
    public void pushProductPrice(@RequestHeader HttpHeaders header, @RequestBody String data, HttpServletRequest req,
            HttpServletResponse resp) throws IOException {
        String action = header.getFirst("SOAPAction");
        if (!checkHeader(action)) {
            OasQMonitorUtils.recordOne(QmonitorNameEnum.API_PRICE_PUSH_PRODUCT_PRICE_REQUEST_INVALID);
            logger.info("{} pushProductPrice invalid:{},header={}",
                    QmonitorNameEnum.API_PRICE_PUSH_PRODUCT_PRICE_REQUEST_INVALID.name(), Result.HEADER_ERROR.desc,
                    header);
            writeResponse(resp, buildRoomPriceResponse(buildResponseParams(Result.HEADER_ERROR)));
            return;
        }

        action = action.toLowerCase();
        if (action.contains(PUSH_ROOM_PRICE_ACTION)) {
            pushRoomPrice(data, req, resp);
        } else if (action.contains(PUSH_ROOM_STATUS_ACTION)) {
            pushRoomStatus(data, req, resp);
        }
    }

    /**
     * 推送房型报价信息
     * 
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "pushRoomPrice", method = RequestMethod.POST)
    public void pushRoomPrice(@RequestBody String data, HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        long startTime = System.currentTimeMillis();

        Result result = checkRequest(req, data);
        if (result != Result.SUCCESS) {
            OasQMonitorUtils.recordOne(QmonitorNameEnum.API_PRICE_PUSH_ROOM_PRICE_REQUEST_INVALID);
            logger.info("{} pushRoomPrice invalid:{}",
                    QmonitorNameEnum.API_PRICE_PUSH_ROOM_PRICE_REQUEST_INVALID.name(), result.desc);
            writeResponse(resp, buildRoomPriceResponse(buildResponseParams(result)));
            return;
        }

        AbstractProductPricePushProcessor processor = PricePushProcessorFactory.getProductPricePushProcessor(AppContext
                .current().getOtaInfo().getOtaDataSource());
        if (processor == null) {
            OasQMonitorUtils.recordOne(QmonitorNameEnum.API_PRICE_PUSH_ROOM_PRICE_REQUEST_INVALID);
            logger.info("{} pushRoomPrice invalid:{}",
                    QmonitorNameEnum.API_PRICE_PUSH_ROOM_PRICE_REQUEST_INVALID.name(), Result.OTA_NOT_SUPPORTED.desc);
            writeResponse(resp, buildRoomPriceResponse(buildResponseParams(Result.OTA_NOT_SUPPORTED)));
            return;
        }
        try {
            processor.process(data);
            OasQMonitorUtils.recordOne(QmonitorNameEnum.API_PRICE_PUSH_ROOM_PRICE, System.currentTimeMillis()
                    - startTime);
            writeResponse(resp, buildRoomPriceResponse(buildResponseParams(Result.SUCCESS)));
        } catch (Exception e) {
            OasQMonitorUtils.recordOne(QmonitorNameEnum.API_PRICE_PUSH_ROOM_PRICE_ERROR);
            logger.error(QmonitorNameEnum.API_PRICE_PUSH_ROOM_PRICE_ERROR.name() + " pushRoomPrice error", e);
            writeResponse(resp, buildRoomPriceResponse(buildResponseParams(Result.OTHER_ERROR)));
        }

    }

    /**
     * 推送房态信息
     * 
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value = "pushRoomStatus", method = RequestMethod.POST)
    public void pushRoomStatus(@RequestBody String data, HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        long startTime = System.currentTimeMillis();

        Result result = checkRequest(req, data);
        if (result != Result.SUCCESS) {
            OasQMonitorUtils.recordOne(QmonitorNameEnum.API_PRICE_PUSH_ROOM_STATUS_REQUEST_INVALID);
            logger.info("{} pushRoomStatus invalid:{}",
                    QmonitorNameEnum.API_PRICE_PUSH_ROOM_STATUS_REQUEST_INVALID.name(), result.desc);
            writeResponse(resp, buildRoomStatusResponse(buildResponseParams(result)));
            return;
        }

        AbstractRoomStatusPushProcessor processor = PricePushProcessorFactory.getRoomStatusPushProcessor(AppContext
                .current().getOtaInfo().getOtaDataSource());
        if (processor == null) {
            OasQMonitorUtils.recordOne(QmonitorNameEnum.API_PRICE_PUSH_ROOM_STATUS_REQUEST_INVALID);
            logger.info("{} pushRoomPrice invalid:{}",
                    QmonitorNameEnum.API_PRICE_PUSH_ROOM_STATUS_REQUEST_INVALID.name(), Result.OTA_NOT_SUPPORTED.desc);
            writeResponse(resp, buildRoomStatusResponse(buildResponseParams(Result.OTA_NOT_SUPPORTED)));
            return;
        }
        try {
            processor.process(data);
            OasQMonitorUtils.recordOne(QmonitorNameEnum.API_PRICE_PUSH_ROOM_STATUS, System.currentTimeMillis()
                    - startTime);
            writeResponse(resp, buildRoomStatusResponse(buildResponseParams(Result.SUCCESS)));
        } catch (Exception e) {
            OasQMonitorUtils.recordOne(QmonitorNameEnum.API_PRICE_PUSH_ROOM_STATUS_ERROR);
            logger.error(QmonitorNameEnum.API_PRICE_PUSH_ROOM_STATUS_ERROR.name() + " pushRoomStatus error", e);
            writeResponse(resp, buildRoomStatusResponse(buildResponseParams(Result.OTHER_ERROR)));
        }

    }

    /**
     * 检查请求是否有效
     * 
     * @param req
     * @param data
     * @return
     */
    private Result checkRequest(HttpServletRequest req, String data) {
        if (!checkOta()) {
            return Result.OTA_NOT_SUPPORTED;
        }

        String userIp = HttpUtil.getUserIPString(req);
        if (!checkIp(userIp)) {
            logger.info("/api/price/push: not allowed ip :" + userIp);
            return Result.IP_INVALID;
        }

        if (StringUtils.isEmpty(data)) {
            return Result.BODY_ERROR;
        }
        return Result.SUCCESS;
    }

    /**
     * 检查代理商是否支持报价推送
     * 
     * @return
     */
    private boolean checkOta() {
        return true;
        // return AppContext.current().getOtaInfo() != null;
    }

    /**
     * 验证ip是否在白名单中
     * 
     * @param ip
     * @return
     */
    private boolean checkIp(String ip) {
        boolean isPass = false;

        if (CollectionUtils.isNotEmpty(safeIps) && ip != null) {
            if (safeIps.contains(ip)) {
                isPass = true;
            }
        }

        return isPass;
    }

    /**
     * 检查请求类型是否合法
     * 
     * @param action
     * @return
     */
    private boolean checkHeader(String action) {
        if (StringUtils.isEmpty(action)) {
            return false;
        }
        action = action.toLowerCase();
        if (!action.contains(PUSH_ROOM_PRICE_ACTION) && !action.contains(PUSH_ROOM_STATUS_ACTION)) {
            return false;
        }
        return true;
    }

    /**
     * 构造响应消息参数
     * 
     * @param result
     * @return
     */
    private Map<String, Object> buildResponseParams(Result result) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("ret", result == Result.SUCCESS);
        params.put("errCode", result.code);
        params.put("errMsg", result.desc);
        return params;
    }

    /**
     * 构造房价响应消息
     * 
     * @param params
     * @return
     */
    private String buildRoomPriceResponse(Map<String, Object> params) {
        IPricePushResponseBuilder responseBuilder = PricePushResponseBuilderFactory.getRoomPriceBuilder(AppContext
                .current().getOtaInfo().getOtaDataSource());
        if (responseBuilder != null) {
            return responseBuilder.build(params);
        }
        try {
            return JsonUtil.getObjectMapperInstance().writeValueAsString(params);
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 构造房态响应消息
     * 
     * @param params
     * @return
     */
    private String buildRoomStatusResponse(Map<String, Object> params) {
        IPricePushResponseBuilder responseBuilder = PricePushResponseBuilderFactory.getRoomStatusBuilder(AppContext
                .current().getOtaInfo().getOtaDataSource());
        if (responseBuilder != null) {
            return responseBuilder.build(params);
        }
        try {
            return JsonUtil.getObjectMapperInstance().writeValueAsString(params);
        } catch (Exception e) {
            return StringUtils.EMPTY;
        }
    }

    /**
     * 给对方返回响应消息
     * 
     * @param resp
     * @param responseMsg
     * @throws java.io.IOException
     */
    private void writeResponse(HttpServletResponse resp, String responseMsg) throws IOException {
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/xml");
        resp.getWriter().write(responseMsg);
    }

    /**
     * 请求处理结果
     * 
     * @author weirongzhou
     * 
     */
    private enum Result {
        SUCCESS(0, "SUCCESS"), OTA_NOT_SUPPORTED(1, "此代理商不支持报价推送功能"), IP_INVALID(2, "ip地址不在白名单里"), HEADER_ERROR(3,
                "消息头错误"), BODY_ERROR(4, "消息体错误"), OTHER_ERROR(99, "其他错误");

        private int code;
        private String desc;

        Result(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

    }

}
