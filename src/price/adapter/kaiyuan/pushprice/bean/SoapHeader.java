package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by he.chen on 14-10-27.
 * version $Id$
 */
public class SoapHeader {
    private ShandsSoapHeader shandsSoapHeader;

    @XmlElement(name = "ShandsSoapHeader",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public ShandsSoapHeader getShandsSoapHeader() {
        return shandsSoapHeader;
    }

    public void setShandsSoapHeader(ShandsSoapHeader shandsSoapHeader) {
        this.shandsSoapHeader = shandsSoapHeader;
    }

    @Override
    public String toString() {
        return "SoapHeader{" +
                "shandsSoapHeader=" + shandsSoapHeader +
                '}';
    }
}
