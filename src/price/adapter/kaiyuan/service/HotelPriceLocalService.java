package com.qunar.hotel.sa.product.price.adapter.kaiyuan.service;

import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.price.bean.RoomDetailBean;
import com.qunar.hotel.sa.product.price.bean.RoomInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 此接口用来从数据库中获取报价信息。
 * Created by he.chen on 14-10-27.
 * version $Id$
 */
public interface HotelPriceLocalService {

    /**
     * 查询酒店基础信息
     */
    public HotelInfo queryHotelInfoBySeq(String seq);

    /**
     * 查询酒店所有房型信息
     */
    public HotelInfo queryHotelRoomInfoBySeq(String seq);

    /**
     * 根据hotelId获取存储在qunar数据库中的报价信息,此报价信息是针对整个酒店的全部报价
     * @param hotelId
     * @param fromDate
     * @param toDate
     * @return
     */
    public HotelInfo queryHotelPriceByHotelId(Integer hotelId, Date fromDate, Date toDate);


    /**
     * 根据seq获取存储在qunar数据库中的报价信息,此报价信息是针对整个酒店的全部报价
     * @param seq
     * @param fromDate
     * @param toDate
     * @return
     */
    public HotelInfo queryHotelPriceBySeq(String seq, Date fromDate, Date toDate);

    /**
     * 根据otaHotelId 指定酒店的 在指定时间段内的 全部报价
     * @param otaHotelId
     * @param fromDate
     * @param toDate
     * @return
     */
    public HotelInfo queryHotelPriceByOtaHotelId(String otaHotelId, Date fromDate, Date toDate);


    /**
     * 获取存储在数据库中的 指定酒店 指定房型 在一段时间内的报价
     * @param hotelId Qunar数据库酒店表Id
     * @param roomId Qunar数据库房型表Id
     * @return
     */
    public HotelInfo queryHotelRoomPrice(int hotelId, int roomId);

    /**
     * 获取存储在数据库中的 指定酒店 指定房型 在一段时间内的报价
     * @param otaHotelId Ota酒店Id
     * @param otaRoomId Ota房型Id
     * @param fromDate
     * @param toDate
     * @return
     */
    public HotelInfo queryHotelRoomPrice(String otaHotelId, String otaRoomId, Date fromDate, Date toDate);

    /**
     *
     * @param hotelId qunarHotelId
     * @param otaRoomId otaRoomId
     * @param fromDate
     * @param toDate
     * @return
     */
    public HotelInfo queryHotelRoomPrice(int hotelId, String otaRoomId, Date fromDate, Date toDate) ;

    /**
     * 通过Qunar房型信息数据库表Id获取房型信息
     * @param roomId
     * @return
     */
    public RoomInfo queryRoomInfoByRoomId(int roomId);


    /**
     * 根据传入的参数获取qunar的roomid
     * @param otaHotelId 指定的otaHotelId
     * @param otaRoomIds 指定的otaRoomIds
     * @return
     */
    public List<Map<String,Object>> queryRoomInfoId(String otaHotelId, Set<String> otaRoomIds);

    /**
     * 更新报价，更新表room_detail中的四个字段(room_id,date_time,price,ext)
     * 注意：由于字段create_time没有提供默认值，所以参数中必须设定createTime
     * @param priceDetails
     * @return
     */
    public int updateLocalPriceDetails(List<RoomDetailBean> priceDetails, Date batchUpdateTime);

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
    public int queryCountUpdateTimeLessThan(Date date);

    /**
     * 删除小等于给定${date}时间，过期的报价，一次最多删除${limit}条
     * @param date
     * @param limit
     */
    public void deletePriceOutOfDate(Date date, int limit);
}
