package com.qunar.hotel.sa.product.price.validator;

import com.qunar.hotel.sa.common.util.DateUtil;
import com.qunar.hotel.sa.common.validator.*;
import com.qunar.hotel.sa.product.price.bean.OtaPriceParam;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OtaPriceParamDayValidator implements Validator<OtaPriceParam> {

    @Override
    public com.qunar.hotel.sa.common.validator.ValidatorResult validate(OtaPriceParam t) {
        String message = "请求时间不在允许值范围内";
        
        List<String> i18nList = Arrays.asList("elongInternational", "expedia", "otel", "orbitz");
        if (i18nList.contains(t.getSupplierInfo().getOtaType())) {
            Date dayAfter364 = DateUtils.addDays(new Date(), 364);
            Date to = t.getCheckOut();
            if (to.after(dayAfter364)) {
                return new com.qunar.hotel.sa.common.validator.ValidatorResult(false, message);
            }
        }

        /**
         * otel关闭T,T+1天
         */

        List<String> afterTlist = Arrays.asList("otel");
        if (afterTlist.contains(t.getSupplierInfo().getOtaType())) {
            Date dayAfter = DateUtils.addDays(new Date(), 1);
            if (t.getCheckIn().before(dayAfter)) {
                return new com.qunar.hotel.sa.common.validator.ValidatorResult(false, message);
            }
        }

        String seq = t.getHotelInfoParam().getSeq();
        String cityUrl = null;
        if (StringUtils.isNotEmpty(seq)) {
            cityUrl = seq.lastIndexOf("_") == -1 ? seq : seq.substring(0, seq.lastIndexOf("_"));
        }



        /**
         * orbitz,AGODA 非香港、澳门、曼谷、首尔，关闭T天
         */
        List<String> afterTlistFilterCity = Arrays.asList("orbitz","agoda");
        List<String> orbitzCityListT = Arrays.asList("hongkong_city","macao_city","seoul","bangkok");
        if (afterTlistFilterCity.contains(t.getSupplierInfo().getOtaType())) {
            Date dayAfter = new Date();
            if (t.getCheckIn().before(dayAfter) && !orbitzCityListT.contains(cityUrl)) {
                return new com.qunar.hotel.sa.common.validator.ValidatorResult(false, message);
            }
        }

        /**
         * ean非香港，澳门 屏蔽T天报价
         * */

        List<String> afterTAddOnelist = Arrays.asList("expedia");
        List<String> eanCityListT = Arrays.asList("hongkong_city","macao_city");
        if (afterTAddOnelist.contains(t.getSupplierInfo().getOtaType())) {
            Date dayAfter = new Date();
            if (t.getCheckIn().before(dayAfter) && !eanCityListT.contains(cityUrl) ) {
                return new com.qunar.hotel.sa.common.validator.ValidatorResult(false, message);
            }
        }

        List<String> diffTlist = Arrays.asList("orbitz");
        if (diffTlist.contains(t.getSupplierInfo().getOtaType())) {
            // orbitz 最多预订30天，最晚预订330天
            Date dayAfter = DateUtils.addDays(new Date(), 330);
            if (t.getCheckIn().after(dayAfter) || DateUtil.diff(t.getCheckIn(), t.getCheckOut()) > 30) {
                return new com.qunar.hotel.sa.common.validator.ValidatorResult(false, message);
            }
        }

        if ("ccinn".equals(t.getSupplierInfo().getOtaType())) {
            Date dayAfter10 = DateUtils.addDays(new Date(),11);
            if (t.getCheckOut().after(dayAfter10)) {
                return new com.qunar.hotel.sa.common.validator.ValidatorResult(false, message);
            }

        }

        return new com.qunar.hotel.sa.common.validator.ValidatorResult(true, message);
    }

}
