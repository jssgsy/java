package com.univ.validate.custom.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.univ.validate.custom.constrain.MyConstraintMutiple;

/**
 * @author univ
 * @date 2019/11/1 7:31 PM
 * @description
 */
public class MyConstraintIntegerValidator implements ConstraintValidator<MyConstraintMutiple, Integer> {
    @Override
    public void initialize(MyConstraintMutiple constraintAnnotation) {
        System.out.println("constraintAnnotation: " + constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value > 1 && value < 10;
    }
}
