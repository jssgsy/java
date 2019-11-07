package com.univ.validate.custom.constrain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import com.univ.validate.custom.validator.ClassOrInterfaceConstraintValidator;

/**
 * @author univ
 * @date 2019/11/7 7:30 PM
 * @description 约束注解也可以使用在类或者接口上，它将对该类或实现该接口的实例进行状态验证；
 *
 */
@Retention(RetentionPolicy.RUNTIME)
// 作用在类型上(类或者接口)
@Target(ElementType.TYPE)
@Constraint(validatedBy = {ClassOrInterfaceConstraintValidator.class})
public @interface ClassOrInterfaceConstraint {

    String message() default "此类验证失败";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
