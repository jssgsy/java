package com.univ.patterndesign.factory.simplefactory;

import org.junit.Test;

/**
 * @author univ
 * @date 2020/12/1 9:12 下午
 * @description
 */
public class SimpleFactoryTest {

    @Test
    public void test1() {
        // 客户端通过入参告诉对象工厂需要什么类型的对象，对象工厂封装了根据入参选择要创建的对象的逻辑，且对象的创建过程也统一在工厂中维护，独此一份
        Product productA = ProductFactory.createProduct("productA");
        productA.fn();

        Product productB = ProductFactory.createProduct("productB");
        productB.fn();
    }
}
