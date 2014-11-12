package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.util;

/**
 * @author xu.cao created on 2014/6/13 3:24
 * @version $Id$
 */

import java.util.Map;

import com.google.common.collect.MapDifference;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean.RoomInfoDiffResult;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean.RoomInfoDiffingWrapper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Maps;
import com.qunar.hotel.otatts.common.common_api.room.bean.RoomInfo;

/**
 * Diff 报价工具类——提供报价信息的Diffing的相关方法 提供三种级别报价实体的Diffing： 1、不同酒店 基础信息 的Diffing ——ota_hotel_id 为KEY 2、相同酒店 中不同房型 基础信息
 * 的Diffing ——ota_room_id 为KEY 3、相同酒店 中相同房型 不同日期 的报价 的Diffing ——date_time 为KEY
 */
public class ProductPriceDiffingUtils {

    // TODO
    /**
     * 以ota_hotel_id为KEY，Diff酒店基础信息
     * 
     * @param left
     * @param right
     * @return
     */
    /*
     * public static HotelInfoDiffResult diffHotelInfo(Map<String, HotelInfo> left, Map<String, HotelInfo> right){
     * return null; }
     */

    /**
     * 以ota_room_id为KEY，Diff相同酒店中不同房型的基础信息
     * 
     * @param left
     * @param right
     * @return
     */
    public static RoomInfoDiffResult diffRoomInfo(Map<String, RoomInfo> left, Map<String, RoomInfo> right) {
        Map<String, RoomInfoDiffingWrapper> wrapperLeft = Maps.newHashMap();
        Map<String, RoomInfoDiffingWrapper> wrapperRight = Maps.newHashMap();

        for (String curKey : left.keySet()) {
            wrapperLeft.put(curKey, new RoomInfoDiffingWrapper(left.get(curKey)));
        }
        for (String curKey : right.keySet()) {
            wrapperRight.put(curKey, new RoomInfoDiffingWrapper(right.get(curKey)));
        }

        final MapDifference<String, RoomInfoDiffingWrapper> result = Maps.difference(wrapperLeft, wrapperRight);

        Map<String, RoomInfo> entriesOnlyOnLeft = Maps.newHashMap();
        Map<String, RoomInfo> entriesOnlyOnRight = Maps.newHashMap();
        Map<String, RoomInfo> entriesInCommon = Maps.newHashMap();
        Map<String, Pair<RoomInfo, RoomInfo>> entriesDiffering = Maps.newHashMap();

        for (final String curOnlyLeftKey : result.entriesOnlyOnLeft().keySet()) {
            entriesOnlyOnLeft.put(curOnlyLeftKey, result.entriesOnlyOnLeft().get(curOnlyLeftKey).getRoomInfo());
        }

        for (final String curOnlyRightKey : result.entriesOnlyOnRight().keySet()) {
            entriesOnlyOnRight.put(curOnlyRightKey, result.entriesOnlyOnRight().get(curOnlyRightKey).getRoomInfo());
        }

        for (final String curInCommonKey : result.entriesInCommon().keySet()) {
            entriesInCommon.put(curInCommonKey, result.entriesInCommon().get(curInCommonKey).getRoomInfo());
        }

        for (final String curDiffKey : result.entriesDiffering().keySet()) {
            RoomInfo leftRoomInfo = result.entriesDiffering().get(curDiffKey).leftValue().getRoomInfo();
            RoomInfo rightRoomInfo = result.entriesDiffering().get(curDiffKey).rightValue().getRoomInfo();
            entriesDiffering.put(curDiffKey, new ImmutablePair<RoomInfo, RoomInfo>(leftRoomInfo, rightRoomInfo));
        }

        return new RoomInfoDiffResult(entriesOnlyOnLeft, entriesOnlyOnRight, entriesInCommon, entriesDiffering);
    }

    // TODO
    /**
     * 以报价日期date_time为KEY，Diff相同酒店中相同房型的报价
     * 
     * @param left
     * @param right
     * @return
     */
    /*
     * public static RoomDetailDiffResult diffRoomDetail(Map<Date, RoomDetailBean> left, Map<Date, RoomDetailBean>
     * right){ return null; }
     */

}
