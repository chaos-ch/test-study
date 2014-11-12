package com.qunar.hotel.sa.product.price.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.HotelInfoParam;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.SupplierInfo;
import com.qunar.hotel.sa.product.price.bean.CustomerInfo;
import org.apache.commons.lang.builder.ToStringBuilder;

public class OtaPriceParam implements Serializable {

    private static final long serialVersionUID = -6493518462681348518L;

    /**
     * 请求类别
     */
    private PriceRequestType priceRequestType;
    /**
     * 代理商酒店唯一标识
     */
    private HotelInfoParam hotelInfoParam;
    /**
     * 代理商房型唯一标识
     */
    private String otaRoomId;
    /**
     * 入住日
     */
    private Date checkIn;
    /**
     * 离店日
     */
    private Date checkOut;
    /**
     * 入住人信息
     */
    private List<CustomerInfo> customerInfos;
    /**
     * 供应商信息
     */
    private SupplierInfo supplierInfo;
    /**
     * 产品销售渠道
     */
    private String channel;
    /**
     * sessionId
     */
    private String sessionId;

    private String queryId;

    private Map<String, Object> extendParam;
   
    /**
     * 预定的房间数量
     */
    int bookRoomNum;

    /**
     * otaHotelList -- qunarHotelList
     */
    private Map<String,Integer> hotelIdList;

    public OtaPriceParam() {
        super();
    }

    public HotelInfoParam getHotelInfoParam() {
        return hotelInfoParam;
    }

    public void setHotelInfoParam(HotelInfoParam hotelInfoParam) {
        this.hotelInfoParam = hotelInfoParam;
    }

    public String getOtaRoomId() {
        return otaRoomId;
    }

    public void setOtaRoomId(String otaRoomId) {
        this.otaRoomId = otaRoomId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public List<CustomerInfo> getCustomerInfos() {
        return customerInfos;
    }

    public void setCustomerInfos(List<CustomerInfo> customerInfos) {
        this.customerInfos = customerInfos;
    }

    public SupplierInfo getSupplierInfo() {
        return supplierInfo;
    }

    public void setSupplierInfo(SupplierInfo supplierInfo) {
        this.supplierInfo = supplierInfo;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public PriceRequestType getPriceRequestType() {
        return priceRequestType;
    }

    public void setPriceRequestType(PriceRequestType priceRequestType) {
        this.priceRequestType = priceRequestType;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public Map<String, Object> getExtendParam() { return extendParam; }

    public void setExtendParam(Map<String, Object> extendParam) { this.extendParam = extendParam; }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public static enum PriceRequestType {
        WRAPPER, ORDER;
    }

    public Map<String, Integer> getHotelIdList() {
        return hotelIdList;
    }

    public void setHotelIdList(Map<String, Integer> hotelIdList) {
        this.hotelIdList = hotelIdList;
    }

    public int getBookRoomNum() {
        return bookRoomNum;
    }

    public void setBookRoomNum(int bookRoomNum) {
        this.bookRoomNum = bookRoomNum;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
}
