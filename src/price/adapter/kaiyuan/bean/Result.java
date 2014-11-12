package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 担保结果
 * 
 * @author weiming.liao
 * 
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 633843153437589275L;
    private boolean isVouch;
    private VouchType vouchType;
    private String description;
    private Date lastCancelTime;

    public boolean isVouch() {
        return isVouch;
    }

    public void setVouch(boolean isVouch) {
        this.isVouch = isVouch;
    }

    public VouchType getVouchType() {
        return vouchType;
    }

    public void setVouchType(VouchType vouchType) {
        this.vouchType = vouchType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastCancelTime() {
        return lastCancelTime;
    }

    public void setLastCancelTime(Date lastCancelTime) {
        this.lastCancelTime = lastCancelTime;
    }

}
