package com.qunar.hotel.sa.product.hotel.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.AdapterHotel;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.ErrorLevel;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.ErrorMessageInfo;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.PriceValidatorCode;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.validator.constraints.Length;

import com.qunar.hotel.sa.product.price.bean.RoomInfo;

public class HotelInfo {

    private int id;
    @NotNull(message = "hotel id 不能为空")
    @Length(min = 1, max = 16, message = "hotel id 长度不在允许范围内，min：1,max：16")
    private String otaHotelId;
    private InterfaceType interfaceType;
    @NotNull(message = "酒店名称不能为空")
    @Length(min = 1, max = 100, message = "酒店名称长度不在允许范围内，min：1,max：100")
    private String hotelName;
    // @NotNull(message = "城市不能为空")
    private String cityUrl = "";

    @NotNull(message = "酒店地址不能为空")
    @Length(min = 1, max = 100, message = "酒店地址长度不在允许范围内，min：1,max：100")
    private String hotelAddress;
    // @NotNull(message = "酒店电话不能为空")
    @Length(max = 50, message = "酒店电话长度不在允许范围内，max：50")
    private String hotelTel;
    private String seq;
    /** 报销凭证 **/
    @Length(max = 1000, message = "报销凭证长度不在允许范围内,max：1000")
    private String reimbursement;
    /** 特殊说明 **/
    @Length(max = 1000, message = "特殊说明长度不在允许范围内,max：1000")
    private String specialRemark;
    private Status status = Status.ACTIVE;
    /** 优惠信息 **/
    @Length(max = 1000, message = "优惠信息长度不在允许范围内,max：1000")
    private String preference;
    private int otaId;
    /** 该酒店的返现配置 */
    private String cashBackSet;

    /** 出发前须知或其他入住要求 */
    private String otherSpecialRemark;

    /** 入住开始办理时间 */
    private String checkInTime;
    /** 离店开始办理时间 */
    private String checkOutTime;

    private String countryCode;
    // 经度
    private double latitude;
    // 经度
    private double longitude;

    private long changeTime;

    private List<RoomInfo> roomInfo;

    // 扩展字段
    private String ext;

    private AdapterHotel.WapperPriceErrorCode wapperPriceErrorCode;

    /**
     * 酒店调整时间，单位：分钟
     */
    private int adjustCacheTime;

    // 城市id
    private String cityId = "";

    /** 代理商酒店url */
    private String otaHotelDetailUrl = "";

    private int bookingType;

    /**
     * 校验错误信息
     */
    private List<ErrorMessageInfo> errorMessageInfos;

    /**
     * 酒店合同状态,默认未上传
     */
    private HotelContract.ContractStatus contractStatus;

    private String cancellation;

    private Date contractExpiredTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOtaHotelId() {
        return otaHotelId;
    }

    public void setOtaHotelId(String otaHotelId) {
        this.otaHotelId = otaHotelId;
    }

    public InterfaceType getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(InterfaceType interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCityUrl() {
        return cityUrl;
    }

    public void setCityUrl(String cityUrl) {
        this.cityUrl = cityUrl;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public int getOtaId() {
        return otaId;
    }

    public void setOtaId(int otaId) {
        this.otaId = otaId;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public List<RoomInfo> getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(List<RoomInfo> roomInfo) {
        this.roomInfo = roomInfo;
    }

    public List<RoomInfo> getRooms() {
        return roomInfo;
    }

    public void setRooms(List<RoomInfo> roomInfo) {
        this.roomInfo = roomInfo;
    }

    public long getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(long changeTime) {
        this.changeTime = changeTime;
    }

    public String getCashBackSet() {
        return cashBackSet;
    }

    public void setCashBackSet(String cashBackSet) {
        this.cashBackSet = cashBackSet;
    }

    public String getOtherSpecialRemark() {
        return otherSpecialRemark;
    }

    public void setOtherSpecialRemark(String otherSpecialRemark) {
        this.otherSpecialRemark = otherSpecialRemark;
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

    public enum Status {
        ACTIVE, DELETED;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String toHotelString() {
        StringBuilder builder = new StringBuilder();
        builder.append(otaHotelId).append(cityId).append(hotelName).append(cityUrl).append(hotelAddress)
                .append(hotelTel).append(reimbursement).append(specialRemark).append(preference).append(checkInTime)
                .append(checkOutTime).append(countryCode).append(latitude).append(longitude).append(ext)
                .append(otaHotelDetailUrl).append(bookingType);
        return builder.toString();
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public int getAdjustCacheTime() {
        return adjustCacheTime;
    }

    public void setAdjustCacheTime(int adjustCacheTime) {
        this.adjustCacheTime = adjustCacheTime;
    }

    public String getOtaHotelDetailUrl() {
        return otaHotelDetailUrl;
    }

    public void setOtaHotelDetailUrl(String otaHotelDetailUrl) {
        this.otaHotelDetailUrl = otaHotelDetailUrl;
    }

    public int getBookingType() {
        return bookingType;
    }

    public void setBookingType(int bookingType) {
        this.bookingType = bookingType;
    }

    public AdapterHotel.WapperPriceErrorCode getWapperPriceErrorCode() {
        return wapperPriceErrorCode;
    }

    public void setWapperPriceErrorCode(AdapterHotel.WapperPriceErrorCode wapperPriceErrorCode) {
        this.wapperPriceErrorCode = wapperPriceErrorCode;
    }

    public List<ErrorMessageInfo> getErrorMessageInfos() {
        return errorMessageInfos;
    }

    public void setErrorMessageInfos(List<ErrorMessageInfo> errorMessageInfos) {
        this.errorMessageInfos = errorMessageInfos;
    }
    public void setErrorMessage (HotelInfo hotelInfo,PriceValidatorCode priceValidatorCode,ErrorLevel errorLevel,String roomId,String roomName) {
        if (hotelInfo == null) {
            return;
        }
        if (CollectionUtils.isEmpty(hotelInfo.getErrorMessageInfos())) {
            hotelInfo.setErrorMessageInfos(new ArrayList<ErrorMessageInfo>());
        }
        hotelInfo.getErrorMessageInfos().add(new ErrorMessageInfo(priceValidatorCode,roomId,roomName));

    }

    public HotelContract.ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(HotelContract.ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getCancellation() {
        return cancellation;
    }

    public void setCancellation(String cancellation) {
        this.cancellation = cancellation;
    }

    public Date getContractExpiredTime() {
        return contractExpiredTime;
    }

    public void setContractExpiredTime(Date contractExpiredTime) {
        this.contractExpiredTime = contractExpiredTime;
    }
}
