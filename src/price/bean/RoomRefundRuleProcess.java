package com.qunar.hotel.sa.product.price.bean;

import com.qunar.hotel.oas.bean.ErrorMessageInfo;
import com.qunar.hotel.oas.bean.PriceProcessResult;
import com.qunar.hotel.oas.bean.PriceValidatorCode;
import com.qunar.hotel.oas.core.util.DateFormatUtils;
import com.qunar.hotel.oas.core.util.RefundRuleUtils;
import com.qunar.hotel.sa.product.hotel.bean.HotelRefundRule;
import com.qunar.hotel.sa.product.hotel.bean.NonRefundableRange;
import com.qunar.hotel.sa.product.hotel.bean.RefundRule;
import com.qunar.hotel.sa.product.hotel.bean.RefundType;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class RoomRefundRuleProcess implements RoomProcess {
    private static final int MAX_PERCENT_VALUE = 1000;
    private static final int MAX_VALUE = 1000;
    private static final int MAX_DATE_HOUR = 24 * 22;
    private static final Pattern AMOUNT_REGEX = Pattern.compile("^\\d+(\\.\\d{1,2})?$");
    private RoomInfo roomInfo;
    private IRoom room;
    private String message;
    private Element roomElement;

    public RoomRefundRuleProcess(IRoom room, RoomInfo roomInfo, Element roomElement) {
        this.room = room;
        this.roomInfo = roomInfo;
        this.roomElement = roomElement;
    }

    @Override
    public PriceProcessResult check() {
        Element refundElement = roomElement.element("refund");
        if (refundElement == null) {
            return new PriceProcessResult(true,null);
        }

        List<Element> rules = refundElement.elements("rule");
        if (CollectionUtils.isEmpty(rules)) {
            return new PriceProcessResult(true,null);
        }

        for (Element rule : rules) {
            String type = rule.attributeValue("type");
            /* 退款类型为不扣房费或扣除首晚房费时，value值不需校验 */
            if ("0".equals(type) || "3".equals(type)) {
                continue;
            }

            String value = rule.attributeValue("value");
            if (!NumberUtils.isNumber(value) || !AMOUNT_REGEX.matcher(value).matches()) {
                return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.PRICE_REFUND_ERROR,room.getId(),room.getName())));
            }

            /* 退款类型为扣除房费的百分比时，value值需不大于100 */
            if ("1".equals(type) && NumberUtils.toDouble(value) > MAX_PERCENT_VALUE) {
                message="退款类型为扣除房费的百分比时，value值需不大于100";
                return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.PRICE_REFUND_ERROR,room.getId(),room.getName())));
            }

            /* 退款类型为扣除固定金额时，value值需不大于1000 */
            if ("2".equals(type) && NumberUtils.toDouble(value) > MAX_VALUE) {
                message = "退款类型为扣除固定金额时，value值需不大于1000";
                return new PriceProcessResult(false, Arrays.asList(new ErrorMessageInfo(PriceValidatorCode.PRICE_REFUND_ERROR,room.getId(),room.getName())));
            }
        }
        
        return new PriceProcessResult(true,null);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void process() {
        Element refund = roomElement.element("refund");
        if (refund == null) {
            return;
        }

        HotelRefundRule hotelRefundRule = new HotelRefundRule();
        hotelRefundRule.setReturnable(StringUtils.equals(refund.attributeValue("type"), "1")); // 是否可退

        if (hotelRefundRule.isReturnable()) {
            List<Element> rules = refund.elements("rule");
            if (!CollectionUtils.isEmpty(rules)) {
                List<RefundRule> refundRules = new ArrayList<RefundRule>();
                for (Element rule : rules) {
                    String before = rule.attributeValue("before");
                    String type = rule.attributeValue("type");
                    String value = rule.attributeValue("value");
                    if (!RefundRuleUtils.checkType(type) || NumberUtils.toInt(before) > MAX_DATE_HOUR
                            || NumberUtils.toDouble(value) > MAX_VALUE) {
                        continue;
                    }
                    RefundRule refundRule = new RefundRule();
                    refundRule.setRuleBefore(NumberUtils.toInt(before));
                    RefundRule.calculateDate(refundRule);
                    refundRule.setRefundType(RefundType.getRefundTypeByCode(NumberUtils.toInt(type)));
                    refundRule.setRuleValue(StringUtils.defaultIfEmpty(value, "0"));
                    refundRules.add(refundRule);
                }

                if (!CollectionUtils.isEmpty(refundRules)) {
                    hotelRefundRule.setBasicRefundRuleList(refundRules);
                }
            }

            List<Element> nonrefundableRanges = refund.elements("nonrefundablerange");
            if (!CollectionUtils.isEmpty(nonrefundableRanges)) {
                List<NonRefundableRange> nonRefundableRanges = new ArrayList<NonRefundableRange>();
                for (Element nonRefundableRangeElement : nonrefundableRanges) {
                    Date fromDate = DateFormatUtils.parse4y2M2d(nonRefundableRangeElement.attributeValue("fromdate"));
                    Date toDate = DateFormatUtils.parse4y2M2d(nonRefundableRangeElement.attributeValue("todate"));
                    if (fromDate != null && toDate != null && DateFormatUtils.diff(fromDate, toDate) >= 0) {
                        NonRefundableRange nonRefundableRange = new NonRefundableRange();
                        nonRefundableRange.setFromDate(fromDate);
                        nonRefundableRange.setToDate(toDate);
                        nonRefundableRanges.add(nonRefundableRange);
                    }
                }

                if (!CollectionUtils.isEmpty(nonRefundableRanges)) {
                    hotelRefundRule.setSpecialTimeRuleList(nonRefundableRanges);
                }
            }
        }

        if (hotelRefundRule.isReturnable() && hotelRefundRule.getBasicRefundRuleList() == null
                && hotelRefundRule.getSpecialTimeRuleList() == null) {
            hotelRefundRule.setReturnable(false);
        }

        roomInfo.setCancellation(RefundRuleUtils.toJson(hotelRefundRule));
    }
}
