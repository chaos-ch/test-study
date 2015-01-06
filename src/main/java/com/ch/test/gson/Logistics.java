package com.ch.test.gson;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ch.test.json.LogisticsData;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Created by he.chen on 14-12-11.
 */
public class Logistics implements Serializable{
    private static final long serialVersionUID = -1881283296748922231L;
    private boolean ret = false;
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private String errmsg;
    private int errcode;
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)

    /** 快递公司名称 **/
    private String expressCompanyName;

    /**快递单号**/
    private String expressNum;

    private Date time = new Date();

    private List<LogisticsData> logisticsDataList;

    public Logistics() {
    }

    public Logistics(String errmsg) {
        this.ret = false;
        this.errmsg = errmsg;
    }

    public Logistics(String expressCompanyName, String expressNum, List<LogisticsData> logisticsDataList) {
        this.ret = true;
        this.expressCompanyName = expressCompanyName;
        this.expressNum = expressNum;
        this.logisticsDataList = logisticsDataList;
    }

    public static Logistics returnFailure(String errmsg){
        return new Logistics(errmsg);
    }

    public static Logistics returnLogistics(String expressCompanyName, String expressNum, List<LogisticsData> logisticsDataList){

        return new Logistics(expressCompanyName, expressNum, logisticsDataList);
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getExpressCompanyName() {
        return expressCompanyName;
    }

    public void setExpressCompanyName(String expressCompanyName) {
        this.expressCompanyName = expressCompanyName;
    }

    public String getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum;
    }

    public List<LogisticsData> getLogisticsDataList() {
        return logisticsDataList;
    }

    public void setLogisticsDataList(List<LogisticsData> logisticsDataList) {
        this.logisticsDataList = logisticsDataList;
    }
}
