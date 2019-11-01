package com.univ.validate.custom.constrain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import com.univ.validate.custom.validator.MyContraintListValidator;

/**
 * @author univ
 * @date 2019/11/1 8:39 PM
 * @description 用来校验集合类型
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = MyContraintListValidator.class)
public @interface MyConstraintList {

    String message() default "集合大小必须为2";

    // 必不可少
    Class<?>[] groups() default {};

    // 必不可少
    Class<? extends Payload>[] payload() default {};
}
