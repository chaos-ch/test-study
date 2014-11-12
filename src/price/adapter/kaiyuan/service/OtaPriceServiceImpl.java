package com.qunar.hotel.sa.product.price.adapter.kaiyuan.service;

import java.util.Date;

import com.qunar.base.meerkat.monitor.QMonitor;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.AdapterHotel;
import com.qunar.hotel.sa.product.price.bean.OtaPriceParam;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.PriceInfoString;
import com.qunar.hotel.sa.product.price.validator.*;
import org.springframework.stereotype.Service;

@Service
public class OtaPriceServiceImpl implements OtaPriceService {
//    @Resource
//    private PricePushService pricePushService;

    @Override
    public AdapterHotel getOtaHotelPrice(OtaPriceParam opp) {
        long startTime = System.currentTimeMillis();
        try {
            if (!checkOtaPriceParam(opp)) {
                return null;
            }
            return PriceFactroy.getPriceProcessor(opp).process();
        } finally {
            if (opp.getPriceRequestType() == OtaPriceParam.PriceRequestType.WRAPPER) {
                QMonitor.recordOne("WrapperService_Price", System.currentTimeMillis() - startTime);
            }
        }
    }

    /**
     * 对报价请求进行校验 基本信息校验、供应商信息校验、酒店信息校验
     * 
     * @param opp
     * @return
     */
    private boolean checkOtaPriceParam(OtaPriceParam opp) {
        SimpleValidator<OtaPriceParam> validator = new SimpleValidator<OtaPriceParam>();
        validator.add(new OtaPriceParamBaseValidator());
        validator.add(new OtaPriceParamDayValidator());
        validator.add(new OtaPriceParamHotelInfoValidator());
        validator.add(new OtaPriceParamOtaInfoValidator());

        return validator.validate(opp).isSuccess();
    }

    /**
     * 解析推送的xml以获取报价信息或房态信息
     */
    @Override
    public AdapterHotel parseProductInfo(OtaPriceParam opp, PriceInfoString riceInfoString) {
        return PriceFactroy.getPushPirceInfoProcessor(opp, riceInfoString).process();
    }

    @Override
    public void deleteCacheForPriceTools(int otaId, String otaHotelId, Date fromDate, Date toDate, String channel) {
//        pricePushService.deleteCacheForPriceTools(otaId,otaHotelId,fromDate,toDate,channel);
    }

}
