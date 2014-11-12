package com.qunar.hotel.sa.product.price.adapter.kaiyuan.controller;

import javax.annotation.Resource;

import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.AdapterHotel;
import com.qunar.hotel.sa.product.price.bean.OtaPriceParam;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.PriceInfoString;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.SupplierInfo;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.util.ProductPriceUtils;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.service.OtaPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by he.chen on 14-10-27.
 * @version $Id$ 报价推送信息处理器 抽象基类
 */

public abstract class AbstractProductPricePushProcessor {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    protected OtaPriceService otaPriceService;

    /**
     * 处理器入口
     * 
     * @param data 推送来的报价原始数据
     */
    public void process(String data) {
        // 记录日志
        writeAccessLog(data);

        // 解析报价
        HotelInfo hotelInfo = parseData(data);

        // 处理报价细节，并更新报价
        updateProductPrice(hotelInfo);
    }

    /**
     * 记录操作日志
     *
     * @param data
     */
    protected void writeAccessLog(String data) {
        logger.info("access /api/price/pushRoomPrice:data={}", data);
    }

    /**
     * 更新本地报价（具体逻辑由相应子类实现）
     *
     * @param hotelInfo
     */
    protected abstract void updateProductPrice(HotelInfo hotelInfo);

    /**
     * 解析报价
     * 
     * @param data
     * @return
     */
    private HotelInfo parseData(String data) {
//        OTAClient otaClient = AppContext.current().getOtaInfo();
//
        SupplierInfo supplierInfo = new SupplierInfo();
//        supplierInfo.setOtaType(otaClient.getOtaDataSource().name());
//        supplierInfo.setOtaName(otaClient.getName());
//        supplierInfo.setWrapperID(otaClient.getWrapperId());
//        supplierInfo.setDomain(otaClient.getDomain());

        OtaPriceParam otaPriceParam = new OtaPriceParam();
        otaPriceParam.setSupplierInfo(supplierInfo);

        PriceInfoString riceInfoString = new PriceInfoString(PriceInfoString.ParseType.RP, data);

        AdapterHotel adapterHotel = otaPriceService.parseProductInfo(otaPriceParam, riceInfoString);

        HotelInfo hotelInfo = ProductPriceUtils.transformHotelInfo(adapterHotel);
        return hotelInfo;
    }
}
