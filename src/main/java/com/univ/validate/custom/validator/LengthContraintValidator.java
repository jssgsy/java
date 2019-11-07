package com.univ.validate.custom.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.univ.validate.custom.constrain.LengthContraint;

/**
 * @author univ
 * @date 2019/11/7 9:49 AM
 * @description
 */
public class LengthContraintValidator implements ConstraintValidator<LengthContraint, String> {

    // 字符串最小长度
    private int minLength;

    @Override
    public void initialize(LengthContraint constraintAnnotation) {
        // 获取注解中其它属性的值
        this.minLength = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return null != value && value.length() >= minLength;
    }
}
