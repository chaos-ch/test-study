package com.qunar.hotel.sa.product.price.adapter.kaiyuan.dao;

import java.util.List;
import java.util.Map;

import com.qunar.hotel.otatts.common.common_api.room.bean.RoomDetailBean;
import org.springframework.stereotype.Repository;

/**
 * Created by he.chen on 14-10-28.
 * @version $Id$
 */

@Repository
public interface RoomDetailMapper {

    /**
     * 查询报价信息
     * @param paramMap 查询条件集合
     */
    public List<RoomDetailBean> queryRoomDetail(Map<String, Object> paramMap);


    /**
     * 插入新报价，on duplicate key 更新报价
     * @param roomDetailBeanList 待插入或更新的报价集合
     */
    public int insertOrUpdateRoomDetail(List<RoomDetailBean> roomDetailBeanList);


    /**
     *批量更新报价的房态 至‘Y’
     * @param roomDetailBeanList
     * @return
     */
    public int updateRoomDetailRoomStatus2Y(List<RoomDetailBean> roomDetailBeanList);


    /**
     * 批量更新报价的房态 至‘N’
     * @param roomDetailBeanList
     * @return
     */
    public int updateRoomDetailRoomStatus2N(List<RoomDetailBean> roomDetailBeanList);
}
