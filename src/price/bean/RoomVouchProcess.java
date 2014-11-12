package com.qunar.hotel.sa.product.price.bean;

import com.qunar.hotel.oas.bean.ErrorMessageInfo;
import com.qunar.hotel.oas.bean.PriceProcessResult;
import com.qunar.hotel.oas.bean.PriceValidatorCode;
import com.qunar.hotel.oas.bean.vouch.GuaranteeRule;
import com.qunar.hotel.oas.spi.norm.util.VouchRuleUtils;
import com.qunar.hotel.oas.spi.norm.util.VouchRuleUtils.CheckResult;
import com.qunar.hotel.sa.product.hotel.bean.OrigVouchRule;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RoomVouchProcess implements RoomProcess {

    private IRoom room;
    private RoomInfo roomInfo;
    private String message;

    public RoomVouchProcess(IRoom room, RoomInfo roomInfo) {
        this.room = room;
        this.roomInfo = roomInfo;
    }

    @Override
    public PriceProcessResult check() {
        if (room.getGuaranteeRules() == null) {
            message = "have guaranteeRule not pass";
            return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.PRICE_GURANTEE_RULE_ERROR, room.getId(),room.getName())));
        }
        if (CollectionUtils.isNotEmpty(room.getGuaranteeRules())) {
            List<OrigVouchRule> origVouchRuleList = buildOrigVouchRule();
            for (OrigVouchRule origVouchRule : origVouchRuleList) {
                CheckResult<GuaranteeRule> result = VouchRuleUtils.checkOrigVouchRule(origVouchRule);
                if (!result.isRet()) {
                    message = "have guaranteeRule not pass";
                    return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.PRICE_GURANTEE_RULE_ERROR,room.getId(),room.getName())));
                }
            }
            return new PriceProcessResult(true,null);
        }
        return new PriceProcessResult(true,null);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void process() {
        if (!CollectionUtils.isEmpty(room.getGuaranteeRules())) {
            List<OrigVouchRule> origVouchRuleList = buildOrigVouchRule();
            roomInfo.setOrigVouchRule(origVouchRuleList);
            for (OrigVouchRule origVouchRule : origVouchRuleList) {
                CheckResult<GuaranteeRule> result = VouchRuleUtils.checkOrigVouchRule(origVouchRule);
                if (result.isRet()) {
                    roomInfo.addGuaranteeRule(result.getData());
                }
            }
            return;
        }
    }

    private List<OrigVouchRule> buildOrigVouchRule() {
        List<OrigVouchRule> origRuleList = new ArrayList<OrigVouchRule>();
        for (Map<String, String> guaranteeRule : room.getGuaranteeRules()) {
            OrigVouchRule origRule = new OrigVouchRule();
            origRule.setGuaranteeTypeInt(NumberUtils.toInt(guaranteeRule.get("guaranteeType"), -1));
            origRule.setCountStr(StringUtils.trimToEmpty(guaranteeRule.get("count")));
            origRule.setArriveStartTimeStr(StringUtils.trimToEmpty(guaranteeRule.get("arriveStartTime")));
            origRule.setArriveEndTimeStr(StringUtils.trimToEmpty(guaranteeRule.get("arriveEndTime")));
            origRule.setChangeRuleInt(NumberUtils.toInt(guaranteeRule.get("changeRule"), -1));
            origRule.setDayNumStr(StringUtils.trimToEmpty(guaranteeRule.get("dayNum")));
            origRule.setTimeNumStr(StringUtils.trimToEmpty(guaranteeRule.get("timeNum")));
            origRule.setHourNumInt(NumberUtils.toInt(guaranteeRule.get("hourNum"), -1));
            origRuleList.add(origRule);
        }
        return origRuleList;
    }
}
