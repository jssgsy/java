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
public class MyConstraintSingleValidator implements ConstraintValidator<MyConstraintSingle, String> {

    /**
     * 用来初始化此validator，会在isValid方法之前被调用
     * @param constraintAnnotation  即要处理(对应)的约束注解。
     *  作用：当约束注解中定义了其它字段，则可以通过此参数获取
     */
    @Override
    public void initialize(MyConstraintSingle constraintAnnotation) {
        // 重要：可利用入参获取约束中定义的其它字段的值(声明时使用的值)
    }

    /**
     * 校验逻辑
     * @param value 要被验证的bean实例
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (null == value || value.length() < 3) {
            return false;
        }
        return true;
    }
}
