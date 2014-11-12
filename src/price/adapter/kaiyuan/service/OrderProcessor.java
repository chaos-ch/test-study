package com.qunar.hotel.sa.product.price.adapter.kaiyuan.service;

import javax.annotation.Resource;

import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.AdapterHotel;
import com.qunar.hotel.sa.product.price.service.PricePushService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.qunar.base.meerkat.monitor.QMonitor;

@Scope("prototype")
@Component("orderProcessor")
public class OrderProcessor extends AbstractPriceProcessor {

    @Resource
    private PricePushService pricePushService;

    public OrderProcessor() {
        super();
    }

    @Override
    public AdapterHotel process() {

        HotelInfo hotelInfo = getOtaInterface().getHotelPriceByOrder(this.otaPriceParam);

        processPriceCache(hotelInfo);

        logger.info("请求参数：{},获取酒店报价：{}", this.otaPriceParam, hotelInfo);

        return tranPriceToApi(hotelInfo,true);

    }

    /**
     * Booking Check 实时接口与变价推送缓存报价校验 主要校验对应日期段的 1. 房态 2. 房型报价 校验不通过 -- 自动更新酒店的推送记录 -- 1次booking触发推送遗漏的酒店 监控： 总的校验不通过的数量
     * 日至： 统计每一个代理商booking 校验不符合的情况. The End
     */
    private void processPriceCache(HotelInfo hotel) {
        if (this.otaPriceParam.getSupplierInfo().getPricePushCode() == PricePushService.PricePushState.Y.getCode()) {
            if (!pricePushService.bookingCheck(this.otaPriceParam, hotel)) {
                QMonitor.recordOne(this.otaPriceParam.getSupplierInfo().getId() + "_booking_check_fail");
                QMonitor.recordOne("booking_check_fail");
            }
        }
    }

}
