package com.ch.test;

import java.io.Serializable;
import java.util.Date;

/**
 * @author binyu.zhan created on 13-12-2 下午4:59
 * @version $Id: APIResponse.java 225837 2014-02-11 07:39:01Z rongqian.xu $
 */
public class APIResponse<T> implements Serializable {

    private static final long serialVersionUID = 5241526151768786394L;

    private final String ver = "1.0";
    private boolean ret;
    private String errmsg;
    private int errcode;
    private T data;
    private Date time = new Date();

    public APIResponse() {
    }

    private APIResponse(T t) {
        this.ret = true;
        this.data = t;
        this.errcode = 0;
    }

    private APIResponse(String errmsg, T t) {
        this.ret = false;
        this.errmsg = errmsg;
        this.data = t;
        this.errcode = -1;
    }

    private APIResponse(int errcode, String errmsg, T t) {
        this.ret = false;
        this.errmsg = errmsg;
        this.errcode = errcode;
        this.data = t;
    }

    public static <T> APIResponse<T> returnSuccess() {
        return new APIResponse<T>(null);
    }

    public static <T> APIResponse<T> returnSuccess(T t) {
        return new APIResponse<T>(t);
    }

    public static <T> APIResponse<T> returnFail(String errmsg) {
        return new APIResponse<T>(errmsg, null);
    }

    public static <T> APIResponse<T> returnFail(String errmsg, T t) {
        return new APIResponse<T>(errmsg, t);
    }

    public static <T> APIResponse<T> returnFail(int errcode, String errmsg, T t) {
        return new APIResponse<T>(errcode, errmsg, t);
    }

    public static <T> APIResponse<T> returnFail(int errcode, String errmsg) {
        return new APIResponse<T>(errcode, errmsg, null);
    }

    public String getVer() {
        return ver;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        APIResponse that = (APIResponse) o;

        if (errcode != that.errcode)
            return false;
        if (ret != that.ret)
            return false;
        if (data != null ? !data.equals(that.data) : that.data != null)
            return false;
        if (errmsg != null ? !errmsg.equals(that.errmsg) : that.errmsg != null)
            return false;
        if (ver != null ? !ver.equals(that.ver) : that.ver != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ver != null ? ver.hashCode() : 0;
        result = 31 * result + (ret ? 1 : 0);
        result = 31 * result + (errmsg != null ? errmsg.hashCode() : 0);
        result = 31 * result + errcode;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
