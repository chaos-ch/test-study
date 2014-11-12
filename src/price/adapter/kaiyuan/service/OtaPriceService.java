package com.qunar.hotel.sa.product.price.adapter.kaiyuan.service;

import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.AdapterHotel;
import com.qunar.hotel.sa.product.price.bean.OtaPriceParam;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.PriceInfoString;

import java.util.Date;


public interface OtaPriceService {
    /**
     * 获取酒店下所有房型指定时间段的报价信息
     * 
     * @param opp 请求对象
     * @return
     */
    AdapterHotel getOtaHotelPrice(OtaPriceParam opp);
    
    /**
     * 直接解析字符串获取报价（目前只有开元使用，如果以后其他代理商使用，请修改相应注释）
     * 
     * @param opp
     * @param riceInfoString
     * @return
     */
    AdapterHotel parseProductInfo(OtaPriceParam opp, PriceInfoString riceInfoString);


    /**
     * 报价不展示工具排查时，删除缓存
     */
    void deleteCacheForPriceTools(int otaId, String otaHotelId, Date fromDate, Date toDate, String channel);
    
}
