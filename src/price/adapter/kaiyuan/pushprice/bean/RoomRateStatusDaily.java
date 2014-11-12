package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by he.chen on 14-10-27.
 * version $Id$
 */
public class RoomRateStatusDaily {
    private String roomTypeCode;
    private String ratePlanCode;
    private Date date;
    private int availableNumber;
    private int status;

    @XmlElement(name = "RoomTypeCode",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }

    @XmlElement(name = "RatePlanCode",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getRatePlanCode() {
        return ratePlanCode;
    }

    public void setRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }

    @XmlElement(name = "Dt",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlElement(name = "AvailableNumber",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public int getAvailableNumber() {
        return availableNumber;
    }

    public void setAvailableNumber(int availableNumber) {
        this.availableNumber = availableNumber;
    }

    @XmlElement(name = "Status",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RoomRateStatusDaily{" + "\n" +
                "roomTypeCode='" + roomTypeCode + '\'' +"\n" +
                ", ratePlanCode='" + ratePlanCode + '\'' +"\n" +
                ", date=" + date +"\n" +
                ", availableNumber=" + availableNumber +"\n" +
                ", status=" + status +"\n" +
                '}';
    }
}
