package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Created by he.chen on 14-10-27.
 * version $Id$
 */
public class HotelRateDailyDetailResponse {
    private List<RoomRateDaily> roomRateDailies;

    @XmlElementWrapper(name = "HotelRateDailyDetailResult",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    @XmlElement(name = "RoomRateDaily",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public List<RoomRateDaily> getRoomRateDailies() {
        return roomRateDailies;
    }

    public void setRoomRateDailies(List<RoomRateDaily> roomRateDailies) {
        this.roomRateDailies = roomRateDailies;
    }

    @Override
    public String toString() {
        return "HotelRateDailyDetailResponse{" + "\n"+
                "roomRateDailies=" + roomRateDailies +"\n"+
                '}';
    }
}
