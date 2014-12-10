package com.qunar.test.json;

import com.alibaba.dubbo.common.json.JSON;
import com.google.common.collect.Lists;
import com.qunar.hotel.qta.base.money.Money;
import org.apache.commons.lang3.time.DateFormatUtils;

import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;

/**
 * Created by he.chen on 14-11-27.
 */
public class ObjectJson {
    public static void main(String[] args) throws IOException {
        System.out.println(pushBody);
       /* OrderInvoiceControlInfo controlInfo = new OrderInvoiceControlInfo();
        controlInfo.setOrderNum(100269286091L);
        controlInfo.setInvoiceDoc("qun100269286091");
        controlInfo.setInvoiceNo("fa2325");
        controlInfo.setInvoiceProviderCompanyName("qinvoice");
        controlInfo.setInvoiceProviderCompanyCode("code135");
        controlInfo.setInvoiceApplyType("申请类型");
        controlInfo.setLastApplyTime(new Date());
        controlInfo.setLastUpdateTime(new Date().getTime());
        controlInfo.setCreateTime(new Date());
        controlInfo.setExpressNo("100033892580");
        controlInfo.setExpressCompany("申通快递");
        System.out.println(JSON.json(controlInfo));
      */
    }
    private static String pushBody = "{" +
            "\"status\":\"polling\"," +
            "\"billstatus\":\"got\"," +
            "\t\"message\":\"\"," +
            "\"lastResult\":{" +
            "\"message\":\"ok\",  " +
            "\"state\":\"0\",     " +
            "\"status\":\"200\"," +
            "\"condition\":\"F00\"," +
            "\"ischeck\":\"0\"," +
            "\"com\":\"yuantong\"," +
            "\t\t\"nu\":\"V030344422\",     " +
            "\"data\":[" +
            "{" +
            "\"context\":\"上海分拨中心/装件入车扫描 \", " +
            "\"time\":\"2012-08-28 16:33:19\",           " +
            "\"ftime\":\"2012-08-28 16:33:19\",         " +
            "\"status\":\"在途\"," +
            "\"areaCode\":\"310000000000\", " +
            "\"areaName\":\"上海市\"       " +
            "},{" +
            "\"context\":\"上海分拨中心/下车扫描 \",     " +
            "\"time\":\"2012-08-27 23:22:42\",          " +
            "\"ftime\":\"2012-08-27 23:22:42\",        " +
            "\"status\":\"在途\"," +
            "\"areaCode\":\"310000000000\", " +
            "\"areaName\":\"上海市\"       " +
            "}" +
            "]" +
            "}" +
            "}";
}
