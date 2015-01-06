package com.ch.test.gson;

import java.io.Serializable;

/**
 * ucenter物流内容
 * Created by he.chen on 14-12-5.
 */
public class UserInvoiceLogisticsItem implements Serializable{
    private static final long serialVersionUID = -266506181361998156L;
    /**
     * 物流到达内容
     */
    private String content;
    /**
     * 时间
     */
    private String time;

    /**
     * 签收状态
     */
    private String status;
    /**
     * 行政区域的编码
     */
    private String areaCode;

    /**
     * 本数据元对应的行政区域的名称
     */
    private String areaName;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @Override
    public String toString() {
        return "LogisticsData{" +
                "content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", status='" + status + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", areaName='" + areaName + '\'' +
                '}';
    }
}
