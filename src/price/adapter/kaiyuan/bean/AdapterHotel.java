package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 酒店基本信息
 * 
 * @author weiming.liao
 * 
 */
public class AdapterHotel implements Serializable {

    private static final long serialVersionUID = -509075219990620690L;
    private int id;
    /** 城市编号 **/
    private String cityUrl;
    /** 酒店名称 **/
    private String hotelName;
    /** 酒店地址 **/
    private String hotelAddress;
    /** 酒店电话 **/
    private String hotelTel;
    /** 酒店seq **/
    private String seq;
    /** 退订说明 **/
    private String cancellation;
    /** 报销凭证 **/
    private String reimbursement;
    /** 特殊说明 **/
    private String specialRemark;
    /** 优惠信息 **/
    private String preference;
    /** 酒店在代理商处的id **/
    private String otaHotelId;
    /** 返现比例配置 */
    private String cashBackSet;

    /** 出发前须知或其他入住要求 */
    private String otherSpecialRemark;

    private String checkInTime;
    private String checkOutTime;

    private List<AdapterRoom> rooms;

    private String contractStatus;
    private Date contractExpiredTime;

    /**
     * 报价不展示的错误信息
     */
    private List<ErrorMessageInfo> errorMessageInfos;
    
    public String getOtherSpecialRemark() {
        return otherSpecialRemark;
    }

    public void setOtherSpecialRemark(String otherSpecialRemark) {
        this.otherSpecialRemark = otherSpecialRemark;
    }

    public static enum WapperPriceErrorCode {
        OK, CONNECT_TIMEOUT, READ_TIMEOUT, TIMEOUT, UNKOWN;
    }

    WapperPriceErrorCode wapperPriceErrorCode = WapperPriceErrorCode.OK;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityUrl() {
        return cityUrl;
    }

    public void setCityUrl(String cityUrl) {
        this.cityUrl = cityUrl;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelTel() {
        return hotelTel;
    }

    public void setHotelTel(String hotelTel) {
        this.hotelTel = hotelTel;
    }

    public String getCancellation() {
        return cancellation;
    }

    public void setCancellation(String cancellation) {
        this.cancellation = cancellation;
    }

    public String getReimbursement() {
        return reimbursement;
    }

    public void setReimbursement(String reimbursement) {
        this.reimbursement = reimbursement;
    }

    public String getSpecialRemark() {
        return specialRemark;
    }

    public void setSpecialRemark(String specialRemark) {
        this.specialRemark = specialRemark;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getOtaHotelId() {
        return otaHotelId;
    }

    public void setOtaHotelId(String otaHotelId) {
        this.otaHotelId = otaHotelId;
    }

    public String getCashBackSet() {
        return cashBackSet;
    }

    public void setCashBackSet(String cashBackSet) {
        this.cashBackSet = cashBackSet;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public Date getContractExpiredTime() {
        return contractExpiredTime;
    }

    public void setContractExpiredTime(Date contractExpiredTime) {
        this.contractExpiredTime = contractExpiredTime;
    }

    public WapperPriceErrorCode getWapperPriceErrorCode() {
        return wapperPriceErrorCode;
    }

    public void setWapperPriceErrorCode(WapperPriceErrorCode wapperPriceErrorCode) {
        this.wapperPriceErrorCode = wapperPriceErrorCode;
    }

    
    public List<AdapterRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<AdapterRoom> rooms) {
        this.rooms = rooms;
    }

    public List<ErrorMessageInfo> getErrorMessageInfos() {
        return errorMessageInfos;
    }

    public void setErrorMessageInfos(List<ErrorMessageInfo> errorMessageInfos) {
        this.errorMessageInfos = errorMessageInfos;
    }

    @Override
    public String toString() {
        return "AdapterHotel [id=" + id + ", cityUrl=" + cityUrl + ", hotelName=" + hotelName + ", hotelAddress="
                + hotelAddress + ", hotelTel=" + hotelTel + ", cancellation=" + cancellation + ", reimbursement="
                + reimbursement + ", specialRemark=" + specialRemark + ", preference=" + preference + ",checkInTime="
                + checkInTime + ",checkOutTime=" + checkOutTime +",contractStatus="+contractStatus+",contractExpiredTIme="
                + contractExpiredTime+",errorMessageInfos="+ errorMessageInfos + "]";
    }

}
