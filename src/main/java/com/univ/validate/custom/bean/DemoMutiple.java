package com.univ.validate.custom.bean;

import com.univ.validate.custom.constrain.MyConstraintMutiple;

import lombok.Data;

/**
 * @author univ
 * @date 2019/11/1 7:50 PM
 * @description
 */
@Data
public class DemoMutiple {

    @MyConstraintMutiple
    private String name;

    @MyConstraintMutiple
    private Integer age;
}
