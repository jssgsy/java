package com.univ.validate.custom.constrain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import com.univ.validate.custom.validator.MyConstraintSingleValidator;

/**
 * @author univ
 * @date 2019/11/1 7:42 PM
 * @description 自定义约束注解，
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
// 约束注解定义完成后，需要同时实现与该约束注解关联的验证器。
// 说明此约束的处理器是谁,可指定多个，此时便能作用在多种类型的字段上了
@Constraint(validatedBy = MyConstraintSingleValidator.class)
public @interface MyConstraintSingle {

    String message() default "必须大于三个字符";

    // 必不可少，约束注解在验证时所属的组别
    Class<?>[] groups() default {};

    // 必不可少，约束注解的有效负载
    // 有效负载通常用来将一些元数据信息与该约束注解相关联，常用的一种情况是用负载表示验证结果的严重程度。
    Class<? extends Payload>[] payload() default {};
}
