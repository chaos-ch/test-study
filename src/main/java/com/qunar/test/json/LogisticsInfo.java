package com.qunar.test.json;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by he.chen on 14-11-27.
 */
public class LogisticsInfo implements Serializable{
    private static final long serialVersionUID = -8436916697397589526L;
    /**物流节点内容**/
    private String content;
    /**到达物流节点时间**/
    private Date time;
    /**物流节点状态**/
    private String status;
    /**行政区域的编码**/
    private String areaCode;
    /**行政区域的名称**/
    private String areaName;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
}
