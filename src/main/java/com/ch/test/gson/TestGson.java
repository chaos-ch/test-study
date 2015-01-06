package com.ch.test.gson;

import com.ch.test.json.OrderInvoiceExpressInfo;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ch.test.http.HttpUtil;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by he.chen on 14-12-15.
 */
public class TestGson<T> {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        UserOrderInvoiceInfo userOrderInvoiceInfo = new UserOrderInvoiceInfo();
        UserInvoiceLogistics logistics = new UserInvoiceLogistics();
        logistics.setUserInvoiceLogisticsItemList(Lists.newArrayList(new UserInvoiceLogisticsItem(), new UserInvoiceLogisticsItem()));
        userOrderInvoiceInfo.setOrderInvoiceExpressInfo(new OrderInvoiceExpressInfo());
        userOrderInvoiceInfo.setUserInvoiceLogistics(logistics);
        String str = gson.toJson(userOrderInvoiceInfo);
        System.out.println(str);


    }
    public String testsaveInvoice(){
        Gson gson = new GsonBuilder().serializeNulls().create();
        MobInvoiceSaveRequest invoiceSaveRequest = new MobInvoiceSaveRequest();
        invoiceSaveRequest.setInvoiceProvider("QUNAR");
        invoiceSaveRequest.setOrderNum("100284473152");
        invoiceSaveRequest.setInvoiceGetType(1);
        invoiceSaveRequest.setDispatch("快递到付");
        invoiceSaveRequest.setAddress("beijing海淀区");
        invoiceSaveRequest.setArea("haidingqu");
        invoiceSaveRequest.setInvoiceCity("北京");
        invoiceSaveRequest.setInvoiceContactName("hechen");
        invoiceSaveRequest.setPhone("18930695849");
        invoiceSaveRequest.setContent("内容是啥阿");
        invoiceSaveRequest.setInvoiceFee(BigDecimal.TEN);
        invoiceSaveRequest.setProvince("北京");
        invoiceSaveRequest.setTitle("qunarwang");
        invoiceSaveRequest.setInvoiceType("旅行社发票");
        invoiceSaveRequest.setInvoiceRemark("备注阿");
        invoiceSaveRequest.setInvoiceEditOption("ADD");
        String str = gson.toJson(invoiceSaveRequest);

        return str;
    }
    public String testSaveOrder(){
        MobOrderSaveRequest request = new MobOrderSaveRequest();
        request.setFrom("Mobile");
        request.setWrapperId("wapqunarqta");
        request.setHotelId("beijing_city_478");
        request.setHotelName("三亚宝宏龙都大酒店");
        request.setHotelAddress("海南省三亚市大东海旅游度假区");
        request.setRoomId("2460784");
        request.setRoomName("ch房型-ch测试");
        request.setCity("白沙");
        request.setCityCode("baisha");
        request.setCheckIn("2014-12-25");
        request.setCheckOut("2014-12-26");
        request.setRooms(1);
        request.setArriveTime("18:00");
        request.setTotalPrice(new BigDecimal(235));
        request.setNeedGuarantee(true);
        request.setPayType(0);
        request.setPayAmount(new BigDecimal(235));
        request.setGuestName("hechen");
        request.setContactName("hechen");
        request.setPhone("18930695849");
        request.setTelephone("18930695849");
        request.setEmail("che@qunar.com");
        request.setRemark("fff");
        request.setBreakfast("无早");
        request.setWebfree("覆盖范围不确定,收费/免费不确定");
        request.setBedType("双床");
        request.setCancellation("订单确认后不可取消，该订单不可变更。如未入住扣除全额房费。");
        request.setHotelRemarks(Lists.newArrayList("暂无。"));
        request.setPreference(null);
        request.setCashBack(BigDecimal.ZERO);
        request.setStarTicketBack(BigDecimal.ZERO);
        request.setUsedStarTickets(BigDecimal.ZERO);
        request.setPriceCut(BigDecimal.ZERO);
        request.setPaySubtractPrice(BigDecimal.ZERO);

        return null;
    }
    public void testsss(){
        Gson gson = new GsonBuilder().serializeNulls().create();
        try {
            String body = HttpUtil.getContent("http://l-qss1.ops.cn1.qunar.com:9000/api/wrapper_cs_info?product_line_id=2");
            ApiResponse<Map<String,Map<String,String>>> response = gson.fromJson(body, ApiResponse.class);
            Map<String, Map<String,String>> m1 = response.getData();
            File file = new File("/home/he.chen/doc/wrapppppppps.txt");

            Files.append("wrapperId     公司名称      推广名称" + "\r\n",file, Charsets.UTF_8);

            List<String> keys = Lists.newArrayList(m1.keySet());
            for (String key :keys) {
                StringBuffer sb = new StringBuffer();
                sb.append(key).append(" ");
                Map<String,String> m2 = m1.get(key);
                sb.append(m2.get("companyName")).append(" ");
                sb.append(m2.get("siteName")).append("\r\n");
                Files.append(sb.toString(), file, Charsets.UTF_8);
            }
            System.out.println(response);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public T castClassssss(Class<T> type){
           return type.cast(new Object());
    }
}
