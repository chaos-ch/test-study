package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by he.chen on 14-10-27.
 * version $Id$
 */
@XmlRootElement(name = "Envelope",namespace = XMLNamespace.soapNamespaceUrl)
public class SoapEnvelope {
    private SoapHeader soapHeader;
    private SoapBody soapBody;

    @XmlElement(name = "Header",namespace = XMLNamespace.soapNamespaceUrl)
    public SoapHeader getSoapHeader() {
        return soapHeader;
    }

    public void setSoapHeader(SoapHeader soapHeader) {
        this.soapHeader = soapHeader;
    }

    @XmlElement(name = "Body",namespace = XMLNamespace.soapNamespaceUrl)
    public SoapBody getSoapBody() {
        return soapBody;
    }

    public void setSoapBody(SoapBody soapBody) {
        this.soapBody = soapBody;
    }

    @Override
    public String toString() {
        return "SoapEnvelope{" +
                "soapHeader=" + soapHeader +
                ", soapBody=" + soapBody +
                '}';
    }
}
