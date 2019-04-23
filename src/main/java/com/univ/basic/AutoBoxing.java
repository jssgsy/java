package com.univ.basic;

import org.junit.Test;

/**
 * @author univ
 * @date 2019/4/23 3:44 PM
 * @description
 */
public class AutoBoxing {

    /**
     * 当 "=="运算符的两个操作数都是 包装器类型的引用，则是比较指向的是否是同一个对象，而如果其中有一个操作数是表达式（即包含算术运算）则比较的是数值（即会触发自动拆箱的过程）
     */
    @Test
    public void intTest() {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long h = 2L;
        Long g = 3L;
        System.out.println(c == d); // true 指向同样的内存
        System.out.println(e == f); // false 指向不同的内存
        System.out.println(c == (a + b));   // true a+b会先拆箱计算得到3，然后==(一个包装类型一个基本类型)，会自动拆箱比较
        System.out.println(c.equals(a + b));    // true a+b会先拆箱计算得到3，然后equals会自动装箱
        System.out.println(g == (a + b));   // true
        System.out.println(g.equals(a + b));    // a+b会先拆箱计算得到3，然后equals会自动装箱，但此时得到的是Integer类型，不equals类型Long
        System.out.println(g.equals(a + h));    // true a + h碰到equals后自动装箱成了Long类型
    }
}
