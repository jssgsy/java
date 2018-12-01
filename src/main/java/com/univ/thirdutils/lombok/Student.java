package com.univ.thirdutils.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author univ
 * @datetime 2018/11/23 2:42 PM
 * @description 简单的pojo，注意看当用不同粒度的lombok注解时自动生成的方法(通过cmd+f12查看)。
 */

/*
常见lombok注解（详细参考为知笔记）：
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
 */

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Integer age;
    private String name;
    private final int id = 1234;
    private static String alias;

}
