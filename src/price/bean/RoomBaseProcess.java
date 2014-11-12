package com.qunar.hotel.sa.product.price.bean;

import com.qunar.hotel.oas.bean.ErrorMessageInfo;
import com.qunar.hotel.oas.bean.PriceProcessResult;
import com.qunar.hotel.oas.bean.PriceValidatorCode;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

public class RoomBaseProcess implements RoomProcess {

    private final int MAX_ID_SIZE = 20;
    private final int MAX_NAME_SIZE = 50;

    private RoomInfo roomInfo;
    private IRoom room;
    private String message;

    public RoomBaseProcess(IRoom room, RoomInfo roomInfo) {
        this.room = room;
        this.roomInfo = roomInfo;
    }

    @Override
    public PriceProcessResult check() {
        if (StringUtils.isBlank(room.getId())) {
            message = "room id is empty";
            return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.ROOM_ID_EMPTY,room.getId(), room.getName())));
        }
        if (room.getId().length() > MAX_ID_SIZE) {
            message = "room id size overlength";
            return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.ROOM_ID_TOO_LONG,room.getId(),room.getName())));
        }
        if (StringUtils.isBlank(room.getName())) {
            message = "room name is empty ";
            return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.ROOM_NAME_EMPTY,room.getId(),room.getName())));
        }
        if (room.getName().length() > MAX_NAME_SIZE) {
            message = "room name size overlength";
            return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.ROOM_NAME_TOO_LONG,room.getId(),room.getName())));
        }
        return new PriceProcessResult(true,null);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void process() {
        roomInfo.setOtaRoomId(room.getId());
        roomInfo.setRoomName(trimToNull(room.getName()));
    }

    private static String trimToNull(String value) {
        return StringUtils.trimToNull(value);
    }
}
