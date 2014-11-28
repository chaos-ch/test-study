package com.qunar.test.json;

import com.qunar.hotel.qta.base.money.Money;

import java.math.BigDecimal;

/**
 * Created by he.chen on 14-11-27.
 */

public enum InvoiceDispatchType {

    BY_EXPRESS(0, "快递预付", new Money(BigDecimal.TEN)), BY_HOTEL(1, "酒店前台领取", new Money(BigDecimal.ZERO));

    /** 费用*/
    int code;

    /** 费用 */
    String desc;

    /** 费用 */
    private Money fee;

    private InvoiceDispatchType(int _code, String _desc, Money _fee) {
        this.code = _code;
        this.desc = _desc;
        this.fee = _fee;
    }

    public static InvoiceDispatchType of(String _name) {
        if (_name == null) {
            throw new IllegalArgumentException("InvoiceDispatchType name is null");
        }
        String name = _name.trim();
        for (InvoiceDispatchType type : InvoiceDispatchType.values()) {
            if (type.name().equals(name) || type.desc.equals(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid InvoiceDispatchType name:" + name);
    }

    public static InvoiceDispatchType of(int code) {
        for (InvoiceDispatchType type : InvoiceDispatchType.values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid InvoiceDispatchType code:" + code);
    }

    public static String showOf(String _name) {
        return of(_name).desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Money getFee() {
        return fee;
    }

    public void setFee(Money fee) {
        this.fee = fee;
    }

    public Money fee() {
        return fee;
    }
}

