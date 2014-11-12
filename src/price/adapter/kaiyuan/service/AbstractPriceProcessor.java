package com.qunar.hotel.sa.product.price.adapter.kaiyuan.service;

import java.util.ArrayList;
import java.util.List;

import com.qunar.hotel.sa.common.annotation.MonitorMethod;
import com.qunar.hotel.sa.product.hotel.bean.Channel;
import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.*;
import com.qunar.hotel.sa.product.price.bean.OtaPriceParam;
import com.qunar.hotel.sa.product.price.bean.PriceInfo;
import com.qunar.hotel.sa.product.price.bean.RoomInfo;
import com.qunar.hotel.sa.product.price.service.IOtaPriceInterface;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public abstract class AbstractPriceProcessor {

    public Logger logger = LoggerFactory.getLogger(getClass());

    protected OtaPriceParam otaPriceParam;
    protected PriceInfoString priceInfoString;

    public OtaPriceParam getOtaPriceParam() {
        return otaPriceParam;
    }

    public void setOtaPriceParam(OtaPriceParam otaPriceParam) {
        this.otaPriceParam = otaPriceParam;
    }

    public PriceInfoString getPiceInfoString() {
        return priceInfoString;
    }

    public void setPriceInfoString(PriceInfoString riceInfoString) {
        this.priceInfoString = riceInfoString;
    }

    public IOtaPriceInterface getOtaInterface() {
        return OtaFactroy.newPriceRealization(this.otaPriceParam.getSupplierInfo().getOtaType());
    }

    /**
     * 将HotelInfo适配为AdapterHotel
     *
     * @param hotelInfo
     * @param needAddBlankPrice 是否需要补充空白价格，wrapper和order的请求都需要，其余暂时不需要
     * @return
     */
    protected AdapterHotel tranPriceToApi(HotelInfo hotelInfo, boolean needAddBlankPrice) {

        List<AdapterRoom> otaRooms = new ArrayList<AdapterRoom>();
        //if (hotelInfo == null || CollectionUtils.isEmpty(hotelInfo.getRoomInfo())) {
        if (hotelInfo == null) {
            return null;
        }

        Channel channel = Channel.valueOfName(this.otaPriceParam.getChannel());
        if(CollectionUtils.isNotEmpty(hotelInfo.getRoomInfo())) {
            for (RoomInfo room : hotelInfo.getRoomInfo()) {
                try {
                    List<Channel> supportsChannels = room.getSupportChannels();
                    if (supportsChannels == null) {
                        supportsChannels = Lists.newArrayList(Channel.MAIN);
                    }
                    if(!supportsChannels.contains(channel)){
                        hotelInfo.setErrorMessage(hotelInfo, PriceValidatorCode.ROOM_CHANNEL_NOT_MATCH, ErrorLevel.ROOM, room.getOtaRoomId(), room.getRoomName());
                        //logger.info("报价过滤，channel:{},channelList:{},roomInfo:{}",channel, JsonUtils.toJson(room.getSupportChannels()),JsonUtils.toJson(room));
                        continue;
                    }

                    room.setChannel(channel);
                    AdapterRoom otaRoom = TransformUtil.tranRoom(room);
                    if (otaRoom == null || CollectionUtils.isEmpty(room.getPriceInfo())) {
                        logger.info("tranRoom null,room:{}", room.toString());
                        continue;
                    }
                    List<AdapterPrice> list = new ArrayList<AdapterPrice>(room.getPriceInfo().size());
                    for (PriceInfo pi : room.getPriceInfo()) {
                        list.add(TransformUtil.tranPrice(pi));
                    }
                    if (needAddBlankPrice) {
                        List<AdapterPrice> fullPri = TransformUtil.addBlankPrice(this.otaPriceParam.getCheckIn(),
                                this.otaPriceParam.getCheckOut(), list);
                        otaRoom.setPrices(fullPri);
                    } else {
                        otaRoom.setPrices(list);
                    }
                    otaRooms.add(otaRoom);
                } catch (Exception ex) {
                    logger.error("parse room error", ex);
                }
            }
        }
        AdapterHotel ah = TransformUtil.tranHotel(hotelInfo);
        ah.setRooms(otaRooms);
        //logger.info("================result##############:{}", JsonUtils.toJson(ah));
        return ah;
    }

    @MonitorMethod
    abstract AdapterHotel process();
}
