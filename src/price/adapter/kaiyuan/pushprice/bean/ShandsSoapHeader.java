package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by he.chen on 14-10-27.
 * version $Id$
 */
public class ShandsSoapHeader {
    private String sessionId;
    private String channel;
    private String resultCode;
    private String errorReason;

    @XmlElement(name = "SessionId",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @XmlElement(name = "Channel",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @XmlElement(name = "ResultCode",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @XmlElement(name = "ErrorReason",namespace = XMLNamespace.kaiyuanNamespaceUrl)
    public String getErrorReason() {
        return errorReason;
    }

    public void setErrorReason(String errorReason) {
        this.errorReason = errorReason;
    }

    @Override
    public String toString() {
        return "ShandsSoapHeader{" +
                "sessionId='" + sessionId + '\'' +
                ", channel='" + channel + '\'' +
                ", resultCode='" + resultCode + '\'' +
                ", errorReason='" + errorReason + '\'' +
                '}';
    }
}
