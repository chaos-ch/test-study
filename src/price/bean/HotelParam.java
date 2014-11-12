package com.qunar.hotel.sa.product.price.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.google.common.collect.Maps;
import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.AdapterHotel;

/**
 * Created by he.chen on 14-10-27.
 * @version $Id$
 */
public class HotelParam implements Serializable {
    private static final long serialVersionUID = -2175759251374665005L;

    private HotelInfo hotelInfo;

    private AdapterHotel adapterHotel;

    private Date fromDate;

    private Map<String,Integer> hotelIdList = Maps.newHashMap();

    private OtaPriceParam.PriceRequestType priceRequestType;

    private ProductQuery productQuery;
    //最多可预订间数
    private int maxRoomNum = 8;

    public HotelParam() {
    }

    public HotelParam(HotelInfo hotelInfo, Date fromDate, OtaPriceParam.PriceRequestType priceRequestType) {
        this.hotelInfo = hotelInfo;
        this.fromDate = fromDate;
        this.priceRequestType = priceRequestType;
    }

    public HotelParam(HotelInfo hotelInfo, AdapterHotel adapterHotel) {
        this.hotelInfo = hotelInfo;
        this.adapterHotel = adapterHotel;
    }

    public HotelParam(HotelInfo hotelInfo, AdapterHotel adapterHotel, Date fromDate, OtaPriceParam.PriceRequestType priceRequestType) {
        this.hotelInfo = hotelInfo;
        this.adapterHotel = adapterHotel;
        this.fromDate = fromDate;
        this.priceRequestType = priceRequestType;
    }

    public HotelParam(HotelInfo hotelInfo, ProductQuery productQuery,
                      OtaPriceParam.PriceRequestType priceRequestType) {
        this.hotelInfo = hotelInfo;
        this.productQuery = productQuery;
        this.priceRequestType = priceRequestType;
    }

    public HotelInfo getHotelInfo() {
        return hotelInfo;
    }

    public void setHotelInfo(HotelInfo hotelInfo) {
        this.hotelInfo = hotelInfo;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public OtaPriceParam.PriceRequestType getPriceRequestType() {
        return priceRequestType;
    }

    public void setPriceRequestType(OtaPriceParam.PriceRequestType priceRequestType) {
        this.priceRequestType = priceRequestType;
    }

    public AdapterHotel getAdapterHotel() {
        return adapterHotel;
    }

    public void setAdapterHotel(AdapterHotel adapterHotel) {
        this.adapterHotel = adapterHotel;
    }

    public ProductQuery getProductQuery() {
        return productQuery;
    }

    public void setProductQuery(ProductQuery productQuery) {
        this.productQuery = productQuery;
    }

    public Map<String, Integer> getHotelIdList() {
        return hotelIdList;
    }

    public void setHotelIdList(Map<String, Integer> hotelIdList) {
        this.hotelIdList = hotelIdList;
    }

    public int getMaxRoomNum() {
        return maxRoomNum;
    }

    public void setMaxRoomNum(int maxRoomNum) {
        this.maxRoomNum = maxRoomNum;
    }
}
