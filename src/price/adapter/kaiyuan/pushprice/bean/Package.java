package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by he.chen on 14-10-27.
 * version $Id$
 */
public class Package {
    private String code;
    private String name;

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

    @Override
    public String toString() {
        return "Package{" +"\n"+
                "code='" + code + '\'' +"\n"+
                ", name='" + name + '\'' +"\n"+
                '}';
    }
}
