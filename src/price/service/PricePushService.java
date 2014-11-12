/**
 * 
 */
package com.qunar.hotel.sa.product.price.service;

import java.util.Date;
import java.util.List;

import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.hotel.bean.Pair;
import com.qunar.hotel.sa.product.price.bean.*;
import org.springframework.stereotype.Service;


/**
 * @author binyu.zhan
 * 
 */
@Service
public interface PricePushService {

    /**
     * 获取酒店下所有房型指定时间段的报价信息 从变价推送缓存表中获取
     *
     * @return Pair<HotelInfo, List<HotelPriceInfo<RoomInfo, List<PriceInfo>>>>
     */
    public HotelInfo getCacheOrOasHotelPriceByHotelId(IOtaPriceInterface inf, OtaPriceParam otaPriceParam);

    /**
     * 线程池控制获取酒店报价信息
     * 
     * @param inf
     * @return
     */
    public HotelInfo getOasHotelPrice(IOtaPriceInterface inf, OtaPriceParam otaPriceParam);

    /**
     * 调用更新变价推送酒店接口
     * 
     * @return
     */
    public int updateHotelPushInfo();

    /**
     * 删除无效的变价推送缓存
     * 
     * @return
     */
    public int delInvalidHotelCache();

    /**
     * 获取缓存信息
     * 
     * @param otaHotelId
     * @param fromDate
     * @param toDate
     * @param otaId
     * @return
     */
    public String getCacheInfo(String otaHotelId, Date fromDate, Date toDate, int otaId, String channel);

    public static enum PricePushState {
        Y(1), N(0);
        private int code;

        PricePushState(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    public boolean bookingCheck(OtaPriceParam otaPriceParam, HotelInfo hotel);

    public enum CheckPushState {
        OK("正常"), ADDPUSH("新增报价或房型推送遗漏"), DEDUCTPUSH("删除报价或房型推送遗漏"), CHANGEPUSH("房态或报价更改推送遗漏");

        private String desc;

        CheckPushState(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }


    /**
     * 删除缓存，仅报价不展示工具使用
     */
    public void deleteCacheForPriceTools(int otaId, String otaHotelId, Date fromDate, Date toDate, String channel);

}
