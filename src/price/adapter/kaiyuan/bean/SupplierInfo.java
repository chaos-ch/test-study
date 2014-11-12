package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 供应商基本信息
 * 
 * @author lucett.zhang
 * 
 */
public class SupplierInfo implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 112444950332726596L;
    public static String STATUS_ACTIVE = "active";
    public static String STATUS_DELETED = "deleted";
    public static final String DEFAULT_CURRENCY = "CNY";

    private int id;
    /** 代理商数据接口类型 **/
    private String otaType;
    /** 代理商名称 **/
    private String otaName;
    /** 代理商wrapperID **/
    private String wrapperID;
    /** 代理商domain **/
    private String domain;
    /** 通用接口，酒店列表页地址 **/
    private String listUrl;
    /** 通用接口，报价接口地址 **/
    private String priceUrl;
    /** 状态 **/
    private String status;
    /** 汇率 **/
    private BigDecimal commissionRate;
    /** 数量 **/
    private BigDecimal discount;
    /** 变价推送时间 **/
    private int pricePushHour;
    /** 变价推送状态 **/
    private int pricePushCode;
    /** 供应商支持的货币类型，默认为人民币CNY */
    private String currencyType = DEFAULT_CURRENCY;

    public SupplierInfo() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOtaType() {
        return otaType;
    }

    public void setOtaType(String otaType) {
        this.otaType = otaType;
    }

    public String getOtaName() {
        return otaName;
    }

    public void setOtaName(String otaName) {
        this.otaName = otaName;
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public static String getSTATUS_DELETED() {
        return STATUS_DELETED;
    }

    public static void setSTATUS_DELETED(String sTATUS_DELETED) {
        STATUS_DELETED = sTATUS_DELETED;
    }

    public String getWrapperID() {
        return wrapperID;
    }

    public void setWrapperID(String wrapperID) {
        this.wrapperID = wrapperID;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getListUrl() {
        return listUrl;
    }

    public void setListUrl(String listUrl) {
        this.listUrl = listUrl;
    }

    public String getPriceUrl() {
        return priceUrl;
    }

    public void setPriceUrl(String priceUrl) {
        this.priceUrl = priceUrl;
    }

    public int getPricePushCode() {
        return pricePushCode;
    }

    public void setPricePushCode(int pricePushCode) {
        this.pricePushCode = pricePushCode;
    }

    public int getPricePushHour() {
        return pricePushHour;
    }

    public void setPricePushHour(int pricePushHour) {
        this.pricePushHour = pricePushHour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
