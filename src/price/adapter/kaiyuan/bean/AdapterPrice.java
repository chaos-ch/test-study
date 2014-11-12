package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.AdapterRoom.PayType;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.AdapterRoom.*;

import org.apache.commons.lang.StringUtils;


/**
 * 报价信息
 * 
 * @author weiming.liao
 * 
 */
public class AdapterPrice implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 2504494910759164090L;
    /** 酒店ID **/
    private int hotelId;
    /** 房型ID **/
    private String roomId;
    /** 时间 **/
    private Date date;
    /** 价格 **/
    private BigDecimal price;
    /** 外币价格 **/
    private BigDecimal forexMoney;
    /** 剩余房量 **/
    private int count;
    /** 房态 **/
    private Status status = Status.Disabled;
    /** 支付 **/
    private PayType payType = PayType.ONLINE;
    /** 保留字段 **/
    private int temp;
    /** 返现 */
    private int cashBack;
    /** 直减 */
    private int priceCut;
    /** 佣金 */
    private double commission;

    /** 早餐 **/
    private BreadType breakfast;
    private String breakfastStr;
    /** 折扣 */
    private int discountAmount;

    /** 自动关房时间*/
    private String closeRoomTime = StringUtils.EMPTY;

    public BreadType getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(BreadType breakfast) {
        this.breakfast = breakfast;
    }

    public String getBreakfastStr() {
        return breakfastStr;
    }

    public void setBreakfastStr(String breakfastStr) {
        this.breakfastStr = breakfastStr;
    }

    public BigDecimal getForexMoney() {
        return forexMoney;
    }

    public void setForexMoney(BigDecimal forexMoney) {
        this.forexMoney = forexMoney;
    }

    /** 预付定金 */
    private BigDecimal deposit;
    /** 货币代码 */
    private String currencyCode;

    /**
     * 房间状态
     * 
     * @author weiming.liao
     * 
     */
    public static enum Status {
        /** 可用、开房 code:0 **/
        Enabled(0),
        /** 需确认 code:2 **/
        Confirm(2),
        /** 禁用、关房 code:1 **/
        Disabled(1);

        private int code;

        private Status(int code) {

        }

        public int getCode() {
            return this.code;
        }
    }

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    /** 如果报价为零展示关房 **/
    public Status getStatus() {
        if (this.price !=null && this.price.compareTo(new BigDecimal("0.00")) <= 0) {
            this.status = Status.Disabled;
        }
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public int getCashBack() {
        return cashBack;
    }

    public void setCashBack(int cashBack) {
        this.cashBack = cashBack;
    }

    public int getPriceCut() {
        return priceCut;
    }

    public void setPriceCut(int priceCut) {
        this.priceCut = priceCut;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getCloseRoomTime() {
        return closeRoomTime;
    }

    public void setCloseRoomTime(String closeRoomTime) {
        this.closeRoomTime = closeRoomTime;
    }

    @Override
    public String toString() {
        return "AdapterPrice [hotelId=" + hotelId + ", roomId=" + roomId + ", date=" + date + ", price=" + price
                + ", count=" + count + ", status=" + status + ", payType=" + payType + ", temp=" + temp + ", cashBack="
                + cashBack + ", breakfast=" + breakfast + ", discountAmount =" + discountAmount + "]";
    }
}
