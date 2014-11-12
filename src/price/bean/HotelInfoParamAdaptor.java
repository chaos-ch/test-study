package com.qunar.hotel.sa.product.price.bean;


import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.HotelInfoParam;
import com.qunar.hotel.sa.product.price.bean.Adaptor;

/**
 * Created by he.chen on 14-10-27.
 */
public class HotelInfoParamAdaptor implements Adaptor<HotelInfo, HotelInfoParam> {
    @Override
    public HotelInfoParam adaptorFrom(HotelInfo bean) throws Exception {
        HotelInfoParam hotelInfoParam = new HotelInfoParam();
        hotelInfoParam.setQunarHotelId(String.valueOf(bean.getId()));
        hotelInfoParam.setSeq(bean.getSeq());
        hotelInfoParam.setOtaHotelId(bean.getOtaHotelId());
        hotelInfoParam.setCityName(bean.getCityId());
        hotelInfoParam.setAdjustCacheTime(bean.getAdjustCacheTime());
        hotelInfoParam.setHotelName(bean.getHotelName());
        hotelInfoParam.setExt(bean.getExt());
        return hotelInfoParam;
    }
}

