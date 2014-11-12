package com.qunar.hotel.sa.product.price.adapter.kaiyuan.controller;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.qunar.hotel.sa.product.price.adapter.kaiyuan.dao.HotelPricesMapper;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.dao.ProductPriceBatchUpdateService;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.dao.RoomDetailMapper;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.dao.RoomInfoMapper;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean.RoomInfoDiffResult;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.util.ProductPriceDiffingUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qunar.hotel.otatts.common.common_api.hotel.bean.HotelInfo;
import com.qunar.hotel.otatts.common.common_api.room.bean.RoomDetailBean;
import com.qunar.hotel.otatts.common.common_api.room.bean.RoomInfo;

/**
 * @author xu.cao created on 2014/6/13 1:17
 * @version $Id$
 */

@Service("kaiyuanProductPricePushProcessor")
public class KaiyuanProductPricePushProcessor extends AbstractProductPricePushProcessor {

    @Resource
    private HotelPricesMapper hotelPricesMapper;

    @Resource
    private RoomInfoMapper roomInfoMapper;

    @Resource
    private RoomDetailMapper roomDetailMapper;

    @Resource
    private ProductPriceBatchUpdateService productPriceBatchUpdateService;

    /**
     * 处理并更新报价至本地数据库
     * 
     * @param hotelInfo
     * @throws java.sql.SQLException
     */
    @Override
    protected void updateProductPrice(HotelInfo hotelInfo) {

        // 参数检查
        if (hotelInfo == null || CollectionUtils.isEmpty(hotelInfo.getRooms())) {
            // 监控
            return;
        }

        // 从数据库中获取待更新报价的 酒店的基础信息 与 原有的房型信息
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("hotelInfoOtaHotelId", hotelInfo.getOtaHotelId());
        List<HotelInfo> dbHotelInfoList = hotelPricesMapper.queryHotelInfoJoinRoomInfo(paramMap);
        HotelInfo dbHotelInfo = CollectionUtils.isEmpty(dbHotelInfoList) ? null : dbHotelInfoList.get(0);
        if (dbHotelInfo == null) {
            // 监控
            return;
        }

        Map<String, RoomInfo> dbRoomInfoMap = buildRoomInfoMap(dbHotelInfo); // left
        Map<String, RoomInfo> roomInfoMap = buildRoomInfoMap(hotelInfo); // right

        // Diff数据库中原有的房型信息 与 解析报价得到的房型信息
        RoomInfoDiffResult roomInfoDiffResult = ProductPriceDiffingUtils.diffRoomInfo(dbRoomInfoMap, roomInfoMap);

        // 获取待更新的房型信息,更新至数据库
        List<RoomInfo> roomInfoListToUpdate = buildRoomInfoListToUpdate(roomInfoDiffResult);
        updateRoomInfos(dbRoomInfoMap, roomInfoListToUpdate);

        // 获取待插入的房型信息，插入至数据库
        List<RoomInfo> roomInfoListToInsert = buildRoomInfoListToInsert(roomInfoDiffResult);
        insertRoomInfos(dbHotelInfo.getId(), roomInfoListToInsert);

        // 插入or更新报价信息至数据库
        insertOrUpdateRoomDetails(dbHotelInfo.getId(), hotelInfo.getRooms());
    }

    /**
     * 从Diff结果中取得 更新的房型信息
     */
    private List<RoomInfo> buildRoomInfoListToUpdate(RoomInfoDiffResult roomInfoDiffResult) {

        List<RoomInfo> roomInfoList = Lists.newArrayList();

        Map<String, Pair<RoomInfo, RoomInfo>> entriesDiffering = roomInfoDiffResult.getEntriesDiffering();
        for (String curKey : entriesDiffering.keySet()) {
            roomInfoList.add(entriesDiffering.get(curKey).getRight());
        }

        return roomInfoList;
    }

    /**
     * 从Diff结果中取得 新增的房型信息
     */
    private List<RoomInfo> buildRoomInfoListToInsert(RoomInfoDiffResult roomInfoDiffResult) {
        Map<String, RoomInfo> entriesOnlyOnRight = roomInfoDiffResult.getEntriesOnlyOnRight();

        return Lists.newArrayList(entriesOnlyOnRight.values());
    }

    /**
     * 建立指定酒店中 房型ota_room_id 与 房型的映射关系
     *
     * @param hotelInfo
     * @return
     */
    private Map<String, RoomInfo> buildRoomInfoMap(HotelInfo hotelInfo) {

        List<RoomInfo> roomInfoList = hotelInfo.getRooms();
        if (CollectionUtils.isEmpty(roomInfoList)) {
            return Collections.emptyMap();
        }

        Map<String, RoomInfo> roomInfoMap = Maps.newHashMap();
        for (RoomInfo curRoomInfo : roomInfoList) {
            if (curRoomInfo != null) {
                roomInfoMap.put(curRoomInfo.getOtaRoomId(), curRoomInfo);
            }
        }

        return roomInfoMap;
    }

    /**
     * 将 本次同步报价中需要更新至数据库的房型信息 更新至数据库
     *
     * @param dbRoomInfoMap 数据库中已存在的房型条目与ota_room_id的映射关系
     * @param roomInfoList 待更新至数据库中的房型条目
     * @return
     * @throws java.sql.SQLException
     */
    private boolean updateRoomInfos(Map<String, RoomInfo> dbRoomInfoMap, List<RoomInfo> roomInfoList) {

        if (CollectionUtils.isEmpty(roomInfoList)) {
            return true;
        }

        // 对原始房型信息设置 数据库表id (根据id更新数据)
        for (RoomInfo curRoomInfo : roomInfoList) {
            curRoomInfo.setId(dbRoomInfoMap.get(curRoomInfo.getOtaRoomId()).getId());
        }

        return productPriceBatchUpdateService.updateRoomInfos(roomInfoList);
    }

    private boolean insertRoomInfos(int dbHotelId, List<RoomInfo> roomInfoList) {

        if (CollectionUtils.isEmpty(roomInfoList)) {
            return true;
        }

        // 对原始房型信息设置物理hotel_id
        for (RoomInfo curRoomInfo : roomInfoList) {
            curRoomInfo.setHotelId(dbHotelId);
        }

        return roomInfoMapper.insertRoomInfo(roomInfoList) > 0;
    }

    private boolean insertOrUpdateRoomDetails(int dbHotelId, List<RoomInfo> roomInfoList) {

        if (CollectionUtils.isEmpty(roomInfoList)) {
            return true;
        }

        // 获取原始房型信息的物理自增id，即报价信息的room_id
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("roomInfoHotelId", dbHotelId);
        List<RoomInfo> dbRoomInfoList = roomInfoMapper.queryRoomInfo(paramMap);

        Map<String, Integer> otaRoomId2Id = Maps.newHashMap();
        for (RoomInfo curDbRoomInfo : dbRoomInfoList) {
            otaRoomId2Id.put(curDbRoomInfo.getOtaRoomId(), curDbRoomInfo.getId());
        }

        List<RoomDetailBean> allRoomDetailsOfHotel = Lists.newArrayList();
        for (RoomInfo curRoomInfo : roomInfoList) {
            List<RoomDetailBean> curRoomDetailBeanList = curRoomInfo.getDetails();
            if (CollectionUtils.isEmpty(curRoomDetailBeanList)) {
                continue;
            }

            int roomDetailRoomId = otaRoomId2Id.get(curRoomInfo.getOtaRoomId());
            for (RoomDetailBean curRoomDetailBean : curRoomDetailBeanList) {
                curRoomDetailBean.setRoomId(roomDetailRoomId);
                curRoomDetailBean.setCreateTime(Calendar.getInstance().getTime());
            }
            allRoomDetailsOfHotel.addAll(curRoomDetailBeanList);
        }

        return roomDetailMapper.insertOrUpdateRoomDetail(allRoomDetailsOfHotel) > 0;
    }

}
