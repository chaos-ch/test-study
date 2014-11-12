package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by he.chen on 14-10-27.
 * xiaoxiong.li $
 */
public class ChannelLoginResponse {
    private boolean channelLoginResult;

    @XmlElement(name = "ChannelLoginResult", namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public boolean getChannelLoginResult() {
        return channelLoginResult;
    }

    public void setChannelLoginResult(boolean channelLoginResult) {
        this.channelLoginResult = channelLoginResult;
    }

    @Override
    public String toString() {
        return "ChannelLoginResponse{" + "channelLoginResult='" + channelLoginResult + '\'' + '}';
    }
}
