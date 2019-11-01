package com.univ.validate.basic;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

/**
 * @author univ
 * @date 2019/11/1 8:04 PM
 * @description
 */
@Data
public class BasicDemo {

    @NotNull(message = "id不能为空")
    private Long id;

    @NotBlank(message = "name不能为空")
    private String name;

    @Min(value = 100, message = "age不能小于100，这里是基本类型int")
    private int age;

    @Pattern(regexp = "\\d{3}", message = "必须为三位数字，很多时候只有当字段不为null时约束才会起作用")
    @NotBlank
    private String digit;

    /**
     * 可以同时加好多个限制constraint
     */
    @Max(value = 10, message = "length不能大于10，这里是包装类型Integer，不会被触发")
    @NotNull(message = "length不能为null")
    private Integer length;

}
