package com.qunar.hotel.sa.product.price.validator;

import com.qunar.hotel.sa.common.validator.*;
import com.qunar.hotel.sa.product.price.bean.OtaPriceParam;

public class OtaPriceParamBaseValidator implements Validator<OtaPriceParam> {

    @Override
    public com.qunar.hotel.sa.common.validator.ValidatorResult validate(OtaPriceParam t) {
        return new com.qunar.hotel.sa.common.validator.ValidatorResult(!(t == null || t.getCheckIn() == null || t.getCheckOut() == null), "无效的请求时间");
    }
}
