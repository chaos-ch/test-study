package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.math.BigDecimal;
import java.util.*;

import com.qunar.hotel.sa.common.util.SysConfigUtil;
import com.qunar.hotel.sa.order.bean.AdapterOrder;
import com.qunar.hotel.sa.order.bean.AdapterResult;
import com.qunar.hotel.sa.order.bean.Order;
import com.qunar.hotel.sa.order.bean.OrderResult;
import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.price.bean.BedTypeInfo;
import com.qunar.hotel.sa.product.price.bean.PriceInfo;
import com.qunar.hotel.sa.product.price.bean.RoomGroup;
import com.qunar.hotel.sa.product.price.bean.RoomInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import com.qunar.hotel.sa.product.price.bean.RoomInfo.*;


public class TransformUtil {

    public static AdapterHotel tranHotel(HotelInfo hi) {
        AdapterHotel hotel = null;
        if (hi != null) {
            hotel = new AdapterHotel();
            hotel.setId(hi.getId());
            hotel.setOtaHotelId(hi.getOtaHotelId());
            hotel.setCityUrl(hi.getCityUrl());
            hotel.setHotelName(hi.getHotelName());
            hotel.setHotelTel(hi.getHotelTel());
            hotel.setHotelAddress(hi.getHotelAddress());
            hotel.setSpecialRemark(hi.getSpecialRemark());
            hotel.setCancellation(hi.getCancellation());
            hotel.setReimbursement(hi.getReimbursement());
            hotel.setPreference(hi.getPreference());
            hotel.setSeq(hi.getSeq());
            hotel.setCashBackSet(hi.getCashBackSet());
            hotel.setOtherSpecialRemark(hi.getOtherSpecialRemark());
            hotel.setCheckInTime(hi.getCheckInTime());
            hotel.setCheckOutTime(hi.getCheckOutTime());
            hotel.setWapperPriceErrorCode(hi.getWapperPriceErrorCode());
            hotel.setErrorMessageInfos(hi.getErrorMessageInfos());
        }
        return hotel;
    }

    public static AdapterHotel tranFPHotel(HotelInfo hi) {
        AdapterHotel hotel = null;
        if (hi != null) {
            hotel = new AdapterHotel();
            hotel.setId(Integer.parseInt(hi.getOtaHotelId()));
            hotel.setCityUrl(hi.getCityUrl());
            hotel.setHotelName(hi.getHotelName());
            hotel.setHotelTel(hi.getHotelTel());
            hotel.setHotelAddress(hi.getHotelAddress());
            hotel.setSeq(hi.getSeq());
        }
        return hotel;
    }

    public static AdapterRoom tranRoom(RoomInfo ri) {
        AdapterRoom room = null;
        if (ri == null || ri.getBedType() == null || ri.getBreakfast() == null || ri.getBroadband() == null) {
            return room;
        }
        if (ri != null) {
            room = new AdapterRoom();
            room.setRoomId(ri.getId());
            room.setHotelId(ri.getOwnHotelId());
            room.setRoomName(ri.getRoomName());
            room.setBedType(AdapterRoom.BedType.valueOf(ri.getBedType().name()));
            room.setBreakfast(AdapterRoom.BreadType.valueOf(ri.getBreakfast().name()));
            room.setBreakfastStr(ri.getBreakfastStr());
            room.setIntnet(AdapterRoom.BroadbandType.valueOf(ri.getBroadband().name()));
            room.setPayType(TransformUtil.getPayType(ri.getPayType()));
            // 对应房间的状态
            room.setStatus(ri.getStatus() == RoomInfo.Status.ACTIVE ? AdapterRoom.Status.ACTIVE
                    : AdapterRoom.Status.DISABLED);
            room.setInstantConfirm(ri.getInstantConfirm() == InstantConfirm.YES ? 1 : 0);
            // room.setRule(ri.getGuaranteeRule());
            room.setAllGuaranteeRule(ri.getAllGuaranteeRule());
            // 特殊价格
            room.setLast(ri.getLast());
            room.setMaxLast(ri.getMaxLastDay());
            room.setAdvance(ri.getAdvance());
            AdapterRoom.GuestType guestType = AdapterRoom.GuestType.ALL_GUEST;
            if(ri.getGuestType() != null){
                guestType = AdapterRoom.GuestType.codeOf(ri.getGuestType().code);
            }
            room.setGuestType(guestType);
            room.setRoomType(AdapterRoom.RoomType.valueOf(ri.getRoomType().name()));
            room.setRefuseState(ri.getRefuseState());
            room.setCustomKey(ri.getCustomKey());
            room.setSmoking(ri.isSmoking());
            room.setMaxRoomOccupancy(ri.getMaxRoomOccupancy());
            room.setMaxRoomNumber(ri.getMaxRoomNumber());
            room.setLatestArriveTime(ri.getLatestArriveTime());
            room.setMinGuestAge(ri.getMinGuestAge());
            room.setServiceFee(ri.getServiceFee());
            room.setBedTypes(tranBedTypeInfos(ri.getBedTypes()));
            room.setOnlineType(ri.getOnlineType());
            room.setCurrencyType(ri.getCurrencyType());
            room.setForexMoney(ri.getForexMoney());
            room.setRateDesc(ri.getRateDesc());
            room.setExtendInfos(ri.getExtendInfos());

            // 温馨提示
            room.setRemarks(ri.getSpecialRemark());
            room.setDescription(ri.getDescription());
            room.setBookingType(ri.getBookingType());
        }
        return room;
    }

    public static AdapterRoom tranFPRoom(RoomInfo ri) {
        AdapterRoom room = null;
        if (ri != null) {
            room = new AdapterRoom();
            room.setRoomType(AdapterRoom.RoomType.NORMAL);
            room.setRoomId(ri.getId());
            room.setHotelId(ri.getOwnHotelId());
            room.setRoomName(ri.getRoomName());
            room.setBedType(AdapterRoom.BedType.valueOf(ri.getBedType().name()));
            room.setBreakfast(AdapterRoom.BreadType.valueOf(ri.getBreakfast().name()));
            room.setBreakfastStr(ri.getBreakfastStr());
            room.setIntnet(AdapterRoom.BroadbandType.valueOf(ri.getBroadband().name()));
            room.setPayType(TransformUtil.getPayType(ri.getPayType()));
            // 对应房间的状态
            room.setStatus(ri.getStatus() == RoomInfo.Status.ACTIVE ? AdapterRoom.Status.ACTIVE
                    : AdapterRoom.Status.DISABLED);
            room.setRefuseState(ri.getRefuseState());
        }
        return room;
    }

    public static List<AdapterBedType> tranBedTypeInfos(List<BedTypeInfo> bedtypes) {
        List<AdapterBedType> adapterBedTypes = new ArrayList<AdapterBedType>();
        if (CollectionUtils.isNotEmpty(bedtypes)) {
            for (BedTypeInfo bedTypeInfo : bedtypes) {
                AdapterBedType adapterBedType = new AdapterBedType();
                adapterBedType.setId(bedTypeInfo.getId());
                adapterBedType.setDescription(bedTypeInfo.getDescription());
                adapterBedTypes.add(adapterBedType);
            }
        }
        return adapterBedTypes;
    }

    public static AdapterResult tranResult(OrderResult result) {
        AdapterResult adapterResult = new AdapterResult();
        adapterResult.setDes(result.getDes());
        adapterResult.setResult(result.isResult());
        adapterResult.setResultMap(result.getResultMap());
        return adapterResult;
    }

    public static Order tranOrder(AdapterOrder adapterOrder) {
        Order order = new Order();
        order.setId(adapterOrder.getId());
        order.setOrderNum(adapterOrder.getOrderNum());
        order.setUserId(adapterOrder.getUserId());
        order.setUserName(adapterOrder.getUserName());
        order.setHotelId(adapterOrder.getHotelId());
        order.setHotelName(adapterOrder.getHotelName());
        order.setHotelAddress(adapterOrder.getHotelAddress());
        order.setHotelPhone(adapterOrder.getHotelPhone());
        order.setCityName(adapterOrder.getCityName());
        order.setRoomName(adapterOrder.getRoomName());
        order.setRoomNum(adapterOrder.getRoomNum());
        order.setRoomId(adapterOrder.getRoomId());
        order.setBedType(adapterOrder.getBedType());
        order.setBreakfast(adapterOrder.getBreakfast());
        order.setWebfree(adapterOrder.getWebfree());
        order.setContactName(adapterOrder.getContactName());
        order.setContactPhone(adapterOrder.getContactPhone());
        order.setContactEmail(adapterOrder.getContactEmail());
        order.setCustomerNames(adapterOrder.getCustomerNames());
        order.setRemark(adapterOrder.getRemark());
        order.setFromDate(adapterOrder.getFromDate());
        order.setToDate(adapterOrder.getToDate());
        order.setPayMoney(adapterOrder.getPayMoney());
        order.setPayType(adapterOrder.getPayType());
        order.setOrderDate(adapterOrder.getOrderDate());
        order.setArriveTime(adapterOrder.getArriveTime());
        order.setStateUpdateTime(adapterOrder.getStateUpdateTime());
        order.setEveryDayPrice(adapterOrder.getEveryDayPrice());
        order.setCancellation(adapterOrder.getCancellation());
        order.setReimbursement(adapterOrder.getReimbursement());
        order.setSpecialRemark(adapterOrder.getSpecialRemark());
        order.setCityCode(adapterOrder.getCityCode());
        order.setConfirmationNumber(adapterOrder.getOrderInfoExtendMap().get("CANCELNUMBER"));
        order.setKey(adapterOrder.getPolicyId());
        order.setGuaranteeOrCharged(adapterOrder.isGuaranteeOrCharged());
        order.setForexMoney(adapterOrder.getForexMoney());
        order.setRoomGroups(tranRoomGroups(adapterOrder.getRoomGroup()));
        order.setOtaHotelId(adapterOrder.getOtaHotelId());
        order.setOrderChannelSource(adapterOrder.getOrderChannelSource());
        return order;
    }

    public static List<RoomGroup> tranRoomGroups(List<AdapterRoomGroup> adapterRoomGroups) {
        List<RoomGroup> roomGroup = new ArrayList<RoomGroup>();
        if (CollectionUtils.isNotEmpty(adapterRoomGroups)) {
            for (AdapterRoomGroup adapterRoomGroup : adapterRoomGroups) {
                RoomGroup group = new RoomGroup();
                try {
                    BeanUtils.copyProperties(group, adapterRoomGroup);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                roomGroup.add(group);
            }
        }
        return roomGroup;
    }

    public static AdapterOrder tranAdapterOrder(Order adapterOrder) {
        AdapterOrder order = new AdapterOrder();
        order.setId(adapterOrder.getId());
        order.setOrderNum(adapterOrder.getOrderNum());
        order.setUserId(adapterOrder.getUserId());
        order.setUserName(adapterOrder.getUserName());
        order.setHotelId(adapterOrder.getHotelId());
        order.setHotelName(adapterOrder.getHotelName());
        order.setHotelAddress(adapterOrder.getHotelAddress());
        order.setHotelPhone(adapterOrder.getHotelPhone());
        order.setCityName(adapterOrder.getCityName());
        order.setRoomName(getRatePlanRoomName(adapterOrder));
        order.setRoomNum(adapterOrder.getRoomNum());
        order.setRoomId(adapterOrder.getRoomId());
        order.setBedType(adapterOrder.getBedType());
        order.setBreakfast(adapterOrder.getBreakfast());
        order.setWebfree(adapterOrder.getWebfree());
        order.setContactName(adapterOrder.getContactName());
        order.setContactPhone(adapterOrder.getContactPhone());
        order.setContactEmail(adapterOrder.getContactEmail());
        order.setCustomerNames(adapterOrder.getCustomerNames());
        order.setRemark(adapterOrder.getRemark());
        order.setFromDate(adapterOrder.getFromDate());
        order.setToDate(adapterOrder.getToDate());
        order.setPayMoney(adapterOrder.getPayMoney());
        order.setPayType(adapterOrder.getPayType());
        order.setOrderDate(adapterOrder.getOrderDate());
        order.setArriveTime(adapterOrder.getArriveTime());
        order.setStateUpdateTime(adapterOrder.getStateUpdateTime());
        order.setEveryDayPrice(adapterOrder.getEveryDayPrice());
        order.setCancellation(adapterOrder.getCancellation());
        order.setReimbursement(adapterOrder.getReimbursement());
        order.setSpecialRemark(adapterOrder.getSpecialRemark());
        order.setCityCode(adapterOrder.getCityCode());
        order.setPolicyId(adapterOrder.getKey());
        order.getOrderInfoExtendMap().put("confirmationNumber", adapterOrder.getConfirmationNumber());
        order.setGuaranteeOrCharged(adapterOrder.isGuaranteeOrCharged());
        order.setOrderStatus(adapterOrder.getOrderStatus());
        order.setSerialId(adapterOrder.getSerialId());
        order.setTakeoffAmount(adapterOrder.getCommission());
        return order;
    }

    public static String getRatePlanRoomName(Order adapterOrder) {
        String roomName = adapterOrder.getRoomName();
        String ratePlanName = adapterOrder.getRatePlanName();
        String roomNameStr = StringUtils.isEmpty(roomName) ? roomName : roomName.replaceAll("\\（|\\(|\\）|\\)", "");
        String ratePlanNameStr = StringUtils.isEmpty(ratePlanName) ? ratePlanName : ratePlanName.replaceAll(
                "\\（|\\(|\\）|\\)", "");
        if (StringUtils.equals(roomNameStr, ratePlanNameStr)) {
            return roomName;
        }
        if (StringUtils.isNotEmpty(ratePlanName)) {
            roomName = roomName + "(" + ratePlanName.replaceAll("（", " ").replaceAll("）", "").trim() + ")";
        }
        return roomName;
    }

    public static AdapterPrice tranPrice(PriceInfo pi) {
        AdapterPrice ap = null;
        if (pi != null) {
            ap = new AdapterPrice();
            ap.setRoomId(pi.getRoomId());
            ap.setDate(DateUtils.truncate(pi.getDate(), Calendar.DAY_OF_MONTH));
            ap.setHotelId(pi.getHotelId());
            ap.setPrice(pi.getPrice());
            ap.setStatus(getPricesStatus(pi.getStatus()));
            ap.setCount(pi.getCount());
            ap.setCashBack(pi.getCashBack());
            ap.setCommission(pi.getCommission());
            if (pi.getBreakfast() == null) {
                pi.setBreakfast(RoomInfo.Breakfast.NONE);
                pi.setBreakfastStr(SysConfigUtil.BREAKFASTNONE);
            }
            ap.setBreakfast(AdapterRoom.BreadType.valueOf(pi.getBreakfast().name()));
            ap.setBreakfastStr(pi.getBreakfastStr());
            ap.setDeposit(pi.getDeposit());
            ap.setCurrencyCode(pi.getCurrencyCode());
            ap.setDiscountAmount(pi.getDiscount());
        }
        return ap;
    }

    private static AdapterRoom.PayType getPayType(RoomInfo.PayType type) {
        if (PayType.ONLINE.equals(type))
            return AdapterRoom.PayType.ONLINE;
        else
            return AdapterRoom.PayType.CASH;
    }

    private static AdapterPrice.Status getPricesStatus(int code) {
        return code == 0 ? AdapterPrice.Status.Enabled : AdapterPrice.Status.Disabled;
    }
    
    /**
     * 填补空白价格
     * 
     * @param from
     * @param to
     * @return
     */
    public static List<AdapterPrice> addBlankPrice(Date from, Date to, List<AdapterPrice> list) {
        List<AdapterPrice> result = new ArrayList<AdapterPrice>();
        Date currentDate = DateUtils.truncate(from, Calendar.DAY_OF_MONTH);
        to = DateUtils.truncate(to, Calendar.DAY_OF_MONTH);
        Map<Date, AdapterPrice> map = new HashMap<Date, AdapterPrice>();
        for (AdapterPrice pri : list) {
            map.put(DateUtils.truncate(pri.getDate(), Calendar.DAY_OF_MONTH), pri);
        }
        while (!DateUtils.isSameDay(currentDate, to)) {
            AdapterPrice ap = map.get(currentDate);
            if (ap != null) {
                result.add(ap);
            } else {
                result.add(createBlankPrice(0, "0", currentDate));
            }
            currentDate = DateUtils.addDays(currentDate, 1);
        }
        return result;
    }

    private static AdapterPrice createBlankPrice(int hotelId, String roomId, Date date) {
        AdapterPrice ap = new AdapterPrice();
        ap.setRoomId(roomId);
        ap.setHotelId(hotelId);
        ap.setDate(date);
        ap.setPrice(new BigDecimal("0.00"));
        ap.setStatus(AdapterPrice.Status.Disabled);
        ap.setBreakfast(AdapterRoom.BreadType.NONE);
        ap.setBreakfastStr(SysConfigUtil.BREAKFASTNONE);
        ap.setDiscountAmount(0);
        return ap;
    }
    

}
