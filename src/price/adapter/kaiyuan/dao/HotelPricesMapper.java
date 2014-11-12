package com.qunar.hotel.sa.product.price.adapter.kaiyuan.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.price.bean.RoomDetailBean;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

/**
 * Created by he.chen on 14-10-27.
 * @version $Id$
 */

/**
 * 联表查询hotel_info、room_info、room_detail表，读取酒店信息，含各个房型信息与报价
 */
@Repository
public interface HotelPricesMapper {
    /**
     * @param paramMap
     * @return
     */
    public HotelInfo selectPriceByHotelIdAndTimeInterval(Map<String, Object> paramMap);

    /**
     * @param paramMap
     * @return
     */
    public HotelInfo selectPriceByHotelIdRoomIdAndTimeInterval(Map<String, Object> paramMap);

    /**
     * @param paramMap
     * @return
     */
    public HotelInfo selectPriceByHotelIdOtaRoomIdAndTimeInterval(Map<String, Object> paramMap);

    /**
     * @param paramMap
     * @return
     */
    public HotelInfo selectPriceByOtaHotelIdOtaRoomIdAndTimeInterval(Map<String, Object> paramMap);

    /**
     * 通过seq和时间段来读取数据库
     * @param paramMap
     * @return
     */
    public HotelInfo selectPriceBySeqAndTimeInterval(Map<String, Object> paramMap);


    /**
     * 根据传入的参数获取qunar的roomid
     * @param otaHotelId 指定的otaHotelId
     * @param otaRoomIds 指定的otaRoomIds
     * @return
     */
    public List<Map<String,Object>> selectRoomInfoId(@Param("otaHotelId") String otaHotelId, @Param("otaRoomIds") Set<String> otaRoomIds);


    /**
     * 更新报价，更新表room_detail中的四个字段(room_id,date_time,price,ext)
     * 注意：由于字段create_time没有提供默认值，所以参数中必须设定createTime
     * @param priceDetails
     * @return
     */
    public int updateLocalPrice(@Param("priceDetails") List<RoomDetailBean> priceDetails, @Param("batchUpdateTime") Date batchUpdateTime);


    /**
     * 更新房态，更新roomDetail中的五个字段(room_id,date_time,price,ext,room_status,create_time)
     * 注意：由于字段create_time没有提供默认值，所以参数中必须设定createTime
     * @param priceDetails
     * @return
     */
    public int updateLocalRoomStatus(List<RoomDetailBean> priceDetails);


    /**
     * 获取更新时间小于给定时间的数量
     * @param date
     * @return
     */
    public int queryCountBatchUpdateTimeLessThan(@Param("date") Date date);
    /**
     * 删除小等于给定${date}时间，过期的报价，一次最多删除${limit}条
     * @param date
     * @param limit
     */
    public void deletePriceOutOfDate(@Param("date") Date date, @Param("limit") int limit);


    /** ******标准化****** **/

    /**
     * 查询酒店的基本信息
     * @param paramMap 查询条件集合
     */
    public List<HotelInfo> queryHotelInfo(Map<String, Object> paramMap);


    /**
     * 查询酒店与房型的基本信息
     * @param paramMap 查询条件集合
     */
    public List<HotelInfo> queryHotelInfoJoinRoomInfo(Map<String, Object> paramMap);


    /**
     * 查询酒店与房型的基本信息、报价信息
     * @param paramMap 查询条件集合
     */
    public List<HotelInfo> queryHotelInfoJoinRoomInfoJoinRoomDetail(Map<String, Object> paramMap);

    /**
     * 只用于手工wrapper查询
     * @param paramMap
     * @return
     */
    public List<HotelInfo> queryHotelInfoByWrapper(Map<String, Object> paramMap);
}
