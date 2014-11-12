package com.qunar.hotel.sa.product.price.adapter.kaiyuan.service;

import com.qunar.hotel.sa.common.util.DateFormatUtils;
import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.dao.HotelPricesMapper;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.dao.RoomInfoMapper;
import com.qunar.hotel.sa.product.price.bean.RoomDetailBean;
import com.qunar.hotel.sa.product.price.bean.RoomInfo;
import com.qunar.hotel.sa.product.price.util.RoomDateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by he.chen on 14-10-27.
 */
public class HotelPriceLocalServiceImpl implements HotelPriceLocalService{
    @Resource
    private HotelPricesMapper hotelPricesMapper;

    @Resource
    private RoomInfoMapper roomInfoMapper;

    /**
     * 查询酒店基础信息
     */
    public HotelInfo queryHotelInfoBySeq(String seq){
        if(seq == null){
            return null;
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("hotelInfoStatus", 0);
        paramMap.put("hotelInfoSeq", seq);

        List<HotelInfo> hotelInfoList = hotelPricesMapper.queryHotelInfo(paramMap);
        HotelInfo hotelInfo = CollectionUtils.isEmpty(hotelInfoList) ? null : hotelInfoList.get(0);

        return hotelInfo;
    }

    /**
     * 查询酒店所有房型信息
     */
    public HotelInfo queryHotelRoomInfoBySeq(String seq){
        if(seq == null){
            return null;
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("hotelInfoStatus", 0);
        paramMap.put("hotelInfoSeq", seq);
        paramMap.put("roomInfoStatus", "ACTIVE");

        List<HotelInfo> hotelInfoList = hotelPricesMapper.queryHotelInfoJoinRoomInfo(paramMap);
        HotelInfo hotelInfo = CollectionUtils.isEmpty(hotelInfoList) ? null : hotelInfoList.get(0);

        return hotelInfo;
    }

    /**
     * 查询在指定时间段内 指定代理商下 指定酒店 所有房型报价相关信息
     *
     * @param hotelId 酒店ID
     * @param fromDate 起始时间
     * @param toDate 结束时间
     */
    @Override
    public HotelInfo queryHotelPriceByHotelId(Integer hotelId, Date fromDate, Date toDate) {
        if(hotelId == null || fromDate == null || toDate == null){
            return null;
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("hotelInfoStatus", 0);
        paramMap.put("hotelInfoId", hotelId);
        paramMap.put("roomDetailDateTimeFromDate", fromDate);
        paramMap.put("roomDetailDateTimeToDate", toDate);
        paramMap.put("roomInfoStatus", "ACTIVE");

        List<HotelInfo> hotelInfoList = hotelPricesMapper.queryHotelInfoJoinRoomInfoJoinRoomDetail(paramMap);
        HotelInfo hotelInfo = CollectionUtils.isEmpty(hotelInfoList) ? null : hotelInfoList.get(0);

        return processHotelInfo(hotelInfo, fromDate, toDate);
    }

    /**
     * 根据seq获取存储在qunar数据库中的报价信息,此报价信息是针对整个酒店的全部报价
     *
     * @param seq
     * @param fromDate
     * @param toDate
     * @return
     */
    @Override
    public HotelInfo queryHotelPriceBySeq(String seq, Date fromDate, Date toDate) {
        if (StringUtils.isBlank(seq) || fromDate == null || toDate == null) {
            return null;
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("hotelInfoStatus", 0);
        paramMap.put("hotelInfoSeq", seq);
        paramMap.put("roomDetailDateTimeFromDate", fromDate);
        paramMap.put("roomDetailDateTimeToDate", toDate);
        paramMap.put("roomInfoStatus", "ACTIVE");

        List<HotelInfo> hotelInfoList = hotelPricesMapper.queryHotelInfoByWrapper(paramMap);
        HotelInfo hotelInfo = CollectionUtils.isEmpty(hotelInfoList) ? null : hotelInfoList.get(0);

        return processHotelInfo(hotelInfo, fromDate, toDate);
    }

    /**
     * 根据otaHotelId 指定酒店的 在指定时间段内的 全部报价
     *
     * @param otaHotelId
     * @param fromDate
     * @param toDate
     * @return
     */
    @Override
    public HotelInfo queryHotelPriceByOtaHotelId(String otaHotelId, Date fromDate, Date toDate) {
        if (StringUtils.isBlank(otaHotelId) || fromDate == null || toDate == null) {
            return null;
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("hotelInfoStatus", 0);
        paramMap.put("hotelInfoOtaHotelId", otaHotelId);
        paramMap.put("roomDetailDateTimeFromDate", fromDate);
        paramMap.put("roomDetailDateTimeToDate", toDate);
        paramMap.put("roomInfoStatus", "ACTIVE");

        List<HotelInfo> hotelInfoList = hotelPricesMapper.queryHotelInfoJoinRoomInfoJoinRoomDetail(paramMap);
        HotelInfo hotelInfo = CollectionUtils.isEmpty(hotelInfoList) ? null : hotelInfoList.get(0);

        return processHotelInfo(hotelInfo, fromDate, toDate);
    }

    /**
     * 根据hotelId，roomId和时间间隔来获取HotelInfo
     *
     * @param hotelId Qunar数据库酒店表Id
     * @param roomId Qunar数据库房型表Id
     * @return
     */
    @Override
    public HotelInfo queryHotelRoomPrice(int hotelId, int roomId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("hotelId", hotelId);
        paramMap.put("roomId", roomId);
        HotelInfo hotelInfo = hotelPricesMapper.selectPriceByHotelIdRoomIdAndTimeInterval(paramMap);
        return hotelInfo;
    }

    /**
     * 根据hotelId，roomId和时间间隔来获取HotelInfo
     *
     * @param hotelId Qunar数据库酒店表Id
     * @param otaRoomId Qunar数据库房型表Id
     * @param fromDate
     * @param toDate
     * @return
     */
    public HotelInfo queryHotelRoomPrice(int hotelId, String otaRoomId, Date fromDate, Date toDate) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("hotelId", hotelId);
        paramMap.put("otaRoomId", otaRoomId);
        paramMap.put("fromDate", fromDate);
        paramMap.put("toDate", toDate);
        HotelInfo hotelInfo = hotelPricesMapper.selectPriceByHotelIdOtaRoomIdAndTimeInterval(paramMap);
        return processHotelInfo(hotelInfo, fromDate, toDate);
    }

    /**
     * 根据otaHotelId,otaRoomId和时间间隔来获取HotelInfo
     *
     * @param otaHotelId ota酒店Id
     * @param otaRoomId ota房型Id
     * @param fromDate
     * @param toDate
     * @return 查询在指定时间段内 指定代理商下 指定酒店 指定房型报价相关信息
     */
    @Override
    public HotelInfo queryHotelRoomPrice(String otaHotelId, String otaRoomId, Date fromDate, Date toDate) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("otaHotelId", otaHotelId);
        paramMap.put("otaRoomId", otaRoomId);
        paramMap.put("fromDate", fromDate);
        paramMap.put("toDate", toDate);
        HotelInfo hotelInfo = hotelPricesMapper.selectPriceByOtaHotelIdOtaRoomIdAndTimeInterval(paramMap);
        return processHotelInfo(hotelInfo, fromDate, toDate);
    }

    /**
     * 根据roomId来获取roomInfo
     *
     * @param roomId qunar数据库房型表Id
     * @return
     */
    @Override
    public RoomInfo queryRoomInfoByRoomId(int roomId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roomId", roomId);
        RoomInfo roomInfo = roomInfoMapper.selectRoomInfoByRoomId(paramMap);
        return roomInfo;
    }

    /**
     * 根据传入的参数获取qunar的roomid
     *
     * @param otaHotelId 指定的otaHotelId
     * @param otaRoomIds 指定的otaRoomIds
     * @return key分别为"otaHotelid","otaRoomId","roomInfoId"
     */
    @Override
    public List<Map<String, Object>> queryRoomInfoId(String otaHotelId, Set<String> otaRoomIds) {
        if (otaHotelId != null && otaRoomIds != null) {
            return hotelPricesMapper.selectRoomInfoId(otaHotelId, otaRoomIds);
        }
        return null;
    }

    /**
     * 更新报价，更新表room_detail中的四个字段(room_id,date_time,price,ext) 注意：由于字段create_time没有提供默认值，所以参数中必须设定createTime
     *
     * @param priceDetails
     * @return
     */
    @Override
    public int updateLocalPriceDetails(List<RoomDetailBean> priceDetails, Date batchUpdateTime) {
        if (priceDetails != null && priceDetails.size() != 0) {
            return hotelPricesMapper.updateLocalPrice(priceDetails, batchUpdateTime);
        }
        return 0;
    }

    /**
     * 更新房态 注意：由于数据库表room_detailzhong中字段create_time没有提供默认值，所以参数中必须设定createTime
     *
     * @param priceDetails
     * @return
     */

    @Override
    public int updateLocalRoomStatus(List<RoomDetailBean> priceDetails) {
        if (priceDetails != null && priceDetails.size() != 0) {
            return hotelPricesMapper.updateLocalRoomStatus(priceDetails);
        }
        return 0;
    }

    /**
     * 加工房型报价
     *
     * @param hotelInfo
     * @param fromDate
     * @param toDate
     * @return
     */
    private HotelInfo processHotelInfo(HotelInfo hotelInfo, Date fromDate, Date toDate) {
        if (hotelInfo == null) {
            return hotelInfo;
        }
        return processNoPriceRoom(hotelInfo, fromDate, toDate);
    }

    /**
     * 处理某些日期没有报价的房型
     *
     * @param hotelInfo
     * @param fromDate
     * @param toDate
     * @return
     */
    private HotelInfo processNoPriceRoom(HotelInfo hotelInfo, Date fromDate, Date toDate) {
        if (hotelInfo == null) {
            return hotelInfo;
        }
        List<String> priceDates = RoomDateUtils.getDateStrList(fromDate, toDate);
        if (CollectionUtils.isNotEmpty(hotelInfo.getRooms())) {
            for (RoomInfo room : hotelInfo.getRooms()) {
                List<RoomDetailBean> details = room.getDetails();
                if (CollectionUtils.isEmpty(details)) {
                    continue;
                }
                Set<String> hasPriceDates = new HashSet<String>();
                for (RoomDetailBean detail : room.getDetails()) {
                    hasPriceDates.add(DateFormatUtils.format4y2M2d(detail.getDateTime()));
                }
                for (String priceDate : priceDates) {
                    if (!hasPriceDates.contains(priceDate)) {
                        RoomDetailBean rdb = new RoomDetailBean();
                        rdb.setDate(priceDate);
                        rdb.setDateTime(DateFormatUtils.parse4y2M2d(priceDate));
                        rdb.setRoomId(NumberUtils.toInt(room.getId()));
                        details.add(rdb);
                    }
                }
                Collections.sort(details, new Comparator<RoomDetailBean>() {
                    @Override
                    public int compare(RoomDetailBean o1, RoomDetailBean o2) {
                        return o1.getDateTime().compareTo(o2.getDateTime());
                    }
                });
                room.setDetails(details);
            }
        }

        return hotelInfo;
    }

    /**
     * 获取更新时间小于给定时间的数量
     *
     * @param date
     * @return
     */
    public int queryCountUpdateTimeLessThan(Date date) {
        int count = hotelPricesMapper.queryCountBatchUpdateTimeLessThan(date);
        return count;
    }

    /**
     * 删除小等于给定${date}时间，过期的报价，一次最多删除${limit}条
     *
     * @param date
     * @param limit
     */
    @Override
    public void deletePriceOutOfDate(Date date, int limit) {
        if (date != null) {
            hotelPricesMapper.deletePriceOutOfDate(date, limit);
        }
    }
}
