package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * User: wei.ding Date: 13-7-30 Time: 下午7:50
 */
public enum OnlineTypeEnum {
    FULL_PAYMENT(1), PART_PAYMENT(2);

    private int code;

    private static Map<Integer, OnlineTypeEnum> map;

    static {
        map = new HashMap<Integer, OnlineTypeEnum>();
        for (OnlineTypeEnum item : OnlineTypeEnum.values()) {
            map.put(item.getCode(), item);
        }
    }

    private OnlineTypeEnum(int code) {
        this.code = code;
    }

    public static OnlineTypeEnum enumOf(int code) {
        return map.get(code);
    }

    public int getCode() {
        return code;
    }
}
