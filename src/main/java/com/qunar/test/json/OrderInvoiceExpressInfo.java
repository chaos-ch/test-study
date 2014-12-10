package com.qunar.test.json;

import com.google.common.base.Objects;
import com.qunar.hotel.qta.base.money.Money;

import java.io.Serializable;
import java.util.List;


/**
 * 发票配送快递信息
 *
 * Created by he.chen on 14-11-27.
 */

public class OrderInvoiceExpressInfo implements Serializable {


    private static final long serialVersionUID = 2440565419841013863L;
    /** 联系人姓名 **/
    private String contactName;

    /** 联系人电话 **/
    private String phone;

    /** 省份 **/
    private String province;

    /** 城市 **/
    private String city;

    /** 区县 **/
    private String area;

    /** 发票配送地址 **/
    private String address;

    /** 用户支付的邮递费用 默认为0元 **/
    private Money fee = InvoiceDispatchType.BY_HOTEL.fee();


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Money getFee() {
        return fee;
    }

    public void setFee(Money fee) {
        this.fee = fee;
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("contactName", contactName)
                .add("phone", phone)
                .add("province", province)
                .add("city", city)
                .add("area", area)
                .add("address", address)
                .add("fee", fee)
                .toString();
    }
}
