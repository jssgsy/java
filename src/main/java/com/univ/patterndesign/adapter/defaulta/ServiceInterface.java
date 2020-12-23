package com.univ.patterndesign.adapter.defaulta;

/**
 * @author univ
 * @date 2020/12/23 3:46 下午
 * @description
 * 缺少适配器模式：缺省适配(Default Adapter)模式为一个接口提供缺省实现，这样子类型可以从这个缺省实现进行扩展，而不必从原有接口进行扩展。
 *
 * 此接口有很多方法
 */
public interface ServiceInterface {

    public void fn1();
    public void fn2();
    public void fn3();
    public void fn4();
    public void fn5();
    public void fn6();
}

/**
 * 缺少适配器：提供接口所有方法的空实现，这样其子类就可以按需只实现其需要的方法而不用一股脑全实现
 */
abstract class ServiceAdapter implements ServiceInterface {

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
}

/**
 * 只实现方法fn1
 */
class ClassA extends ServiceAdapter {

    @Override
    public void fn1() {
        System.out.println("fn1");
    }
}
/**
 * 只实现方法fn1与fn2
 */
class ClassB extends ServiceAdapter {

    @Override
    public void fn1() {
        System.out.println("fn1");
    }

    @Override
    public void fn2() {
        System.out.println("fn2");
    }
}