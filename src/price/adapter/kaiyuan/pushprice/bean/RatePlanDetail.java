package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Created by he.chen on 14-10-27.
 * version $Id$
 */
public class RatePlanDetail {
    private RoomType roomType;
    private Date beginDate;
    private Date endDate;
    private BigDecimal rateAmount;
    private BigDecimal rateAmount2;
    private BigDecimal baseAmount;
    private BigDecimal extraBedAmount;
    private BigDecimal extraChildAmount;
    private int serviceChargeFlag;//int合适吗？
    private BigDecimal serviceCharge;
    private int taxFlag;//int合适吗？
    private BigDecimal tax;
    private String weekControl;
    private String des;
    private List<Package> packages;


    @XmlElement(name = "roomType",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @XmlElement(name = "beginDate",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    @XmlElement(name = "endDate",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @XmlElement(name = "rateAmount",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public BigDecimal getRateAmount() {
        return rateAmount;
    }

    public void setRateAmount(BigDecimal rateAmount) {
        this.rateAmount = rateAmount;
    }

    @XmlElement(name = "rateAmount2",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public BigDecimal getRateAmount2() {
        return rateAmount2;
    }

    public void setRateAmount2(BigDecimal rateAmount2) {
        this.rateAmount2 = rateAmount2;
    }

    @XmlElement(name = "baseAmount",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    @XmlElement(name = "extraBedAmount",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public BigDecimal getExtraBedAmount() {
        return extraBedAmount;
    }

    public void setExtraBedAmount(BigDecimal extraBedAmount) {
        this.extraBedAmount = extraBedAmount;
    }

    @XmlElement(name = "extraChildAmount",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public BigDecimal getExtraChildAmount() {
        return extraChildAmount;
    }

    public void setExtraChildAmount(BigDecimal extraChildAmount) {
        this.extraChildAmount = extraChildAmount;
    }

    @XmlElement(name = "ServiceChargeFlag",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public int getServiceChargeFlag() {
        return serviceChargeFlag;
    }

    public void setServiceChargeFlag(int serviceChargeFlag) {
        this.serviceChargeFlag = serviceChargeFlag;
    }

    @XmlElement(name = "ServiceCharge",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    @XmlElement(name = "TaxFlag",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public int getTaxFlag() {
        return taxFlag;
    }

    public void setTaxFlag(int taxFlag) {
        this.taxFlag = taxFlag;
    }

    @XmlElement(name = "Tax",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    @XmlElement(name = "weekControl",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getWeekControl() {
        return weekControl;
    }

    public void setWeekControl(String weekControl) {
        this.weekControl = weekControl;
    }

    @XmlElement(name = "Des",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @XmlElementWrapper(name = "Packages",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    @XmlElement(name = "Package",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }
    @Override
    public String toString() {
        return "RatePlanDetail{" +"\n" +
                "roomType=" + roomType +"\n" +
                ", beginDate=" + beginDate +"\n" +
                ", endDate=" + endDate +"\n" +
                ", rateAmount=" + rateAmount +"\n" +
                ", rateAmount2=" + rateAmount2 +"\n" +
                ", baseAmount=" + baseAmount +"\n" +
                ", extraBedAmount=" + extraBedAmount +"\n" +
                ", extraChildAmount=" + extraChildAmount +"\n" +
                ", serviceChargeFlag=" + serviceChargeFlag +"\n" +
                ", serviceCharge=" + serviceCharge +"\n" +
                ", taxFlag=" + taxFlag +"\n" +
                ", tax=" + tax +"\n" +
                ", weekControl='" + weekControl + '\'' +"\n" +
                ", des='" + des + '\'' +"\n" +
                '}';
    }
}
