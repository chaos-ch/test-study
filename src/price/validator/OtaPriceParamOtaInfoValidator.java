package com.qunar.hotel.sa.product.price.validator;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.qunar.hotel.sa.common.validator.Validator;
import com.qunar.hotel.sa.product.price.bean.OtaPriceParam;
import org.apache.commons.lang.math.NumberUtils;

public class OtaPriceParamOtaInfoValidator implements Validator<OtaPriceParam> {

    @Override
    public com.qunar.hotel.sa.common.validator.ValidatorResult validate(OtaPriceParam t) {
        boolean success = t.getSupplierInfo() == null || StringUtils.isBlank(t.getSupplierInfo().getOtaName()) || NumberUtils.INTEGER_ZERO == t.getSupplierInfo().getId();
        return new com.qunar.hotel.sa.common.validator.ValidatorResult(!success, "无效的代理商信息");
    }

}
