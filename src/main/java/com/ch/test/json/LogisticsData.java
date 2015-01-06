package com.ch.test.json;

import java.io.Serializable;

/**
 * Created by he.chen on 14-12-5.
 */
public class LogisticsData implements Serializable {
    private static final long serialVersionUID = -2814751221802743781L;
    /**
     * 物流到达内容
     */
    private String content;
    /**
     * 时间
     */
    private String time;

    /**
     * 本数据元对应的行政区域的名称
     */
    private String areaName;

    /**
     * 行政区域的编码
     */
    private String areaCode;

    /**
     * 签收状态
     */
    private String status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
