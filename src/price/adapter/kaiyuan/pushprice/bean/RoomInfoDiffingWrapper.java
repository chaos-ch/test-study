package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean;

/**
 * @author xu.cao created on 2014/6/13 18:25
 * @version $Id$
 */

import org.apache.commons.lang3.StringUtils;

import com.qunar.hotel.otatts.common.common_api.order.bean.PayType;
import com.qunar.hotel.otatts.common.common_api.room.bean.RoomInfo;

/**
 *房型基础信息的包装类（For指明：判断房型基础信息相同时，要比较的属性列）
 */
public class RoomInfoDiffingWrapper {

    /**
     * 待diff的room_info的引用
     */
    private RoomInfo roomInfo;

    public RoomInfoDiffingWrapper(RoomInfo roomInfo){
        this.roomInfo = roomInfo;
    }

    public RoomInfo getRoomInfo() {
        return roomInfo;
    }

    public void setRoomInfo(RoomInfo roomInfo) {
        this.roomInfo = roomInfo;
    }

    @Override
    public boolean equals(Object target) {
        if (this == target) return true;
        if (target == null || getClass() != target.getClass()) return false;

        RoomInfoDiffingWrapper that = (RoomInfoDiffingWrapper) target;

        if(this.roomInfo == that.roomInfo || (this.roomInfo == null && that.roomInfo == null)) return true;
        if((this.roomInfo == null && that.roomInfo != null) || (this.roomInfo != null && that.roomInfo == null)) return false;

        //全部的“非数据库相关属性”都相同，才算两者相同
        if(!(this.roomInfo.getBreakfast() == that.roomInfo.getBreakfast())) return false;
        if(!(this.roomInfo.getBedType() == that.roomInfo.getBedType())) return false;
        if(!(this.roomInfo.getBroadband() == that.roomInfo.getBroadband())) return false;
        if(!(this.roomInfo.getPayType() == that.roomInfo.getPayType())) return false;
        if(!(this.roomInfo.getStatus() == that.roomInfo.getStatus()))   return false;
        if(!(this.roomInfo.getRoomType() == that.roomInfo.getRoomType()))   return false;
        if(!(this.roomInfo.getLast() == that.roomInfo.getLast()))   return false;
        if(!(this.roomInfo.getMaxLastDay() == that.roomInfo.getMaxLastDay())) return false;
        if(!(this.roomInfo.getAdvance() == that.roomInfo.getAdvance())) return false;
        if(!(this.roomInfo.getBookType() == that.roomInfo.getBookType()))   return false;

        if(!StringUtils.equals(this.roomInfo.getRoomName(), that.roomInfo.getRoomName()))   return false;
        if(!StringUtils.equals(this.roomInfo.getOtaRoomId(), that.roomInfo.getOtaRoomId())) return false;
        if(!StringUtils.equals(this.roomInfo.getDescription(), that.roomInfo.getDescription())) return false;

        return true;
    }

    @Override
    public int hashCode() {

        String roomName = this.roomInfo.getRoomName();
        String otaRoomId = this.roomInfo.getOtaRoomId();
        String description = this.roomInfo.getDescription();

        int breakfast = this.roomInfo.getBreakfast();
        int bedType = this.roomInfo.getBedType();
        int broadband = this.roomInfo.getBroadband();
        int last = this.roomInfo.getLast();
        int maxLastDay = this.roomInfo.getMaxLastDay();
        int advance = this.roomInfo.getAdvance();

        PayType payType = this.roomInfo.getPayType();
        RoomInfo.Status status = this.roomInfo.getStatus();
        RoomInfo.RoomType roomType = this.roomInfo.getRoomType();
        RoomInfo.BookType bookType = this.roomInfo.getBookType();

        int result = roomName != null ? roomName.hashCode() : 0;
        result = 31 * result + (otaRoomId != null ? otaRoomId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + breakfast;
        result = 31 * result + bedType;
        result = 31 * result + broadband;
        result = 31 * result + (payType != null ? payType.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        result = 31 * result + last;
        result = 31 * result + maxLastDay;
        result = 31 * result + advance;
        result = 31 * result + (bookType != null ? bookType.hashCode() : 0);
        return result;
    }
}
