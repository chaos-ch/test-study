package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.util;

import java.math.BigDecimal;
import java.util.*;

import com.qunar.hotel.sa.common.util.PropertyConfigUtils;
import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.AdapterHotel;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.AdapterRoom;
import com.qunar.hotel.sa.product.price.bean.RoomInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 房型报价处理工具类
 * 
 * @author weirongzhou
 * 
 */
public class ProductPriceUtils {

    /**
     * 参加连住特惠活动的城市
     */
    private static final List<String> hotelLastPromotionCities = Arrays.asList(PropertyConfigUtils.getItem("kaiyuan.hotel.lastPromotion.city", "").split(","));

    /**
     * 将oas的AdapterHotel转成小b的HotelInfo
     * 
     * @param adapterHotel
     * @return
     */
    public static HotelInfo transformHotelInfo(AdapterHotel adapterHotel) {
        if (adapterHotel == null) {
            return null;
        }
        HotelInfo hotelInfo = new HotelInfo();
        hotelInfo.setId(adapterHotel.getId());
        hotelInfo.setOtaHotelId(adapterHotel.getOtaHotelId());
        hotelInfo.setCityUrl(adapterHotel.getCityUrl());
        hotelInfo.setHotelName(adapterHotel.getHotelName());
        hotelInfo.setHotelAddress(adapterHotel.getHotelAddress());
        hotelInfo.setHotelTel(adapterHotel.getHotelTel());
        hotelInfo.setSeq(adapterHotel.getSeq());
        hotelInfo.setCancellation(adapterHotel.getCancellation());
        hotelInfo.setReimbursement(adapterHotel.getReimbursement());
        hotelInfo.setSpecialRemark(adapterHotel.getSpecialRemark());
        hotelInfo.setCheckInTime(adapterHotel.getCheckInTime());
        hotelInfo.setCheckOutTime(adapterHotel.getCheckOutTime());

        // 解析房型信息
        if (CollectionUtils.isNotEmpty(adapterHotel.getRooms())) {
            List<RoomInfo> rooms = new ArrayList<RoomInfo>();
            for (AdapterRoom adapterRoom : adapterHotel.getRooms()) {
                rooms.add(transformRoomInfo(adapterRoom));
            }
            hotelInfo.setRooms(rooms);
        }

        return hotelInfo;
    }

    /**
     * 将oas的AdapterRoom转成小b的RoomInfo
     * 
     * @param adapterRoom
     * @return
     */
    public static RoomInfo transformRoomInfo(AdapterRoom adapterRoom) {
        if (adapterRoom == null) {
            return null;
        }
        RoomInfo roomInfo = new RoomInfo();
        roomInfo.setHotelId(adapterRoom.getHotelId());
        roomInfo.setOtaRoomId(adapterRoom.getRoomId());
        roomInfo.setRoomName(adapterRoom.getRoomName());
        roomInfo.setRealName(adapterRoom.getRoomName());
        roomInfo.setBreakfast(adapterRoom.getBreakfast().code());
        roomInfo.setBreakfastStr(adapterRoom.getBreakfastStr());
        roomInfo.setBroadband(adapterRoom.getIntnet().code());
        roomInfo.setBroadbandStr(RoomControlUtils.getBoradbandStr(adapterRoom.getIntnet().code()));
        roomInfo.setPayType(adapterRoom.getPayType() == AdapterRoom.PayType.ONLINE ? PayType.ONLINE
                : PayType.CASH);
        OnlineTypeEnum onlineType = OnlineTypeEnum.enumOf(adapterRoom.getOnlineType().getCode());
        roomInfo.setOnlineType(onlineType == null ? OnlineTypeEnum.FULL_PAYMENT : onlineType);
        roomInfo.setStatus(adapterRoom.getStatus() == AdapterRoom.Status.ACTIVE ? Status.ACTIVE
                : Status.DELETED);
        String priceType = adapterRoom.getPriceType().name();
        roomInfo.setRoomType(StringUtils.isNotBlank(priceType) ? RoomType.valueOf(priceType) : RoomType.NORMAL);
        roomInfo.setLast(adapterRoom.getLast());
        roomInfo.setMaxLastDay(adapterRoom.getMaxLast());
        roomInfo.setAdvance(adapterRoom.getAdvance());
        roomInfo.setMaxRoomNum(adapterRoom.getMaxRoomNumber());
        roomInfo.setInstantConfirm(adapterRoom.getInstantConfirm());
        roomInfo.setThreshold(RoomInfo.HIHOLIDAY_THRESHOLD);
        roomInfo.setBookType(adapterRoom.getRefuseState() == 1 ? BookType.REFUSE_BOOK : BookType.CAN_BOOK);
        roomInfo.setDescription(adapterRoom.getDescription());

        // 国际酒店增加床型列表
        if (CollectionUtils.isNotEmpty(adapterRoom.getBedTypes())) {
            Map<String, String> bedMap = null;
            for (AdapterBedType adapterBedType : adapterRoom.getBedTypes()) {
                if (bedMap == null) {
                    bedMap = new HashMap<String, String>();
                }
                bedMap.put(adapterBedType.getId(), adapterBedType.getDescription());
            }
            roomInfo.setBedTypeStrMap(bedMap);
        } else {
            roomInfo.setBedType(adapterRoom.getBedType().code());
            roomInfo.setBedTypeStr(RoomControlUtils.getBedTypeStr(adapterRoom.getBedType().code()));
        }

        roomInfo.setServiceFee(adapterRoom.getServiceFee());
        roomInfo.setSmoking(Smoking.stringValueOf(adapterRoom.isSmoking() == null ? AdapterRoom.Smoking.UNKNOWN.name()
                : adapterRoom.isSmoking().name()));
        roomInfo.setMaxRoomOccupancy(adapterRoom.getMaxRoomOccupancy());
        if (adapterRoom.getMinGuestAge() > 0) {
            roomInfo.setMinGuestAge("至少有一名入住人年龄在" + adapterRoom.getMinGuestAge() + "岁以上");
        }
        roomInfo.setExtendInfos(adapterRoom.getExtendInfos());

        // 转换报价信息
        if (CollectionUtils.isNotEmpty(adapterRoom.getPrices())) {
            List<RoomDetailBean> details = new ArrayList<RoomDetailBean>();
            for (AdapterPrice adapterPrice : adapterRoom.getPrices()) {
                details.add(transformRoomDetail(adapterPrice));
            }
            roomInfo.setDetails(details);
        }

        return roomInfo;
    }

    /**
     * 将oas的AdapterPrice转成小b的RoomDetail
     * 
     * @param adapterPrice
     * @return
     */
    public static RoomDetailBean transformRoomDetail(AdapterPrice adapterPrice) {
        if (adapterPrice == null) {
            return null;
        }
        RoomDetailBean roomDetailBean = new RoomDetailBean();
        roomDetailBean.setRoomId(NumberUtils.toInt(adapterPrice.getRoomId()));
        roomDetailBean.setPrice(adapterPrice.getPrice());
        roomDetailBean.setCashBack(adapterPrice.getCashBack());
        roomDetailBean.setStatus(adapterPrice.getStatus() == AdapterPrice.Status.Enabled ? RoomDetailBean.PriceStatus.Y
                : RoomDetailBean.PriceStatus.N);
        roomDetailBean.setCount(adapterPrice.getCount());
        roomDetailBean.setBreakfast(adapterPrice.getBreakfast().code);
        roomDetailBean.setBreakfastStr(adapterPrice.getBreakfastStr());
        roomDetailBean.setDeposit(adapterPrice.getDeposit());
        roomDetailBean.setDateTime(adapterPrice.getDate());
        roomDetailBean.setDate(DateFormatUtils.format4y2M2d(adapterPrice.getDate()));

        return roomDetailBean;
    }

    /**
     * 生成房型状态标识
     * 
     * @param bookingType 预订方式
     * @param lastDay 连住天数
     * @param cityUrl 参加连住特惠的城市
     * @return 房型状态标识
     */
    public static String genRoomBit(RoomInfo.BookingType bookingType, int lastDay, String cityUrl) {
        List<StateBitEnum> bitEnums = Lists.newArrayList();
        bitEnums.add(StateBitEnum.BOOKING_TYPE);
        List<Integer> values = Lists.newArrayList();
        values.add((bookingType == null ? RoomInfo.BookingType.BOOKING : bookingType).getType());
        if (lastDay > 1 && StringUtils.isNotBlank(cityUrl) && hotelLastPromotionCities.contains(cityUrl)) {
            bitEnums.add(StateBitEnum.LAST_DAY);
            values.add(OpenCloseState.OPEN.getCode());
        }
        return String.valueOf(StateBitUtils.formatStatus(bitEnums, values));
    }

    /**
     * 根据房型价格以及保险产品计算保险价格
     * 
     * @param priceInfoMode
     * @param insuranceInfos
     * @return
     */
    public static List<OrderInsuranceInfo> rebuildOrderInsuranceInfos(PriceInfoMode priceInfoMode,
            List<OrderInsuranceInfo> insuranceInfos) {
        if (CollectionUtils.isEmpty(insuranceInfos)) {
            return insuranceInfos;
        }
        List<BigDecimal> eachDayPriceList = getEachDayPriceList(priceInfoMode.getDailyPrices());
        BigDecimal allDaysPriceOfOneRoom = getAllDaysPriceOfOneRoom(eachDayPriceList);
        Map<String, Object> eachPrice1to8Room = getEachPrice1to8Room(allDaysPriceOfOneRoom);

        for (OrderInsuranceInfo insuranceInfo : insuranceInfos) {
            insuranceInfo.setEachRoomPrice(OrderInsuranceUtils.getEachRoomInsurancePrice(
                    insuranceInfo.getInsuranceProduct(), eachPrice1to8Room, priceInfoMode.getServiceFee()));
        }
        return insuranceInfos;
    }

    private static List<BigDecimal> getEachDayPriceList(List<DailyPrice> dailyPrices) {
        return Lists.transform(dailyPrices, new Function<DailyPrice, BigDecimal>() {
            @Override
            public BigDecimal apply(DailyPrice input) {
                return input.getPrice();
            }
        });
    }

    private static BigDecimal getAllDaysPriceOfOneRoom(List<BigDecimal> eachDayPriceList) {
        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal curDayPrice : eachDayPriceList) {
            result = result.add(curDayPrice.compareTo(new BigDecimal(-1)) == 0 ? BigDecimal.ZERO : curDayPrice);
        }
        return result;
    }

    private static Map<String, Object> getEachPrice1to8Room(BigDecimal allDaysPriceOfOneRoom) {
        Map<String, Object> priceMap = Maps.newTreeMap();
        for (int i = 1; i <= 8; i++) {
            BigDecimal price = allDaysPriceOfOneRoom.multiply(new BigDecimal(i));
            priceMap.put("roomNum" + i, price.setScale(2, BigDecimal.ROUND_UP));
        }
        return priceMap;
    }

}
