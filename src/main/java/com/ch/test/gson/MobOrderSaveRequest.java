/*
 * Copyright (c) 2013 Qunar.com. All Rights Reserved.
 */
package com.ch.test.gson;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.ch.test.json.MobGuaranteeRule;
import org.codehaus.jackson.annotate.JsonProperty;


/**
 * 下订单的一些参数
 * 
 * @author rongqian.xu created on 3/12/14 7:44 PM
 * @version $Id$
 */
public class MobOrderSaveRequest implements Serializable {
    private static final long serialVersionUID = -6297165710921305789L;
    /** 渠道 */
    private String from;
    /** wrapperId */
    private String wrapperId;
    /** 酒店Id */
    private String hotelId;
    /** 酒店名称 */
    private String hotelName;
    /** 酒店地址 */
    private String hotelAddress;
    /** 产品房型Id 对应productId */
    private String roomId;
    /** 产品房型名称，对应productName */
    private String roomName;
    /** 城市中文 */
    private String city;
    /** 城市编码 */
    private String cityCode;
    /** 入住日期 */
    private String checkIn;
    /** 离店日期 */
    private String checkOut;
    /** 房间数 */
    private int rooms;
    /** 最晚到店时间 */
    private String arriveTime;
    /** 订单总金额 */
    private BigDecimal totalPrice;
    /** 是否担保 */
    private boolean needGuarantee;
    /** 支付类型 0-预付、1-现付 */
    private int payType;
    /** 支付/担保 金额 */
    private BigDecimal payAmount;
    /** 入住人姓名 以|分隔 */
    private String guestName;
    /** 联系人姓名 */
    private String contactName;
    /** 联系人电话 */
    private String telephone;
    /** 联系人邮箱地址 */
    private String email;
    /** 特殊要求 */
    private String remark;
    /** 早餐 以|分隔 */
    private String breakfast;
    /** 宽带 */
    private String webfree;
    /** 床型 */
    private String bedType;
    /** 退订说明 */
    private String cancellation;
    /** 温馨提示 */
    private List<String> hotelRemarks;
    /** 优惠信息 */
    private List<MobPrefInfo> preference;
    /** 返现金额 */
    private BigDecimal cashBack;
    /** 星券返现金额 */
    private BigDecimal starTicketBack;
    /** 使用的星券金额 */
    private BigDecimal usedStarTickets;
    /** 直减金额 */
    private BigDecimal priceCut; // 待确认
    
    /** 超减补贴金额 */
    private BigDecimal paySubtractPrice; 
    
    /** 担保结果 */
    private MobGuaranteeRule guaranteeRule;
    /** 服务费 */
    private BigDecimal serviceFee; // QTA 在温馨提示中
    /** 是否需要分开填写姓和名 */
    private boolean westNameStyle;
    /** 选择使用的红包Id列表,以逗号分隔 **/
    private String redpacketIds;
    /** 红包随机码 **/
    private String randomCode;
    /** 下单的红包综合信息 **/
    private RedpacketRecordInfo redpacketRecord;
    /** UDID */
    @JsonProperty("UDID")
    private String UDID;
    /**App版本号*/
    private String vid;
    /**uid*/
    private String uid;
    /** 未处理的设备编号*/
    private String gid;
    /** LBS百度用户Id */
    private String partnerUserId;
    /** 百度LBS字段 */
    private String baiduSsid;
    /**
     * <pre>
     *      @since 2014-07-21
     *      @author hanzhen.cao
     *      酒店Seq
     * </pre>
     */
    private String hotelSeq;
    /**
     * <pre>
     *      @since 2014-07-21
     *      @author hanzhen.cao
     *      城市url
     * </pre>
     */
    private String cityUrl;
    /**
     * <pre>
     *     @since 2014-07-21
     *     @author hanzhen.cao
     *    产品类型
     *    1 -> 普通产品
     *    2 -> 动态返现产品
     *    3 -> 有关联的PS产品
     *    4 -> 无关联的PS产品
     * </pre>
     */
    private int productType;

    /***
     *  库存类型
     */
    private int inventoryType;

    /**
     * <pre>
     *     @since 增加预定来源：wechat
     * </pre>
     */
    private String bookingSource;

    /***
     * 如果是百度订单来源，Qunar的用户名，仅在没有用户登录的情况下有效
     */
    private String userName;

    /** 关联订单号 */
    private Long relatedOrderNum;
    /** 关联订单的类型 */
    private String relatedOrderType;

    /**促销优惠码*/
    private String promotionCode;

    // ---- 发票 @yushen.ma

    /** 发票领取方式(0:不支持发票  1：快递预付 2：联系客服  3：酒店前台获取 4：不需要发票 5：电子发票) **/
    private int invoiceGetType;
    /** 获取方式(快递预付;前台领取)**/
    private String dispatch;
    /** 地址 **/
    private String address;
    /** 区 **/
    private String area;
    /** 市 **/
    private String invoiceCity;
    /** 联系人 **/
    private String invoiceContactName;
    /** 联系人电话 **/
    private String phone;
    /** 发票内容 **/
    private String content;
    /** 快递费用 **/
    private BigDecimal invoiceFee;
    /** 省 **/
    private String province;
    /** 抬头 **/
    private String title;
    /** 发票类型(eg.旅行社发票) **/
    private String invoiceType;
    /** 发票备注信息 */
    private String invoiceRemark;

    /** 产品唯一标识 */
    private String productUniqKey;
    
    /** 产品特殊属性 */
    private String specialProp;

    /** 轻松退 */
    private String freeRefund;
    // --- end 发票

    /**
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the wrapperId
     */
    public String getWrapperId() {
        return wrapperId;
    }

    /**
     * @param wrapperId the wrapperId to set
     */
    public void setWrapperId(String wrapperId) {
        this.wrapperId = wrapperId;
    }

    /**
     * @return the hotelId
     */
    public String getHotelId() {
        return hotelId;
    }

    /**
     * @param hotelId the hotelId to set
     */
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * @return the hotelName
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * @param hotelName the hotelName to set
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * @return the hotelAddress
     */
    public String getHotelAddress() {
        return hotelAddress;
    }

    /**
     * @param hotelAddress the hotelAddress to set
     */
    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    /**
     * @return the roomId
     */
    public String getRoomId() {
        return roomId;
    }

    /**
     * @param roomId the roomId to set
     */
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    /**
     * @return the roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @param roomName the roomName to set
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the checkIn
     */
    public String getCheckIn() {
        return checkIn;
    }

    /**
     * @param checkIn the checkIn to set
     */
    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    /**
     * @return the checkOut
     */
    public String getCheckOut() {
        return checkOut;
    }

    /**
     * @param checkOut the checkOut to set
     */
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    /**
     * @return the rooms
     */
    public int getRooms() {
        return rooms;
    }

    /**
     * @param rooms the rooms to set
     */
    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    /**
     * @return the arriveTime
     */
    public String getArriveTime() {
        return arriveTime;
    }

    /**
     * @param arriveTime the arriveTime to set
     */
    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * @return the totalPrice
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * @param totalPrice the totalPrice to set
     */
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * @return the payType
     */
    public int getPayType() {
        return payType;
    }

    /**
     * @param payType the payType to set
     */
    public void setPayType(int payType) {
        this.payType = payType;
    }

    /**
     * @return the payAmount
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * @param payAmount the payAmount to set
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * @return the guestName
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * @param guestName the guestName to set
     */
    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    /**
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the breakfast
     */
    public String getBreakfast() {
        return breakfast;
    }

    /**
     * @param breakfast the breakfast to set
     */
    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    /**
     * @return the webfree
     */
    public String getWebfree() {
        return webfree;
    }

    /**
     * @param webfree the webfree to set
     */
    public void setWebfree(String webfree) {
        this.webfree = webfree;
    }

    /**
     * @return the bedType
     */
    public String getBedType() {
        return bedType;
    }

    /**
     * @param bedType the bedType to set
     */
    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    /**
     * @return the cancellation
     */
    public String getCancellation() {
        return cancellation;
    }

    /**
     * @param cancellation the cancellation to set
     */
    public void setCancellation(String cancellation) {
        this.cancellation = cancellation;
    }

    /**
     * @return the cashBack
     */
    public BigDecimal getCashBack() {
        return cashBack;
    }

    /**
     * @param cashBack the cashBack to set
     */
    public void setCashBack(BigDecimal cashBack) {
        this.cashBack = cashBack;
    }

    /**
     * @return the priceCut
     */
    public BigDecimal getPriceCut() {
        return priceCut;
    }

    /**
     * @param priceCut the priceCut to set
     */
    public void setPriceCut(BigDecimal priceCut) {
        this.priceCut = priceCut;
    }

    /**
     * @return the serviceFee
     */
    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    /**
     * @param serviceFee the serviceFee to set
     */
    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    /**
     * @return the cityCode
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * @param cityCode the cityCode to set
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * @return the needGuarantee
     */
    public boolean isNeedGuarantee() {
        return needGuarantee;
    }

    /**
     * @param needGuarantee the needGuarantee to set
     */
    public void setNeedGuarantee(boolean needGuarantee) {
        this.needGuarantee = needGuarantee;
    }

    /**
     * @return the westNameStyle
     */
    public boolean isWestNameStyle() {
        return westNameStyle;
    }

    /**
     * @param westNameStyle the westNameStyle to set
     */
    public void setWestNameStyle(boolean westNameStyle) {
        this.westNameStyle = westNameStyle;
    }

    /**
     * @return the preference
     */
    public List<MobPrefInfo> getPreference() {
        return preference;
    }

    /**
     * @param preference the preference to set
     */
    public void setPreference(List<MobPrefInfo> preference) {
        this.preference = preference;
    }

    /**
     * @return the hotelRemarks
     */
    public List<String> getHotelRemarks() {
        return hotelRemarks;
    }

    /**
     * @param hotelRemarks the hotelRemarks to set
     */
    public void setHotelRemarks(List<String> hotelRemarks) {
        this.hotelRemarks = hotelRemarks;
    }

    public MobGuaranteeRule getGuaranteeRule() {
        return guaranteeRule;
    }

    public void setGuaranteeRule(MobGuaranteeRule guaranteeRule) {
        this.guaranteeRule = guaranteeRule;
    }

    public String getRedpacketIds() {
        return redpacketIds;
    }

    public void setRedpacketIds(String redpacketIds) {
        this.redpacketIds = redpacketIds;
    }

    public String getUDID() {
        return UDID;
    }

    public void setUDID(String UDID) {
        this.UDID = UDID;
    }

    public RedpacketRecordInfo getRedpacketRecord() {
        return redpacketRecord;
    }

    public void setRedpacketRecord(RedpacketRecordInfo redpacketRecord) {
        this.redpacketRecord = redpacketRecord;
    }

    public String getPartnerUserId() {
        return partnerUserId;
    }

    public void setPartnerUserId(String partnerUserId) {
        this.partnerUserId = partnerUserId;
    }

    public String getHotelSeq() {
        return hotelSeq;
    }

    public void setHotelSeq(String hotelSeq) {
        this.hotelSeq = hotelSeq;
    }

    public String getCityUrl() {
        return cityUrl;
    }

    public void setCityUrl(String cityUrl) {
        this.cityUrl = cityUrl;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }

    public String getBookingSource() {
        return bookingSource;
    }

    public String getInvoiceRemark() {
        return invoiceRemark;
    }

    public void setInvoiceRemark(String invoiceRemark) {
        this.invoiceRemark = invoiceRemark;
    }

    public void setBookingSource(String bookingSource) {
        this.bookingSource = bookingSource;
    }

    public String getBaiduSsid() {
        return baiduSsid;
    }

    public void setBaiduSsid(String baiduSsid) {
        this.baiduSsid = baiduSsid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(int inventoryType) {
        this.inventoryType = inventoryType;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getRelatedOrderNum() {
        return relatedOrderNum;
    }

    public void setRelatedOrderNum(Long relatedOrderNum) {
        this.relatedOrderNum = relatedOrderNum;
    }

    public String getRelatedOrderType() {
        return relatedOrderType;
    }

    public void setRelatedOrderType(String relatedOrderType) {
        this.relatedOrderType = relatedOrderType;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getInvoiceGetType() {
        return invoiceGetType;
    }

    public void setInvoiceGetType(int invoiceGetType) {
        this.invoiceGetType = invoiceGetType;
    }

    public String getDispatch() {
        return dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInvoiceCity() {
        return invoiceCity;
    }

    public void setInvoiceCity(String invoiceCity) {
        this.invoiceCity = invoiceCity;
    }

    public String getInvoiceContactName() {
        return invoiceContactName;
    }

    public void setInvoiceContactName(String invoiceContactName) {
        this.invoiceContactName = invoiceContactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getInvoiceFee() {
        return invoiceFee;
    }

    public void setInvoiceFee(BigDecimal invoiceFee) {
        this.invoiceFee = invoiceFee;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    /**
     * @return the paySubtractPrice
     */
    public BigDecimal getPaySubtractPrice() {
        return paySubtractPrice;
    }

    /**
     * @param paySubtractPrice the paySubtractPrice to set
     */
    public void setPaySubtractPrice(BigDecimal paySubtractPrice) {
        this.paySubtractPrice = paySubtractPrice;
    }

    public BigDecimal getStarTicketBack() {
        return starTicketBack;
    }

    public void setStarTicketBack(BigDecimal starTicketBack) {
        this.starTicketBack = starTicketBack;
    }

    public BigDecimal getUsedStarTickets() {
        return usedStarTickets;
    }

    public void setUsedStarTickets(BigDecimal usedStarTickets) {
        this.usedStarTickets = usedStarTickets;
    }

    /**
     * @return the productUniqKey
     */
    public String getProductUniqKey() {
        return productUniqKey;
    }

    /**
     * @param productUniqKey the productUniqKey to set
     */
    public void setProductUniqKey(String productUniqKey) {
        this.productUniqKey = productUniqKey;
    }

    /**
     * @return the specialProp
     */
    public String getSpecialProp() {
        return specialProp;
    }

    /**
     * @param specialProp the specialProp to set
     */
    public void setSpecialProp(String specialProp) {
        this.specialProp = specialProp;
    }

    public String getFreeRefund() {
        return freeRefund;
    }

    public void setFreeRefund(String freeRefund) {
        this.freeRefund = freeRefund;
    }
}
