package com.univ.validate.custom.validator;

import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.univ.validate.custom.DemoSingle;
import com.univ.validate.custom.constrain.MyConstraintList;

/**
 * @author univ
 * @date 2019/11/1 8:40 PM
 * @description
 */
public class MyContraintListValidator implements ConstraintValidator<MyConstraintList, List<DemoSingle>> {

    @Override
    public void initialize(MyConstraintList constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<DemoSingle> value, ConstraintValidatorContext context) {
       return value.size() == 2;
    }
}
