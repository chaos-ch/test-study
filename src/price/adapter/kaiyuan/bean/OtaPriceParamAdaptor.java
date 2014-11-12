package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import com.qunar.hotel.sa.product.price.bean.*;

/**
 * Created by he.chen on 14-10-27.
 * @version $Id$
 */
public class OtaPriceParamAdaptor implements Adaptor<HotelParam, OtaPriceParam> {

    @Override
    public OtaPriceParam adaptorFrom(HotelParam bean) throws Exception {

        OtaPriceParam param = new OtaPriceParam();
        ProductQuery productQuery = bean.getProductQuery();
        if (bean.getPriceRequestType() == OtaPriceParam.PriceRequestType.ORDER) {
            param.setCheckIn(productQuery.getCheckInDate());
            param.setCheckOut(productQuery.getCheckOutDate());
            param.setOtaRoomId(productQuery.getRoomId());
            param.setHotelInfoParam(new HotelInfoParamAdaptor().adaptorFrom(bean.getHotelInfo()));
            param.setSessionId(productQuery.getSessionId());
            param.setPriceRequestType(OtaPriceParam.PriceRequestType.ORDER);
            param.setCustomerInfos(productQuery.getCustomerInfos());
//            param.setSupplierInfo(new SupplierInfoAdaptor().adaptorFrom(AppContext.current().getOtaInfo()));
            if (productQuery.getChannel() != null) {
                param.setChannel(productQuery.getChannel().name());
            }
            param.setBookRoomNum(productQuery.getRoomNum() == 0 ? 1 : productQuery.getRoomNum());
            param.setExtendParam(bean.getProductQuery().getExtendParam());

        } else if (bean.getPriceRequestType() == OtaPriceParam.PriceRequestType.WRAPPER) {
            param.setCheckIn(productQuery.getCheckInDate());
            param.setCheckOut(productQuery.getCheckOutDate());
            param.setQueryId(productQuery.getQueryId());
            param.setHotelInfoParam(new HotelInfoParamAdaptor().adaptorFrom(bean.getHotelInfo()));
            param.setPriceRequestType(OtaPriceParam.PriceRequestType.WRAPPER);
//            param.setSupplierInfo(new SupplierInfoAdaptor().adaptorFrom(AppContext.current().getOtaInfo()));
            if (productQuery.getChannel() != null) {
                param.setChannel(productQuery.getChannel().name());
            }
            param.setHotelIdList(bean.getHotelIdList());
        }
        return param;
    }



}
