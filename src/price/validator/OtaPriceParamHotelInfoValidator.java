package com.qunar.hotel.sa.product.price.validator;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.qunar.hotel.sa.common.validator.*;
import com.qunar.hotel.sa.product.price.bean.OtaPriceParam;

public class OtaPriceParamHotelInfoValidator implements Validator<OtaPriceParam> {

    @Override
    public com.qunar.hotel.sa.common.validator.ValidatorResult validate(OtaPriceParam t) {
        boolean success = t.getHotelInfoParam() == null || StringUtils.isBlank(t.getHotelInfoParam().getOtaHotelId()) || StringUtils.isBlank(t.getHotelInfoParam().getQunarHotelId());
        return new com.qunar.hotel.sa.common.validator.ValidatorResult(!success, "无效的酒店信息");
    }

}
