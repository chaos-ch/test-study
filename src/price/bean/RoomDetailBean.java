package com.qunar.hotel.sa.product.price.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class RoomDetailBean implements Serializable {
    private static final long serialVersionUID = 907568171766687029L;

    public enum PriceStatus {
        Y, N;
    }

    private long id;
    private int roomId;
    private BigDecimal price = new BigDecimal("-1");
    /**报价的额外描述 两种价格的拼接 门市价：活动价**/
    private String ext = StringUtils.EMPTY;

    //此报价的创建时间
    Date createTime;
    //此报价的最后更新时间
    Date updateTime;
    private int cashBack = 0;
    private int priceCut = 0;
    private PriceStatus status = PriceStatus.N;
    private int count = 0;
    private String date;
    private int consume = 0;
    private Date dateTime;

    private int fixedPrice = -1;
    private PriceStatus fixedPriceStatus = PriceStatus.N;
    private int fixedPriceConsume = 0;
    private String closeRoomTime = StringUtils.EMPTY;
    /**
     * 接口代理商使用
     */
    private int breakfast;
    private String breakfastStr;
    private BigDecimal deposit;
    private Date batchUpdateTime;

    public RoomDetailBean() {
        super();
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public PriceStatus getStatus() {
        return status;
    }

    public void setStatus(PriceStatus status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // public Date getDate() {
    // return date;
    // }
    // public void setDate(Date date) {
    // this.date = date;
    // }
    public int getConsume() {
        return consume;
    }

    public void setConsume(int consume) {
        this.consume = consume;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getCashBack() {
        return cashBack;
    }

    public void setCashBack(int cashBack) {
        this.cashBack = cashBack;
    }

    public int getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(int fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public PriceStatus getFixedPriceStatus() {
        return fixedPriceStatus;
    }

    public void setFixedPriceStatus(PriceStatus fixedPriceStatus) {
        this.fixedPriceStatus = fixedPriceStatus;
    }

    public int getFixedPriceConsume() {
        return fixedPriceConsume;
    }

    public void setFixedPriceConsume(int fixedPriceConsume) {
        this.fixedPriceConsume = fixedPriceConsume;
    }

    public String getCloseRoomTime() {
        return closeRoomTime;
    }

    public void setCloseRoomTime(String closeRoomTime) {
        this.closeRoomTime = closeRoomTime;
    }

    public int getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public String getBreakfastStr() {
        return breakfastStr;
    }

    public void setBreakfastStr(String breakfastStr) {
        this.breakfastStr = breakfastStr;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getBatchUpdateTime() {
        return batchUpdateTime;
    }

    public void setBatchUpdateTime(Date batchUpdateTime) {
        this.batchUpdateTime = batchUpdateTime;
    }

    public int getPriceCut() {
        return priceCut;
    }

    public void setPriceCut(int priceCut) {
        this.priceCut = priceCut;
    }
}
