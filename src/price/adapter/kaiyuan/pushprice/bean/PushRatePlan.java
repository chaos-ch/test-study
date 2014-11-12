package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

/**
 * Created by he.chen on 14-10-27.
 * version $Id$
 */
public class PushRatePlan {
    private List<RatePlan> ratePlans;

    @XmlElementWrapper(name = "ratePlans",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    @XmlElement(name = "RatePlan",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public List<RatePlan> getRatePlans() {
        return ratePlans;
    }

    public void setRatePlans(List<RatePlan> ratePlans) {
        this.ratePlans = ratePlans;
    }

    @Override
    public String toString() {
        return "PushRatePlan{" +"\n" +
                "ratePlans=" + ratePlans +"\n" +
                '}';
    }
}
