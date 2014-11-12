package com.qunar.hotel.sa.product.price.bean;

import java.io.Serializable;
import java.util.List;

import com.qunar.hotel.sa.product.hotel.bean.Pair;
import com.qunar.hotel.sa.product.hotel.bean.Three;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.AdapterHotel;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.AdapterPrice;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.AdapterRoom;

/**
 * 产品信息
 * 
 * @author jianjun.li created on 6/13/14 9:13 PM
 * @version $Id$
 */
public class ProductInfo implements Serializable {
    private static final long serialVersionUID = 7899518535208243431L;

    /** 订单报价 */
    private Three<AdapterHotel, AdapterRoom, List<AdapterPrice>> orderPrice;

    /** Wrapper报价 */
    private Pair<AdapterHotel, List<Pair<AdapterRoom, List<AdapterPrice>>>> wrapperPrice;

    public ProductInfo() {
    }

    public ProductInfo(Three<AdapterHotel, AdapterRoom, List<AdapterPrice>> orderPrice) {
        this.orderPrice = orderPrice;
    }

    public ProductInfo(Pair<AdapterHotel, List<Pair<AdapterRoom, List<AdapterPrice>>>> wrapperPrice) {
        this.wrapperPrice = wrapperPrice;
    }

    public Three<AdapterHotel, AdapterRoom, List<AdapterPrice>> getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Three<AdapterHotel, AdapterRoom, List<AdapterPrice>> orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Pair<AdapterHotel, List<Pair<AdapterRoom, List<AdapterPrice>>>> getWrapperPrice() {
        return wrapperPrice;
    }

    public void setWrapperPrice(Pair<AdapterHotel, List<Pair<AdapterRoom, List<AdapterPrice>>>> wrapperPrice) {
        this.wrapperPrice = wrapperPrice;
    }
}
