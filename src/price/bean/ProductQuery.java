package com.qunar.hotel.sa.product.price.bean;

import com.qunar.hotel.sa.product.hotel.bean.Channel;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.SupplierInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by he.chen on 14-10-27.
 * @version $Id$
 */
public class ProductQuery implements Serializable {
    private static final long serialVersionUID = 6319813691967641959L;

    /** Qunar酒店Id */
    private int hotelId;
    /** seq */
    private String seq;
    /** 房型Id */
    private String roomId;
    /** 开始日期 */
    private Date checkInDate;
    /** 结束日期 */
    private Date checkOutDate;
    /** 预定间数 */
    private int roomNum;
    /** queryId */
    private String queryId;
    /** 渠道 */
    private Channel channel;
    /** SeesionId */
    private String sessionId;
    /** 预定人信息 */
    private List<CustomerInfo> customerInfos;
    /** 供应商信息 */
    private SupplierInfo supplierInfo;
    /** RequestType */
    private OtaPriceParam.PriceRequestType priceRequestType;
    /** 扩展查询字段 */
    private Map<String,Object> extendParam;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public String getQueryId() {
        return queryId;
    }

    public void setQueryId(String queryId) {
        this.queryId = queryId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<CustomerInfo> getCustomerInfos() {
        return customerInfos;
    }

    public void setCustomerInfos(List<CustomerInfo> customerInfos) {
        this.customerInfos = customerInfos;
    }

    public SupplierInfo getSupplierInfo() {
        return supplierInfo;
    }

    public void setSupplierInfo(SupplierInfo supplierInfo) {
        this.supplierInfo = supplierInfo;
    }

    public OtaPriceParam.PriceRequestType getPriceRequestType() {
        return priceRequestType;
    }

    public void setPriceRequestType(OtaPriceParam.PriceRequestType priceRequestType) {
        this.priceRequestType = priceRequestType;
    }

    public Map<String, Object> getExtendParam() { return extendParam; }

    public void setExtendParam(Map<String, Object> extendParam) { this.extendParam = extendParam; }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
