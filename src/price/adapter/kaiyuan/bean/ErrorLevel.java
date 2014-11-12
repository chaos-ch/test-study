package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.io.Serializable;

/**
 * User: yanyan
 * Date: 7/22/14
 * Time: 4:09 PM
 */
public enum ErrorLevel implements Serializable {

    ROOM("房型级",0),HOTEL("酒店级",1),OTATTS("OTATTS错误",2),SYSTEM("系统级",3),QHOTEL("QHOTEL错误",4),TWELL("TWELL错误",5);

    private String name;
    private int value;

    ErrorLevel(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isLower(ErrorLevel errorLevel) {
        if(errorLevel == null || this.value < errorLevel.getValue()) {
            return true;
        }
        return false;
    }

    public boolean isHigher(ErrorLevel errorLevel) {
        if(errorLevel == null || this.value > errorLevel.getValue()) {
            return true;
        }
        return false;
    }
}
