package com.univ.patterndesign.factory.factorymethod;

import org.junit.Test;

/**
 * @author univ
 * @date 2020/12/2 10:25 上午
 * @description
 */
public class FactoryMethodTest {

    @Test
    public void test() {
        // 此时客户端需要选择使用哪种工厂
        ProductFactory productFactoryA = new ProductAFactory();
        Product productA = productFactoryA.createProduct();
        productA.fn();

        ProductBFactory productBFactory = new ProductBFactory();
        Product productB = productBFactory.createProduct();
        productB.fn();
    }
}
