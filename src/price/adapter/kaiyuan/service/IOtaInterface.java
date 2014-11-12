package com.qunar.hotel.sa.product.price.adapter.kaiyuan.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.qunar.hotel.oas.bean.AdapterOrder;
import com.qunar.hotel.oas.bean.AdapterQueryOrderParam;
import com.qunar.hotel.oas.bean.Pair;
import com.qunar.hotel.oas.bean.homeinns.SyncOrderResult;
import com.qunar.hotel.oas.bean.price.SupplierInfo;
import com.qunar.hotel.sa.order.bean.OrderResult;

public interface IOtaInterface {
    
    
    /**
     * 设置代理商信息
     * 
     * @param otaInfo 代理商代理信息
     */
    void setOtaInfo(SupplierInfo otaInfo);

    /**
     * 获取代理商信息
     * 
     * @return proxy
     */
    SupplierInfo getOtaInfo();
    
    
    /**
     * 获取接口代理商的在指定时间点后报价、房态等发生过变化的 酒店seq列表。
     * 
     * @param updateTime
     * @return
     */
    List<Pair<String, String>> getFPUpdateHotelIds(String fpUpdateIdList, Date updateTime);

    /**
     * 订单保存
     * 
     * @param order
     * @return
     */
    OrderResult saveOrder(AdapterOrder order);

    /**
     * 校验订单
     * 
     * @param order
     * @return
     */
    OrderResult checkOrder(AdapterOrder order);

    /**
     * 订单取消
     * 
     * @param id
     * @param order
     * @param reason
     * @return
     */
    OrderResult cancelOrder(String id, AdapterOrder order, String reason);

    /**
     * 同步订单
     * 
     * @param orderNums
     * @return
     */
    public List<AdapterOrder> syncOrder(List<String> orderNums, Map<String, Object> params);

    /**
     * 获取LastID
     * 
     * @param date
     * @return
     */
    Long getLastId(Date date);

    /**
     * 同步订单by Last Id
     * 
     * @param lastId
     * @return
     */
    List<AdapterOrder> syncOrder(Long lastId);

    /**
     * 同步单个订单
     * 
     * @param order
     * @return
     */
    AdapterOrder syncOrder(AdapterOrder order);

    /**
     * 查询订单
     * 
     * @param order
     * @return
     */
    List<AdapterOrder> queryOrder(AdapterQueryOrderParam queryParam);

    /**
     * 获取订单即时确认状态
     * 
     * @param lastId
     * @return
     */
    OrderResult instantOrder(String id);

    public String getOrderDetailResponse(List<String> orderNums, Map<String, Object> params);

    public String getOrderDetailStatus(String serialId);

    public String getHotelDetail(String hotelId, String roomId, Date fromDate, Date toDate);

    public String syncOrderChangeList(String serialId);

    public String getSerialIds(String orderNum);

    SyncOrderResult syncOrderByDate(String startDate, String endDate);
}
