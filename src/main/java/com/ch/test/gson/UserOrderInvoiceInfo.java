package com.ch.test.gson;

import com.ch.test.json.OrderInvoiceExpressInfo;
import com.qunar.hotel.qta.base.money.Money;

import java.io.Serializable;


/**
 * ucenter 订单详情中的发票、物流信息
 * @author he.chen created on 14-12-23.
 * @version $Id$
 */
public class UserOrderInvoiceInfo implements Serializable{

    private static final long serialVersionUID = -1037728712853785038L;
    /** 发票领取方式(0:不支持发票  1：快递预付 2：联系客服  3：酒店前台获取 4：不需要发票 5：电子发票) **/
    private int invoiceGetType;

    /** 获取方式(快递预付;前台领取)**/
    private String dispatch;

    /** 发票内容 **/
    private String invoiceContent;

    /** 抬头 **/
    private String invoiceTitle;

    /** 发票金额 **/
    private Money invoiceFee;

    /** 发票类型(eg.旅行社发票) **/
    private String invoiceType;

    /** 发票备注 **/
    private String invoiceRemark;

    /** 发票的快递信息 */
    private OrderInvoiceExpressInfo orderInvoiceExpressInfo;

    /**发票的物流信息**/
    private UserInvoiceLogistics userInvoiceLogistics;

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

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public Money getInvoiceFee() {
        return invoiceFee;
    }

    public void setInvoiceFee(Money invoiceFee) {
        this.invoiceFee = invoiceFee;
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

    public OrderInvoiceExpressInfo getOrderInvoiceExpressInfo() {
        return orderInvoiceExpressInfo;
    }

    public void setOrderInvoiceExpressInfo(OrderInvoiceExpressInfo orderInvoiceExpressInfo) {
        this.orderInvoiceExpressInfo = orderInvoiceExpressInfo;
    }

    public UserInvoiceLogistics getUserInvoiceLogistics() {
        return userInvoiceLogistics;
    }

    public void setUserInvoiceLogistics(UserInvoiceLogistics userInvoiceLogistics) {
        this.userInvoiceLogistics = userInvoiceLogistics;
    }
}
