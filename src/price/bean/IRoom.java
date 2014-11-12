package com.qunar.hotel.sa.product.price.bean;

import java.util.List;
import java.util.Map;

public interface IRoom {
    String getId();

    String getName();

    String getBedTypeValue();

    String getBroadbandValue();

    String getPrepayValue();

    String getPrices();

    String getDeposits();

    String getStatus();

    String getCounts();

    String getCashBacks();

    String getBreakfast();

    String getLast();

    String getAdvance();

    String getRefusestate();

    String getMaxRoomNum();
    
    String getLatestArriveTime();

    List<Map<String, String>> getGuaranteeRules();

    /**
     * 售卖渠道，xml中channle 为1，2;默认为0
     * @return
     */
    String getChannel();

    /**
     * 折扣价格
     * @return
     */
    String getDiscountAmount();
    /**
     * 预订类型（预订、申请、积分、钥匙）
     * @return
     */
    String getBookingType();

    /**
     * 获得该房型所支持的货币类型
     * @return
     */
    String getCurrencyType();

    /**
     * 支持的宾客类型
     * @return
     */
    String getGuestType();
}
