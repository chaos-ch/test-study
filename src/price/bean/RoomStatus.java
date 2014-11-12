package com.qunar.hotel.sa.product.price.bean;

import java.util.Date;

/**
 * User: wei.ding Date: 14-1-8 Time: 下午2:04
 */
public class RoomStatus {
    private int id;
    private int otaId;
    private String hotelId;
    private String roomId;
    private Date date;
    private int status;

    public RoomStatus() {
    }

    public RoomStatus(int otaId, String hotelId, String roomId, Date date, int status) {
        this.otaId = otaId;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOtaId() {
        return otaId;
    }

    public void setOtaId(int otaId) {
        this.otaId = otaId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
