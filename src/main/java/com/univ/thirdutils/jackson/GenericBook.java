package com.univ.thirdutils.jackson;

import lombok.Data;

/**
 * 用来演示泛型
 * @author univ
 * 2022/2/17 5:37 下午
 */
@Data
class GenericBook<T> {
    private String name;
    private T value;
}
