package com.qunar.hotel.sa.product.price.adapter.kaiyuan.controller;

import com.qunar.hotel.otatts.common.common_api.ota.bean.OTADATASOURCE;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;

/**
 * Created by he.chen on 14-10-27.
 * @version $Id$ 推送报价模式 处理器 工厂类
 */

public class PricePushProcessorFactory {

    private static final String PRODUCT_PRICE_PUSH_PROCESSOR = "ProductPricePushProcessor";
    private static final String ROOM_STATUS_PUSH_PROCESSOR = "RoomStatusPushProcessor";

    @Resource
    private static ApplicationContext applicationContext;

    /**
     * 获取对应的 报价推送 处理器
     * 
     * @param otaType
     * @return
     */
    public static AbstractProductPricePushProcessor getProductPricePushProcessor(OTADATASOURCE otaType) {
        StringBuilder stringBuilder = new StringBuilder(otaType.name());
        stringBuilder.append(PRODUCT_PRICE_PUSH_PROCESSOR);
        return (AbstractProductPricePushProcessor)applicationContext.getBean(stringBuilder.toString());
    }

    /**
     * 获取对应的 房态推送 处理器
     * 
     * @param otaType
     * @return
     */
    public static AbstractRoomStatusPushProcessor getRoomStatusPushProcessor(OTADATASOURCE otaType) {
        StringBuilder stringBuilder = new StringBuilder(otaType.name());
        stringBuilder.append(ROOM_STATUS_PUSH_PROCESSOR);
        return (AbstractRoomStatusPushProcessor) applicationContext.getBean(stringBuilder.toString());
    }
}
