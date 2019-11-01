package com.univ.validate.custom.constrain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import com.univ.validate.custom.validator.MyConstraintIntegerValidator;
import com.univ.validate.custom.validator.MyConstraintStringValidator;

/**
 * @author univ
 * @date 2019/11/1 7:42 PM
 * @description 可同时作用于多种类型的字段上
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
// 可同时作用于String与Integer类型的字段上
@Constraint(validatedBy = {MyConstraintStringValidator.class, MyConstraintIntegerValidator.class})
public @interface MyConstraintMutiple {

    String message() default "必须为数字或者字符串形式的数字";

    // 必不可少
    Class<?>[] groups() default {};

    // 必不可少
    Class<? extends Payload>[] payload() default {};
}
