package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 处理参数
 * 
 * @author weiming.liao
 * 
 */
public class Params implements Serializable {
    private static final long serialVersionUID = 7708047931058463411L;
    protected Date checkInDate;
    protected Date checkOutDate;
    protected Date arriveEarlyTime;
    protected Date arriveLaterTime;
    protected int roomNum;

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

    public Date getArriveEarlyTime() {
        return arriveEarlyTime;
    }

    public void setArriveEarlyTime(Date arriveEarlyTime) {
        this.arriveEarlyTime = arriveEarlyTime;
    }

    public Date getArriveLaterTime() {
        return arriveLaterTime;
    }

    public void setArriveLaterTime(Date arriveLaterTime) {
        this.arriveLaterTime = arriveLaterTime;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

}
