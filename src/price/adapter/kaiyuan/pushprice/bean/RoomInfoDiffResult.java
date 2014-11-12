package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean;

import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.qunar.hotel.otatts.common.common_api.room.bean.RoomInfo;

/**
 * @author xu.cao created on 2014/6/13 18:27
 * @version $Id$
 * 房型Diff结果Bean
 */

public class RoomInfoDiffResult {

    /**
     * KEY : 房型的ota_room_id
     * VALUE : 房型Bean
     */
    private Map<String, RoomInfo> entriesOnlyOnLeft;
    private Map<String, RoomInfo> entriesOnlyOnRight;
    private Map<String, RoomInfo> entriesInCommon;
    private Map<String, Pair<RoomInfo, RoomInfo>> entriesDiffering;

    /**
     *
     * @param entriesOnlyOnLeft     KEY仅在left中出现的键值对的集合
     * @param entriesOnlyOnRight    KEY仅在right中出现的键值对的集合
     * @param entriesInCommon       在left与right中，KEY与VALUE都相等的键值对的集合
     * @param entriesDiffering      在left与right中，KEY相等但VALUE不相等的键-值(2个值)对的集合
     */
    public RoomInfoDiffResult(Map<String, RoomInfo> entriesOnlyOnLeft,
                              Map<String, RoomInfo> entriesOnlyOnRight,
                              Map<String, RoomInfo> entriesInCommon,
                              Map<String, Pair<RoomInfo, RoomInfo>> entriesDiffering){
        this.entriesOnlyOnLeft = entriesOnlyOnLeft;
        this.entriesOnlyOnRight = entriesOnlyOnRight;
        this.entriesInCommon = entriesInCommon;
        this.entriesDiffering = entriesDiffering;
    }

    public Map<String, RoomInfo> getEntriesOnlyOnRight() {
        return entriesOnlyOnRight;
    }

    public void setEntriesOnlyOnRight(Map<String, RoomInfo> entriesOnlyOnRight) {
        this.entriesOnlyOnRight = entriesOnlyOnRight;
    }

    public Map<String, RoomInfo> getEntriesInCommon() {
        return entriesInCommon;
    }

    public void setEntriesInCommon(Map<String, RoomInfo> entriesInCommon) {
        this.entriesInCommon = entriesInCommon;
    }

    public Map<String, RoomInfo> getEntriesOnlyOnLeft() {
        return entriesOnlyOnLeft;
    }

    public void setEntriesOnlyOnLeft(Map<String, RoomInfo> entriesOnlyOnLeft) {
        this.entriesOnlyOnLeft = entriesOnlyOnLeft;
    }

    public Map<String, Pair<RoomInfo, RoomInfo>> getEntriesDiffering() {
        return entriesDiffering;
    }

    public void setEntriesDiffering(Map<String, Pair<RoomInfo, RoomInfo>> entriesDiffering) {
        this.entriesDiffering = entriesDiffering;
    }
}
