package com.univ.validate.custom.constrain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import com.univ.validate.custom.validator.MyConstraintValidator;

/**
 * @author univ
 * @date 2019/11/1 7:42 PM
 * @description
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = MyConstraintValidator.class)      // 说明此约束的处理器是谁,可指定多个，此时便能作用在多种类型的字段上了
public @interface MyConstraintSingle {

    String message() default "必须大于三个字符";

    // 必不可少
    Class<?>[] groups() default {};

    // 必不可少
    Class<? extends Payload>[] payload() default {};
}
