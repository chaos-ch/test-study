package com.qunar.hotel.sa.product.price.service;

import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.price.bean.OtaPriceParam;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.PriceInfoString;

public interface IOtaPriceInterface {
    /**
     * 
     * 获取指定酒店所有房型即时价格。
     * 
     * @return List<HotelPriceInfo<RoomInfo,List<PriceInfo>>>
     * @throws Exception
     */
    // TODO
    HotelInfo getHotelPriceByWrapper(OtaPriceParam opp);
    /**
     * 
     * 获取指定酒店指定房型即时价格。
     * 
     * @return List<HotelPriceInfo<RoomInfo,List<PriceInfo>>>
     * @throws Exception
     */
    HotelInfo getHotelPriceByOrder(OtaPriceParam opp);
    /**
     * 解析推送的报价信息
     * 
     * @return
     */
    HotelInfo parsePushPriceInfo(OtaPriceParam opp, PriceInfoString riceInfoString);
}
