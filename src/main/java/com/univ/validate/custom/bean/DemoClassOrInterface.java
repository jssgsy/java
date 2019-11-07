package com.univ.validate.custom.bean;

import com.univ.validate.custom.constrain.ClassOrInterfaceConstraint;

import lombok.Data;

/**
 * @author univ
 * @date 2019/11/7 7:33 PM
 * @description
 */
@Data
// 将约束用在对象上
@ClassOrInterfaceConstraint
public class DemoClassOrInterface {

    // 必须大于18
    private Integer age;

    // 必须有3个字符以上
    private String name;

    // 校验逻辑都在ClassOrInterfaceConstraintValidator中
}
