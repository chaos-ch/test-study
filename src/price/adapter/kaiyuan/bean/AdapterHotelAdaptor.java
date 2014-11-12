package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.util.ArrayList;
import java.util.List;

import com.qunar.hotel.sa.common.util.JsonUtil;
import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.price.bean.Adaptor;
import com.qunar.hotel.sa.product.price.bean.HotelParam;
import com.qunar.hotel.sa.product.price.bean.RoomInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.type.TypeReference;


/**
 * Created by he.chen on 14-10-27.
 * @version $Id$
 */
public class AdapterHotelAdaptor implements Adaptor<HotelParam, AdapterHotel> {

    @Override
    public AdapterHotel adaptorFrom(HotelParam bean) throws Exception {
        HotelInfo hotelInfo = bean.getHotelInfo();
        AdapterHotel adapterHotel = bean.getAdapterHotel();

        adapterHotel.setHotelName(hotelInfo.getHotelName());
        adapterHotel.setHotelAddress(hotelInfo.getHotelAddress());
        adapterHotel.setHotelTel(hotelInfo.getHotelTel());
        adapterHotel.setId(hotelInfo.getId());
        adapterHotel.setOtaHotelId(hotelInfo.getOtaHotelId());
        adapterHotel.setSeq(hotelInfo.getSeq());
        adapterHotel.setCityUrl(hotelInfo.getCityUrl());
        if (hotelInfo != null && hotelInfo.getContractStatus() != null){
            adapterHotel.setContractStatus(hotelInfo.getContractStatus().name());
            adapterHotel.setContractExpiredTime(hotelInfo.getContractExpiredTime());
        }
        if (StringUtils.isBlank(adapterHotel.getCancellation())) {
            adapterHotel.setCancellation(hotelInfo.getCancellation());
        }
        if (StringUtils.isBlank(adapterHotel.getReimbursement())) {
            adapterHotel.setReimbursement(hotelInfo.getReimbursement());
        }
        if (StringUtils.isBlank(adapterHotel.getCheckInTime())) {
            adapterHotel.setCheckInTime(hotelInfo.getCheckInTime());
        }
        if (StringUtils.isBlank(adapterHotel.getCheckOutTime())) {
            adapterHotel.setCheckOutTime(hotelInfo.getCheckOutTime());
        }

        if (StringUtils.isBlank(adapterHotel.getSpecialRemark())) {
            adapterHotel.setSpecialRemark(hotelInfo.getSpecialRemark());
        } else {
            setSpecialRemark(hotelInfo, adapterHotel);
        }
        if(CollectionUtils.isNotEmpty(adapterHotel.getRooms()) && hotelInfo.getBookingType() != RoomInfo.BookingType.BOOKING.getType()){
            for(AdapterRoom room :adapterHotel.getRooms()){
                if(StringUtils.isEmpty(room.getBookingType())){
                    room.setBookingType(String.valueOf(hotelInfo.getBookingType()));
                }
            }
        }
        return adapterHotel;
    }

    private void setSpecialRemark(HotelInfo hotelInfo, AdapterHotel result) {
        try {
            List<String> hotelRemarkList = new ArrayList<String>();
            if (StringUtils.isNotEmpty(result.getSpecialRemark())
                    && StringUtils.isNotEmpty(hotelInfo.getSpecialRemark())) {
                List<String> roomRemarkList = JsonUtil.getObjectMapperInstance().readValue(result.getSpecialRemark(),
                        new TypeReference<List<String>>() {
                        });

                if (StringUtils.isNotEmpty(hotelInfo.getSpecialRemark())) {
                    hotelRemarkList = JsonUtil.getObjectMapperInstance().readValue(hotelInfo.getSpecialRemark(),
                            new TypeReference<List<String>>() {
                            });
                }

                hotelRemarkList.addAll(roomRemarkList);
            }

            if (!hotelRemarkList.isEmpty()) {
                result.setSpecialRemark(JsonUtil.getObjectMapperInstance().writeValueAsString(hotelRemarkList));
            }
        } catch (Exception e) {

        }
    }

    private String filterCheckTime(boolean checkIn, String time) {
        if (StringUtils.isBlank(time)) {
            return null;
        }
        StringBuilder timeDesc = new StringBuilder(checkIn ? "入住办理时间：" : "离店办理时间：");
        if (StringUtils.equalsIgnoreCase(time.trim(), "noon")) {
            time = "中午";
        }
        return timeDesc.append(time).toString();
    }

}
