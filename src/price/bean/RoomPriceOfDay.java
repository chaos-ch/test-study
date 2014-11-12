package com.qunar.hotel.sa.product.price.bean;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

public class RoomPriceOfDay implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = -7611424581301288431L;
    private String date;
    private String price;
    private String deposit;
    private int roomStatus;
    private String breakfastStr;

    public RoomPriceOfDay() {
        super();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public int getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(int roomStatus) {
        this.roomStatus = roomStatus;
    }

    @JsonIgnore
    public String getBreakfastStr() {
        return breakfastStr;
    }

    @JsonIgnore
    public void setBreakfastStr(String breakfastStr) {
        this.breakfastStr = breakfastStr;
    }

}
