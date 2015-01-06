package com.ch.test.json;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * QInvoice发票系统流程状态枚举
 *
 * Created by xu.cao on 2014/10/27.
 */
public enum QInvoiceProcessState {

    APPLY_NOT_SUCCESS(100, "未向发票系统提交申请或未申请成功"), APPLY_SUCCESS(101, "已经成功提交申请"),
    APPLY_FAILED(103, "向发票系统提交申请失败"),

    INVOICE_NOT_MADE(0, "未开票"), INVOICE_HAS_MADE(1, "已开票"), IN_PRINTING(2, "打印中"), HAS_CHECKED(3, "已审核"), HAS_EXPORTED(
            4, "已导出"), WROTE_OFF(5, "已冲红"), BROKE_UP(6, "已拆分"), HAS_MERGED(7, "已合并"), HAS_ASSOCIATED(8, "已关联"), WROTE_BACK(
            9, "已回写"), VOIDED_INVOICE(10, "已作废"), HAS_REJECTED(11, "已驳回"), HAS_REPEALED(12, "已撤销"),
    /** QInvoice不直接返回13.当返回状态为1，且返回结果中含有快递信息时，为13 **/
    HAS_EXPRESSED(13, "已快递");


    final static List<QInvoiceProcessState> finalState;

    static {//初始化发票终态
        List<QInvoiceProcessState> tmp = new ArrayList<QInvoiceProcessState>();
        tmp.add(HAS_REJECTED);
        tmp.add(HAS_REPEALED);
        tmp.add(HAS_EXPRESSED);
        finalState = Collections.unmodifiableList(tmp);
    }

    /**
     * 根据状态判断是否已经提交成功
     * @return boolean
     */
    public boolean pushSuccess() {
        return !(this == APPLY_NOT_SUCCESS || this == APPLY_FAILED);
    }

    public static QInvoiceProcessState defaultValue() {
        return APPLY_NOT_SUCCESS;
    }

    /**
     * 获取发票终态列表
     * @return List
     */
    public static List<QInvoiceProcessState> finalState() {
        return finalState;
    }

    public static QInvoiceProcessState of(int code) {
        for (QInvoiceProcessState state : QInvoiceProcessState.values()) {
            if (code == state.code) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid QInvoiceProcessState code: " + code);
    }

    public static QInvoiceProcessState of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("QInvoiceProcessState name is null");
        }
        for (QInvoiceProcessState state : QInvoiceProcessState.values()) {
            if (state.name().equals(name)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid QInvoiceProcessState name:" + name);
    }

    private int code;
    private String desc;

    private QInvoiceProcessState(int code, String desc) {
        this.code = code;
        this.desc = desc;
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
