package com.qunar.hotel.sa.product.price.adapter.kaiyuan.service;

import com.qunar.hotel.oas.bean.price.OtaPriceParam;
import com.qunar.hotel.oas.bean.price.PriceInfoString;
import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.price.service.IOtaPriceInterface;

/**
 * @Date:Jun 10, 2014
 * @Time:11:39:31 AM
 * @Author:fengtao.yang
 */
public abstract class AbstractOtaPriceService implements IOtaPriceInterface {

    public HotelInfo parsePushPriceInfo(OtaPriceParam opp, PriceInfoString riceInfoString) {
        throw new RuntimeException("请在具体实现类中实现此方法");
    }

}
