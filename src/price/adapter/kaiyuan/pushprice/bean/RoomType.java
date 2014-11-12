package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by he.chen on 14-10-27.
 * version $Id$
 */
public class RoomType {
    private int area;//int合适吗？
    private int number;
    private String code;
    private String name;
    private Date created;
    private Date modified;
    private int status;//int合适吗？

    @XmlElement(name = "Area",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @XmlElement(name = "Number",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    @XmlElement(name = "Code",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @XmlElement(name = "Name",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @XmlElement(name = "Created",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
    @XmlElement(name = "Modified",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
    @XmlElement(name = "Status",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RoomType{" + "\n" +
                "area=" + area + "\n" +
                ", number=" + number +"\n" +
                ", code='" + code + '\'' +"\n" +
                ", name='" + name + '\'' +"\n" +
                ", created=" + created +"\n" +
                ", modified=" + modified +"\n" +
                ", status=" + status +"\n" +
                '}';
    }
}
