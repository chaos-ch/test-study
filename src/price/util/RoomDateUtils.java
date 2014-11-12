package com.qunar.hotel.sa.product.price.util;

import java.util.*;

import com.qunar.hotel.sa.common.util.DateUtil;
import com.qunar.hotel.sa.product.hotel.bean.Pair;
import com.qunar.hotel.sa.product.price.bean.RoomConsumeBeanForJson;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import com.qunar.hotel.sa.common.util.DateFormatUtils;


public class RoomDateUtils {

    /**
     * 获取指定日期段内（闭区间）所有日期的字符串形式
     * 
     * @param fromDate
     * @param toDate
     * @return
     */
    public static List<String> getDateStrList(Date fromDate, Date toDate) {
        return getDateStrList(fromDate, toDate, "yyyy-MM-dd");
    }

    public static List<String> getDateStrList(Date fromDate, Date toDate, String dateFormat) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(fromDate);
        c2.setTime(toDate);
        if (StringUtils.isBlank(dateFormat)) {
            dateFormat = "yyyy-MM-dd";
        }

        return getDateStrList(c1, c2, dateFormat);
    }

    /**
     * 获取指定日期段内（闭区间）所有日期的Date形式
     * 
     * @param fromDate
     * @param toDate
     * @return
     */
    public static List<Date> getDateList(Date fromDate, Date toDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(fromDate);
        c2.setTime(toDate);

        return getDateList(c1, c2);
    }

    /**
     * 获取一个日期段内(闭区间)的所有满足给定星期的日期，1表示周日，2表示周一.....7表示周六
     * 
     * @param startDate
     * @param endDate
     * @param weekDefineArr 可以为空，如果为空，则表示部分星期，全部都算
     * @return
     */
    public static List<Date> getDateList(Date startDate, Date endDate, String[] weekDefineArr) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(startDate);
        c2.setTime(endDate);

        if (weekDefineArr != null && weekDefineArr.length > 0) {
            return getDateList(c1, c2, weekDefineArr);
        } else {
            return getDateList(c1, c2);
        }
    }

    public static List<Date> getDateList(Calendar c1, Calendar c2) {
        List<Date> dateList = new ArrayList<Date>();
        while (c1.compareTo(c2) <= 0) {
            dateList.add(c1.getTime());
            c1.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dateList;
    }

    public static List<String> getDateStrList(Calendar c1, Calendar c2, String dateFormat) {
        List<String> dateStrList = new ArrayList<String>();
        while (c1.compareTo(c2) <= 0) {
            dateStrList.add(DateFormatUtils.format4y2M2d(c1.getTime()));
            c1.add(Calendar.DAY_OF_MONTH, 1);
        }

        return dateStrList;
    }

    public static List<Date> getDateList(Calendar c1, Calendar c2, String[] weekDefineArr) {
        List<Date> dateList = new ArrayList<Date>();
        List<Integer> weekDefineList = new ArrayList<Integer>();
        for (String e : weekDefineArr) {
            weekDefineList.add(NumberUtils.toInt(e));
        }
        while (c1.compareTo(c2) <= 0) {
            int dow = c1.get(Calendar.DAY_OF_WEEK);
            if (weekDefineList.contains(dow)) {
                dateList.add(c1.getTime());
            }
            c1.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dateList;
    }

    /**
     * 合并扣减记录
     *
     * @param consumeInfo
     * @return
     */
    public static List<RoomConsumeBeanForJson> getRcbfjList(List<Pair<Date, Integer>> consumeInfo) {
        Collections.sort(consumeInfo, new PairComparator());
        List<Date> dateList = new ArrayList<Date>();
        List<Integer> countList = new ArrayList<Integer>();
        for (Pair<Date, Integer> pair : consumeInfo) {
            dateList.add(pair.getONE());
            countList.add(pair.getTWO());
        }
        return getRcbfjList(dateList, countList);
    }

    /**
     * 合并扣减记录
     * 
     * @param fromDate
     * @param toDate
     * @param countList
     * @return
     */
    public static List<RoomConsumeBeanForJson> getRcbfjList(Date fromDate, Date toDate, List<Integer> countList) {
        if (countList == null || countList.size() == 0) {
            return Collections.emptyList();
        }

        List<Date> dateList = RoomDateUtils.getDateList(fromDate, toDate);
        return getRcbfjList(dateList, countList);
    }

    private static List<RoomConsumeBeanForJson> getRcbfjList(List<Date> dateList, List<Integer> countList) {
        if (countList == null || countList.size() == 0) {
            return Collections.emptyList();
        }

        if (dateList == null || dateList.size() != countList.size()) {
            return Collections.emptyList();
        }
        Date date = dateList.get(0);
        List<RoomConsumeBeanForJson> rcsbList = new ArrayList<RoomConsumeBeanForJson>();
        RoomConsumeBeanForJson rcsb = new RoomConsumeBeanForJson();
        rcsb.setFromDate(DateFormatUtils.format4y2M2d(date));
        rcsb.setToDate(DateFormatUtils.format4y2M2d(date));
        rcsb.setCount(countList.get(0));
        rcsbList.add(rcsb);

        for (int i = 1; i < dateList.size(); i++) {
            Date _date = dateList.get(i);
            Integer count = countList.get(i);
            date = com.qunar.hotel.sa.common.util.DateFormatUtils.parse4y2M2d(rcsb.getToDate());
            if (count == rcsb.getCount() && date.getTime() + DateUtil.ONE_DAY_IN_MILLSECONDS == _date.getTime()) {
                rcsb.setToDate(DateFormatUtils.format4y2M2d(_date));
            } else {
                rcsb = new RoomConsumeBeanForJson();
                rcsb.setFromDate(DateFormatUtils.format4y2M2d(_date));
                rcsb.setToDate(DateFormatUtils.format4y2M2d(_date));
                rcsb.setCount(count);
                rcsbList.add(rcsb);
            }
        }
        return rcsbList;
    }

    static class PairComparator implements Comparator<Pair<Date, Integer>> {

        @Override
        public int compare(Pair<Date, Integer> pair1, Pair<Date, Integer> pair2) {
            if (pair1 == pair2) {
                return 0;
            }
            if (pair1.getONE().equals(pair2.getONE())) {
                return 0;
            }
            if (pair1.getONE().before(pair2.getONE())) {
                return -1;
            }
            return 1;
        }

    }

}
