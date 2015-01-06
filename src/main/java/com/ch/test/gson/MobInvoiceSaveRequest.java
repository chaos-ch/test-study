package com.ch.test.gson;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by he.chen on 14-12-3.
 */
public class MobInvoiceSaveRequest implements Serializable{
    private static final long serialVersionUID = -3702318001757984097L;
    /**订单号*/
    private String orderNum;
    /** 发票领取方式(0:不支持发票  1：快递预付 2：联系客服  3：酒店前台获取 4：不需要发票 5：电子发票) **/
    private int invoiceGetType;
    /**发票提供者，hotel, qunar, hotel_n_qunar, non **/
    private String invoiceProvider;
    /** 获取方式(快递预付;快递到付;前台领取)**/
    private String dispatch;
    /** 地址 **/
    private String address;
    /** 区 **/
    private String area;
    /** 市 **/
    private String invoiceCity;
    /** 联系人 **/
    private String invoiceContactName;
    /** 联系人电话 **/
    private String phone;
    /** 发票内容 **/
    private String content;
    /** 快递费用 **/
    private BigDecimal invoiceFee;
    /** 省 **/
    private String province;
    /** 抬头 **/
    private String title;
    /** 发票类型(eg.旅行社发票) **/
    private String invoiceType;
    /**发票备注*/
    private String invoiceRemark;
    /**发票开票状态(MODIFY:可修改， ADD: 可补开，NO_OPTION：不能进行修改/补充)*/
    private String invoiceEditOption;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public int getInvoiceGetType() {
        return invoiceGetType;
    }

    public void setInvoiceGetType(int invoiceGetType) {
        this.invoiceGetType = invoiceGetType;
    }

    public String getInvoiceProvider() {
        return invoiceProvider;
    }

    public void setInvoiceProvider(String invoiceProvider) {
        this.invoiceProvider = invoiceProvider;
    }

    public String getDispatch() {
        return dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInvoiceCity() {
        return invoiceCity;
    }

    public void setInvoiceCity(String invoiceCity) {
        this.invoiceCity = invoiceCity;
    }

    public String getInvoiceContactName() {
        return invoiceContactName;
    }

    public void setInvoiceContactName(String invoiceContactName) {
        this.invoiceContactName = invoiceContactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getInvoiceFee() {
        return invoiceFee;
    }

    public void setInvoiceFee(BigDecimal invoiceFee) {
        this.invoiceFee = invoiceFee;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceRemark() {
        return invoiceRemark;
    }

    public void setInvoiceRemark(String invoiceRemark) {
        this.invoiceRemark = invoiceRemark;
    }

    public String getInvoiceEditOption() {
        return invoiceEditOption;
    }

    public void setInvoiceEditOption(String invoiceEditOption) {
        this.invoiceEditOption = invoiceEditOption;
    }
}
