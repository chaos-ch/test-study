package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

import javax.xml.bind.JAXBException;

import com.qunar.hotel.sa.common.util.DateRangeUtils;
import com.qunar.hotel.sa.common.util.OasQMonitorUtils;
import com.qunar.hotel.sa.common.util.QmonitorNameEnum;
import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean.*;
import com.qunar.hotel.sa.product.price.bean.OtaPriceParam;
import com.qunar.hotel.sa.product.price.bean.PriceInfo;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean.Package;
import com.qunar.hotel.sa.product.price.bean.RoomInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import com.qunar.hotel.sa.product.price.bean.RoomInfo.*;


/**
 * @Date:Jun 6, 2014
 * @Time:1:22:40 PM
 * Created by he.chen on 14-10-27.
 */
public class KaiyuanParseUtil {
    protected static Logger logger = LoggerFactory.getLogger("com.qunar.hotel.oas.spi.kaiyuan");
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String DATA_FIELS_SEP_CHAR = "|";
    private static final String ROOMID_SEP_CHAR = "|";
    private static final int MAX_BOOKING_DAYS = 95;

    private static final Map<String, RoomInfo.Breakfast> BREAKFAST_MAP = new HashMap<String, RoomInfo.Breakfast>();
    static {
        BREAKFAST_MAP.put("0BK", RoomInfo.Breakfast.NONE);
        BREAKFAST_MAP.put("1BK", RoomInfo.Breakfast.ONE);
        BREAKFAST_MAP.put("2BK", RoomInfo.Breakfast.TWO);
    }
    private static final Map<String, RoomInfo.BedType> BED_TYPE_MAP = new HashMap<String, RoomInfo.BedType>();
    static {
        BED_TYPE_MAP.put("NULL", RoomInfo.BedType.BIG);
        BED_TYPE_MAP.put("单人", RoomInfo.BedType.ONE);
        BED_TYPE_MAP.put("大床", BedType.BIG);
        BED_TYPE_MAP.put("双床", BedType.DOUBLE);
        BED_TYPE_MAP.put("大床/双床", BedType.BIG);
        BED_TYPE_MAP.put("双床/大床", BedType.BIG_DOUBULE);
        BED_TYPE_MAP.put("大床/单人", BedType.BIG);

    }
    /**
     * 从文件中加载房型的床型和描述信息
     */
    private static Map<String, String[]> ROOM_INFO_MAP = new HashMap<String, String[]>();
    static {
        BufferedReader br = null;
        try {
            String hotelFileName = KaiyuanParseUtil.class.getClassLoader().getResource("kaiyuan_roomInfo.txt")
                    .getFile();

            br = new BufferedReader(new InputStreamReader(new FileInputStream(hotelFileName), "utf-8"));
            String data = null;
            while ((data = br.readLine()) != null) {
                String[] arr = StringUtils.splitPreserveAllTokens(data, DATA_FIELS_SEP_CHAR);
                if (arr == null || arr.length != 4) {
                    logger.warn("kaiyuan roomInfo data file contains less than 3 colums:{}", data);
                    continue;
                }
                ROOM_INFO_MAP.put(arr[0] + DATA_FIELS_SEP_CHAR + arr[1], arr);
            }
        } catch (Exception e) {
            logger.error("load kaiyuan roomInfo data file failed !", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    logger.error("close kaiyuan roomInfo data file failed !", e);
                }
            }
        }
    }

    /**
     * 解析登录字符串以获取sessionId
     * 
     * @param loginResultXml
     * @return
     */
    public static String parseSessionId(String loginResultXml) {
        logger.info("login response:{}", loginResultXml);
        try {
            SoapEnvelope channelLoginResponse = KaiyuanXMLUtil.xml2SoapEnvelopeBean(loginResultXml);
            if (channelLoginResponse.getSoapBody().getChannelLoginResponse().getChannelLoginResult()) {
                return channelLoginResponse.getSoapHeader().getShandsSoapHeader().getSessionId();
            }else{
                logger.error(
                        QmonitorNameEnum.OAS_REMOTE_LOGIN_ERR.name() + "：parse login xml，loginResultXml:{}",
                        loginResultXml);
                OasQMonitorUtils.recordOne(QmonitorNameEnum.OAS_REMOTE_LOGIN_ERR);
            }
        } catch (Exception e) {
            logger.error(
                    QmonitorNameEnum.OAS_REMOTE_LOGIN_ERR.name() + "：parse login xml exception ，loginResultXml:{}",
                    loginResultXml, e);
            OasQMonitorUtils.recordOne(QmonitorNameEnum.OAS_REMOTE_LOGIN_ERR);
            return null;
        }
        return null;
    }

    /**
     * 解析开元推送过来报价信息
     * 
     * @param opp
     * @param pushXml
     * @return
     */
    public static HotelInfo parsePushRatePlan(OtaPriceParam opp, String pushXml) {
        PushRatePlan pushRatePlan = null;
        try {
            pushRatePlan = KaiyuanXMLUtil.xml2PushRatePlan(pushXml);
        } catch (JAXBException e) {
            logger.error("parse push price info xml exception, pushXml:{}", pushXml, e);
            OasQMonitorUtils.recordOne(QmonitorNameEnum.OAS_PUSH_PRICE_ERR);
            return null;
        }
        HotelInfo hotelInfo = new HotelInfo();
        hotelInfo.setOtaId(opp.getSupplierInfo().getId());
        parseAndSetRatePlan(hotelInfo, pushRatePlan);
        return hotelInfo;
    }

    /**
     * 解析推送的报价信息并设置到hotelInfo中
     * 
     * @param hotelInfo
     * @param pushRatePlan
     */
    public static void parseAndSetRatePlan(HotelInfo hotelInfo, PushRatePlan pushRatePlan) {
        Map<String, RoomInfo> roomInfoMap = new HashMap<String, RoomInfo>();
        if (pushRatePlan == null || pushRatePlan.getRatePlans() == null) {
            return;
        }
        for (RatePlan ratePlan : pushRatePlan.getRatePlans()) {
            if (CollectionUtils.isEmpty(ratePlan.getRatePlanDetails())) {
                return;
            }
            for (RatePlanDetail ratePlanDetail : ratePlan.getRatePlanDetails()) {
                String roomId = ratePlanDetail.getRoomType().getCode() + ROOMID_SEP_CHAR + ratePlan.getCode();
                RoomInfo roomInfo = roomInfoMap.get(roomId);
                if (roomInfo == null) {
                    roomInfo = new RoomInfo();
                    roomInfo.setId(roomId);
                    roomInfo.setOtaRoomId(roomId);
                    String[] strArr = ROOM_INFO_MAP.get(ratePlan.getHotelCode() + ROOMID_SEP_CHAR
                            + ratePlanDetail.getRoomType().getCode());
                    if (strArr == null) {
                        logger.warn(
                                "kaiyuan push priceInfo contains room not exists in data files,HotelCode:{},roomTypeCode:{}",
                                ratePlan.getHotelCode(), ratePlanDetail.getRoomType().getCode());
                        continue;
                    }
                    BedType bedType = BED_TYPE_MAP.get(strArr[2]);
                    if (bedType == null) {
                        bedType = BedType.UNKNOWN;
                    }
                    roomInfo.setBedType(bedType);
                    roomInfo.setDescription(strArr[3]);
                    roomInfo.setOtaHotelId(ratePlan.getHotelCode());
                    roomInfo.setRoomName(ratePlanDetail.getRoomType().getName());
                    roomInfo.setAdvance(ratePlan.getAdvBookin());
                    roomInfo.setLast(ratePlan.getMinLos());
                    roomInfo.setMaxLastDay(ratePlan.getMaxLos());
                    Breakfast breakfast = parseBreakFastFromPackages(ratePlan.getPackages());
                    if (breakfast == null) {
                        breakfast = Breakfast.NONE;
                    }
                    roomInfo.setBreakfast(breakfast);
                    roomInfo.setBreakfastStr(roomInfo.getBreakfast().desc);
                    if (!ratePlan.isPrepay()) {// 如果不是预付，过滤
                        continue;
                    }
                    roomInfo.setPayType(PayType.ONLINE);
                    roomInfo.setRoomType(getPriceType(roomInfo));
                    roomInfo.setBroadband(Broadband.FREE);// 宽带固定为：免费
                    roomInfo.setRefuseState(0);// 房量不足，可以预定
                    roomInfoMap.put(roomId, roomInfo);
                }
                setPriceInfoListFromPush(roomInfo, ratePlanDetail);
            }
        }
        String otaHotelId = null;
        List<RoomInfo> roomInfoList = new ArrayList<RoomInfo>();
        for (RoomInfo roomInfo : roomInfoMap.values()) {
            if (otaHotelId == null) {
                otaHotelId = roomInfo.getOtaHotelId();
            } else if (!StringUtils.equals(otaHotelId, roomInfo.getOtaHotelId())) {// 如果发现hotelCode和第一个出现的Hotelcode不一样，则过滤
                logger.warn("kaiyuan push priceInfo contains more than one hotelCode");
                continue;
            }
            roomInfoList.add(roomInfo);
        }
        hotelInfo.setOtaHotelId(otaHotelId);
        hotelInfo.setRoomInfo(roomInfoList);
    }

    private static void setPriceInfoListFromPush(RoomInfo roomInfo, RatePlanDetail ratePlanDetail) {
        BigDecimal price = parsePrice(ratePlanDetail.getRateAmount(), ratePlanDetail.getServiceCharge(),
                ratePlanDetail.getServiceChargeFlag(), ratePlanDetail.getTax(), ratePlanDetail.getTaxFlag());
        if (price == null) {
            logger.warn("kaiyuan parse price error");
            return;
        }
        Breakfast breakfast = parseBreakFastFromPackages(ratePlanDetail.getPackages());
        if (breakfast == null) {
            breakfast = roomInfo.getBreakfast();
        }
        String[] weeks = null;
        List<Integer> weekDefineList = new ArrayList<Integer>();
        if (StringUtils.isNotBlank(ratePlanDetail.getWeekControl())) {
            weeks = StringUtils.split(ratePlanDetail.getWeekControl(), ",");
            for (String e : weeks) {
                int dayOfWeek = NumberUtils.toInt(e) + 1;// 1\2\3....7分别表示周一\周二\周三....周日，需要转化成这样的对应关系：1表示周日，2表示周一.....7表示周六
                if (dayOfWeek > 7) {
                    dayOfWeek = dayOfWeek % 7;
                }
                weekDefineList.add(dayOfWeek);
            }
        }
        Date beginDate = new Date();
        beginDate = DateUtils.truncate(beginDate, Calendar.DATE);
        Date endDate = DateUtils.addDays(beginDate, MAX_BOOKING_DAYS);
        if (beginDate.before(ratePlanDetail.getBeginDate())) {
            beginDate = ratePlanDetail.getBeginDate();
        }
        if (endDate.after(ratePlanDetail.getEndDate())) {// 如果报价多于MAX_BOOKING_DAYS天，则取MAX_BOOKING_DAYS天的报价
            endDate = ratePlanDetail.getEndDate();
        }
        List<Date> dataList = DateRangeUtils.getDateList(beginDate, endDate, weekDefineList);
        if (roomInfo.getPriceInfo() == null) {
            roomInfo.setPriceInfo(new ArrayList<PriceInfo>());
        }
        for (Date date : dataList) {
            PriceInfo priceInfo = new PriceInfo();
            priceInfo.setRoomId(roomInfo.getOtaRoomId());
            priceInfo.setDate(date);
            priceInfo.setPrice(price);
            priceInfo.setStatus(0);
            priceInfo.setBreakfast(breakfast);
            priceInfo.setBreakfastStr(breakfast.desc);
            roomInfo.getPriceInfo().add(priceInfo);
        }

    }

    /**
     * 解析每日房价详情查询接口
     * 
     * @param opp
     * @param soapEnvelope
     * @return
     */
    public static HotelInfo parseDailyDetail(OtaPriceParam opp, SoapEnvelope soapEnvelope) {
        HotelInfo hotelInfo = new HotelInfo();
        hotelInfo.setOtaHotelId(opp.getHotelInfoParam().getOtaHotelId());
        RoomInfo roomInfo = parseRateDailyDetailResponse(opp, soapEnvelope.getSoapBody()
                .getHotelRateDailyDetailResponse());
        hotelInfo.setRoomInfo(Arrays.asList(roomInfo));
        return hotelInfo;
    }

    public static RoomInfo parseRateDailyDetailResponse(OtaPriceParam opp,
            HotelRateDailyDetailResponse rateDailyDetailResponse) {
        if (rateDailyDetailResponse == null) {
            return null;
        }
        RoomInfo roomInfo = new RoomInfo();
        roomInfo.setId(opp.getOtaRoomId());
        roomInfo.setOtaRoomId(opp.getOtaRoomId());
        roomInfo.setRoomType(getPriceType(roomInfo));
        roomInfo.setPriceInfo(getPriceInfoList(opp, rateDailyDetailResponse));
        roomInfo.setBroadband(Broadband.FREE);
        roomInfo.setRefuseState(0);
        return roomInfo;
    }

    public static HotelInfo parsePushState(OtaPriceParam opp, String pushXml) {
        if (StringUtils.isBlank(pushXml)) {
            logger.error("parse push state info xml exception ，pushXml is blank");
            return null;
        }
        PushRoomRateStatus pushRoomRateStatus = null;
        try {
            pushRoomRateStatus = KaiyuanXMLUtil.xml2PushRoomRateStatus(pushXml);
        } catch (JAXBException e) {
            logger.error(QmonitorNameEnum.OAS_PUSH_STATE_ERR + ":parse push state info xml exception ，pushXml{}",
                    pushXml, e);
            OasQMonitorUtils.recordOne(QmonitorNameEnum.OAS_PUSH_STATE_ERR);
            return null;
        }
        if (pushRoomRateStatus == null) {
            logger.error(QmonitorNameEnum.OAS_PUSH_STATE_ERR + ":parse push state info xml errpr ，pushXml{}", pushXml);
            OasQMonitorUtils.recordOne(QmonitorNameEnum.OAS_PUSH_STATE_ERR);
            return null;
        }
        HotelInfo hotelInfo = new HotelInfo();
        hotelInfo.setOtaId(opp.getSupplierInfo().getId());
        hotelInfo.setOtaHotelId(pushRoomRateStatus.getHotelCode());
        parseAndSetStateFromPush(hotelInfo, pushRoomRateStatus);
        return hotelInfo;
    }

    private static void parseAndSetStateFromPush(HotelInfo hotelInfo, PushRoomRateStatus pushRoomRateStatus) {
        Date beginDate = new Date();
        beginDate = DateUtils.truncate(beginDate, Calendar.DATE);
        Date endDate = DateUtils.addDays(beginDate, MAX_BOOKING_DAYS);
        Map<String, RoomInfo> roomInfoMap = new HashMap<String, RoomInfo>();
        if (pushRoomRateStatus == null || pushRoomRateStatus.getRoomRateStatusDailys() == null) {
            return;
        }
        for (RoomRateStatusDaily roomRateStatusDaily : pushRoomRateStatus.getRoomRateStatusDailys()) {
            if (roomRateStatusDaily.getDate().before(beginDate) || roomRateStatusDaily.getDate().after(endDate)) {// 如果是历史的房态或MAX_BOOKING_DAYS天后的房态，忽略
                continue;
            }
            String roomId = roomRateStatusDaily.getRoomTypeCode() + ROOMID_SEP_CHAR
                    + roomRateStatusDaily.getRatePlanCode();
            RoomInfo roomInfo = roomInfoMap.get(roomId);
            if (roomInfo == null) {
                roomInfo = new RoomInfo();
                roomInfo.setId(roomId);
                roomInfo.setOtaRoomId(roomId);
                roomInfo.setOtaHotelId(hotelInfo.getOtaHotelId());
                roomInfo.setRoomType(getPriceType(roomInfo));
                roomInfo.setBroadband(Broadband.FREE);
                roomInfo.setRefuseState(0);
                roomInfoMap.put(roomId, roomInfo);
            }
            List<PriceInfo> priceInfoList = roomInfo.getPriceInfo();
            if (priceInfoList == null) {
                priceInfoList = new ArrayList<PriceInfo>();
                roomInfo.setPriceInfo(priceInfoList);
            }
            PriceInfo priceInfo = new PriceInfo();
            priceInfo.setRoomId(roomId);
            priceInfo.setDate(roomRateStatusDaily.getDate());
            priceInfo.setStatus(roomRateStatusDaily.getStatus() == 1 ? 0 : 1);// 开元的status意义：1表示可订，其余都认为是不可订
            priceInfoList.add(priceInfo);
        }

        List<RoomInfo> roomInfoList = new ArrayList<RoomInfo>();
        for (RoomInfo roomInfo : roomInfoMap.values()) {
            roomInfoList.add(roomInfo);
        }
        hotelInfo.setRoomInfo(roomInfoList);
    }

    private static List<PriceInfo> getPriceInfoList(OtaPriceParam opp,
            HotelRateDailyDetailResponse rateDailyDetailResponse) {
        List<PriceInfo> priceInfoList = new ArrayList<PriceInfo>();
        if (CollectionUtils.isEmpty(rateDailyDetailResponse.getRoomRateDailies())) {
            return priceInfoList;
        }
        for (RoomRateDaily roomRateDaily : rateDailyDetailResponse.getRoomRateDailies()) {
            PriceInfo priceInfo = parseRoomRateDaily(opp, roomRateDaily);
            priceInfoList.add(priceInfo);
        }
        return priceInfoList;
    }

    /**
     * 解析以获取每天的报价
     * 
     * @param opp
     * @param roomRateDaily
     * @return
     */
    private static PriceInfo parseRoomRateDaily(OtaPriceParam opp, RoomRateDaily roomRateDaily) {
        if (roomRateDaily == null) {
            return null;
        }
        String[] roomIdArr = StringUtils.split(opp.getOtaRoomId(), ROOMID_SEP_CHAR);
        if (!StringUtils.equals(roomIdArr[0], roomRateDaily.getRoomTypeCode())
                || !StringUtils.equals(roomIdArr[1], roomRateDaily.getRatePlanCode())) {
            logger.warn("kaiyuan roomTypeCode or ratePlanCode not match");
            return null;
        }
        PriceInfo priceInfo = new PriceInfo();
        priceInfo.setRoomId(opp.getOtaRoomId());
        BigDecimal price = parsePriceFromRoomRateDail(roomRateDaily);
        if (price == null) {
            logger.warn("kaiyuan parse price error");
            return null;
        }
        priceInfo.setPrice(price);

        priceInfo.setDate(roomRateDaily.getInHouseDate());
        priceInfo.setStatus(roomRateDaily.getStatus() == 1 ? 0 : 1);// 开元的status意义：1表示可订，其余都认为是不可订
        Breakfast breakfast = parseBreakFastFromPackages(roomRateDaily.getPackages());
        if (breakfast == null) {
            breakfast = Breakfast.NONE;
        }
        priceInfo.setBreakfast(breakfast);
        priceInfo.setBreakfastStr(priceInfo.getBreakfast().desc);
        return priceInfo;
    }

    /**
     * 处理报价
     * 
     * @param roomRateDaily
     * @return
     */
    private static BigDecimal parsePriceFromRoomRateDail(RoomRateDaily roomRateDaily) {
        return parsePrice(roomRateDaily.getRateAmount(), roomRateDaily.getServiceCharge(),
                roomRateDaily.getServiceChargeFlag(), roomRateDaily.getTax(), roomRateDaily.getRaxFlag());
    }

    private static BigDecimal parsePrice(BigDecimal rateAmount, BigDecimal serviceCharge, int serviceChargeFlag,
            BigDecimal tax, int taxFlag) {
        if (BigDecimal.ZERO.compareTo(serviceCharge) != 0) {
            if (serviceChargeFlag == 1) {// 1表示百分比，需要先转换成绝对值
                serviceCharge = rateAmount.multiply(serviceCharge);
            } else if (serviceChargeFlag != 0) {// 不是1且不是0,非法数据
                return null;
            }
        }
        if (BigDecimal.ZERO.compareTo(tax) != 0) {
            if (taxFlag == 1) {// 1表示百分比，需要先转换成绝对值
                tax = rateAmount.multiply(tax);
            } else if (taxFlag != 0) {// 不是1且不是0,非法数据
                return null;
            }
        }
        return rateAmount.add(serviceCharge).add(tax).setScale(0, BigDecimal.ROUND_UP);
    }

    /**
     * 解析以获取早餐
     * 
     * @param packages
     * @return
     */
    private static Breakfast parseBreakFastFromPackages(List<Package> packages) {
        if (packages == null) {
            return null;
        }
        for (Package pkg : packages) {
            Breakfast breakfast = BREAKFAST_MAP.get(pkg.getCode());
            if (breakfast != null) {
                return breakfast;
            }
        }
        return null;
    }

    private static RoomInfo.RoomType getPriceType(RoomInfo roomInfo) {
        if (roomInfo.getLast() >= 2 && roomInfo.getAdvance() >= 1) {
            return RoomInfo.RoomType.LASTADV;
        } else if (roomInfo.getLast() >= 2) {
            return RoomInfo.RoomType.LAST;
        } else if (roomInfo.getAdvance() >= 1) {
            return RoomInfo.RoomType.ADVANCE;
        } else {
            return RoomInfo.RoomType.NORMAL;
        }
    }

}
