package com.qunar.hotel.sa.product.price.bean;


import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlElementRoom implements IRoom {

    private Element room;

    protected Element guaranteeRulesElement;

    private List<Element> guaranteeRuleElements;

    public XmlElementRoom(Element room) {
        this.room = room;
        this.guaranteeRulesElement = room.element("guaranteeRules");
        this.guaranteeRuleElements = guaranteeRulesElement == null ? null : guaranteeRulesElement
                .elements("guaranteeRule");
    }

    public Element getRoom() {
        return room;
    }

    public void setRoom(Element room) {
        this.room = room;
    }

    public Element getGuaranteeRulesElement() {
        return guaranteeRulesElement;
    }

    public void setGuaranteeRulesElement(Element guaranteeRulesElement) {
        this.guaranteeRulesElement = guaranteeRulesElement;
    }

    public List<Element> getGuaranteeRuleElements() {
        return guaranteeRuleElements;
    }

    public void setGuaranteeRuleElements(List<Element> guaranteeRuleElements) {
        this.guaranteeRuleElements = guaranteeRuleElements;
    }

    private String getValue(String key) {
        return room.attributeValue(key);
    }

    private String getGuaranteeValue(String key, Element guaranteeRuleElement) {
        if (guaranteeRuleElement == null) {
            return "";
        }
        return guaranteeRuleElement.attributeValue(key);
    }

    @Override
    public String getId() {
        return getValue("id");
    }

    @Override
    public String getName() {
        return getValue("name");
    }

    @Override
    public String getBedTypeValue() {
        return getValue("bed");
    }

    @Override
    public String getBroadbandValue() {
        return getValue("broadband");
    }

    @Override
    public String getPrepayValue() {
        return getValue("prepay");
    }

    @Override
    public String getPrices() {
        return getValue("prices");
    }

    @Override
    public String getDeposits() {
        return getValue("deposits");
    }

    @Override
    public String getStatus() {
        return getValue("status");
    }

    @Override
    public String getCounts() {
        return getValue("counts");
    }

    @Override
    public String getCashBacks() {
        return getValue("cashBacks");
    }

    @Override
    public String getBreakfast() {
        return getValue("breakfast");
    }

    @Override
    public String getLast() {
        return getValue("last");
    }

    @Override
    public String getAdvance() {
        return getValue("advance");
    }

    @Override
    public String getRefusestate() {
        return getValue("refusestate");
    }

    @Override
    public String getMaxRoomNum() {
        return getValue("maxRoomNum");
    }

    @Override
    public String getLatestArriveTime() {
        return getValue("latestArriveTime");
    }
    
    @Override
    public List<Map<String, String>> getGuaranteeRules() {
        List<Map<String, String>> guaranteeList = new ArrayList<Map<String, String>>();
        if ("0".equals(getPrepayValue()) || guaranteeRulesElement == null || guaranteeRuleElements == null) {
            return guaranteeList;
        }
        for (Element element : guaranteeRuleElements) {
            Map<String, String> guarantee = new HashMap<String, String>();
            guarantee.put("guaranteeType", getGuaranteeValue("guaranteeType", element));
            guarantee.put("arriveStartTime", getGuaranteeValue("arriveStartTime", element));
            guarantee.put("arriveEndTime", getGuaranteeValue("arriveEndTime", element));
            guarantee.put("count", getGuaranteeValue("count", element));
            guarantee.put("changeRule", getGuaranteeValue("changeRule", element));
            guarantee.put("dayNum", getGuaranteeValue("dayNum", element));
            guarantee.put("timeNum", getGuaranteeValue("timeNum", element));
            guarantee.put("hourNum", getGuaranteeValue("hourNum", element));
            if (guarantee.values().contains(null)) {
                return null;
            }
            guaranteeList.add(guarantee);
        }
        return guaranteeList;
    }

    @Override
    public String getChannel() {
        return getValue("channel");
    }

    @Override
    public String getDiscountAmount() {
        return getValue("discountAmount");
    }

	@Override
	public String getBookingType() {
		return getValue("bookingType");
	}

    @Override
    public String getCurrencyType() {
        return getValue("currencyType");
    }

    @Override
    public String getGuestType() {
        //说明：代理商不进行维护的房型默类型为所有宾客；代理商不传宾客类型的房型默认接受的宾客类型为所有宾客 （所有宾客为0）
        String guestTypeStr = getValue("guestType");
        return StringUtils.isBlank(guestTypeStr) ? "0" : guestTypeStr;
    }
}
