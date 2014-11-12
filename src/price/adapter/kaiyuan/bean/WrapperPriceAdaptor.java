package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.math.BigDecimal;
import java.util.List;

import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.hotel.bean.Pair;
import com.qunar.hotel.sa.product.price.bean.Adaptor;
import com.qunar.hotel.sa.product.price.bean.HotelParam;
import com.qunar.hotel.sa.product.price.bean.RoomDetailBean;
import com.qunar.hotel.sa.product.price.bean.RoomInfo;
import org.apache.commons.collections.CollectionUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * 开元Wrapper报价适配
 * 
 * Created by he.chen on 14-10-27.
 * @version $Id$
 */
public class WrapperPriceAdaptor implements
        Adaptor<HotelParam, Pair<AdapterHotel, List<Pair<AdapterRoom, List<AdapterPrice>>>>> {

    private static final int IGNORE_DAYS = 0;
    private static final int MAX_LAST_DAY = 5;

    @Override
    public Pair<AdapterHotel, List<Pair<AdapterRoom, List<AdapterPrice>>>> adaptorFrom(HotelParam bean)
            throws Exception {

        HotelInfo hotelInfo = bean.getHotelInfo();

        AdapterHotel adapterHotel = new AdapterHotel();
        adapterHotel.setId(hotelInfo.getId());
        adapterHotel.setCityUrl(hotelInfo.getCityUrl());
        adapterHotel.setHotelName(hotelInfo.getHotelName());
        adapterHotel.setHotelTel(hotelInfo.getHotelTel());
        adapterHotel.setHotelAddress(hotelInfo.getHotelAddress());
        adapterHotel.setSpecialRemark(hotelInfo.getSpecialRemark());
        adapterHotel.setCancellation(hotelInfo.getCancellation());
        adapterHotel.setReimbursement(hotelInfo.getReimbursement());
        adapterHotel.setSeq(hotelInfo.getSeq());
        adapterHotel.setCheckInTime(hotelInfo.getCheckInTime());
        adapterHotel.setCheckOutTime(hotelInfo.getCheckOutTime());
        adapterHotel.setOtaHotelId(hotelInfo.getOtaHotelId());
        if (hotelInfo != null && hotelInfo.getContractStatus() != null){
            adapterHotel.setContractStatus(hotelInfo.getContractStatus().name());
            adapterHotel.setContractExpiredTime(hotelInfo.getContractExpiredTime());
        }

        List<RoomInfo> rooms = hotelInfo.getRooms();
        List<AdapterRoom> adapterRooms = adapterHotel.getRooms();
        if (adapterRooms == null) {
            adapterRooms = Lists.newArrayList();
        }

        for (RoomInfo room : rooms) {
            // 最大连住天数处理
            int queryDays = room.getDetails().size();
            if (room.getMaxLastDay() > IGNORE_DAYS && queryDays > room.getMaxLastDay()) {
                continue;
            }

            AdapterRoom adapterRoom = new AdapterRoom();
            adapterRoom.setRoomId(room.getOtaRoomId());
            adapterRoom.setHotelId(hotelInfo.getId());
            adapterRoom.setRoomName(room.getRoomName());

            adapterRoom.setBreakfast(AdapterRoom.BreadType.codeOf(room.getBreakfast().code));
            adapterRoom.setBreakfastStr(adapterRoom.getBreakfast() == null ? null : adapterRoom.getBreakfast().desc);
            adapterRoom.setBedType(AdapterRoom.BedType.codeOf(room.getBedType().code));
            adapterRoom.setIntnet(AdapterRoom.BroadbandType.codeOf(room.getBroadband().code));

            adapterRoom.setMaxRoomNumber(MAX_LAST_DAY);
            adapterRoom.setInstantConfirm(room.getInstantConfirm().code);
            adapterRoom.setSmoking(AdapterRoom.Smoking.UNKNOWN);
            adapterRoom.setPayType(room.getPayType() == RoomInfo.PayType.ONLINE ? AdapterRoom.PayType.ONLINE
                    : AdapterRoom.PayType.CASH);
            adapterRoom.setStatus(room.getStatus() == RoomInfo.Status.ACTIVE ? AdapterRoom.Status.ACTIVE
                    : AdapterRoom.Status.DISABLED);


            // 房型类型设置
            adapterRoom.setRoomType(AdapterRoom.RoomType.valueOf(room.getRoomType().name()));
            adapterRoom.setLast(room.getLast());
            adapterRoom.setMaxLast(room.getMaxLastDay());
            adapterRoom.setAdvance(room.getAdvance());

            adapterRoom.setPrices(transformPrice(room.getDetails(), adapterRoom));
            adapterRooms.add(adapterRoom);
        }
        adapterHotel.setRooms(adapterRooms);

        List<Pair<AdapterRoom, List<AdapterPrice>>> pair = Lists.newArrayList();
        for (AdapterRoom adapterRoom : adapterHotel.getRooms()) {
            pair.add(new Pair<AdapterRoom, List<AdapterPrice>>(adapterRoom, adapterRoom.getPrices()));
        }
        return new Pair<AdapterHotel, List<Pair<AdapterRoom, List<AdapterPrice>>>>(adapterHotel, pair);
    }

    private List<AdapterPrice> transformPrice(List<RoomDetailBean> roomDetailBeans, final AdapterRoom adapterRoom) {
        if (CollectionUtils.isEmpty(roomDetailBeans)) {
            return Lists.newArrayList();
        }

        return Lists.transform(roomDetailBeans, new Function<RoomDetailBean, AdapterPrice>() {
            @Override
            public AdapterPrice apply(RoomDetailBean bean) {
                AdapterPrice adapterPrice = new AdapterPrice();
                adapterPrice.setRoomId(String.valueOf(bean.getId()));
                adapterPrice.setDate(bean.getDateTime());

                BigDecimal scale = bean.getPrice();
                adapterPrice.setPrice(scale);
                if (scale.compareTo(BigDecimal.ZERO) <= 0) {// 价格小于等于0，强行设置房态为 关房
                    adapterPrice.setStatus(AdapterPrice.Status.Disabled);
                } else {
                    adapterPrice.setStatus(bean.getStatus() == RoomDetailBean.PriceStatus.Y ? AdapterPrice.Status.Enabled
                            : AdapterPrice.Status.Disabled);
                }

                adapterPrice.setCount(bean.getCount());
                adapterPrice.setCashBack(bean.getCashBack());

                adapterPrice.setBreakfast(adapterRoom.getBreakfast());
                adapterPrice.setBreakfastStr(adapterRoom.getBreakfast().desc);
                return adapterPrice;
            }
        });
    }

}
