package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

/**
 * 担保类型
 * 
 * @author weiming.liao
 * 
 */
public enum VouchType {
    /** 首晚担保 code:1 **/
    FIRST(1),
    /** 全额担保 code:2 **/
    ALL(2);
    private int code;

    private VouchType(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
