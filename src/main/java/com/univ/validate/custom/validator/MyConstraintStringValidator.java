package com.univ.validate.custom.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.univ.validate.custom.constrain.MyConstraintMutiple;

/**
 * @author univ
 * @date 2019/11/1 7:59 PM
 * @description
 */
public class MyConstraintStringValidator implements ConstraintValidator<MyConstraintMutiple, String> {
    @Override
    public void initialize(MyConstraintMutiple constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Integer.valueOf(value);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
