package com.ch.test.json;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by he.chen on 14-11-28.
 */
/**
 * Wrapper信息
 *
 */
public class WrapperInfo {

    private static final long serialVersionUID = 5171694111901895631L;

    /** wrapper Id */
    private String wrapperId;

    /** 有效开始时间 */
    private Date validStart;

    /** 图标 URL */
    private String logoUrl;

    /** 代理商名称 */
    private String logoText;

    public String getWrapperId() {
        return wrapperId;
    }

    public void setWrapperId(String wrapperId) {
        this.wrapperId = wrapperId;
    }

    public Date getValidStart() {
        return validStart;
    }

    public void setValidStart(Date validStart) {
        this.validStart = validStart;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoText() {
        return logoText;
    }

    public void setLogoText(String logoText) {
        this.logoText = logoText;
    }
}

