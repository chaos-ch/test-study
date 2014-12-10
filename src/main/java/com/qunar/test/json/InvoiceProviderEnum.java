package com.qunar.test.json;

/**
 * 发票提供方
 * @author rongqian.xu created on 3/19/14 4:50 PM
 * @modify yushen.ma 2014/10/24
 * @version $Id$
 */
public enum InvoiceProviderEnum {

    /**不提供发票*/
    none,
    /**酒店提供发票*/
    hotel,
    /**qunar提供*/
    qunar;

    InvoiceProviderEnum() { }

    public static InvoiceProviderEnum of(String _name) {
        if (_name == null)
            throw new NullPointerException("InvoiceProvider所支持的发票类型不能为空");
        //case to lowwer case
        String name = _name.trim().toLowerCase();
        for (InvoiceProviderEnum tmp : values()) {
            if(name.equals(tmp.name())) {
                return tmp;
            } else if(name.equals("non")) {//增加支持non
                return none;
            }
        }
        throw new RuntimeException("InvoiceProvider不支持该类型:" + _name);
    }
}
