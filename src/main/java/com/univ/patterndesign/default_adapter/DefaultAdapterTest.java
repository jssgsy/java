package com.univ.patterndesign.default_adapter;

/**
 * 缺省适配器模式
 *
 * @author univ
 * @datetime 2018/11/6 8:53 AM
 * @description
 */
public class DefaultAdapterTest {
}


interface A {
    void fn1();

    void fn2();

    void fn3();

    void fn4();

    void fn5();

    void fn6();
}

/**
 * 此时任何实现接口A的类均需要实现A中的所有方法，即使C1可能只关心其中的一两个方法
 */
/*class C1 implements A {

    @Override
    public void fn1() {

    }

    @Override
    public void fn2() {

    }

    @Override
    public void fn3() {

    }

    @Override
    public void fn4() {

    }

    @Override
    public void fn5() {

    }

    @Override
    public void fn6() {

    }
}*/

/**
 * 定义出一个抽象类，为接口A中的每个方法均提供一个实现，不过是一个空实现
 */
abstract class B implements A {
    @Override
    public void fn1() {
        // do nothing,即空实现
    }

    @Override
    public void fn2() {
        // do nothing,即空实现
    }

    @Override
    public void fn3() {
        // do nothing,即空实现
    }

    @Override
    public void fn4() {
        // do nothing,即空实现
    }

    @Override
    public void fn5() {
        // do nothing,即空实现
    }

    @Override
    public void fn6() {
        // do nothing,即空实现
    }
}

/**
 * 此时C可以只实现自己关心的方法了，而不用和C1一样
 */
class C extends B {
    @Override
    public void fn1() {
        System.out.println("我只关心fn1方法");
    }
}