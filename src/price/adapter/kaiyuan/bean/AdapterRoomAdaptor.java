package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;


import com.qunar.hotel.sa.product.price.bean.Adaptor;
import com.qunar.hotel.sa.product.price.bean.RoomInfo;

/**
 * Created by he.chen on 14-10-27.
 * @version $Id$
 */
public class AdapterRoomAdaptor implements Adaptor<RoomInfo, AdapterRoom> {
    @Override
    public AdapterRoom adaptorFrom(RoomInfo bean) throws Exception {
        AdapterRoom room = new AdapterRoom();
        room.setRoomName(bean.getRoomName());
        room.setRoomId(bean.getOtaRoomId());
        room.setAdvance(bean.getAdvance());
        room.setLast(bean.getLast());
        room.setMaxLast(bean.getMaxLastDay());
        room.setBedType(AdapterRoom.BedType.codeOf(bean.getBedType().code));
        room.setBreakfast(AdapterRoom.BreadType.codeOf(bean.getBreakfast().code));
        room.setBreakfastStr(AdapterRoom.BreadType.codeOf(bean.getBreakfast().code).desc);
        room.setIntnet(AdapterRoom.BroadbandType.codeOf(bean.getBroadband().code));
        room.setRoomType(AdapterRoom.RoomType.valueOf(bean.getRoomType().name()));
        room.setStatus(bean.getStatus() == RoomInfo.Status.ACTIVE ? AdapterRoom.Status.ACTIVE
                : AdapterRoom.Status.DISABLED);
        room.setMaxRoomNumber(bean.getMaxRoomNumber()<=0?8:bean.getMaxRoomNumber());

        return room;
    }
}
