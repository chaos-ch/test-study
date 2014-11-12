package com.qunar.hotel.sa.product.price.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * User: wei.ding Date: 3/27/13
 */
@XStreamAlias("room")
public class RoomBean {
    @XStreamAsAttribute
    private String id;

    @XStreamAsAttribute
    private String name;

    @XStreamAsAttribute
    private String breakfast;

    @XStreamAsAttribute
    @XStreamAlias("bed")
    private String bedType;

    @XStreamAsAttribute
    private String broadband;

    @XStreamAsAttribute
    @XStreamAlias("prepay")
    private String payType;

    @XStreamAsAttribute
    private String prices;

    @XStreamAsAttribute
    private String status;

    @XStreamAsAttribute
    private String counts;

    @XStreamAsAttribute
    private String cashBacks;

    @XStreamAsAttribute
    private String last;

    @XStreamAsAttribute
    private String advance;

    @XStreamAsAttribute
    @XStreamAlias("refusestate")
    private String refuseState;

    @XStreamAlias("vouchrule")
    private VouchRuleBean vouchRuleBean;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public String getBroadband() {
        return broadband;
    }

    public void setBroadband(String broadband) {
        this.broadband = broadband;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCounts() {
        return counts;
    }

    public void setCounts(String counts) {
        this.counts = counts;
    }

    public String getCashBacks() {
        return cashBacks;
    }

    public void setCashBacks(String cashBacks) {
        this.cashBacks = cashBacks;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getAdvance() {
        return advance;
    }

    public void setAdvance(String advance) {
        this.advance = advance;
    }

    public String getRefuseState() {
        return refuseState;
    }

    public void setRefuseState(String refuseState) {
        this.refuseState = refuseState;
    }

    public VouchRuleBean getVouchRuleBean() {
        return vouchRuleBean;
    }

    public void setVouchRuleBean(VouchRuleBean vouchRuleBean) {
        this.vouchRuleBean = vouchRuleBean;
    }

    public static class VouchRuleBean {
        @XStreamAlias("isvouch")
        @XStreamAsAttribute
        private String isVouch;
        @XStreamAlias("vouchtype")
        @XStreamAsAttribute
        private String vouchType;
        @XStreamAlias("arriverule")
        @XStreamAsAttribute
        private String arriveRule;
        @XStreamAlias("arrivestarttime")
        @XStreamAsAttribute
        private String arriveStartTime;
        @XStreamAlias("arriveendtime")
        @XStreamAsAttribute
        private String arriveEndTime;
        @XStreamAlias("istomorrow")
        @XStreamAsAttribute
        private String isTomorrow;
        @XStreamAlias("countrule")
        @XStreamAsAttribute
        private String countRule;
        @XStreamAsAttribute
        private String count;
        @XStreamAlias("daterule")
        @XStreamAsAttribute
        private String dateRule;
        @XStreamAlias("datelimit")
        @XStreamAsAttribute
        private String dateLimit;
        @XStreamAlias("starttime")
        @XStreamAsAttribute
        private String startTime;
        @XStreamAlias("endtime")
        @XStreamAsAttribute
        private String endTime;
        @XStreamAsAttribute
        private String weekset;
        @XStreamAlias("changerule")
        @XStreamAsAttribute
        private String changeRule;
        @XStreamAlias("daynum")
        @XStreamAsAttribute
        private String dayNum;
        @XStreamAlias("timenum")
        @XStreamAsAttribute
        private String timeNum;
        @XStreamAlias("hournum")
        @XStreamAsAttribute
        private String hourNum;

        public String getVouch() {
            return isVouch;
        }

        public String getVouchType() {
            return vouchType;
        }

        public String getArriveRule() {
            return arriveRule;
        }

        public String getArriveStartTime() {
            return arriveStartTime;
        }

        public String getArriveEndTime() {
            return arriveEndTime;
        }

        public String getTomorrow() {
            return isTomorrow;
        }

        public String getCountRule() {
            return countRule;
        }

        public String getCount() {
            return count;
        }

        public String getDateRule() {
            return dateRule;
        }

        public String getDateLimit() {
            return dateLimit;
        }

        public String getStartTime() {
            return startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public String getWeekset() {
            return weekset;
        }

        public String getChangeRule() {
            return changeRule;
        }

        public String getDayNum() {
            return dayNum;
        }

        public String getTimeNum() {
            return timeNum;
        }

        public String getHourNum() {
            return hourNum;
        }
    }

    @Override
    public String toString() {
        return "room id=\"" + id + "\" name=\"" + name + "\"";
    }
}
