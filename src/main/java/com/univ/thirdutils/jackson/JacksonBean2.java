package com.univ.thirdutils.jackson;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * @author univ
 * 2022/2/17 5:37 下午
 */
@Data
class JacksonBean2 {

    /**
     * serialization与deserialization都忽略掉此字段
     *  serialization时生成的json串中不包含此属性；
     *  deserialization时忽略掉json串中的此key，即使其有值
     */
    @JsonIgnore
    private Long id = 10l;

    /**
     * serialization时将这里的name属性转成json中的nameV2;
     * deserialization时将json中的nameV2转成这里的name属性;
     *  注：要求json字符串中必须有nameV2
     */
    @JsonProperty("nameV2")
    private String name = "univ";

    /**
     * 只有当age字段的值不为null时才参与到serialization中；
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer age = 28;

    // 注意不要忘了timezone = "GMT+8"
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday = new Date();
}
