package com.qunar.hotel.sa.product.price.adapter.kaiyuan.dao;

import java.util.List;
import java.util.Map;

import com.qunar.hotel.sa.product.price.bean.RoomInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by he.chen on 14-10-27.
 * @version $Id$
 */

@Repository
public interface RoomInfoMapper {

    /**
     * 查询房型的基本信息
     * 
     * @param paramMap 查询条件集合
     */
    public List<RoomInfo> queryRoomInfo(Map<String, Object> paramMap);

    /**
     * 查询房型的基本信息、报价信息
     * 
     * @param paramMap 查询条件集合
     */
    public List<RoomInfo> queryRoomInfoPrice(Map<String, Object> paramMap);

    /**
     * 插入新房型基本信息, on duplicate key 更新
     * 
     * @param roomInfoList 待插入的新房型基本信息集合
     */
    public int insertOrUpdateRoomInfo(List<RoomInfo> roomInfoList);

    /**
     * 保存房型信息
     * 
     * @param roomInfoList
     * @return
     */
    public int insertRoomInfo(List<RoomInfo> roomInfoList);

    /**
     * 更新房型信息
     * 
     * @param roomInfo
     * @return
     */
    public int updateRoomInfo(RoomInfo roomInfo);

    /**
     * 关闭指定的房型
     * 
     * @param roomInfoList 待关闭的房型集合
     */
    public int closeRoomInfo(List<RoomInfo> roomInfoList);

    /**
     * 关闭指定酒店的所有房型
     * @param hotelId
     * @return
     */
    public int closeRooms(int hotelId);

    /** Deprecated **/

    /**
     * 通过Qunar房型表的表Id取房型信息
     * 
     * @param paramMap
     * @return
     */
    @Deprecated
    public RoomInfo selectRoomInfoByRoomId(Map<String, Object> paramMap);

    List<RoomInfo> getAllRoomInfo();

    void updateRoomInfoByRoomId(RoomInfo roomInfo);
}
