package com.qunar.test.json;

import com.alibaba.dubbo.common.json.JSON;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

/**
 * Created by he.chen on 14-11-27.
 */
public class ObjectJson {
    public static void main(String[] args) throws IOException {
        MobOrderDetail mobOrderDetail = new MobOrderDetail();
        mobOrderDetail.setGuaranteeRule(new MobGuaranteeRule());
        mobOrderDetail.setInvoiceInfo(new MobOrderInvoiceInfo());
        mobOrderDetail.setOcncRule(new MobOcncRule());
        mobOrderDetail.setWrapperInfo(new WrapperInfo());
        String jsonStr1 = JSON.json(mobOrderDetail);
        System.out.println(jsonStr1);
                UserOrderInvoiceInfo userOrderInvoiceInfo = new UserOrderInvoiceInfo();
        OrderInvoiceExpressInfo orderInvoiceExpressInfo = new OrderInvoiceExpressInfo();
        LogisticsInfo logisticsInfo1 = new LogisticsInfo();
        LogisticsInfo logisticsInfo2 = new LogisticsInfo();
        List<LogisticsInfo> logisticsInfoList = Lists.newArrayList(logisticsInfo1,logisticsInfo2);
        orderInvoiceExpressInfo.setLogisticsInfos(logisticsInfoList);
        userOrderInvoiceInfo.setOrderInvoiceExpressInfo(orderInvoiceExpressInfo);
        String jsonStr = JSON.json(userOrderInvoiceInfo);
        System.out.println(jsonStr);
    }
}
