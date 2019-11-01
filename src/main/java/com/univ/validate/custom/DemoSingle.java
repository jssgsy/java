package com.univ.validate.custom;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.univ.validate.custom.constrain.MyConstraintSingle;

import lombok.Data;

/**
 * @author univ
 * @date 2019/11/1 11:47 AM
 * @description
 */
@Data
public class DemoSingle {

    @Range(min = 1, max = 10)
    private Integer id;

    @MyConstraintSingle // 自定义约束
    @NotBlank
    private String name;

}
