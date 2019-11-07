package com.univ.validate.custom.bean;

import com.univ.validate.custom.constrain.CompoundConstraint;

import lombok.Data;

/**
 * @author univ
 * @date 2019/11/7 7:05 PM
 * @description
 */
@Data
public class DemoCompound {

    @CompoundConstraint
    private String name;
}
