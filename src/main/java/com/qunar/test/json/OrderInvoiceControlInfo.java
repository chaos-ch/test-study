package com.qunar.test.json;

import com.google.common.base.Objects;
import com.qunar.hotel.qta.base.money.Money;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 发票流程控制信息
 *
 * Created by xu.cao on 2014/10/27.
 */
public class OrderInvoiceControlInfo implements Serializable {

    private static final long serialVersionUID = 2854579567699889933L;

    /** 订单号 */
    private long orderNum = 0L;

    /** 重试次数 */
    private int retry = 0;

    /** 发票税 */
    private BigDecimal taxRate;

    /** Qunar 承担的税费 */
    private Money taxFee;

    /** （QInvoice系统）发票类型代码 例如：BT（Business Tax营业税） **/
    private String invoiceTypeCode =
            InvoiceCategory.defaultValue().name();

    /** 用户态的state */
    private String userInvoiceState
            = UserInvoiceState.defaultValue().name();

    /** (QInvoice系统)发票单据号 **/
    private String invoiceDoc;

    /** 发票号码 **/
    private String invoiceNo;

    /** 开具发票方公司名称 **/
    private String invoiceProviderCompanyName;

    /** 开具发票方公司代码 **/
    private String invoiceProviderCompanyCode;

    /** 向QInvoice发票系统提交开票申请类型 **/
    private String InvoiceApplyType;

    /** 向QInvoice发票系统提交开票申请返回信息（如驳回申请的理由） **/
    private String invoiceApplyMessage;

    /** 最后提交开票申请时间 **/
    private Date lastApplyTime;

    /** QInvoice发票系统处理流程状态 **/
    private String qInvoiceProcessState
            = QInvoiceProcessState.APPLY_NOT_SUCCESS.name();

    /** 发票流程状态最后更新时间 **/
    private long lastUpdateTime;

    /** 发票流程启动时间,通常为离店或退款的T+1*/
    private Date createTime;

    /** 快递单号 **/
    private String expressNo;

    /** 快递公司名称 **/
    private String expressCompany;

    /** 订单的版本号 */
    private int version;

    /**物流详细信息*/
    private List<LogisticsData> logisticsDataList;

    /**
     * 订阅重试次数
     */
    private int subscribeRetry;

    /**
     * 快递跟踪状态
     */
    private String trackStatus;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getInvoiceTypeCode() {
        return invoiceTypeCode;
    }

    public void setInvoiceTypeCode(String invoiceTypeCode) {
        this.invoiceTypeCode = invoiceTypeCode;
    }

    public String getInvoiceDoc() {
        return invoiceDoc;
    }

    public void setInvoiceDoc(String invoiceDoc) {
        this.invoiceDoc = invoiceDoc;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceProviderCompanyName() {
        return invoiceProviderCompanyName;
    }

    public void setInvoiceProviderCompanyName(String invoiceProviderCompanyName) {
        this.invoiceProviderCompanyName = invoiceProviderCompanyName;
    }

    public String getInvoiceProviderCompanyCode() {
        return invoiceProviderCompanyCode;
    }

    public void setInvoiceProviderCompanyCode(String invoiceProviderCompanyCode) {
        this.invoiceProviderCompanyCode = invoiceProviderCompanyCode;
    }

    public String getInvoiceApplyType() {
        return InvoiceApplyType;
    }

    public void setInvoiceApplyType(String invoiceApplyType) {
        InvoiceApplyType = invoiceApplyType;
    }

    public String getInvoiceApplyMessage() {
        return invoiceApplyMessage;
    }

    public void setInvoiceApplyMessage(String invoiceApplyMessage) {
        this.invoiceApplyMessage = invoiceApplyMessage;
    }

    public Date getLastApplyTime() {
        return lastApplyTime;
    }

    public void setLastApplyTime(Date lastApplyTime) {
        this.lastApplyTime = lastApplyTime;
    }

    public String getqInvoiceProcessState() {
        return qInvoiceProcessState;
    }

    public void setqInvoiceProcessState(String qInvoiceProcessState) {
        this.qInvoiceProcessState = qInvoiceProcessState;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getUserInvoiceState() {
        return userInvoiceState;
    }

    public void setUserInvoiceState(String userInvoiceState) {
        this.userInvoiceState = userInvoiceState;
    }

    public long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(long orderNum) {
        this.orderNum = orderNum;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public Money getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(Money taxFee) {
        this.taxFee = taxFee;
    }

    public OrderInvoiceControlInfo retryIncrease() {
        this.setRetry(getRetry() + 1);
        return this;
    }

    public OrderInvoiceControlInfo retryDecrease() {
        this.setRetry(getRetry() - 1);
        return this;
    }

    public List<LogisticsData> getLogisticsDataList() {
        return logisticsDataList;
    }

    public void setLogisticsDataList(List<LogisticsData> logisticsDataList) {
        this.logisticsDataList = logisticsDataList;
    }

    public int getSubscribeRetry() {
        return subscribeRetry;
    }

    public void setSubscribeRetry(int subscribeRetry) {
        this.subscribeRetry = subscribeRetry;
    }

    public String getTrackStatus() {
        return trackStatus;
    }

    public void setTrackStatus(String trackStatus) {
        this.trackStatus = trackStatus;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("orderNum", orderNum)
                .add("retry", retry)
                .add("taxRate", taxRate)
                .add("taxFee", taxFee)
                .add("invoiceTypeCode", invoiceTypeCode)
                .add("userInvoiceState", userInvoiceState)
                .add("invoiceDoc", invoiceDoc)
                .add("invoiceNo", invoiceNo)
                .add("invoiceProviderCompanyName", invoiceProviderCompanyName)
                .add("invoiceProviderCompanyCode", invoiceProviderCompanyCode)
                .add("InvoiceApplyType", InvoiceApplyType)
                .add("invoiceApplyMessage", invoiceApplyMessage)
                .add("lastApplyTime", lastApplyTime)
                .add("qInvoiceProcessState", qInvoiceProcessState)
                .add("lastUpdateTime", lastUpdateTime)
                .add("createTime", createTime)
                .add("expressNo", expressNo)
                .add("expressCompany", expressCompany)
                .add("version", version)
                .add("logisticsData", logisticsDataList.toString())
                .add("subscribeRetry",subscribeRetry)
                .add("trackStatus",trackStatus)
                .toString();
    }
}
