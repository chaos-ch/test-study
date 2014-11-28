package com.qunar.test.json;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author dustin.wang
 * 
 */
public class MobGuaranteeRule implements Serializable {

    private static final long serialVersionUID = 1198909219660686341L;

    private BigDecimal guaranteeMoney;
    private int guaranteeType;
    private int count;
    private String arriveTime;
    private boolean isTommorrow;
    private boolean isChange;
    private String lastCancelTime;
    private String ruleDesc;

    public BigDecimal getGuaranteeMoney() {
        return guaranteeMoney;
    }

    public void setGuaranteeMoney(BigDecimal guaranteeMoney) {
        this.guaranteeMoney = guaranteeMoney;
    }

    public String getLastCancelTime() {
        return lastCancelTime;
    }

    public void setLastCancelTime(String lastCancelTime) {
        this.lastCancelTime = lastCancelTime;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getGuaranteeType() {
        return guaranteeType;
    }

    public void setGuaranteeType(int guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    public boolean getIsChange() {
        return isChange;
    }

    public void setIsChange(boolean change) {
        isChange = change;
    }

    public boolean getIsTommorrow() {
        return isTommorrow;
    }

    public void setIsTommorrow(boolean tommorrow) {
        isTommorrow = tommorrow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MobGuaranteeRule that = (MobGuaranteeRule) o;

        if (count != that.count) return false;
        if (guaranteeType != that.guaranteeType) return false;
        if (isChange != that.isChange) return false;
        if (isTommorrow != that.isTommorrow) return false;
        if (arriveTime != null ? !arriveTime.equals(that.arriveTime) : that.arriveTime != null) return false;
        if (guaranteeMoney != null ? !guaranteeMoney.equals(that.guaranteeMoney) : that.guaranteeMoney != null)
            return false;
        if (lastCancelTime != null ? !lastCancelTime.equals(that.lastCancelTime) : that.lastCancelTime != null)
            return false;
        if (ruleDesc != null ? !ruleDesc.equals(that.ruleDesc) : that.ruleDesc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = guaranteeMoney != null ? guaranteeMoney.hashCode() : 0;
        result = 31 * result + guaranteeType;
        result = 31 * result + count;
        result = 31 * result + (arriveTime != null ? arriveTime.hashCode() : 0);
        result = 31 * result + (isTommorrow ? 1 : 0);
        result = 31 * result + (isChange ? 1 : 0);
        result = 31 * result + (lastCancelTime != null ? lastCancelTime.hashCode() : 0);
        result = 31 * result + (ruleDesc != null ? ruleDesc.hashCode() : 0);
        return result;
    }
}
