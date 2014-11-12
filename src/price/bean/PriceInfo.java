package com.qunar.hotel.sa.product.price.bean;

import com.qunar.hotel.sa.common.util.SysConfigUtil;
import com.qunar.hotel.sa.product.price.bean.RoomInfo.*;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

public class PriceInfo {
    /**
     * 酒店id
     */
    private int hotelId;
    private String roomId;
    /**
     * 日期
     */
    private Date date;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 外币价格
     */
    private BigDecimal forexMoney;
    /**
     * 保证金
     */
    private BigDecimal deposit;
    /**
     * 1满房，0空房
     */
    private int status;
    /**
     * 房量
     */
    private int count;
    /**
     * 返现金额
     */
    private int cashBack;

    /**
     * 佣金
     */
    private double commission;
    /** 货币代码 */
    private String currencyCode;
    /** 早餐 **/
    /** 早餐 **/
    private RoomInfo.Breakfast breakfast = Breakfast.NONE;
    private String breakfastStr = SysConfigUtil.BREAKFASTNONE;
    /** 折扣（夜销） */
    private int discount;

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getForexMoney() {
        return forexMoney;
    }

    public void setForexMoney(BigDecimal forexMoney) {
        this.forexMoney = forexMoney;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCashBack() {
        return cashBack;
    }

    public void setCashBack(int cashBack) {
        this.cashBack = cashBack;
    }

    public RoomInfo.Breakfast getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(RoomInfo.Breakfast breakfast) {
        this.breakfast = breakfast;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public void setBreakfastStr(String breakfastStr) {
        this.breakfastStr = breakfastStr;
    }

    public String getBreakfastStr() {
        return breakfastStr;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
