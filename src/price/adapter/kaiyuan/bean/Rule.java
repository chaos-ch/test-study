package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.io.Serializable;

/**
 * 担保规则
 * 
 * @author weiming.liao
 * 
 */
public interface Rule extends Serializable {

    /**
     * 决策
     * 
     * @param params
     * @return true:满足规则
     */
    public boolean decision(Params params);
}
