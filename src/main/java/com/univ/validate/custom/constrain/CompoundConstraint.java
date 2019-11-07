package com.univ.validate.custom.constrain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.univ.validate.custom.validator.CompoundConstraintValidator;

/**
 * @author univ
 * @date 2019/11/7 7:01 PM
 * @description 组合约束注解演示
 * 所谓组合约束：就是多个约束(a,b,c)放到一起组合成一个新的注解，这个注解就同时具备了a,b,c约束的作用
 * 注意：能被用作元约束的注解的@Target必须包含值ANNOTATION_TYPE，表示能用在其它注解上；
 */
@NotNull
@Size(min = 1)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
// 1. 可以不需要额外的校验器，用默认的即可
// 2. 也可以额外指定校验器，此时是叠加的效果，即只有当@NotNull、@@Size(min = 1)及CompoundConstraintValidator校验都通过之后才算校验通过
@Constraint(validatedBy = { CompoundConstraintValidator.class})

public @interface CompoundConstraint {

    String message() default "此字符串不能为null，且最少包含一个字符，且必须少于5个字符";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
