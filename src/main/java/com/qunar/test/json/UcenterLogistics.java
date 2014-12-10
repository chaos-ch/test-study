package com.qunar.test.json;

import com.qunar.hotel.qta.base.money.Money;

import java.io.Serializable;
import java.util.List;

/**
 * ucenter 物流接口
 * Created by he.chen on 14-12-9.
 */
public class UcenterLogistics implements Serializable{

    private static final long serialVersionUID = -3130702955691148553L;
    /**发票领取方式(0:不支持发票  1：快递预付 2：联系客服  3：酒店前台获取 4：不需要发票 5：电子发票)*/
    private int invoiceGetType;

    /**发票的物流信息**/
    private OrderInvoiceExpressInfo orderInvoiceExpressInfo;

    private List<LogisticsData> logisticsInfos;

    /** 发票类型(eg.旅行社发票) **/
    private String invoiceType;

    /** 获取方式(快递预付;快递到付;前台领取)**/
    private String dispatch;

    /** 发票金额 **/
    private Money invoiceFee;

    /** 抬头 **/
    private String invoiceTitle;

    /** 发票内容 **/
    private String invoiceContent;

    /** 发票备注信息 */
    private String invoiceRemark;

    public int getInvoiceGetType() {
        return invoiceGetType;
    }

    public void setInvoiceGetType(int invoiceGetType) {
        this.invoiceGetType = invoiceGetType;
    }

    public OrderInvoiceExpressInfo getOrderInvoiceExpressInfo() {
        return orderInvoiceExpressInfo;
    }

    public void setOrderInvoiceExpressInfo(OrderInvoiceExpressInfo orderInvoiceExpressInfo) {
        this.orderInvoiceExpressInfo = orderInvoiceExpressInfo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<LogisticsData> getLogisticsInfos() {
        return logisticsInfos;
    }

    public void setLogisticsInfos(List<LogisticsData> logisticsInfos) {
        this.logisticsInfos = logisticsInfos;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getDispatch() {
        return dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public Money getInvoiceFee() {
        return invoiceFee;
    }

    public void setInvoiceFee(Money invoiceFee) {
        this.invoiceFee = invoiceFee;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public String getInvoiceRemark() {
        return invoiceRemark;
    }

    public void setInvoiceRemark(String invoiceRemark) {
        this.invoiceRemark = invoiceRemark;
    }
}
