package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * User: yanyan
 * Date: 7/22/14
 * Time: 4:04 PM
 */
public class ErrorMessageInfo implements Serializable {
    private String qunarRoomId;
    private String roomId;
    private String roomName;
    private PriceValidatorCode priceValidatorCode;

    public ErrorMessageInfo(PriceValidatorCode priceValidatorCode) {
        this.priceValidatorCode = priceValidatorCode;

    }

    public ErrorMessageInfo() {

    }

    public ErrorMessageInfo(PriceValidatorCode priceValidatorCode, String roomId, String roomName) {
        this.roomId = transferWrapperRoomIdIntoRoomId(roomId);
        this.roomName = roomName;
        this.priceValidatorCode = priceValidatorCode;
    }

    String transferWrapperRoomIdIntoRoomId(String wrapperRoomId) {
        if(StringUtils.isEmpty(wrapperRoomId)) {
            return null;
        }
        if(wrapperRoomId.startsWith("M") || wrapperRoomId.startsWith("B")) {
            wrapperRoomId = wrapperRoomId.substring(wrapperRoomId.indexOf("_",2)+1);
        }
        return wrapperRoomId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = transferWrapperRoomIdIntoRoomId(roomId);
    }


    public PriceValidatorCode getPriceValidatorCode() {
        return priceValidatorCode;
    }

    public void setPriceValidatorCode(PriceValidatorCode priceValidatorCode) {
        this.priceValidatorCode = priceValidatorCode;
    }


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getQunarRoomId() {
        return qunarRoomId;
    }

    public void setQunarRoomId(String qunarRoomId) {
        this.qunarRoomId = qunarRoomId;
    }
}
