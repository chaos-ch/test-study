package com.qunar.hotel.sa.product.price.adapter.kaiyuan.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qunar.hotel.otatts.base.utils.OtaQMonitorUtils;
import com.qunar.hotel.otatts.base.utils.QmonitorNameEnum;
import com.qunar.hotel.otatts.common.common_api.hotel.bean.HotelInfo;
import com.qunar.hotel.otatts.common.common_api.room.bean.RoomDetailBean;
import com.qunar.hotel.otatts.common.common_api.room.bean.RoomDetailBean.PriceStatus;
import com.qunar.hotel.otatts.common.common_api.room.bean.RoomInfo;
import com.qunar.hotel.otatts.common.common_api.support.util.DateFormatUtils;
import com.qunar.hotel.otatts.provider.price.price.dao.HotelPricesMapper;
import com.qunar.hotel.otatts.provider.price.price.dao.RoomDetailMapper;

/**
 * @author weirongzhou
 * 
 */
@Service("kaiyuanRoomStatusPushProcessor")
public class KaiyuanRoomStatusPushProcessor extends AbstractRoomStatusPushProcessor {

    /**
     * 最大批量更新记录数
     */
    private static final int MAX_UPDATE_NUM = 500;

    @Resource
    private HotelPricesMapper hotelPricesMapper;

    @Resource
    private RoomDetailMapper roomDetailMapper;

    @Override
    protected void updateRoomStatus(HotelInfo hotelInfo) {

        if (hotelInfo == null) {
            return;
        }

        // 封装接口数据
        Map<String, RoomDetailBean> detailsMap = buildDetailsMap(hotelInfo);
        if (MapUtils.isEmpty(detailsMap)) {
            return;
        }

        // 封装数据库的数据
        Map<String, RoomDetailBean> dbDetailsMap = buildDbDetailsMap(hotelInfo);
        if (MapUtils.isEmpty(dbDetailsMap)) {
            OtaQMonitorUtils.recordOne(QmonitorNameEnum.API_PRICE_ROOM_STATUS_NOT_EXIST);
            logger.info("{} RoomDetail is null: otaHotelId={},checkInTime={},checkOutTime={}",
                    QmonitorNameEnum.API_PRICE_ROOM_STATUS_NOT_EXIST.name(), hotelInfo.getOtaHotelId(),
                    hotelInfo.getCheckInTime(), hotelInfo.getCheckOutTime());
            return;
        }

        // diff接口数据和数据库数据
        Map<PriceStatus, List<RoomDetailBean>> diffDetailsMap = diffRoomStatus(detailsMap, dbDetailsMap);

        // 更新数据库
        for (List<RoomDetailBean> diffDetails : diffDetailsMap.values()) {
            updateRoomDetail(diffDetails);
        }
    }

    /**
     * 将房态信息封装成Map
     * 
     * @param hotelInfo
     * @return
     */
    private Map<String, RoomDetailBean> buildDetailsMap(HotelInfo hotelInfo) {
        if (hotelInfo == null) {
            return Collections.emptyMap();
        }
        List<RoomInfo> rooms = hotelInfo.getRooms();
        if (CollectionUtils.isEmpty(rooms)) {
            return Collections.emptyMap();
        }
        Map<String, RoomDetailBean> detailsMap = Maps.newHashMap();
        List<RoomDetailBean> details = Lists.newArrayList();
        for (RoomInfo room : rooms) {
            if (CollectionUtils.isEmpty(room.getDetails())) {
                continue;
            }
            for (RoomDetailBean detail : room.getDetails()) {
                detailsMap.put(room.getOtaRoomId() + DateFormatUtils.format4y2M2d(detail.getDateTime()), detail);
                details.add(detail);
            }
        }
        setDateArea(hotelInfo, details);
        return detailsMap;
    }

    /**
     * 设置房态日期区间
     * 
     * @param hotelInfo
     * @param details
     */
    private void setDateArea(HotelInfo hotelInfo, List<RoomDetailBean> details) {
        Collections.sort(details, new Comparator<RoomDetailBean>() {
            @Override
            public int compare(RoomDetailBean o1, RoomDetailBean o2) {
                return o1.getDateTime().compareTo(o2.getDateTime());
            }
        });
        hotelInfo.setCheckInTime(DateFormatUtils.format4y2M2d(details.get(0).getDateTime()));
        hotelInfo.setCheckOutTime(DateFormatUtils.format4y2M2d(details.get(details.size() - 1).getDateTime()));
    }

    /**
     * 封装数据库的房态信息
     * 
     * @param hotelInfo
     * @return
     */
    private Map<String, RoomDetailBean> buildDbDetailsMap(HotelInfo hotelInfo) {
        HotelInfo dbHotelInfo = queryRoomDetail(hotelInfo);
        return buildDetailsMap(dbHotelInfo);
    }

    /**
     * 查询数据库的房态信息
     * 
     * @param hotelInfo
     * @return
     */
    private HotelInfo queryRoomDetail(HotelInfo hotelInfo) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("hotelInfoOtaHotelId", hotelInfo.getOtaHotelId());
        paramMap.put("roomDetailDateTimeFromDate", hotelInfo.getCheckInTime());
        paramMap.put("roomDetailDateTimeToDate", hotelInfo.getCheckOutTime());

        List<HotelInfo> dbHotelInfoList = hotelPricesMapper.queryHotelInfoJoinRoomInfoJoinRoomDetail(paramMap);
        HotelInfo dbHotelInfo = CollectionUtils.isEmpty(dbHotelInfoList) ? null : dbHotelInfoList.get(0);
        return dbHotelInfo;
    }

    /**
     * diff接口数据和数据库数据
     * 
     * @param detailsMap
     * @param dbDetailsMap
     * @return
     */
    private Map<PriceStatus, List<RoomDetailBean>> diffRoomStatus(Map<String, RoomDetailBean> detailsMap,
            Map<String, RoomDetailBean> dbDetailsMap) {
        Map<PriceStatus, List<RoomDetailBean>> classifyDetailsMap = Maps.newHashMap();
        for (PriceStatus status : PriceStatus.values()) {
            classifyDetailsMap.put(status, Lists.<RoomDetailBean> newArrayList());
        }
        for (String key : detailsMap.keySet()) {
            RoomDetailBean detail = detailsMap.get(key);
            RoomDetailBean dbDetail = dbDetailsMap.get(key);
            if (dbDetail != null && detail.getStatus() != dbDetail.getStatus()) {
                detail.setRoomId(dbDetail.getRoomId());
                detail.setId(dbDetail.getId());
                classifyDetailsMap.get(detail.getStatus()).add(detail);
            }
        }
        return classifyDetailsMap;
    }

    /**
     * 更新更新数据库房态信息
     * 
     * @param details
     */
    private void updateRoomDetail(List<RoomDetailBean> details) {
        if (CollectionUtils.isEmpty(details)) {
            return;
        }
        List<List<RoomDetailBean>> partitionDetails = Lists.partition(details, MAX_UPDATE_NUM);
        for (List<RoomDetailBean> updateDetails : partitionDetails) {
            PriceStatus staus = updateDetails.get(0).getStatus();
            if (staus == PriceStatus.Y) {
                roomDetailMapper.updateRoomDetailRoomStatus2Y(updateDetails);
            } else {
                roomDetailMapper.updateRoomDetailRoomStatus2N(updateDetails);
            }

        }
    }

}
