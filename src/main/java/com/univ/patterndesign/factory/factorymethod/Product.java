package com.univ.patterndesign.factory.factorymethod;

/**
 * @author univ
 * @date 2020/12/1 9:10 下午
 * @description 前提：product是一个创建过程很复杂的对象
 */

/**
 * 抽象的产品，在需要使用到工厂模式的场景中，实际的Product的创建过程一般是复杂的，因此才需要使用工厂模式解耦
 */
public interface Product {
    void fn();
}

/**
 * 具体的产品
 */
class ProductA implements Product {

    @Override
    public void fn() {
        System.out.println("product a");
    }
}

/**
 * 具体的产品
 */
class ProductB implements Product {

    @Override
    public void fn() {
        System.out.println("product b");
    }
}
