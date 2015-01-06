package com.ch.test.json;

/**
 * 向终端用户展示的发票流程状态
 *
 * Created by xu.cao on 2014/10/28.
 */
public enum UserInvoiceState {
    INVOICE_NOT_MADE(0, "未开票"),
    INVOICE_MADE_NOT_EXPRESSED(1, "已开票但未寄出"),
    INVOICE_MADE_AND_EXPRESSED(2, "已寄出");

    /** code */
    private int code;

    /** 描述 */
    private String desc;

    private UserInvoiceState(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static UserInvoiceState defaultValue() {
        return INVOICE_NOT_MADE;
    }

    public static UserInvoiceState of(int code){
        for(UserInvoiceState state : UserInvoiceState.values()){
            if(state.code == code){
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid UserInvoiceState code: " + code);
    }

    public static UserInvoiceState of(String _name){
        if (_name == null) {
            throw new IllegalArgumentException("UserInvoiceState name is null");
        }
        String name = _name.toUpperCase().trim();
        for(UserInvoiceState state : UserInvoiceState.values()){
            if(state.name().equals(name)){
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid UserInvoiceState name: " + name);
    }

    /**
     * 给用户展示的内容
     * @param name 枚举的名字
     * @return String 展示的内容
     */
    public static String showOf(String name) {
        return of(name).desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
