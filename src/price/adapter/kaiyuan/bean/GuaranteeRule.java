package com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;

/**
 * 担保规则
 * 
 * @author weiming.liao
 */
@SuppressWarnings("serial")
public class GuaranteeRule implements Serializable {

    private static final long serialVersionUID = -1021770082237511029L;
    protected DateRule dateRule;
    protected ArriveRule arriveRule;
    protected CountRule countRule;
    protected VouchType vouchType;
    protected String arriveTime;

    protected int changeType;
    protected String DayNum;
    protected String timeNum;
    protected String HourNum;
    protected String description;
    protected int advanceDayNum = 0;
    protected String advanceTimeNum;

    public int getAdvanceDayNum() {
        return advanceDayNum;
    }

    public void setAdvanceDayNum(int advanceDayNum) {
        this.advanceDayNum = advanceDayNum;
    }

    public String getAdvanceTimeNum() {
        return advanceTimeNum;
    }

    public void setAdvanceTimeNum(String advanceTimeNum) {
        this.advanceTimeNum = advanceTimeNum;
    }

    /**
     * @param params
     * @return
     */
    public Result decision(Params params) {
        // 所有参数不能为空
        if (params == null || params.getCheckInDate() == null || params.getCheckOutDate() == null
        // || params.getArriveEarlyTime() == null
                || params.getArriveLaterTime() == null || params.getRoomNum() == 0) {
            Result result = new Result();
            result.setVouch(false);
            return result;
        }
        // 默认时间规则
        boolean isGuarantee = false;
        // 到店时间 房间数量规则不同时为空时
        if (arriveRule != null || countRule != null || dateRule != null) {
            boolean date = this.dateRule == null || dateRule.decision(params);
            boolean arri = this.arriveRule == null || arriveRule.decision(params);
            boolean count = this.countRule == null || countRule.decision(params);
            isGuarantee = date && arri && count;
        }
        // 设置返回值
        Result result = new Result();
        result.setVouch(false);
        result.setDescription(description);
        result.setVouchType(vouchType);
        if (isGuarantee) {
            result.setVouch(true);
            result.setLastCancelTime(changeDate(params));
        }
        return result;
    }

    /**
     * @param params
     * @return
     */
    public Result decision2(Params params) {
        // 所有参数不能为空
        if (params == null || params.getCheckInDate() == null || params.getCheckOutDate() == null
        // || params.getArriveEarlyTime() == null
                || params.getArriveLaterTime() == null || params.getRoomNum() == 0) {
            Result result = new Result();
            result.setVouch(false);
            return result;
        }
        // 默认时间规则
        boolean isGuarantee = dateRule != null && dateRule.decision(params);
        // 到店时间 房间数量规则不同时为空时
        if (arriveRule != null || countRule != null) {
            boolean arri = this.arriveRule != null && arriveRule.decision(params);
            boolean count = this.countRule != null && countRule.decision(params);
            isGuarantee = isGuarantee && (arri || count);
        }
        // 设置返回值
        Result result = new Result();
        result.setVouch(false);
        result.setDescription(description);
        result.setVouchType(vouchType);
        if (isGuarantee) {
            result.setVouch(true);
            result.setLastCancelTime(changeDate(params));
        }
        return result;
    }

    private Date changeDate(Params params) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        switch (this.changeType) {
        case 1:
            return date;
        case 2:
            try {
                date = sdf.parse(this.DayNum + " " + this.timeNum);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        case 3:
            return DateUtils.addHours(
                    params.getArriveEarlyTime() == null ? params.getArriveLaterTime() : params.getArriveEarlyTime(),
                    -Integer.parseInt(HourNum));
            // Calendar time = Calendar.getInstance();
            // time.setTime(params.getCheckInDate());
            // String at = this.arriveTime == null ? "18:00" : this.arriveTime;
            // String[] strs = at.split(":");
            // time.set(Calendar.HOUR_OF_DAY, Integer.parseInt(strs[0]));
            // time.set(Calendar.MINUTE, Integer.parseInt(strs[1]));
            // time.set(Calendar.SECOND, 0);
            //
            // time.add(Calendar.HOUR_OF_DAY, -Integer.parseInt(HourNum));
            // return time.getTime();
        case 4:
            Calendar checkIn = Calendar.getInstance();
            checkIn.setTime(DateUtils.addDays(params.getCheckInDate(), -(Integer.parseInt(HourNum) / 24)));
            checkIn.set(Calendar.HOUR_OF_DAY, 24);
            checkIn.set(Calendar.MINUTE, 0);
            checkIn.set(Calendar.SECOND, 0);
            checkIn.add(Calendar.HOUR_OF_DAY, -Integer.parseInt(HourNum) % 24);
            return checkIn.getTime();
        case 5:
            try {
                SimpleDateFormat sdfTemp = new SimpleDateFormat("yyyy-MM-dd");
                date = sdf.parse(sdfTemp.format(DateUtils.addDays(params.getCheckInDate(), -(advanceDayNum))) + " "
                        + advanceTimeNum);
                return date;
            } catch (Exception e) {
                e.printStackTrace();
            }
        default:
            return date;

        }
    }

    /**
     * 时间规则*
     */
    public static class DateRule implements Rule {
        private Date startDate;
        private Date endDate;
        private int dateType;
        private Set<Integer> weekSet;

        public DateRule() {
            super();
        }

        public DateRule(Date startDate, Date endDate, int dateType, Set<Integer> weekSet) {
            super();
            this.startDate = startDate;
            this.endDate = endDate;
            this.dateType = dateType;
            this.weekSet = weekSet;
        }

        @SuppressWarnings("deprecation")
        @Override
        public boolean decision(Params params) {
            boolean inDate = false;
            boolean inWeek = false;
            int day = 0;
            switch (dateType) {
            case 1:// 入住时间验证
                inDate = !startDate.after(params.getCheckInDate()) && !endDate.before(params.getCheckInDate());
                day = params.getCheckInDate().getDay();
                if (day == 0) {
                    day = 7;
                }
                inWeek = weekSet.contains(day);
                break;
            case 2:// 在店时间验证
                inDate = !startDate.after(params.getCheckOutDate()) && !endDate.before(params.getCheckInDate());
                Date end = params.getCheckInDate();
                while (!inWeek && end.before(params.getCheckOutDate())) {
                    day = end.getDay();
                    if (day == 0) {
                        day = 7;
                    }
                    inWeek = weekSet.contains(day);
                    end = DateUtils.addDays(end, 1);
                }
                break;
            case 3:// 预订时间验证
                Date thisTime = new Date();
                inDate = !startDate.after(thisTime) && !endDate.before(thisTime);
                day = thisTime.getDay();
                if (day == 0) {
                    day = 7;
                }
                inWeek = weekSet.contains(day);
                break;
            default:
                inDate = false;
            }

            return inDate && inWeek;
        }

        @Override
        public String toString() {
            return "DateRule [startDate=" + startDate + ", endDate=" + endDate + ", dateType=" + dateType
                    + ", weekSet=" + weekSet + "]";
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public int getDateType() {
            return dateType;
        }

        public void setDateType(int dateType) {
            this.dateType = dateType;
        }

        public Set<Integer> getWeekSet() {
            return weekSet;
        }

        public void setWeekSet(Set<Integer> weekSet) {
            this.weekSet = weekSet;
        }

    }

    /**
     * 到店时间规则*
     */
    public static class ArriveRule implements Rule {
        private String arriveStatTime;
        private String arriveEndTime;
        private boolean isTomorrow;

        public ArriveRule() {
            super();
            // TODO Auto-generated constructor stub
        }

        public ArriveRule(String arriveStatTime, String arriveEndTime, boolean isTomorrow) {
            super();
            this.arriveStatTime = arriveStatTime;
            this.arriveEndTime = arriveEndTime;
            this.isTomorrow = isTomorrow;
        }

        @Override
        public boolean decision(Params params) {
            Calendar base = Calendar.getInstance();
            base.setTime(params.getCheckInDate());
            String[] strs = arriveStatTime.split(":");
            base.set(Calendar.HOUR_OF_DAY, Integer.parseInt(strs[0]));
            base.set(Calendar.MINUTE, Integer.parseInt(strs[1]));
            base.set(Calendar.SECOND, 0);

            Calendar arrive = Calendar.getInstance();
            arrive.setTime(params.getArriveLaterTime());
            return arrive.after(base);
        }

        @Override
        public String toString() {
            return "ArriveRule [arriveStatTime=" + arriveStatTime + ", arriveEndTime=" + arriveEndTime
                    + ", isTomorrow=" + isTomorrow + "]";
        }

        public String getArriveStatTime() {
            return arriveStatTime;
        }

        public void setArriveStatTime(String arriveStatTime) {
            this.arriveStatTime = arriveStatTime;
        }

        public String getArriveEndTime() {
            return arriveEndTime;
        }

        public void setArriveEndTime(String arriveEndTime) {
            this.arriveEndTime = arriveEndTime;
        }

        public boolean isTomorrow() {
            return isTomorrow;
        }

        public void setTomorrow(boolean isTomorrow) {
            this.isTomorrow = isTomorrow;
        }

    }

    /**
     * 数量规则*
     */
    public static class CountRule implements Rule {
        int count;

        public CountRule() {
            super();
        }

        public CountRule(int count) {
            super();
            this.count = count;
        }

        @Override
        public boolean decision(Params params) {

            return params.getRoomNum() >= count;
        }

        @Override
        public String toString() {
            return "CountRule [count=" + count + "]";
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

    }

    // //geter & setter
    public DateRule getDateRule() {
        return dateRule;
    }

    public void setDateRule(DateRule dateRule) {
        this.dateRule = dateRule;
    }

    public ArriveRule getArriveRule() {
        return arriveRule;
    }

    public void setArriveRule(ArriveRule arriveRule) {
        this.arriveRule = arriveRule;
    }

    public CountRule getCountRule() {
        return countRule;
    }

    public void setCountRule(CountRule countRule) {
        this.countRule = countRule;
    }

    public VouchType getVouchType() {
        return vouchType;
    }

    public void setVouchType(VouchType vouchType) {
        this.vouchType = vouchType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public String getDayNum() {
        return DayNum;
    }

    public void setDayNum(String dayNum) {
        DayNum = dayNum;
    }

    public String getTimeNum() {
        return timeNum;
    }

    public void setTimeNum(String timeNum) {
        this.timeNum = timeNum;
    }

    public String getHourNum() {
        return HourNum;
    }

    public void setHourNum(String hourNum) {
        HourNum = hourNum;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    @Override
    public String toString() {
        return "GuaranteeRule [dateRule=" + dateRule + ", arriveRule=" + arriveRule + ", countRule=" + countRule
                + ", vouchType=" + vouchType + ", changeType=" + changeType + ", DayNum=" + DayNum + ", timeNum="
                + timeNum + ", HourNum=" + HourNum + ", description=" + description + ",advanceDayNum=" + advanceDayNum
                + ",advanceTimeNum=" + advanceTimeNum + "]";
    }

}
