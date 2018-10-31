package com.univ.jdk8.functional_interface;

import org.junit.Test;

/**
 * 函数式接口
 */
public class FunctionalInterfaceTest {

    @Test
    public void test() {

    }
}

/**
 * 1. 只有一个方法的接口称之为函数式接口
 */
interface A {
    int fn();
}

/**
 * 2. jdk8中新增了FunctionalInterface注解，用来表示是一个函数式接口，此时接口中若多于1个抽象方法，则编译器报错
 */
@FunctionalInterface
interface B {
    int fn2();
}

/**
 * 3. jdk8中新增的默认方法与静态方法不在FunctionalInterface的要求中
 */
@FunctionalInterface
interface C {
    int fn3();

    default int fn4() {
        return 10;
    }

    static int fn5() {
        return 20;
    }
}
