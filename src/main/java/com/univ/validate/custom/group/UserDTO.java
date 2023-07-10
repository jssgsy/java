package com.univ.validate.custom.group;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author univ date 2023/6/29
 */
public class UserDTO {

    // 只有Update组时校验
    @NotNull(groups = Update.class, message = "Update: id不能为空")
    @Null(groups = Create.class, message = "Create: 创建时id必须为null")
    private Long id;

    @Length(min = 1, max = 10, groups = Update.class, message = "Update: hobby不能为空")
    @NotEmpty(groups = Create.class, message = "Create: hobby不能为空")
    private String hobby;

    // 只在包含Create或者Update组时校验
    @NotEmpty(groups = {Create.class, Update.class}, message = "Create And Update: name不能为空")
    private String name;

    /**
     * 只在默认组时下校验
     * @see javax.validation.groups.Default
     */
    @NotNull(message = "Default: age默认不能为空")
    private Integer age;

    public void setId(Long id) {
        this.id = id;
    }
}
