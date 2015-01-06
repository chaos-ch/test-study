package com.ch.test.gson;

import java.io.Serializable;

/**
 * @author he.chen created on 14-12-23.
 * @version $Id$
 */
public class ApiResponse<T> implements Serializable{
    private boolean ret;
    private T data;

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
