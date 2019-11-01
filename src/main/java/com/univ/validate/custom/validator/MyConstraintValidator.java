package com.univ.validate.custom.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.univ.validate.custom.constrain.MyConstraintSingle;

/**
 * @author univ
 * @date 2019/11/1 11:37 AM
 * @description
 * 一种约束处理器只能对应一种要作用的数据类型，如这里的String；
 * 自定义约束处理器必须实现ConstraintValidator接口，注意其泛型：
 *  UnivConstraint：表示必须其适用于UnivString注解上；
 *  String：表示UnivString注解只能作用于String类型字段、参数上
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraintSingle, String> {

    @Override
    public void initialize(MyConstraintSingle constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (null == value || value.length() < 3) {
            return false;
        }
        return true;
    }
}
