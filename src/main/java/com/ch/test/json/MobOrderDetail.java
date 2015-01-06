package com.ch.test.json;


import java.io.Serializable;
import java.math.BigDecimal;

public class MobOrderDetail implements Serializable {

    private static final long serialVersionUID = -7010583931501700363L;
    private String breakfast;         //早餐信息

    private String orderNum; // 单号
    private int orderStatusCode; // 订单状态,如：1-新订等
    private String orderStatus; // 订单状态描述
    private boolean ifPay; // 是否可支付
    private boolean ifCancel; // 是否可取消,QTA：需扣钱的订单不可取消
    private int refundType; // 退款类型 1:表示可以全额退款，2:部分退款，3:不可退款
    private boolean ifCashBack; // 是否可返现,仅当订单可返现且是qunar返现时，此值为true。
    private WrapperInfo wrapperInfo; // OTA信息
    private String hotelID; // 酒店编号,预订酒店的OTA方唯一酒店编号
    private String hotelSeq;    //酒店SEQ
    private String roomID; // 房型编号
    private String checkIn; // 入住时间,如：2012-05-16
    private String checkOut; // 离店时间,如：2012-05-16
    private int rooms; // 预订间数
    private String arriveTime; // 最晚到达时间,如：18:00
    private BigDecimal totalPrice; // 订单总价
    private int payType; // 付款类型,定义：0-预付、1-现付
    private BigDecimal payAmount; // 预付金额
    private BigDecimal guaraAmount; // 担保金额
    private String guestName; // 入住人,入住人姓名（多个以,号分开)
    private String contactName; // 联系人
    private String telephone; // 联系电话
    private String email; // 电子邮箱
    private String remark; // 酒店备注,如：要有窗户的房间
    private String hotelName; // 酒店名称
    private String hotelAddr; // 酒店地址
    private String roomName; // 房型名称
    private String bedType; // 床型
    private String webfree; // 是否有免费宽带
    private String bookDate; // 下定时间,yyyy-MM-dd hh:mm:ss
    private String city; // 城市
    private String hotelPhone; // 酒店电话
    private BigDecimal cashBack; // 返现金额
    private BigDecimal realCashBack; // 实际返现金额
    private MobGuaranteeRule guaranteeRule; // 担保规则
    private String userName; // 绑定的用户名
    private String priceOfferInfo; // 优惠信息
    private boolean ifApplyCashBack; // 是否申请返现
    private int mainStatusCode;          //主订单状态
    private int payStatusCode;           //支付状态
    private int ocncStatusCode;           //ocnc状态
    private boolean ifOcncPay;      //是否可以ocnc支付
    private boolean ifCheckOut;     //是否可以离店操作
    private boolean canInstant;     //是否是即时确认
    private MobOcncRule ocncRule;   //ocnc规则
    private int payTimeDuration; // 距离支付关闭剩余分钟数
    private int lifeCycle;      //当前生命周期 参见OrderLifecycle
    private boolean hasUrge;        //（true:催过确认,false:未催过确认）
    private int  noShowReason;      //NOSHOW原因 参见..
    private int cashBackStatus;     //返现状态
    /**代理商did电话*/
    private String otaDidPhone;
    /** ICP备案号 */
    private String icpNo;
    /** 供应商服务时间 如7*24 */
    private String serviceTime;
    private boolean ifProlong ; // 是否可以延迟离店

    private MobOrderInvoiceInfo invoiceInfo;

    public static final int REFUND_TYPE_ALL_REFUND = 1;
    public static final int REFUND_TYPE_PART_REFUND = 2;
    public static final int REFUND_TYPE_NO_REFUND = 3;

    public static final int NONE_NOSHOW_REASON = -1;    //没有NOSHOW原因

    public static final int CASHBACK_UNKNOWN = 0;   //未知
    public static final int CASHBACKING = 1;    //正在返现
    public static final int CASHBACK_SUCCESS = 2;   //返现成功
    public static final int CASHBACK_DRAWABLE = 3;    //可以领取
    public static final int CASHBACK_APPLIABLE = 4; //可以申请返现



    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public int getOrderStatusCode() {
        return orderStatusCode;
    }

    public void setOrderStatusCode(int orderStatusCode) {
        this.orderStatusCode = orderStatusCode;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isIfPay() {
        return ifPay;
    }

    public void setIfPay(boolean ifPay) {
        this.ifPay = ifPay;
    }

    public boolean isIfCancel() {
        return ifCancel;
    }

    public void setIfCancel(boolean ifCancel) {
        this.ifCancel = ifCancel;
    }

    public int getRefundType() {
        return refundType;
    }

    public void setRefundType(int refundType) {
        this.refundType = refundType;
    }

    public boolean isIfCashBack() {
        return ifCashBack;
    }

    public void setIfCashBack(boolean ifCashBack) {
        this.ifCashBack = ifCashBack;
    }

    public WrapperInfo getWrapperInfo() {
        return wrapperInfo;
    }

    public void setWrapperInfo(WrapperInfo wrapperInfo) {
        this.wrapperInfo = wrapperInfo;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddr() {
        return hotelAddr;
    }

    public void setHotelAddr(String hotelAddr) {
        this.hotelAddr = hotelAddr;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getWebfree() {
        return webfree;
    }

    public void setWebfree(String webfree) {
        this.webfree = webfree;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public BigDecimal getCashBack() {
        return cashBack;
    }

    public void setCashBack(BigDecimal cashBack) {
        this.cashBack = cashBack;
    }

    public BigDecimal getRealCashBack() {
        return realCashBack;
    }

    public void setRealCashBack(BigDecimal realCashBack) {
        this.realCashBack = realCashBack;
    }

    public MobGuaranteeRule getGuaranteeRule() {
        return guaranteeRule;
    }

    public void setGuaranteeRule(MobGuaranteeRule guaranteeRule) {
        this.guaranteeRule = guaranteeRule;
    }

    public String getPriceOfferInfo() {
        return priceOfferInfo;
    }

    public void setPriceOfferInfo(String priceOfferInfo) {
        this.priceOfferInfo = priceOfferInfo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isIfApplyCashBack() {
        return ifApplyCashBack;
    }

    public void setIfApplyCashBack(boolean ifApplyCashBack) {
        this.ifApplyCashBack = ifApplyCashBack;
    }

    public BigDecimal getGuaraAmount() {
        return guaraAmount;
    }

    public void setGuaraAmount(BigDecimal guaraAmount) {
        this.guaraAmount = guaraAmount;
    }

    public boolean isIfCheckOut() {
        return ifCheckOut;
    }

    public void setIfCheckOut(boolean ifCheckOut) {
        this.ifCheckOut = ifCheckOut;
    }
    
    public String getBreakfast() {
        return breakfast;
    }
    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }
 
    public int getPayTimeDuration() {
        return payTimeDuration;
    }

    public void setPayTimeDuration(int payTimeDuration) {
        this.payTimeDuration = payTimeDuration;
    }

    public boolean isIfOcncPay() {
        return ifOcncPay;
    }

    public void setIfOcncPay(boolean ifOcncPay) {
        this.ifOcncPay = ifOcncPay;
    }

    public int getMainStatusCode() {
        return mainStatusCode;
    }

    public void setMainStatusCode(int mainStatusCode) {
        this.mainStatusCode = mainStatusCode;
    }

    public int getOcncStatusCode() {
        return ocncStatusCode;
    }

    public void setOcncStatusCode(int ocncStatusCode) {
        this.ocncStatusCode = ocncStatusCode;
    }

    public int getPayStatusCode() {
        return payStatusCode;
    }

    public void setPayStatusCode(int payStatusCode) {
        this.payStatusCode = payStatusCode;
    }

    public boolean isCanInstant() {
        return canInstant;
    }

    public void setCanInstant(boolean canInstant) {
        this.canInstant = canInstant;
    }

    public MobOcncRule getOcncRule() {
        return ocncRule;
    }

    public void setOcncRule(MobOcncRule ocncRule) {
        this.ocncRule = ocncRule;
    }

    public String getHotelSeq() {
        return hotelSeq;
    }

    public void setHotelSeq(String hotelSeq) {
        this.hotelSeq = hotelSeq;
    }

    public int getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(int lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public boolean isHasUrge() {
        return hasUrge;
    }

    public void setHasUrge(boolean hasUrge) {
        this.hasUrge = hasUrge;
    }

    public int getNoShowReason() {
        return noShowReason;
    }

    public void setNoShowReason(int noShowReason) {
        this.noShowReason = noShowReason;
    }

    public int getCashBackStatus() {
        return cashBackStatus;
    }

    public void setCashBackStatus(int cashBackStatus) {
        this.cashBackStatus = cashBackStatus;
    }

    public String getIcpNo() {
        return icpNo;
    }

    public void setIcpNo(String icpNo) {
        this.icpNo = icpNo;
    }

    public String getOtaDidPhone() {
        return otaDidPhone;
    }

    public void setOtaDidPhone(String otaDidPhone) {
        this.otaDidPhone = otaDidPhone;
    }

    public String getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(String serviceTime) {
        this.serviceTime = serviceTime;
    }

    public MobOrderInvoiceInfo getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setInvoiceInfo(MobOrderInvoiceInfo invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    public boolean isIfProlong() {
        return ifProlong;
    }

    public void setIfProlong(boolean ifProlong) {
        this.ifProlong = ifProlong;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static int getRefundTypeAllRefund() {
        return REFUND_TYPE_ALL_REFUND;
    }

    public static int getRefundTypePartRefund() {
        return REFUND_TYPE_PART_REFUND;
    }

    public static int getRefundTypeNoRefund() {
        return REFUND_TYPE_NO_REFUND;
    }

    public static int getNoneNoshowReason() {
        return NONE_NOSHOW_REASON;
    }

    public static int getCashbackUnknown() {
        return CASHBACK_UNKNOWN;
    }

    public static int getCashbacking() {
        return CASHBACKING;
    }

    public static int getCashbackSuccess() {
        return CASHBACK_SUCCESS;
    }

    public static int getCashbackDrawable() {
        return CASHBACK_DRAWABLE;
    }

    public static int getCashbackAppliable() {
        return CASHBACK_APPLIABLE;
    }
}
