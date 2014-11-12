package com.qunar.hotel.sa.product.price.bean;

import com.google.common.collect.Lists;
import com.qunar.hotel.sa.common.util.JsonUtil;
import com.qunar.hotel.sa.product.hotel.bean.Channel;
import com.qunar.hotel.sa.product.hotel.bean.InterfaceType;
import com.qunar.hotel.sa.product.hotel.bean.OrigVouchRule;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.GuaranteeRule;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.OnlineTypeEnum;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.type.TypeReference;
import org.hibernate.validator.constraints.Length;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.AdapterRoom.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomInfo {
    private String id;
    private int ownHotelId;
    @Length(min = 1, max = 20, message = "房间Id长度不在允许范围内，min：1,max：20")
    @NotNull(message = "房间ID不能为空")
    private String otaRoomId;
    @NotNull(message = "酒店ID不能为空")
    private String otaHotelId;
    private InterfaceType interfaceType;
    @Length(min = 1, max = 50, message = "房间长度不在允许范围内，min：1,max：50")
    @NotNull(message = "房间名字不能为空")
    private String roomName;

    /** 担保规则列表 **/
    private List<GuaranteeRule> allGuaranteeRule;
    // private GuaranteeRule guaranteeRule;

    /** 早餐 **/
    private Breakfast breakfast = Breakfast.NONE;
    /** 床型 **/
    private BedType bedType = BedType.BIG;

    private List<BedTypeInfo> bedTypes;
    /** 网络 **/
    private Broadband broadband = Broadband.CHARGE;
    private PayType payType = PayType.ONLINE;
    private OnlineTypeEnum onlineType = OnlineTypeEnum.FULL_PAYMENT;
    private InstantConfirm instantConfirm;
    private Status status = Status.ACTIVE;
    private String specialRemark;
    /** 退款规则 **/
    private String cancellation;
    private String preference;
    private String description;

    @Min(value = 0, message = "连住天数不能小于0")
    @Max(value = 90, message = "连住天数不能大于90")
    private int last;
    @Min(value = 0, message = "最大连住天数不能小于0")
    /** 最多连住天数,默认0天 **/
    private int maxLastDay = 0;
    @Min(value = 0, message = "提前预订天数不能小于0")
    @Max(value = 90, message = "提前预订天数不能大于90")
    private int advance;

    /**
     * 房量不足决绝预订 1 房量不足时拒绝预订 0 或其他房量不足时为申请房
     */
    @Min(value = 0)
    @Max(value = 1)
    private int refuseState = 0;
    /**
     * 担保规则初始值
     */
    private List<OrigVouchRule> origVouchRule;

    private List<PriceInfo> priceInfo;

    private String customKey;
    /** 国际酒店新增字段 */
    /** 是否可吸烟 */
    private Smoking smoking;

    /** 税费，服务费 */
    private BigDecimal serviceFee;

    /** 最多可入住人数 */
    private int maxRoomOccupancy;

    /** 最多可预订间数 */
    private int maxRoomNumber = 8;
    
    /** 最晚到店时间（00-48） */
    private String latestArriveTime;
    

    /** 入住人最小年龄 */
    private int minGuestAge;

    /** 额外费用 */
    private String checkInInstuctions;

    /** 均价 */
    private BigDecimal nightlyRate;

    /** 总价 */
    private BigDecimal total;
    /** 外币金额 */
    private BigDecimal forexMoney;
    /** 外币币种 */
    private String currencyType;

    private BigDecimal maxNightlyRate;

    /** 价格描述 */
    private String rateDesc;
    private String breakfastStr;

    /** 存放不同代理商特殊属性，如图片 **/
    private Map<AdapterRoomExtendType, Object> extendInfos = new HashMap<AdapterRoomExtendType, Object>();

    /** 售卖渠道 */
    private Channel channel;
    /** 预订类别 */
    private String bookingType;

    /** 宾客类型 */
    private GuestType guestType = GuestType.ALL_GUEST;

    private List<RoomDetailBean> details;

    /**
     * 可支持多种渠道
     */
    private List<Channel> supportChannels = Lists.newArrayList(Channel.MAIN);

    /** 房型类型 **/
    private RoomType roomType = RoomType.NORMAL;
    
    public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

	public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Map<AdapterRoomExtendType, Object> getExtendInfos() {
        return extendInfos;
    }

    public void setExtendInfos(Map<AdapterRoomExtendType, Object> extendInfos) {
        this.extendInfos = extendInfos;
    }

    public String getRateDesc() {
        return rateDesc;
    }

    public void setRateDesc(String rateDesc) {
        this.rateDesc = rateDesc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOwnHotelId() {
        return ownHotelId;
    }

    public void setOwnHotelId(int ownHotelId) {
        this.ownHotelId = ownHotelId;
    }

    public String getOtaRoomId() {
        return otaRoomId;
    }

    public void setOtaRoomId(String otaRoomId) {
        this.otaRoomId = otaRoomId;
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Breakfast getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Breakfast breakfast) {
        this.breakfast = breakfast;
    }

    public BedType getBedType() {
        return bedType;
    }

    public void setBedType(BedType bedType) {
        this.bedType = bedType;
    }

    public Broadband getBroadband() {
        return broadband;
    }

    public void setBroadband(Broadband broadband) {
        this.broadband = broadband;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public OnlineTypeEnum getOnlineType() {
        return onlineType;
    }

    public void setOnlineType(OnlineTypeEnum onlineType) {
        this.onlineType = onlineType;
    }

    public InstantConfirm getInstantConfirm() {
        return instantConfirm;
    }

    public void setInstantConfirm(InstantConfirm instantConfirm) {
        this.instantConfirm = instantConfirm;
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

    public void setBreakfastStr(String breakfastStr) {
        this.breakfastStr = breakfastStr;
    }

    public String getBreakfastStr() {
        return breakfastStr;
    }
    // public GuaranteeRule getGuaranteeRule() {
    // return guaranteeRule;
    // }
    //
    // public void setGuaranteeRule(GuaranteeRule guaranteeRule) {
    // this.guaranteeRule = guaranteeRule;
    // }


    public Smoking getSmoking() {
        return smoking;
    }

    public List<Channel> getSupportChannels() {
        return supportChannels;
    }

    public void setSupportChannels(List<Channel> supportChannels) {
        this.supportChannels = supportChannels;
    }

    public String getLatestArriveTime() {
        return latestArriveTime;
    }

    public void setLatestArriveTime(String latestArriveTime) {
        this.latestArriveTime = latestArriveTime;
    }

    public GuestType getGuestType() {
        return guestType;
    }

    public void setGuestType(GuestType guestType) {
        this.guestType = guestType;
    }

    public static enum Breakfast {
        NONE(0, "无早"), HAVE(1, "含早"), ONE(2, "单早"), TWO(3, "双早"), THREE(4, "三早"), FOUR(5, "四早");
        public int code;
        public String desc;

        Breakfast(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static Breakfast codeOf(int code) {
            Breakfast result = null;
            for (Breakfast t : Breakfast.values()) {
                if (t.code == code) {
                    result = t;
                    break;
                }
            }
            return result;
        }
    }

    public static enum BedType {
        BIG(0, "大床"), DOUBLE(1, "双人床"), BIG_DOUBULE(2, "大/双床"), THREE(3, "三床"), TWO_ONE(4, "一双一单"), ONE(5, "单人床"), UP_DOWN(
                6, "上下铺"), WIDE(7, "通铺"), TAMI(8, "榻榻米"), WATER(9, "水床"), ROUND(10, "圆床"), SPELL(11, "拼床"), UNKNOWN(99,
                "未知");
        public int code;
        public String desc;

        BedType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static BedType codeOf(int code) {
            BedType result = null;
            for (BedType t : BedType.values()) {
                if (t.code == code) {
                    result = t;
                    break;
                }
            }
            return result;
        }
    }

    public static enum Broadband {
        NONE(0, "无"), HAVE(1, "有"), FREE(2, "免费"), CHARGE(3, "收费"), PART_CHARGE(4, "部分收费"), PART_HAVE_CHARGE(5,
                "部分有且收费"), PART_HAVE_FREE(6, "部分有且免费"), PART_HAVE_PART_FREE(7, "部分有且部分收费"), UNKNOWN(8, "未知");
        public int code;
        public String desc;

        Broadband(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public static Broadband codeOf(int code) {
            Broadband result = null;
            for (Broadband t : Broadband.values()) {
                if (t.code == code) {
                    result = t;
                    break;
                }
            }
            return result;
        }
    }

    public static enum PayType {
        CASH, ONLINE
    }

    public enum Status {
        ACTIVE, DELETED;
    }

    public enum InstantConfirm {
        NO(0), YES(1) ;
        public int code;


        InstantConfirm(int code) {
            this.code = code;
        }
    }

    public enum GuestType{
        ALL_GUEST(0, "所有宾客"), DOMESTIC_GUEST(1, "内宾"), CHINESE_GUEST(2, "中宾"), FOREIGN_GUEST(3, "外宾");

        public int code;
        public String desc;

        GuestType(int code, String desc){
            this.code = code;
            this.desc = desc;
        }

        public static GuestType codeOf(int code){
            GuestType result = null;
            for(GuestType type : GuestType.values()){
                if (type.code == code){
                    result = type;
                    break;
                }
            }
            return result;
        }
    }
    public static enum RoomType {
        /** 普通房型 **/
        NORMAL,
        /** 连住房型 **/
        LAST,
        /** 提前预订房型 **/
        ADVANCE,
        /** 连住&提前预订房型 **/
        LASTADV;
    }

    /**
     * 预订方式 0：预订 1：申请 2：积分 3：钥匙
     *
     * @author zhangping
     *
     */
    public static enum BookingType {
        BOOKING(0, "预订"),

        APPLY(1, "申请"),

        INTEGRAL(2, "积分"),

        KEY(3, "钥匙");

        private int type;
        private String desc;

        BookingType(int type, String desc) {
            this.type = type;
            this.desc = desc;
        }

        public static BookingType valueOf(int type) {
            BookingType result = BOOKING;
            for (BookingType t : BookingType.values()) {
                if (t.type == type) {
                    result = t;
                    break;
                }
            }
            return result;
        }

        public static BookingType getBookTypeByDesc(String desc) {
            BookingType result = BOOKING;
            for (BookingType bookType : BookingType.values()) {
                if (StringUtils.equals(bookType.getDesc(), desc)) {
                    result = bookType;
                    break;
                }
            }
            return result;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

    }
    public String getSpecialRemark() {
        return specialRemark;
    }

    public void setSpecialRemark(String specialRemark) {
        this.specialRemark = specialRemark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getAdvance() {
        return advance;
    }

    public void setAdvance(int advance) {
        this.advance = advance;
    }


    public int getRefuseState() {
        return refuseState;
    }

    public void setRefuseState(int refuseState) {
        this.refuseState = refuseState;
    }

    public List<OrigVouchRule> getOrigVouchRule() {
        return origVouchRule;
    }

    public void setOrigVouchRule(List<OrigVouchRule> origVouchRule) {
        this.origVouchRule = origVouchRule;
    }

    public List<PriceInfo> getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(List<PriceInfo> priceInfo) {
        this.priceInfo = priceInfo;
    }

    public void addPriceInfo(PriceInfo price) {
        if(this.priceInfo == null) {
            this.priceInfo = new ArrayList<PriceInfo>();
        }
        this.priceInfo.add(price);
    }

    public String getCustomKey() {
        return customKey;
    }

    public void setCustomKey(String customKey) {
        this.customKey = customKey;
    }

    public List<GuaranteeRule> getAllGuaranteeRule() {
        return allGuaranteeRule;
    }

    public void setAllGuaranteeRule(List<GuaranteeRule> allGuaranteeRule) {
        this.allGuaranteeRule = allGuaranteeRule;
    }

    public void addGuaranteeRule(GuaranteeRule guaranteeRule) {
        if (this.allGuaranteeRule == null) {
            this.allGuaranteeRule = new ArrayList<GuaranteeRule>();
        }
        if (guaranteeRule == null)
            return;
        allGuaranteeRule.add(guaranteeRule);
    }

    public void addSplitGuaranteeRule(GuaranteeRule guaranteeRule)  {
        if (this.allGuaranteeRule == null) {
            this.allGuaranteeRule = new ArrayList<GuaranteeRule>();
        }
        if (guaranteeRule != null) {
            if (guaranteeRule.getArriveRule() != null && guaranteeRule.getCountRule() != null) {
                String ruleStr = JsonUtil.toJsonString(guaranteeRule);
                GuaranteeRule rule1 = null;
                GuaranteeRule rule2 = null;
                try {
                    rule1 = JsonUtil.parseObject(ruleStr, new TypeReference<GuaranteeRule>() {
                    });
                    rule2 = JsonUtil.parseObject(ruleStr,new TypeReference<GuaranteeRule>() {
                    });

                } catch (IOException e) {

                }
                // 拆分担保规则
                rule1.setArriveRule(null);
                rule2.setCountRule(null);
                allGuaranteeRule.add(rule1);
                allGuaranteeRule.add(rule2);
            } else {
                allGuaranteeRule.add(guaranteeRule);
            }
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public void setBedTypes(List<BedTypeInfo> bedTypes) {
        this.bedTypes = bedTypes;
    }

    public List<BedTypeInfo> getBedTypes() {
        return bedTypes;
    }

    public Smoking isSmoking() {
        return smoking;
    }

    public void setSmoking(Smoking smoking) {
        this.smoking = smoking;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public int getMaxRoomOccupancy() {
        return maxRoomOccupancy;
    }

    public void setMaxRoomOccupancy(int maxRoomOccupancy) {
        this.maxRoomOccupancy = maxRoomOccupancy;
    }

    public int getMinGuestAge() {
        return minGuestAge;
    }

    public void setMinGuestAge(int minGuestAge) {
        this.minGuestAge = minGuestAge;
    }

    public String getCancellation() {
        return cancellation;
    }

    public void setCancellation(String cancellation) {
        this.cancellation = cancellation;
    }

    public String getCheckInInstuctions() {
        return checkInInstuctions;
    }

    public void setCheckInInstuctions(String checkInInstuctions) {
        this.checkInInstuctions = checkInInstuctions;
    }

    public BigDecimal getNightlyRate() {
        return nightlyRate;
    }

    public void setNightlyRate(BigDecimal nightlyRate) {
        this.nightlyRate = nightlyRate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getForexMoney() {
        return forexMoney;
    }

    public void setForexMoney(BigDecimal forexMoney) {
        this.forexMoney = forexMoney;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public BigDecimal getMaxNightlyRate() {
        return maxNightlyRate;
    }

    public void setMaxNightlyRate(BigDecimal maxNightlyRate) {
        this.maxNightlyRate = maxNightlyRate;
    }

    public int getMaxRoomNumber() {
        return maxRoomNumber;
    }

    public void setMaxRoomNumber(int maxRoomNumber) {
        this.maxRoomNumber = maxRoomNumber;
    }

    public int getMaxLastDay() {
        return maxLastDay;
    }

    public void setMaxLastDay(int maxLastDay) {
        this.maxLastDay = maxLastDay;
    }

    public List<RoomDetailBean> getDetails() {
        if (details == null) {
            details = new ArrayList<RoomDetailBean>();
        }
        return details;
    }

    public void setDetails(List<RoomDetailBean> details) {
        this.details = details;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
