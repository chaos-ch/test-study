package com.qunar.hotel.sa.product.price.adapter.kaiyuan.controller;

import com.qunar.hotel.otatts.base.context.AppContext;
import com.qunar.hotel.otatts.common.common_api.ota.bean.OTADATASOURCE;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 * @author weirongzhou
 * 
 */
public class PricePushResponseBuilderFactory {

    private static final String ROOM_PRICE_RESPONSE_BUILDER = "RoomPricePushResponseBuilder";

    private static final String ROOM_STATUS_RESPONSE_BUILDER = "RoomStatusPushResponseBuilder";

    @Resource
    private static ApplicationContext applicationContext;
    /**
     * 获取报价推送响应消息构造器
     * 
     * @param otaType
     * @return
     */
    public static IPricePushResponseBuilder getRoomPriceBuilder(OTADATASOURCE otaType) {
        StringBuilder stringBuilder = new StringBuilder(otaType.name());
        stringBuilder.append(ROOM_PRICE_RESPONSE_BUILDER);
        return (IPricePushResponseBuilder)  applicationContext.getBean(stringBuilder.toString());
    }

    /**
     * 获取房态推送响应消息构造器
     * 
     * @param otaType
     * @return
     */
    public static IPricePushResponseBuilder getRoomStatusBuilder(OTADATASOURCE otaType) {
        StringBuilder stringBuilder = new StringBuilder(otaType.name());
        stringBuilder.append(ROOM_STATUS_RESPONSE_BUILDER);
        return (IPricePushResponseBuilder) applicationContext.getBean(stringBuilder.toString());
    }

}
