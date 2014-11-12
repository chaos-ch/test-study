package com.qunar.hotel.sa.product.price.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class PriceParam {
    private RequestType requestType;
    private int hotelId;
    private String roomId;
    private Date checkInDate;
    private Date checkOutDate;
    private int otaId;
    private List<CustomerInfo> customerInfos;
    private String sessionId;
    private String otaHotelId;
    private String otaUrl;
    private String ext;
    private String city;
    private int roomNum;
    /**
     * 产品销售渠道
     */
    private String channel;
    /**
     * otaHotelId  --  qunarHotelId
     */
    private Map<String,Integer> hotelIdList;

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public List<CustomerInfo> getCustomerInfos() {
        return customerInfos;
    }

    public void setCustomerInfos(List<CustomerInfo> customerInfos) {
        this.customerInfos = customerInfos;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getOtaId() {
        return otaId;
    }

    public void setOtaId(int otaId) {
        this.otaId = otaId;
    }

    public enum RequestType {
        wrapper, order;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getOtaHotelId() {
        return otaHotelId;
    }

    public void setOtaHotelId(String otaHotelId) {
        this.otaHotelId = otaHotelId;
    }

    public String getOtaUrl() {
        return otaUrl;
    }

    public void setOtaUrl(String otaUrl) {
        this.otaUrl = otaUrl;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Map<String, Integer> getHotelIdList() {
        return hotelIdList;
    }

    public void setHotelIdList(Map<String, Integer> hotelIdList) {
        this.hotelIdList = hotelIdList;
    }
}
