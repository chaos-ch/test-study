package com.qunar.hotel.sa.product.price.bean;

import com.qunar.hotel.oas.bean.ErrorMessageInfo;
import com.qunar.hotel.oas.bean.OnlineTypeEnum;
import com.qunar.hotel.oas.bean.PriceProcessResult;
import com.qunar.hotel.oas.bean.PriceValidatorCode;
import com.qunar.hotel.oas.core.bean.RoomInfo.*;
import com.qunar.hotel.sa.product.hotel.bean.Channel;
import org.apache.commons.lang.math.NumberUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RoomInfoProcess implements RoomProcess {

    private static final Map<String, PayType> prepayMap = new HashMap<String, PayType>();
    static {
        prepayMap.put("0", PayType.ONLINE);
        prepayMap.put("1", PayType.CASH);
    }

    private RoomInfo roomInfo;
    private IRoom room;
    private String message;

    public RoomInfoProcess(IRoom room, RoomInfo roomInfo) {
        this.room = room;
        this.roomInfo = roomInfo;
    }

    @Override
    public PriceProcessResult check() {
        if (prepayMap.get(room.getPrepayValue()) == null) {
            message = "prepay unavailable";
            return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.ROOM_PAY_TYPE_ERROR, room.getId(),room.getName())));

        }
        if (BedType.codeOf(NumberUtils.toInt(room.getBedTypeValue())) == null) {
            message = "bed unavailable";
            return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.ROOM_BED_ERROR,room.getId(),room.getName())));

        }
        if (Broadband.codeOf(NumberUtils.toInt(room.getBroadbandValue())) == null) {
            message = "broadband unavailable";
            return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.ROOM_BROADBAND_ERROR,room.getId(),room.getName())));

        }
        if (prepayMap.get(room.getPrepayValue()) == PayType.CASH
                && Channel.LAST_MINUTE.getCode() == NumberUtils.toInt(room.getChannel(), -1)) {
            message = "last_minute channel unavailable";
            return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.ROOM_CHANNEL_UNAVAILABLE,room.getId(),room.getName())));

        }

        if (prepayMap.get(room.getPrepayValue()) == PayType.CASH
                && Channel.FIXED_PRICE.getCode() == NumberUtils.toInt(room.getChannel(), -1)) {
            message = "fix_price channel unavailable";
            return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.ROOM_CHANNEL_UNAVAILABLE,room.getId(),room.getName())));

        }

        if(GuestType.codeOf(NumberUtils.toInt(room.getGuestType())) == null) {
            message = "guestType unavailable or value error";
            return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.ROOM_GUEST_TYPE_ERROR, room.getId(), room.getName())));
        }

        return new PriceProcessResult(true,null);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void process() {
        roomInfo.setBedType(BedType.codeOf(NumberUtils.toInt(room.getBedTypeValue())));
        roomInfo.setBroadband(Broadband.codeOf(NumberUtils.toInt(room.getBroadbandValue())));
        roomInfo.setPayType(prepayMap.get(room.getPrepayValue()));
        roomInfo.setInstantConfirm(InstantConfirm.NO);

        if (roomInfo.getPayType() == PayType.ONLINE && room.getDeposits() != null) {
            roomInfo.setOnlineType(OnlineTypeEnum.PART_PAYMENT);
        } else {
            roomInfo.setOnlineType(OnlineTypeEnum.FULL_PAYMENT);
        }
        roomInfo.setChannel(Channel.valueOfCode(NumberUtils.toInt(room.getChannel(), -1)));
        roomInfo.setBookingType(room.getBookingType());
        roomInfo.setGuestType(GuestType.codeOf(NumberUtils.toInt(room.getGuestType())));
    }
}
