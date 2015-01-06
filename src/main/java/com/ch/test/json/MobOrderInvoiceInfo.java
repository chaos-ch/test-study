package com.ch.test.json;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by xiaoxiong.li on 2014/10/29 19:27).
 * version $Id$
 */
public class MobOrderInvoiceInfo implements Serializable {
    private static final long serialVersionUID = 1619862524237899613L;
    /**发票开票状态(NONE：未开票，MODIFY:可修改， MAKEUP: 可补开，DONE：已开票终结)**/
    private String invoiceMakeStatus;
    /** 发票领取方式(0:不支持发票  1：快递预付 2：联系客服  3：酒店前台获取 4：不需要发票 5：电子发票) **/
    private int invoiceGetType;
    /** 获取方式(快递预付;前台领取)**/
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
    /** 发票备注 **/
    private String invoiceRemark;

    public String getInvoiceMakeStatus() {
        return invoiceMakeStatus;
    }

    public void setInvoiceMakeStatus(String invoiceMakeStatus) {
        this.invoiceMakeStatus = invoiceMakeStatus;
    }

    public int getInvoiceGetType() {
        return invoiceGetType;
    }

    public void setInvoiceGetType(int invoiceGetType) {
        this.invoiceGetType = invoiceGetType;
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
}
