package com.ch.test.json;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by he.chen on 2014/11/27 .
 * version $Id$
 */
public class UserOrderInvoiceInfo implements Serializable {

    private static final long serialVersionUID = -1494986476201138759L;
    /** 发票领取方式(0:不支持发票  1：快递预付 2：联系客服  3：酒店前台获取 4：不需要发票 5：电子发票) **/
    private int invoiceGetType;

    /** 获取方式(快递预付;前台领取)**/
    private String dispatch;

    /** 发票内容 **/
    private String invoiceContent;

    /** 抬头 **/
    private String invoiceTitle;

    /** 快递金额 **/
    private BigDecimal invoiceFee;

    /** 发票类型(eg.旅行社发票) **/
    private String invoiceType;

    /**发票的物流信息**/
    private OrderInvoiceExpressInfo orderInvoiceExpressInfo;

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


    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public BigDecimal getInvoiceFee() {
        return invoiceFee;
    }

    public void setInvoiceFee(BigDecimal invoiceFee) {
        this.invoiceFee = invoiceFee;
    }


    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public OrderInvoiceExpressInfo getOrderInvoiceExpressInfo() {
        return orderInvoiceExpressInfo;
    }

    public void setOrderInvoiceExpressInfo(OrderInvoiceExpressInfo orderInvoiceExpressInfo) {
        this.orderInvoiceExpressInfo = orderInvoiceExpressInfo;
    }
}
