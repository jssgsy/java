package com.univ.validate.custom.constrain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import com.univ.validate.custom.validator.LengthContraintValidator;

/**
 * @author univ
 * @date 2019/11/7 9:47 AM
 * @description 带有其它参数的自定义约束
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = { LengthContraintValidator.class})
public @interface LengthContraint {

    // 字符串的最小长度
    int min();

    String message() default "长度不对";

    // 必不可少
    Class<?>[] groups() default {};

    // 必不可少
    Class<? extends Payload>[] payload() default {};
}
