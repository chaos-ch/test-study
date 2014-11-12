package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 房型信息
 * 
 * @author weiming.liao
 * 
 */
public class AdapterRoom implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 2504494910759164090L;

    private static final String EMPTY = "";

    private String roomId;
    private int hotelId;
    /** 房型名称 **/
    private String roomName;
    /**
     * 早餐 注意统一接口代理商 不使用room级别的早餐，使用每日级别的早餐
     **/
    private BreadType breakfast;
    private String breakfastStr;

    List<AdapterPrice> prices;
    
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

    /** 床型 **/
    private BedType bedType;
    /** 网络 **/
    private BroadbandType intnet;
    /** 支付 **/
    private PayType payType = PayType.ONLINE;
    /** 状态 **/
    private Status status = Status.ACTIVE;
    /** 房型类别 1：即时确认房型；0：非即时确认房型 **/
    private int instantConfirm;

    /** 担保规则列表 **/
    private List<GuaranteeRule> allGuaranteeRule;
    // private GuaranteeRule rule;

    /** 最少连住天数 **/
    private int last;
    /** 最多连住天数,默认0天，表示不单独处理 **/
    private int maxLast = 0;
    /** 提前预订天数 **/
    private int advance;

    /** 统一接口，房量不足拒绝预订 */
    private int refuseState = 0;

    /** 代理商保存订单时需要报价中的key */
    private String customKey;

    /** 国际酒店新增字段 */
    /** 是否可吸烟 */
    private Smoking smoking;

    /** 税费，服务费 */
    private BigDecimal serviceFee;

    /** 最多可入住人数 */
    private int maxRoomOccupancy;

    /** 最多可预订间数 */
    private int maxRoomNumber;

    /** 入住人最小年龄 */
    private int minGuestAge;

    /** 外币金额 */
    private BigDecimal forexMoney;
    /** 外币币种 */
    private String currencyType;

    /** 价格描述 */
    private String rateDesc;

    private List<AdapterBedType> bedTypes;

    private OnlineTypeEnum onlineType = OnlineTypeEnum.FULL_PAYMENT;

    /** 存放不同代理商特殊属性，如图片 **/
    private Map<AdapterRoomExtendType, Object> extendInfos = new HashMap<AdapterRoomExtendType, Object>();
    
    /**
     * 温馨提示
     */
    private String remarks;
    
    /**
     * 房型描述信息
     */
    private String description;
    
    /**
     * 用户可选择的最晚到店时间
     */
    private String latestArriveTime;
    
    /** 预订类别 */
    private String bookingType;

    /** 宾客类型 */
    private GuestType guestType = GuestType.ALL_GUEST;

    /** 房型类型 **/
    private RoomType roomType = RoomType.NORMAL;
    
    public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Map<AdapterRoomExtendType, Object> getExtendInfos() {
        return extendInfos;
    }

    public void setExtendInfos(Map<AdapterRoomExtendType, Object> extendInfos) {
        this.extendInfos = extendInfos;
    }

    public static enum Status {

        /** 可订 **/
        ACTIVE(1, "可订"),
        /** 不可订 **/
        DISABLED(0, "不可订");
        // /**需确认**/
        // CONFIRM(2,"需确认");
        private int code;
        private String desc;

        private Status(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int code() {
            return this.code;
        }

        public String desc() {
            return this.desc;
        }
    }

    public static enum BreadType {
        NONE(0, "无早"), HAVE(1, "含早"), ONE(2, "单早"), TWO(3, "双早"), THREE(4, "三早"), FOUR(5, "四早"), UNKNOWN(99, "未知");
        public int code;
        public String desc;

        BreadType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int code() {
            return this.code;
        }

        public static BreadType codeOf(int code) {
            for(BreadType breadType : BreadType.values()) {
                if(code == breadType.code) {
                    return breadType;
                }
            }
            return null;
        }
    }

    public static enum BedType {
        BIG(0, "大床"), DOUBLE(1, "双床"), BIG_DOUBULE(2, "大/双床"), THREE(3, "三床"), TWO_ONE(4, "一双一单"), ONE(5, "单人床"), UP_DOWN(
                6, "上下铺"), WIDE(7, "通铺"), TAMI(8, "榻榻米"), WATER(9, "水床"), ROUND(10, "圆床"), SPELL(11, "拼床"), UNKNOWN(99,
                "未知");
        public int code;
        public String desc;

        BedType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int code() {
            return this.code;
        }
        
        public static BedType codeOf(int code) {
            for(BedType bedType : BedType.values()) {
                if(code == bedType.code) {
                    return bedType;
                }
            }
            return null;
        }
    }

    public static enum PayType {
        /** 预付 **/
        ONLINE(0, "预付"),
        /** 现付 **/
        CASH(1, "现付");
        private int code;
        private String desc;

        private PayType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int code() {
            return this.code;
        }

        public String desc() {
            return this.desc;
        }
    }

    public static enum BroadbandType {
        NONE(0, "无"), HAVE(1, "收费"), FREE(2, "免费"), CHARGE(3, "收费"), PART_CHARGE(4, "部分收费"), PART_HAVE_CHARGE(5,
                "部分有且收费"), PART_HAVE_FREE(6, "部分有且免费"), PART_HAVE_PART_FREE(7, "部分有且部分收费"), UNKNOWN(8, "未知");
        public int code;
        public String desc;

        BroadbandType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int code() {
            return this.code;
        }

        public static BroadbandType codeOf(int code) {
            for(BroadbandType broadbandType : BroadbandType.values()) {
                if(code == broadbandType.code) {
                    return broadbandType;
                }
            }
            return null;
        }

    }



    public static enum Smoking {
        Y, N, UNKNOWN
    }

    public static enum AdapterRoomExtendType {
        /** booking代理商使用 **/
        PICURL("房型图片"), REFUNDABLETIME("最晚取消订单时间"), MOREROOMRMBPRICEMAP("多间房对应人民币和外币的价格"), DEPOSITREQUIRED("是否收取定金"), SERVICEINFO(
                "服务信息"), IMPORTANTINFO("重要信息");
        public String desc;

        AdapterRoomExtendType(String desc) {
            this.desc = desc;
        }
    }

    public static enum ServiceInfoType {
        /** booking代理商使用 **/
        ROOMDESC("客房"), ROOMFACILITY("客房设施"), DEPOSITDESC("定金描述"), BREAKFASTDESC("餐饮"), CHILDRENDESC("儿童"), NETWORKDESC(
                "宽带"), PARKINGDESC("停车场"), PETSDESC("宠物"), HOTELGROUP("酒店Group信息");
        public String desc;

        ServiceInfoType(String desc) {
            this.desc = desc;
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
    // GET && SET
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    // @Deprecated
    // public BreadType getBreakfast() {
    // return breakfast;
    // }
    // @Deprecated
    // public void setBreakfast(BreadType breakfast) {
    // this.breakfast = breakfast;
    // }
    public BedType getBedType() {
        return bedType;
    }

    public void setBedType(BedType bedType) {
        this.bedType = bedType;
    }

    public BroadbandType getIntnet() {
        return intnet;
    }

    public void setIntnet(BroadbandType intnet) {
        this.intnet = intnet;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getInstantConfirm() {
        return instantConfirm;
    }

    public void setInstantConfirm(int instantConfirm) {
        this.instantConfirm = instantConfirm;
    }

    // public GuaranteeRule getRule() {
    // return rule;
    // }
    // public void setRule(GuaranteeRule rule) {
    // this.rule = rule;
    // }


    public List<GuaranteeRule> getAllGuaranteeRule() {
        return allGuaranteeRule;
    }

    public void setAllGuaranteeRule(List<GuaranteeRule> allGuaranteeRule) {
        this.allGuaranteeRule = allGuaranteeRule;
    }


    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }
    
    public int getMaxLast() {
        return maxLast;
    }

    public void setMaxLast(int maxLast) {
        this.maxLast = maxLast;
    }

    public int getAdvance() {
        return advance;
    }

    public void setAdvance(int advance) {
        this.advance = advance;
    }

    public String getRoomTypeDesc() {
        switch (getRoomType()) {
        case NORMAL:
            return EMPTY;
        case LAST:
            return "连住" + getLast() + "晚及以上";
        case ADVANCE:
            return "提前" + getAdvance() + "天预订";
        case LASTADV:
            return "连住" + getLast() + "晚及以上且提前" + getAdvance() + "天预订";
        }
        return EMPTY;
    }

    public int getRefuseState() {
        return refuseState;
    }

    public void setRefuseState(int refuseState) {
        this.refuseState = refuseState;
    }

    public String getCustomKey() {
        return customKey;
    }

    public void setCustomKey(String customKey) {
        this.customKey = customKey;
    }

    public Smoking isSmoking() {
        return smoking;
    }

    public void setSmoking(Smoking smoking) {
        this.smoking = smoking;
    }

    public BigDecimal getServiceFee() {
        if (this.serviceFee == null) {
            return new BigDecimal(0);
        }
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

    public List<AdapterBedType> getBedTypes() {
        return bedTypes;
    }

    public void setBedTypes(List<AdapterBedType> bedTypes) {
        this.bedTypes = bedTypes;
    }

    public int getMinGuestAge() {
        return minGuestAge;
    }

    public void setMinGuestAge(int minGuestAge) {
        this.minGuestAge = minGuestAge;
    }

    public OnlineTypeEnum getOnlineType() {
        return onlineType;
    }

    public void setOnlineType(OnlineTypeEnum onlineType) {
        this.onlineType = onlineType;
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

    public String getRateDesc() {
        return rateDesc;
    }

    public void setRateDesc(String rateDesc) {
        this.rateDesc = rateDesc;
    }

    public int getMaxRoomNumber() {
        return maxRoomNumber;
    }

    public void setMaxRoomNumber(int maxRoomNumber) {
        this.maxRoomNumber = maxRoomNumber;
    }
    
    public List<AdapterPrice> getPrices() {
        return prices;
    }

    public void setPrices(List<AdapterPrice> prices) {
        this.prices = prices;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "AdapterRoom [roomId=" + roomId + ", hotelId=" + hotelId + ", roomName=" + roomName + ", breakfast="
                + breakfast + ", bedType=" + bedType + ", intnet=" + intnet + ", payType=" + payType + ", status="
                + status + ", instantConfirm=" + instantConfirm + ", rule=" + allGuaranteeRule + ", priceType=" + roomType
                + ", last=" + last + ", advance=" + advance + ", refuseState=" + refuseState + ",smoking=" + smoking
                + ",serviceFee=" + serviceFee + ",maxRoomOccupancy=" + maxRoomOccupancy + ",maxRoomNumber="
                + maxRoomNumber + ",adapterRoomGroupList=" + bedTypes + ",minGuestAge=" + minGuestAge + ",forexMoney="
                + forexMoney + "]";
    }
}
