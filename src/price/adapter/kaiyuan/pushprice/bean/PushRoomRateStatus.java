package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Created by he.chen on 14-10-27.
 * version $Id$
 */
public class PushRoomRateStatus {
    private String hotelCode;
    private List<RoomRateStatusDaily> roomRateStatusDailys;

    @XmlElement(name = "hotelCode",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    @XmlElementWrapper(name = "roomRateDailies",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    @XmlElement(name = "RoomRateStatusDaily",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public List<RoomRateStatusDaily> getRoomRateStatusDailys() {
        return roomRateStatusDailys;
    }

    public void setRoomRateStatusDailys(List<RoomRateStatusDaily> roomRateStatusDailys) {
        this.roomRateStatusDailys = roomRateStatusDailys;
    }

    @Override
    public String toString() {
        return "PushRoomRateStatus{" +"\n" +
                "hotelCode='" + hotelCode + '\'' +"\n" +
                ", roomRateStatusDailys=" + roomRateStatusDailys +"\n" +
                '}';
    }
}
