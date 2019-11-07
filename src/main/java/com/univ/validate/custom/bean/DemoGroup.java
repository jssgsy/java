package com.univ.validate.custom.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.univ.validate.custom.group.GroupA;
import com.univ.validate.custom.group.GroupB;

import lombok.Data;

/**
 * @author univ
 * @date 2019/11/7 7:55 PM
 * @description 组。
 * 在声明约束时声明适用于哪个组
 */
@Data
public class DemoGroup {

    @NotNull
    @Size(min = 5, message = "nameDefault必须大于5个字符") // 只验证默认组时生效
    private String nameDefault;

    @NotNull    // 注意，此约束并没有指定groups，所以会适用于默认组
    @Size(min = 10, message = "nameA必须大于10个字符", groups = {GroupA.class})    // 只验证GroupA组时生效
    private String nameA;

    @NotNull    // 注意，此约束并没有指定groups，所以会适用于默认组
    @Size(min = 15, message = "nameB必须大于15个字符", groups = {GroupB.class})    // 只验证GroupB组时生效
    private String nameB;

}
