package com.univ.patterndesign.adapter;

import org.junit.Test;

/**
 * @author univ
 * @date 2020/12/23 3:36 下午
 * @description
 */
public class AdapterTest {

    @Test
    public void test() {

    }
}

interface Target {

    void fn1();
}

class Adaptee {
    public void fn2() {
        System.out.println("fn2");
    }
}

class Adapter implements Target {

    /**
     * 引用
     */
    private Adaptee adaptee;

    @Override
    public void fn1() {
        System.out.println("fn1");
    }

    /**
     * 委托给adaptee
     */
    public void fn2() {
        this.adaptee.fn2();
    }
}
