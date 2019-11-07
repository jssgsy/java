package com.univ.validate.custom.bean;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author univ
 * @date 2019/11/7 7:40 PM
 * @description 嵌套验证，即Object Graph 验证。使用@Valid
 */
@Data
public class DemoNested {

    @NotNull
    @Min(value = 20, message = "DemoNested: age必须大于20")
    public Integer age;

    // 在验证的同时也验证对象demoInner，则需要加@Valid
    @Valid
    @NotNull    // 注解，不要少了此注解，@Valid与其它注解一样，只有在所注解的字段不为null时才进行验证
    private DemoInner demoInner;


    @Data
    public static class DemoInner {
        @NotNull(message = "DemoInner: name不能为null")
        private String name;

        @NotNull
        @Min(value = 175, message = "DemoInner: height必须大于175cm")
        private Integer height;

    }
}
