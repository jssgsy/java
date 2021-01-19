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
        // 将适配器当作Target类使用!!!
        Target target = new Adapter();
        target.fn2();
    }
}

/**
 * 这是系统期望的接口，只是这里的某些实现已有其它类(Adaptee)实现过了,而系统又没法直接使用Adaptee
 */
interface Target {

    void fn2();
}

/**
 * 系统中现有的接口，但没法直接在系统中使用(可能是签名要求或者其它),但其实现又是有用的
 */
class Adaptee {
    public void fn2() {
        System.out.println("Adaptee fn2");
    }
}

/**
 * 适配器：系统要的是Target类，但此类的实现已经有其它类(Adaptee)实现了，可以用适配器将两种结合起来，
 * 达到即能满足系统使用要求，又能利用己有类的功能
 */
class Adapter implements Target {

    /**
     * 引用
     */
    private Adaptee adaptee = new Adaptee();

    public void fn1() {
        System.out.println("Adapter fn1");
    }

    /**
     * 委托给adaptee
     */
    @Override
    public void fn2() {
        // 此方法的逻辑adaptee已经有了，直接委托就好，不用再实现一遍
        this.adaptee.fn2();
    }
}
