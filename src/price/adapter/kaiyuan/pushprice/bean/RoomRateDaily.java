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
public class RoomRateDaily {
    private String roomTypeCode;
    private String ratePlanCode;
    private Date inHouseDate;
    private int availableNumber;
    private BigDecimal rateAmount;
    private BigDecimal rateAmount2;
    private BigDecimal baseAmount;
    private BigDecimal extraBedAmount;
    private BigDecimal extraChildAmount;
    private int serviceChargeFlag;//int????
    private BigDecimal serviceCharge;
    private int raxFlag;
    private BigDecimal tax;
    private int status;
    private List<Package> packages;

    @XmlElement(name = "RoomTypeCode",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getRoomTypeCode() {
        return roomTypeCode;
    }

    public void setRoomTypeCode(String roomTypeCode) {
        this.roomTypeCode = roomTypeCode;
    }
    @XmlElement(name = "RatePlanCode",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getRatePlanCode() {
        return ratePlanCode;
    }

    public void setRatePlanCode(String ratePlanCode) {
        this.ratePlanCode = ratePlanCode;
    }
    @XmlElement(name = "inHouseDate",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public Date getInHouseDate() {
        return inHouseDate;
    }

    public void setInHouseDate(Date inHouseDate) {
        this.inHouseDate = inHouseDate;
    }
    @XmlElement(name = "AvailableNumber",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public int getAvailableNumber() {
        return availableNumber;
    }

    public void setAvailableNumber(int availableNumber) {
        this.availableNumber = availableNumber;
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
    public int getRaxFlag() {
        return raxFlag;
    }

    public void setRaxFlag(int raxFlag) {
        this.raxFlag = raxFlag;
    }
    @XmlElement(name = "Tax",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
    @XmlElement(name = "Status",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        return "RoomRateDaily{" +"\n"+
                "roomTypeCode='" + roomTypeCode + '\'' +"\n"+
                ", ratePlanCode='" + ratePlanCode + '\'' +"\n"+
                ", inHouseDate=" + inHouseDate +"\n"+
                ", availableNumber=" + availableNumber +"\n"+
                ", rateAmount=" + rateAmount +"\n"+
                ", rateAmount2=" + rateAmount2 +"\n"+
                ", baseAmount=" + baseAmount +"\n"+
                ", extraBedAmount=" + extraBedAmount +"\n"+
                ", extraChildAmount=" + extraChildAmount +"\n"+
                ", serviceChargeFlag=" + serviceChargeFlag +"\n"+
                ", serviceCharge=" + serviceCharge +"\n"+
                ", raxFlag=" + raxFlag +"\n"+
                ", tax=" + tax +"\n"+
                ", status=" + status +"\n"+
                ", packages=" + packages +"\n"+
                '}';
    }
}
