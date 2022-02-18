package com.univ.thirdutils.jackson.advance;

import java.util.List;

import lombok.Data;

/**
 * @author univ
 * 2022/2/18 10:31 上午
 */
@Data
public class BeanInterfaceImplOne implements BeanInterface{

    private String identify;
    private Integer age;
    private List<String> list;
}
