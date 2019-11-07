package com.univ.validate.custom.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.univ.validate.custom.constrain.CompoundConstraint;

/**
 * @author univ
 * @date 2019/11/7 7:14 PM
 * @description
 */
public class CompoundConstraintValidator implements ConstraintValidator<CompoundConstraint, String> {
    @Override
    public void initialize(CompoundConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() < 5;
    }
}
