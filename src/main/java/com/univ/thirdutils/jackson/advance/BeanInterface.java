package com.univ.thirdutils.jackson.advance;

/**
 * @author univ
 * 2022/2/18 10:30 上午
 */

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * use：用来定义子类使用什么来唯一标识；
 * include：
 * property：仅在include属性为As.PROPERTY时起作用
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "identify")
@JsonSubTypes({ // 用来定义具体子类元信息
     @JsonSubTypes.Type(value = BeanInterfaceImplOne.class, name = "one"),
     @JsonSubTypes.Type(value = BeanInterfaceImplTwo.class, name = "two")
})
public interface BeanInterface {

}

/**
 * 另一种写法，只用@JsonTypeInfo。(在实际中可能不常用，因为不太可能要求json串中包含子类的全路径名)
 * 此时要求json串中的identify部分的值为某个子类的全路径名
 */
// @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "identify")
// public interface BeanInterface {
//
// }
