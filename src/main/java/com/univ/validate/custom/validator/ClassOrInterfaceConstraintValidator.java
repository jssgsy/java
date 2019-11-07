package com.univ.validate.custom.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.univ.validate.custom.bean.DemoClassOrInterface;
import com.univ.validate.custom.constrain.ClassOrInterfaceConstraint;

/**
 * @author univ
 * @date 2019/11/7 7:33 PM
 * @description
 */
public class ClassOrInterfaceConstraintValidator implements ConstraintValidator<ClassOrInterfaceConstraint, DemoClassOrInterface> {

    @Override
    public void initialize(ClassOrInterfaceConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(DemoClassOrInterface value, ConstraintValidatorContext context) {
        Integer age = value.getAge();
        String name = value.getName();
        return age != null && age > 18 && null != name && name.length() > 3;
    }
}
