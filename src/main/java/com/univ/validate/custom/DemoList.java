package com.univ.validate.custom;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.univ.validate.custom.constrain.MyConstraintList;

import lombok.Data;

/**
 * @author univ
 * @date 2019/11/1 8:41 PM
 * @description
 */
@Data
public class DemoList {

    @NotNull
    @Length(min = 7, max = 10, message = "DemoList: name必须在7到10个字符之间")
    private String name;

    @Range(min = 5, max = 10, message = "DemoList: age必须在5到10之间")
    private Integer age;

    @MyConstraintList(message = "DemoList: list大小必须为2")   // 重要：用来校验list本身
    @Valid  // 重要：用来嵌套校验DemoSingle
    private List<DemoSingle> list;
}
