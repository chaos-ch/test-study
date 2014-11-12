package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Created by he.chen on 14-10-27.
 * version $Id$
 */
public class RatePlan {
    private String code;
    private String Name;
    private int MinLos;
    private int MaxLos;
    private int AdvBookin;
    private String Des;
    private boolean IsPrepay;
    private BigDecimal basePrice;
    private String ShortInfo;
    private String LongInfo;
    private String HotelCode;
    private List<Package> packages;
    private List<RatePlanDetail> ratePlanDetails;

    @XmlElement(name = "Code",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlElement(name = "Name",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @XmlElement(name = "MinLos",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public int getMinLos() {
        return MinLos;
    }

    public void setMinLos(int minLos) {
        MinLos = minLos;
    }

    @XmlElement(name = "MaxLos",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public int getMaxLos() {
        return MaxLos;
    }

    public void setMaxLos(int maxLos) {
        MaxLos = maxLos;
    }

    @XmlElement(name = "AdvBookin",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public int getAdvBookin() {
        return AdvBookin;
    }

    public void setAdvBookin(int advBookin) {
        AdvBookin = advBookin;
    }

    @XmlElement(name = "Des",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getDes() {
        return Des;
    }

    public void setDes(String des) {
        Des = des;
    }

    @XmlElement(name = "IsPrepay",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public boolean isPrepay() {
        return IsPrepay;
    }

    public void setPrepay(boolean isPrepay) {
        IsPrepay = isPrepay;
    }

    @XmlElement(name = "BasePrice",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    @XmlElement(name = "ShortInfo",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getShortInfo() {
        return ShortInfo;
    }

    public void setShortInfo(String shortInfo) {
        ShortInfo = shortInfo;
    }

    @XmlElement(name = "LongInfo",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getLongInfo() {
        return LongInfo;
    }

    public void setLongInfo(String longInfo) {
        LongInfo = longInfo;
    }

    @XmlElement(name = "HotelCode",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getHotelCode() {
        return HotelCode;
    }

    public void setHotelCode(String hotelCode) {
        HotelCode = hotelCode;
    }

    @XmlElementWrapper(name = "Packages",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    @XmlElement(name = "Package",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    @XmlElementWrapper(name = "ratePlanDetails",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    @XmlElement(name = "RatePlanDetail",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public List<RatePlanDetail> getRatePlanDetails() {
        return ratePlanDetails;
    }

    public void setRatePlanDetails(List<RatePlanDetail> ratePlanDetails) {
        this.ratePlanDetails = ratePlanDetails;
    }

    @Override
    public String toString() {
        return "RatePlan{" +"\n" +
                "code='" + code + '\'' +"\n" +
                ", Name='" + Name + '\'' +"\n" +
                ", MinLos=" + MinLos +"\n" +
                ", MaxLos=" + MaxLos +"\n" +
                ", AdvBookin=" + AdvBookin +"\n" +
                ", Des='" + Des + '\'' +"\n" +
                ", IsPrepay=" + IsPrepay +"\n" +
                ", basePrice=" + basePrice +"\n" +
                ", ShortInfo='" + ShortInfo + '\'' +"\n" +
                ", LongInfo='" + LongInfo + '\'' +"\n" +
                ", HotelCode='" + HotelCode + '\'' +"\n" +
                ", packages='" + packages + '\'' +"\n" +
                ", ratePlanDetails=" + ratePlanDetails +"\n" +
                '}';
    }
}
