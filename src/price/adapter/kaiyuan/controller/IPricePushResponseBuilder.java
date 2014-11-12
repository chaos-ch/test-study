package com.qunar.hotel.sa.product.price.adapter.kaiyuan.controller;

import java.util.Map;

/**
 * @author weirongzhou
 * 
 */
public interface IPricePushResponseBuilder {
    
    /**
     * 构造报价推送响应消息
     * 
     * @param params
     * @return
     */
    public String build(Map<String, Object> params);

}
