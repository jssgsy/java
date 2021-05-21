package com.univ.algorithom.beanconvert;

import lombok.Data;

/**
 * @author univ
 * @date 2021/5/21 10:20 上午
 * @description
 */
@Data
public class Source extends Base {

    private Integer age;

    private String name;

    private int success;
}

@Data
class Base {

    private Integer age = 190;

    private String address = "default_address";
}