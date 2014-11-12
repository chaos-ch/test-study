package com.qunar.hotel.sa.product.price.bean;

import com.qunar.hotel.oas.bean.PriceProcessResult;
import com.qunar.hotel.oas.core.util.PriceTypeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public class RoomAdvanceProcess implements RoomProcess {

    private RoomInfo roomInfo;
    private IRoom room;
    private String message;

    public RoomAdvanceProcess(IRoom room, RoomInfo roomInfo) {
        this.room = room;
        this.roomInfo = roomInfo;
    }

    @Override
    public PriceProcessResult check() {
        return new PriceProcessResult(true,null);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void process() {
        int last = NumberUtils.toInt(room.getLast(), 0);
        int advance = NumberUtils.toInt(room.getAdvance(), 0);
        roomInfo.setLast(last);
        roomInfo.setAdvance(advance);
        roomInfo.setPriceType(PriceTypeUtils.roomPriceType(last, advance));
        roomInfo.setRefuseState(StringUtils.equals(room.getRefusestate(), "1") ? 1 : 0);
    }
}
