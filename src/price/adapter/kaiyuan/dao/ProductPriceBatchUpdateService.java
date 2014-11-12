package com.qunar.hotel.sa.product.price.adapter.kaiyuan.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import qunar.sql.ECon;

import com.google.common.collect.Lists;
import com.qunar.hotel.otatts.base.context.AppContext;
import com.qunar.hotel.otatts.common.common_api.exception.OPBException;
import com.qunar.hotel.otatts.common.common_api.room.bean.RoomInfo;

/**
 * @author weirongzhou
 * 
 */
@Service("productPriceBatchUpdateService")
public class ProductPriceBatchUpdateService {

    /**
     * 单次批量更新条数
     */
    private static final int BATCH_NUM = 500;

    /**
     * 更新房型信息的sql
     */
    private static final String SQL_UPDATE_ROOMINFOS = "update room_info set room_name = ?,breakfast = ?,bed_type = ?,broadband = ?,pay_type = ?,status = ?,room_type = ?,last_day = ?,advance_day = ?,book_type = ?,max_last_day = ?,description = ?  where id = ?";

    /**
     * 批量更新房型信息
     * 
     * @param roomInfos
     * @return
     * @throws java.sql.SQLException
     */
    public boolean updateRoomInfos(List<RoomInfo> roomInfos) {
        ECon conn = AppContext.current().getWCon();
        List<Object[]> paramsList = Lists.newArrayList();
        for (RoomInfo roomInfo : roomInfos) {
            List<Object> params = Lists.newArrayList();
            params.add(roomInfo.getRoomName());
            params.add(roomInfo.getBreakfast());
            params.add(roomInfo.getBedType());
            params.add(roomInfo.getBroadband());
            params.add(roomInfo.getPayType().getIntValue());
            params.add(roomInfo.getStatus().name());
            params.add(roomInfo.getRoomType().name());
            params.add(roomInfo.getLast());
            params.add(roomInfo.getAdvance());
            params.add(roomInfo.getBookType().getType());
            params.add(roomInfo.getMaxLastDay());
            params.add(roomInfo.getDescription());
            params.add(roomInfo.getId());
            paramsList.add(params.toArray());
        }
        List<Integer> ret = null;
        try {
            ret = conn.exec(SQL_UPDATE_ROOMINFOS, paramsList, BATCH_NUM);
        } catch (SQLException e) {
            throw new OPBException("update room_info error", e);
        }
        return CollectionUtils.isNotEmpty(ret);
    }

}
