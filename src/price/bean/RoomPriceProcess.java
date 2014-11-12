package com.qunar.hotel.sa.product.price.bean;

import com.qunar.hotel.oas.bean.ErrorMessageInfo;
import com.qunar.hotel.oas.bean.PriceProcessResult;
import com.qunar.hotel.oas.bean.PriceValidatorCode;
import com.qunar.hotel.oas.bean.SysConfig;
import com.qunar.hotel.oas.bean.price.SupplierInfo;
import com.qunar.hotel.oas.common.util.SysConfigUtil;
import com.qunar.hotel.oas.core.bean.RoomInfo.Breakfast;
import com.qunar.hotel.oas.core.util.DateFormatUtils;
import com.qunar.hotel.oas.core.util.OasConfigUtils;
import com.qunar.hotel.sa.product.hotel.bean.Channel;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

public class RoomPriceProcess implements RoomProcess {
    private static final String META_SEPARATIVE_SIGN = "|";
    private static final Pattern AMOUNT_REGEX = Pattern.compile("^\\d+(\\.\\d{1,2})?$"); // 金额的正则表达式
    private static final Map<String, Breakfast> breakfastMap = new HashMap<String, Breakfast>();
    static {
        breakfastMap.put("-1", Breakfast.HAVE);// "含早"
        breakfastMap.put("0", Breakfast.NONE);// "无早"
        breakfastMap.put("1", Breakfast.ONE);// "单早"
        breakfastMap.put("2", Breakfast.TWO);// "双早"
        breakfastMap.put("3", Breakfast.THREE);// "三早"
        breakfastMap.put("4", Breakfast.FOUR);// "四早"
    }

    private RoomInfo roomInfo;
    private IRoom room;
    private String message;
    private Date beginDate;
    private Date toDate;
    private static final int MAX_ROOM_NUM = 8;

    public RoomPriceProcess(IRoom room, RoomInfo roomInfo, Date beginDate, Date toDate) {
        this.room = room;
        this.roomInfo = roomInfo;
        this.beginDate = beginDate;
        this.toDate = toDate;

    }

    @Override
    public PriceProcessResult check() {
        /** 若报价接口中未传货币类型字段，或所传货币字段的值为空，则默认设置其为人民币 */
        String currencyType = StringUtils.defaultIfEmpty(room.getCurrencyType(), SupplierInfo.DEFAULT_CURRENCY);
        if (!StringUtils.equals(currencyType, roomInfo.getCurrencyType())) {
            message = "代理商不支持该货币类型：" + currencyType;
            return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.ROOM_CURRENCYTYPE_ERROR,room.getId(),room.getName())));
        }

        String prices = room.getPrices();
        String deposits = room.getDeposits();
        String status = room.getStatus();
        String counts = room.getCounts();
        String cashBacks = room.getCashBacks();
        String breakfast = room.getBreakfast();
        String discounts = room.getDiscountAmount();
        int diff = DateFormatUtils.diff(beginDate, toDate);

        if (!checkDayInfo(diff, prices)) {
            message = "prices is not match";
            return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.PRICE_PRICE_ERROR,room.getId(),room.getName())));
        }

        if (deposits != null) {
            if (roomInfo.getPayType() == RoomInfo.PayType.CASH) {
                return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.PRICE_DEPOSIT_PAY_TYPE_NOT_MATCH,room.getId(),room.getName())));
            } else if (roomInfo.getPayType() == RoomInfo.PayType.ONLINE) {
                if (!checkDeposits(diff, deposits, prices)) {
                    return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.PRICE_DEPOSIT_ERROR,room.getId(),room.getName())));
                }
            } else {
                return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.ROOM_PAY_TYPE_ERROR,room.getId(),room.getName())));
            }
        }

        if (!checkDayInfo(diff, status)) {
            message = "status is not match";
            return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.PRICE_STATUS_RULE_ERROR,room.getId(),room.getName())));
        }

        if (StringUtils.isNotBlank(counts) && !StringUtils.equals(counts, "0")) {
            if (!checkDayInfo(diff, counts)) {
                message = "counts is not match";
                return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.PRICE_COUNT_RULE_ERROR,room.getId(),room.getName())));
            }
        }

        if (StringUtils.isNotBlank(cashBacks) && !StringUtils.equals(cashBacks, "0")) {
            if (!checkDayInfo(diff, cashBacks)) {
                message = "cashBacks is not match";
                return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.PRICE_CASH_BACK_RULE_ERROR,room.getId(),room.getName())));
            }
        }

        if (StringUtils.isBlank(breakfast)) {
            message = "breakfast is empty";
            return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.PRICE_BREAKFAST_RULE_ERROR,room.getId(),room.getName())));
        }

        /**
         * 夜销校验
         */
        if (StringUtils.equals(Channel.LAST_MINUTE.getCode()+"",room.getChannel()) && StringUtils.isNotEmpty(discounts)) {
            if (!checkDiscount(diff, discounts)) {
                message = "discounts is not match";
                return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.PRICE_LAST_MINUTE_DISCOUNT_ERROR,room.getId(),room.getName())));
            }
            if (!checkLastMinuteRoomNum(diff, counts)) {
                message = "roomNum is not match";
                return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.PRICE_LAST_MINUTE_COUNT_ERROR,room.getId(),room.getName())));
            }

        }

        int maxRoomNum = NumberUtils.toInt(room.getMaxRoomNum(), MAX_ROOM_NUM);

        if (maxRoomNum < 0) {
            return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.ROOM_MAX_ROOMNUM_ERROR,room.getId(),room.getName())));
        }

        //最晚到店时间为00：00,不显示报价
        //TODO
        List<String> notAllowTime = Arrays.asList("0:00", "00:00");
        if (StringUtils.isNotBlank(room.getLatestArriveTime())) {
            if (room.getLatestArriveTime().indexOf(":") == -1) {
               // hotelInfo.setErrorMessage(hotelInfo, PriceValidatorCode.PRICE_CASH_BACK_RULE_ERROR, ErrorLevel.ROOM,message);
                return new PriceProcessResult(false,Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.ROOM_LATEST_ARRIVE_TIME_RULE_ERROR,room.getId(),room.getName())));
            }
            if (notAllowTime.contains(room.getLatestArriveTime())) {
                //hotelInfo.setErrorMessage(hotelInfo, PriceValidatorCode.PRICE_CASH_BACK_RULE_ERROR, ErrorLevel.ROOM,message);
                return new PriceProcessResult(false,Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.ROOM_LATEST_ARRIVE_TIME_ZERO,room.getId(),room.getName())));
            }
        }

        //当日报价中，最晚到店时间小于等于当前时间的,不显示报价（分钟向上取整）
        Date now = new Date();
        if (DateUtils.isSameInstant(DateUtils.truncate(now, Calendar.DATE), beginDate)) {
            if (StringUtils.isNotBlank(room.getLatestArriveTime())) {
                String[] startArr = StringUtils.split(room.getLatestArriveTime(), ":");
                int startHourNum = NumberUtils.toInt(startArr[0], -1);
                if (startHourNum <= (int) DateUtils.getFragmentInHours(now, Calendar.DAY_OF_YEAR)) {
                   // hotelInfo.setErrorMessage(hotelInfo, PriceValidatorCode.PRICE_CASH_BACK_RULE_ERROR, ErrorLevel.ROOM,message);
                    return new PriceProcessResult(false,Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.ROOM_LATEST_ARRIVE_TIME_AFTER_NOW,room.getId(),room.getName())));
                }
            }
        }

        return new PriceProcessResult(true,null);
    }

    /**
     * 判断夜销房量大于0
     * 
     * @param diff
     * @param counts
     * @return
     */
    private boolean checkLastMinuteRoomNum(int diff, String counts) {
        String[] countList = new String[diff];
        if (StringUtils.isNotBlank(counts) && !StringUtils.equals(counts, "0")) {
            countList = StringUtils.split(counts, META_SEPARATIVE_SIGN);
        } else {
            Arrays.fill(countList, "0");
        }
        for (String count : countList) {
            if (NumberUtils.toInt(count, -1) <= 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void process() {
        int diff = DateFormatUtils.diff(beginDate, toDate);
        String[] priceList = StringUtils.split(room.getPrices(), META_SEPARATIVE_SIGN);
        String[] statusList = StringUtils.split(room.getStatus(), META_SEPARATIVE_SIGN);

        String[] countList = new String[diff];
        String counts = room.getCounts();
        if (StringUtils.isNotBlank(counts) && !StringUtils.equals(counts, "0")) {
            countList = StringUtils.split(counts, META_SEPARATIVE_SIGN);
        } else {
            Arrays.fill(countList, "0");
        }

        String[] cashBackList = new String[diff];
        String cashBacks = room.getCashBacks();
        if (StringUtils.isNotBlank(cashBacks) && !StringUtils.equals(cashBacks, "0")) {
            cashBackList = StringUtils.split(cashBacks, META_SEPARATIVE_SIGN);
        } else {
            Arrays.fill(cashBackList, "0");
        }

        String[] breakfastList = new String[diff];
        String breakfast = room.getBreakfast();
        String[] breakfastemp = StringUtils.split(breakfast, META_SEPARATIVE_SIGN);
        if (breakfastemp.length == 1) {
            Arrays.fill(breakfastList, breakfast);
            // TODO
            roomInfo.setBreakfast(convertToBreakfast(breakfast));
            roomInfo.setBreakfastStr(convertToBreakfastStr(breakfast));
        } else {
            breakfastList = breakfastemp;
        }

        String[] depositList = null;
        if (roomInfo.getPayType() == RoomInfo.PayType.ONLINE) {
            String deposits = room.getDeposits();
            if (deposits != null) {
                depositList = StringUtils.split(deposits, META_SEPARATIVE_SIGN);
            }
        }
        String[] discountList = new String[diff];
        if (roomInfo.getChannel() != null) {
            String discounts = room.getDiscountAmount();
            if (StringUtils.isNotBlank(discounts) && !StringUtils.equals(discounts, "0")) {
                discountList = StringUtils.split(discounts, META_SEPARATIVE_SIGN);
            } else {
                Arrays.fill(discountList, "0");
            }
        } else {
            Arrays.fill(discountList, "0");
        }

        List<PriceInfo> priceInfoList = new ArrayList<PriceInfo>();
        BigDecimal forexSumMoney = BigDecimal.ZERO;
        while (diff > 0) {
            Date date = DateUtils.addDays(beginDate, diff - 1);

            PriceInfo priceInfo = new PriceInfo();
            priceInfo.setHotelId(roomInfo.getOwnHotelId());
            priceInfo.setRoomId(roomInfo.getOtaRoomId());
            priceInfo.setDate(date);
            BigDecimal price = new BigDecimal(priceList[diff - 1]);
            priceInfo.setPrice(price.setScale(2));

            String currencyType = roomInfo.getCurrencyType();
            priceInfo.setCurrencyCode(currencyType);
            if (!StringUtils.equals(currencyType, SupplierInfo.DEFAULT_CURRENCY)) {
                priceInfo.setForexMoney(price);
                forexSumMoney = forexSumMoney.add(price);
            }

            if (price.compareTo(new BigDecimal(0)) == 0) {
                priceInfo.setStatus(1);
            } else {
                priceInfo.setStatus(NumberUtils.toInt(statusList[diff - 1]));
            }
            priceInfo.setCount(NumberUtils.toInt(countList[diff - 1]));
            priceInfo.setCashBack(NumberUtils.toInt(cashBackList[diff - 1]));
            priceInfo.setBreakfast(convertToBreakfast(breakfastList[diff - 1]));
            priceInfo.setBreakfastStr(convertToBreakfastStr(breakfastList[diff - 1]));
            priceInfo.setDeposit(depositList == null ? null : new BigDecimal(NumberUtils.toInt(depositList[diff - 1]))
                    .setScale(2, BigDecimal.ROUND_UP));
            priceInfo.setDiscount(NumberUtils.toInt(discountList[diff - 1]));
            priceInfoList.add(priceInfo);

            diff--;
        }

        int maxRoomNum = NumberUtils.toInt(room.getMaxRoomNum(), MAX_ROOM_NUM);

        roomInfo.setMaxRoomNumber(maxRoomNum > MAX_ROOM_NUM || maxRoomNum < 1 ? MAX_ROOM_NUM : maxRoomNum);
        roomInfo.setLatestArriveTime(room.getLatestArriveTime());
        roomInfo.setForexMoney(forexSumMoney);
        roomInfo.setPriceInfo(priceInfoList);
    }

    private boolean checkDayInfo(int diff, String info) {
        String[] dayInfos = StringUtils.split(info, META_SEPARATIVE_SIGN);
        return ArrayUtils.isNotEmpty(dayInfos) && dayInfos.length == diff && isDailyAmountValid(dayInfos);
    }

    /**
     * 检查夜销的格式
     * 
     * @param diff
     * @param info
     * @return
     */
    private boolean checkDiscount(int diff, String info) {
        String[] dayInfos = StringUtils.split(info, META_SEPARATIVE_SIGN);
        return ArrayUtils.isNotEmpty(dayInfos) && dayInfos.length == diff && isInteger(dayInfos);
    }

    public static boolean isInteger(String[] values) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        for (String value : values) {
            if (!pattern.matcher(value).matches()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 校验每日的金额是否合法（支持正整数和小数，小数的小数部分不超过两位数字）
     * 
     * @param values 多天的金额
     * @return
     */
    private boolean isDailyAmountValid(String[] values) {
        for (String value : values) {
            /** 此值可为整数或小数，如果为小数则需要小数部分数字不超过两位，eg：200.13 */
            if (!NumberUtils.isNumber(value) || !AMOUNT_REGEX.matcher(value).matches()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 校验预付定金金额
     * 
     * @param diff 入住日期和离店日期的间隔天数
     * @param deposits 每日定金
     * @param prices 每日报价
     * @return
     */
    private boolean checkDeposits(int diff, String deposits, String prices) {
        String[] dayDeposits = StringUtils.split(deposits, META_SEPARATIVE_SIGN);

        if (ArrayUtils.isEmpty(dayDeposits) || dayDeposits.length != diff) {
            return false;
        }

        String[] dayPrices = StringUtils.split(prices, META_SEPARATIVE_SIGN);
        BigDecimal depositSum = BigDecimal.ZERO;
        BigDecimal priceSum = BigDecimal.ZERO;
        for (int i = 0, size = dayDeposits.length; i < size; i++) {
            String deposit = dayDeposits[i];
            String price = dayPrices[i];

            // 目前只支持整数，支持小数点时修改成NumberUtils.isNumber
            if (!NumberUtils.isDigits(deposit) || NumberUtils.toDouble(deposit) < 0) {
                return false;
            }

            BigDecimal depositDecimal = new BigDecimal(deposit);
            BigDecimal priceDecimal = new BigDecimal(price);
            int cmp = depositDecimal.compareTo(priceDecimal);
            if (cmp > 0) {
                return false;
            }
            depositSum = depositSum.add(depositDecimal);
            priceSum = priceSum.add(priceDecimal);
        }

        int cmp = depositSum.compareTo(priceSum);
        boolean isDepositLargerThanPrice = OasConfigUtils.isDepositEqualToPriceAllowed() ? cmp > 0 : cmp >= 0;
        if (isDepositLargerThanPrice) {
            return false;
        }

        return true;
    }

    private static Breakfast convertToBreakfast(String breakfastNumber) {
        Breakfast breakfast = breakfastMap.get(breakfastNumber);
        return breakfast == null ? Breakfast.NONE : breakfast;
    }

    private static String convertToBreakfastStr(String breakfastNumber) {
        String breakfast = SysConfigUtil.getBreakfast(breakfastNumber, SysConfig.OTATYPE.NORM);
        return breakfast;
    }

}
