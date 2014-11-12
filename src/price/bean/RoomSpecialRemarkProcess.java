package com.qunar.hotel.sa.product.price.bean;

import com.qunar.hotel.oas.bean.PriceProcessResult;
import com.qunar.hotel.otatts.common.common_api.support.util.JsonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomSpecialRemarkProcess implements RoomProcess {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoomSpecialRemarkProcess.class);
    private static final int MAX_REMARK_RECORD = 3;
    private static final int MAX_REMARK_LENGTH = 45;
    private RoomInfo roomInfo;
    private IRoom room;
    private String message;
    private Element roomElement;

    public RoomSpecialRemarkProcess(IRoom room, RoomInfo roomInfo, Element roomElement) {
        this.room = room;
        this.roomInfo = roomInfo;
        this.roomElement = roomElement;
    }

    @Override
    public PriceProcessResult check() {
        return new PriceProcessResult(true,null);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void process() {
        Element remarks = roomElement.element("remarks");
        if (remarks == null) {
            return;
        }

        List<Element> remarkElements = remarks.elements("remark");
        if (CollectionUtils.isEmpty(remarkElements)) {
            return;
        }

        List<String> remarkList = new ArrayList<String>();
        for (Element element : remarkElements) {
            String value = element.attributeValue("value");
            if (StringUtils.isNotEmpty(value) && value.length() <= MAX_REMARK_LENGTH) {
                remarkList.add(value);
            }
        }

        if (remarkList.size() == 0) {
            return;
        }

        try {
            remarkList = remarkList.size() > MAX_REMARK_RECORD ? remarkList.subList(0, MAX_REMARK_RECORD) : remarkList;
            String specialRemark = JsonUtil.getObjectMapperInstance().writeValueAsString(remarkList);
            roomInfo.setSpecialRemark(specialRemark);
        } catch (IOException e) {
            LOGGER.warn(String.format("设置房型[%s]特殊说明异常", roomInfo.getRoomName()), e);
        }
    }
}
