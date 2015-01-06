package com.ch.test.gson;

import java.io.Serializable;
import java.util.List;

/**
 * ucenter 接口发票物流信息
 * @author he.chen created on 14-12-25.
 * @version $Id$
 */
public class UserInvoiceLogistics implements Serializable{

    private static final long serialVersionUID = -7329813272228508334L;
    /** 快递公司名称 */
    private String expressCompanyName;

    /** 快递单号 */
    private String expressNo;

    /** 发票物流 **/
    private List<UserInvoiceLogisticsItem> userInvoiceLogisticsItemList;

    public String getExpressCompanyName() {
        return expressCompanyName;
    }

    public void setExpressCompanyName(String expressCompanyName) {
        this.expressCompanyName = expressCompanyName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public List<UserInvoiceLogisticsItem> getUserInvoiceLogisticsItemList() {
        return userInvoiceLogisticsItemList;
    }

    public void setUserInvoiceLogisticsItemList(List<UserInvoiceLogisticsItem> userInvoiceLogisticsItemList) {
        this.userInvoiceLogisticsItemList = userInvoiceLogisticsItemList;
    }
}
