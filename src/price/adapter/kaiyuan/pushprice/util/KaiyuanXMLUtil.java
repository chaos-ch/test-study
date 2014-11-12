package com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.util;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean.HotelRateDailyDetailResponse;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean.PushRatePlan;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean.PushRoomRateStatus;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.pushprice.bean.SoapEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by he.chen on 14-10-27.
 * $
 */
public class KaiyuanXMLUtil {


    protected static Logger logger = LoggerFactory.getLogger("com.qunar.hotel.oas.spi.kaiyuan.util.KaiyuanXMLUtil");
    private static Unmarshaller unmarshaller;

    static {
        initUnmarshallerInstance();
    }

    /**
     * 将开元push的xml格式报价字符串映射为一个PushRatePlan
     * 
     * @param priceXml xml格式的报价字符串 <PushRatePlan xmlns=""> ... </PushRatePlan>
     * @return
     * @throws javax.xml.bind.JAXBException
     */
    public static PushRatePlan xml2PushRatePlan(String priceXml) throws JAXBException {
        if (priceXml == null) {
            return null;
        }
        //Unmarshaller unmarshaller = getUnmarshallerInstance(SoapEnvelope.class);
        SoapEnvelope soapEnvelope = null;
        StringReader stringReader = new StringReader(priceXml);
        if (unmarshaller != null) {
            soapEnvelope = (SoapEnvelope) unmarshaller.unmarshal(stringReader);
        }
        return soapEnvelope.getSoapBody().getPushRatePlan();
    }

    /**
     * 将开元push的xml的房态字符串映射为一个PushRoomRateStatus
     *
     * @param statusXml
     * @return
     * @throws javax.xml.bind.JAXBException
     */
    public static PushRoomRateStatus xml2PushRoomRateStatus(String statusXml) throws JAXBException {
        if (statusXml == null) {
            return null;
        }
        //Unmarshaller unmarshaller = getUnmarshallerInstance(SoapEnvelope.class);
        SoapEnvelope soapEnvelope = null;
        StringReader stringReader = new StringReader(statusXml);
        if (unmarshaller != null) {
            soapEnvelope = (SoapEnvelope) unmarshaller.unmarshal(stringReader);
        }
        return soapEnvelope.getSoapBody().getPushRoomRateStatus();
    }

    /**
     * 将实时抓取得报价xml字符串映射为HotelRateDailyDetailResponse
     *
     * @param realTimePriceXml
     * @return
     * @throws javax.xml.bind.JAXBException
     */
    public static HotelRateDailyDetailResponse xml2HotelRateDailyDetailResponse(String realTimePriceXml)
            throws JAXBException {
        if (realTimePriceXml == null) {
            return null;
        }
        //Unmarshaller unmarshaller = getUnmarshallerInstance(SoapEnvelope.class);
        SoapEnvelope soapEnvelope = null;
        StringReader stringReader = new StringReader(realTimePriceXml);
        if (unmarshaller != null) {
            soapEnvelope = (SoapEnvelope) unmarshaller.unmarshal(stringReader);
        }
        return soapEnvelope.getSoapBody().getHotelRateDailyDetailResponse();
    }


    /**
     * 解析开元服务器返回的登录结果xml，映射为SoapEnvelope，SoapEnvelope包含header信息
     *
     * @param loginResultXml
     * @return
     * @throws javax.xml.bind.JAXBException
     */
    public static SoapEnvelope xml2SoapEnvelopeBean(String loginResultXml) throws JAXBException {
        if (loginResultXml == null) {
            return null;
        }
        //Unmarshaller unmarshaller = getUnmarshallerInstance(SoapEnvelope.class);
        SoapEnvelope soapEnvelope = null;
        StringReader stringReader = new StringReader(loginResultXml);
        if (unmarshaller != null) {
            soapEnvelope = (SoapEnvelope) unmarshaller.unmarshal(stringReader);
        }
        return soapEnvelope;
    }

    private static void initUnmarshallerInstance() {
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(SoapEnvelope.class);
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            logger.error("init Unmarshaller instance error",e);
        }
    }
}
