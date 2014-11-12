package com.qunar.hotel.sa.product.price.adapter.kaiyuan.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.qunar.hotel.sa.common.util.JsonUtil;
import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.hotel.bean.Three;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.*;
import com.qunar.hotel.sa.product.price.bean.*;
import com.qunar.hotel.sa.product.price.service.AbstractService;
import com.qunar.hotel.sa.product.price.service.SupplierProductService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

/**
 * 开元报价
 * 
 * Created by he.chen on 14-10-27.
 * 
 * @version $Id$
 */
@Service("productKaiyuanService")
public class ProductKaiyuanServiceImpl extends AbstractService implements SupplierProductService {

    @Resource
    private HotelPriceLocalService hotelPriceLocalService;
    @Resource
    private OtaPriceService otaPriceService;

    @Override
    public ProductInfo queryOrderProduct(ProductQuery productQuery) throws Exception {

        HotelInfo hotelInfo = hotelPriceLocalService.queryHotelRoomPrice(productQuery.getHotelId(),
                productQuery.getRoomId(), productQuery.getCheckInDate(),
                DateUtils.addDays(productQuery.getCheckOutDate(), -1));
        if (hotelInfo == null || CollectionUtils.isEmpty(hotelInfo.getRooms())) {
            return null;
        }

        OtaPriceParam param = new OtaPriceParamAdaptor().adaptorFrom(new HotelParam(hotelInfo, productQuery,
                OtaPriceParam.PriceRequestType.ORDER));
        AdapterHotel adapterHotel = otaPriceService.getOtaHotelPrice(param);
        if (adapterHotel == null || CollectionUtils.isEmpty(adapterHotel.getRooms())) {
            return null;
        }

        RoomInfo roomInfo = hotelInfo.getRooms().get(0);
        AdapterHotel hotel = new AdapterHotelAdaptor().adaptorFrom(new HotelParam(hotelInfo, new AdapterHotel()));
        handleHotel(hotel, roomInfo);
        AdapterRoom room = new AdapterRoomAdaptor().adaptorFrom(roomInfo);
        handleRoom(room);
        List<AdapterPrice> prices = adapterHotel.getRooms().get(0).getPrices();

        return new ProductInfo(new Three<AdapterHotel, AdapterRoom, List<AdapterPrice>>(hotel, room, prices));
    }

    @Override
    public ProductInfo queryWrapperProduct(ProductQuery productQuery) throws Exception {
        HotelInfo hotelInfo = hotelPriceLocalService.queryHotelPriceBySeq(productQuery.getSeq(),
                productQuery.getCheckInDate(), DateUtils.addDays(productQuery.getCheckOutDate(), -1));

        return new ProductInfo(new WrapperPriceAdaptor().adaptorFrom(new HotelParam(hotelInfo, productQuery
                .getCheckInDate(), OtaPriceParam.PriceRequestType.WRAPPER)));
    }

    /**
     * 酒店信息的一些后续处理
     * 
     * @param hotel
     * @param roomInfo
     */
    private void handleHotel(AdapterHotel hotel, RoomInfo roomInfo) {
        // 房型级别的描述添加到酒店的特殊备注中，以展示在温馨提示
        if (StringUtils.isNotEmpty(roomInfo.getDescription())) {
            try {
                hotel.setSpecialRemark(JsonUtil.getObjectMapperInstance().writeValueAsString(
                        Arrays.asList(roomInfo.getDescription())));
            } catch (IOException e) {
                logger.error("handleHotel error, description:{}", roomInfo.getDescription(), e);
            }
        }
    }

    /**
     * 房型信息的一些后续处理
     * 
     * @param room
     */
    private void handleRoom(AdapterRoom room) {
        // 最大可预定间数
        room.setMaxRoomNumber(5);
    }
}
