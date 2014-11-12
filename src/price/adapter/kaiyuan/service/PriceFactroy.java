package com.qunar.hotel.sa.product.price.adapter.kaiyuan.service;


import com.qunar.hotel.sa.product.price.bean.OtaPriceParam;
import com.qunar.hotel.sa.product.price.adapter.kaiyuan.bean.PriceInfoString;
import org.springframework.context.ApplicationContext;

public class PriceFactroy {
    private static ApplicationContext applicationContext;

    /**
     * 获取对应的报价处理器
     * 
     * @param opp
     * @return
     */
    public static AbstractPriceProcessor getPriceProcessor(OtaPriceParam opp) {
        AbstractPriceProcessor process = null;
        switch (opp.getPriceRequestType()) {
        case WRAPPER:
            process = (AbstractPriceProcessor) applicationContext.getBean("wrapperProcessor");
            break;
        case ORDER:
            process = (AbstractPriceProcessor) applicationContext.getBean("orderProcessor");
            break;
        }
        if (process != null) {
            process.setOtaPriceParam(opp);
        }
        return process;
    }

    /**
     * 获取相应的推送报价解析的处理器
     * 
     * @param opp
     * @param riceInfoString
     * @return
     */
    public static AbstractPriceProcessor getPushPirceInfoProcessor(OtaPriceParam opp,
            PriceInfoString riceInfoString) {
        AbstractPriceProcessor process = (AbstractPriceProcessor) applicationContext.getBean("pushPriceProcessor");
        if(process != null){
            process.setOtaPriceParam(opp);
            process.setPriceInfoString(riceInfoString);
        }
        return process;
    }
}
