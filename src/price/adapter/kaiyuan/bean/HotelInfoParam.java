package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class HotelInfoParam implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5358917611541879518L;
    /**
     * qunar酒店ID
     */
    private String qunarHotelId;
    /**
     * 代理商酒店ID
     */
    private String otaHotelId;
    /**
     * 酒店名称
     */
    private String hotelName;
    
    
    /**
     * 缓存时间
     */
    private int adjustCacheTime;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 酒店seq
     */
    private String seq;
    /**
     * 酒店扩展信息
     */
    private String ext;

    public String getQunarHotelId() {
        return qunarHotelId;
    }

    public void setQunarHotelId(String qunarHotelId) {
        this.qunarHotelId = qunarHotelId;
    }

    public String getOtaHotelId() {
        return otaHotelId;
    }

    public void setOtaHotelId(String otaHotelId) {
        this.otaHotelId = otaHotelId;
    }

    public int getAdjustCacheTime() {
        return adjustCacheTime;
    }

    public void setAdjustCacheTime(int adjustCacheTime) {
        this.adjustCacheTime = adjustCacheTime;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
