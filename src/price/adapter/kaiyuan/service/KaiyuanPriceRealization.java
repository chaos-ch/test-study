package com.qunar.hotel.sa.product.price.adapter.kaiyuan.service;

import com.qunar.hotel.sa.common.util.OasQMonitorUtils;
import com.qunar.hotel.sa.common.util.QmonitorNameEnum;
import com.qunar.hotel.sa.product.hotel.bean.HotelInfo;
import com.qunar.hotel.sa.product.price.bean.OtaPriceParam;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.PriceInfoString;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean.SoapEnvelope;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.util.KaiyuanHttpUtil;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.util.KaiyuanParseUtil;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.util.KaiyuanXMLUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.qunar.base.meerkat.monitor.QMonitor;

/**
 * Created by he.chen on 14-10-27.
 * @version $Id$
 */
@Scope("prototype")
@Component("kaiyuanPriceRealization")
@DependsOn
public class KaiyuanPriceRealization  extends AbstractOtaPriceService {
    protected static Logger logger = LoggerFactory.getLogger("com.qunar.hotel.oas.spi.kaiyuan");

    public HotelInfo getHotelPriceByWrapper(OtaPriceParam opp) {
        return null;
    }

    public HotelInfo getHotelPriceByOrder(OtaPriceParam param) {
        QMonitor.recordOne(param.getSupplierInfo().getId() + "_count");
        long beginTime = System.currentTimeMillis();
        String requestXml = "";
        String responseXml = "";
        SoapEnvelope soapEnvelope = null;
        try {
            requestXml = KaiyuanHttpUtil.getOrderPriceRequestXml(param);
            responseXml = KaiyuanHttpUtil.getOrderPriceResponseXml(requestXml);
            soapEnvelope = KaiyuanXMLUtil.xml2SoapEnvelopeBean(responseXml);
            if (StringUtils.equalsIgnoreCase(soapEnvelope.getSoapHeader().getShandsSoapHeader().getResultCode(),
                    KaiyuanHttpUtil.SESSION_INVALID_CODE)) {// 如果sessionId已经失效，需要将KaiyuanHttpUtil中保存的sessionId设置为“”，然后重新抓取接口
                KaiyuanHttpUtil.sessionId = "";
                requestXml = KaiyuanHttpUtil.getOrderPriceRequestXml(param);
                responseXml = KaiyuanHttpUtil.getOrderPriceResponseXml(requestXml);
                soapEnvelope = KaiyuanXMLUtil.xml2SoapEnvelopeBean(responseXml);
            }
        } catch (Exception e) {
            logger.error(QmonitorNameEnum.OAS_ORDER_PRICE_ERROR.name()
                    + ":get kaiyuan order price exception, param:{},requestXml:{},responseXML:{}", param, requestXml,
                    responseXml, e);
            QMonitor.recordOne("otaPriceError");
            OasQMonitorUtils.recordOne(param.getSupplierInfo().getId(), QmonitorNameEnum.OAS_ORDER_PRICE_ERROR);
            return null;
        }
        logger.info("get RateDailyDetail ，param:{},requestXml:{},responseXML:{}", param, requestXml, responseXml);

        HotelInfo hotelInfo = null;
        try {
            hotelInfo = KaiyuanParseUtil.parseDailyDetail(param, soapEnvelope);
        } catch (Exception e) {
            logger.error(QmonitorNameEnum.OAS_ORDER_PRICE_ERROR.name()
                    + ":parse kaiyuan order price exception ，param:{},requestXml:{},responseXML:{}", param, requestXml,
                    responseXml, e);
            QMonitor.recordOne("otaPriceError");
            OasQMonitorUtils.recordOne(param.getSupplierInfo().getId(), QmonitorNameEnum.OAS_ORDER_PRICE_ERROR);
            return null;
        }
        OasQMonitorUtils.recordOne(param.getSupplierInfo().getId(), QmonitorNameEnum.OAS_ORDER_PRICE,
                System.currentTimeMillis() - beginTime);
        return hotelInfo;
    }

    public HotelInfo parsePushPriceInfo(OtaPriceParam param, PriceInfoString riceInfoString) {
        long startTime = System.currentTimeMillis();
        HotelInfo hotelInfo = null;
        if (riceInfoString.getParseType() == PriceInfoString.ParseType.RP) {
            try {
                hotelInfo = KaiyuanParseUtil.parsePushRatePlan(param, riceInfoString.getInitialString());
            } catch (Exception e) {
                logger.error(QmonitorNameEnum.OAS_PUSH_PRICE_ERR.name()
                        + ":parse push price info xml exception,pushXml:{}", riceInfoString.getInitialString(), e);
                OasQMonitorUtils.recordOne(QmonitorNameEnum.OAS_PUSH_PRICE_ERR);
                return null;
            }
        } else if (riceInfoString.getParseType() == PriceInfoString.ParseType.RS) {
            try {
                hotelInfo = KaiyuanParseUtil.parsePushState(param, riceInfoString.getInitialString());
            } catch (Exception e) {
                logger.error(QmonitorNameEnum.OAS_PUSH_PRICE_ERR.name() + ":parse push state info xml exception", e);
                OasQMonitorUtils.recordOne(QmonitorNameEnum.OAS_PUSH_PRICE_ERR);
                return null;
            }

        }
        if (riceInfoString.getParseType() == PriceInfoString.ParseType.RP) {
            OasQMonitorUtils.recordOne(QmonitorNameEnum.OAS_PUSH_PRICE, System.currentTimeMillis() - startTime);
        } else if (riceInfoString.getParseType() == PriceInfoString.ParseType.RS) {
            OasQMonitorUtils.recordOne(QmonitorNameEnum.OAS_PUSH_STATE, System.currentTimeMillis() - startTime);
        }
        return hotelInfo;
    }

}
