package com.qunar.hotel.sa.product.price.validator;

import com.qunar.hotel.sa.common.validator.*;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 简易校验器<br>
 * 对于一组校验器，只要检测不通过立即返回校验结果
 * 
 * @param <T>
 */
public class SimpleValidator<T> implements Validator<T> {

    private List<Validator<T>> validators = new ArrayList<Validator<T>>();

    @Override
    public com.qunar.hotel.sa.common.validator.ValidatorResult validate(T t) {
        for (Validator<T> validator : validators) {
            com.qunar.hotel.sa.common.validator.ValidatorResult validate = validator.validate(t);
            if(!validate.isSuccess()){
                return validate;
            }
        }
        return new com.qunar.hotel.sa.common.validator.ValidatorResult(true,StringUtils.EMPTY);
    }

    public SimpleValidator<T> add(Validator<T> validator) {
        validators.add(validator);
        return this;
    }

}
