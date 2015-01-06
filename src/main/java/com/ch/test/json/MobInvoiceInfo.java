package com.ch.test.json;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * com.qunar.hotel.qta.order.web.api.price.MobInvoiceInfo
 * 无线端发票信息
 * @author yushen.ma
 * @date 2014-10-28
 */
public class MobInvoiceInfo implements Serializable {

    private static final long serialVersionUID = -5278777301256509250L;

    /**
     * 发票提供者，qta对无线中当同时支持hotel和qunar，传qunar,
     * 使用dispatchList来判断
     **/
    private InvoiceProviderEnum invoiceProvider
            = InvoiceProviderEnum.none;

    /** 发票类型，现在是固定值旅行社发票*/
    private List<String> typeList;

    /** 发票内容 */
    private List<String> contentList;

    /** 运送方式列表 */
    private List<String> dispatchList;

    /** 快递预付全国费用 */
    private BigDecimal nationwideFee;

    /** 发票说明描述列表 **/
    private List<String> descList;

    /* ====================== 以下为兼容ota的无效字段 ====================*/
    /** 快递预付同城费用 */
    private BigDecimal cityFee;

    private String province = "天津";

    private String city = "天津";

    private int id = 0;

    private String channel = null;

    private long dataId = 0L;

    private String hotelInvoice = null;

    public InvoiceProviderEnum getInvoiceProvider() {
        return invoiceProvider;
    }

    public void setInvoiceProvider(InvoiceProviderEnum invoiceProvider) {
        this.invoiceProvider = invoiceProvider;
    }

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public List<String> getContentList() {
        return contentList;
    }

    public void setContentList(List<String> contentList) {
        this.contentList = contentList;
    }

    public List<String> getDispatchList() {
        return dispatchList;
    }

    public void setDispatchList(List<String> dispatchList) {
        this.dispatchList = dispatchList;
    }

    public BigDecimal getNationwideFee() {
        return nationwideFee;
    }

    public void setNationwideFee(BigDecimal nationwideFee) {
        this.nationwideFee = nationwideFee;
    }

    public List<String> getDescList() {
        return descList;
    }

    public void setDescList(List<String> descList) {
        this.descList = descList;
    }

    public BigDecimal getCityFee() {
        return cityFee;
    }

    public void setCityFee(BigDecimal cityFee) {
        this.cityFee = cityFee;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public long getDataId() {
        return dataId;
    }

    public void setDataId(long dataId) {
        this.dataId = dataId;
    }

    public String getHotelInvoice() {
        return hotelInvoice;
    }

    public void setHotelInvoice(String hotelInvoice) {
        this.hotelInvoice = hotelInvoice;
    }

    @Override
    public String toString() {
        return "MobInvoiceInfo{" +
                "invoiceProvider=" + invoiceProvider +
                ", typeList=" + typeList +
                ", contentList=" + contentList +
                ", dispatchList=" + dispatchList +
                ", nationwideFee=" + nationwideFee +
                ", descList=" + descList +
                ", cityFee=" + cityFee +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", id=" + id +
                ", channel='" + channel + '\'' +
                ", dataId=" + dataId +
                ", hotelInvoice='" + hotelInvoice + '\'' +
                '}';
    }
}
