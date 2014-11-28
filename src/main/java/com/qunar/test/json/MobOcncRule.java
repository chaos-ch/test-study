/*
 * Copyright (c) 2013 Qunar.com. All Rights Reserved.
 */
package com.qunar.test.json;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author rongqian.xu created on 7/23/14 11:31 PM
 * @version $Id$
 */
public class MobOcncRule implements Serializable {
    private static final long serialVersionUID = 6498120293295844271L;
    /**如果转为ocnc直减金额*/
    private BigDecimal subtractPrice;
    /**如果转为ocnc预付金额*/
    private BigDecimal prepayPrice;
    /**如果转为ocnc退款描述*/
    private String refundDesc;

    public BigDecimal getPrepayPrice() {
        return prepayPrice;
    }

    public void setPrepayPrice(BigDecimal prepayPrice) {
        this.prepayPrice = prepayPrice;
    }

    public String getRefundDesc() {
        return refundDesc;
    }

    public void setRefundDesc(String refundDesc) {
        this.refundDesc = refundDesc;
    }

    public BigDecimal getSubtractPrice() {
        return subtractPrice;
    }

    public void setSubtractPrice(BigDecimal subtractPrice) {
        this.subtractPrice = subtractPrice;
    }
}
