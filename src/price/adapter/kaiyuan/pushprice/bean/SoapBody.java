package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by he.chen on 14-10-27.
 * version $Id$
 */
public class SoapBody {

    //push价格
    private PushRatePlan pushRatePlan;

    //push房态
    private PushRoomRateStatus pushRoomRateStatus;

    //实时获取报价
    private HotelRateDailyDetailResponse hotelRateDailyDetailResponse;

    //登录开元服务器返回接结果解析
    private ChannelLoginResponse channelLoginResponse;

    @XmlElement(name = "ChannelLoginResponse",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public ChannelLoginResponse getChannelLoginResponse() {
        return channelLoginResponse;
    }

    public void setChannelLoginResponse(ChannelLoginResponse channelLoginResult) {
        this.channelLoginResponse = channelLoginResult;
    }

    @XmlElement(name = "PushRatePlan",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public PushRatePlan getPushRatePlan() {
        return pushRatePlan;
    }

    public void setPushRatePlan(PushRatePlan pushRatePlan) {
        this.pushRatePlan = pushRatePlan;
    }

    @XmlElement(name = "PushRoomRateStatus",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public PushRoomRateStatus getPushRoomRateStatus() {
        return pushRoomRateStatus;
    }

    public void setPushRoomRateStatus(PushRoomRateStatus pushRoomRateStatus) {
        this.pushRoomRateStatus = pushRoomRateStatus;
    }

    @XmlElement(name = "HotelRateDailyDetailResponse",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public HotelRateDailyDetailResponse getHotelRateDailyDetailResponse() {
        return hotelRateDailyDetailResponse;
    }

    public void setHotelRateDailyDetailResponse(HotelRateDailyDetailResponse hotelRateDailyDetailResponse) {
        this.hotelRateDailyDetailResponse = hotelRateDailyDetailResponse;
    }

    @Override
    public String toString() {
        return "SoapBody{" +
                "pushRatePlan=" + pushRatePlan +
                ", pushRoomRateStatus=" + pushRoomRateStatus +
                ", hotelRateDailyDetailResponse=" + hotelRateDailyDetailResponse +
                '}';
    }
}
