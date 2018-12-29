package com.univ.validate;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.junit.Test;

/**
 * @author univ
 * @datetime 2018/12/29 10:06 AM
 * @description
 */
public class ValidateTest {

    @Test
    public void test() {
        // 1. 获取validator对象
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        // 2. 构造需要校验的对象
        A a = new A();

        // 3. 校验对象，并获取校验的结果
        Set<ConstraintViolation<A>> validateResult = validator.validate(a);
        for (ConstraintViolation<A> result : validateResult) {
            System.out.println(result.getMessage());
        }
    }


}

class A {
    @NotNull(message = "id不能为空")
    private Long id;

    @NotBlank(message = "name不能为空")
    private String name;

    @Min(value = 100, message = "age不能小于100，这里是基本类型int")
    private int age;

    /**
     * 可以同时加好多个限制constraint
     */
    @Max(value = 10, message = "length不能大于10，这里是包装类型Integer，不会被触发")
    @NotNull(message = "length不能为null")
    private Integer length;

    // 这里可以不用getter/setter
}
