package com.univ.patterndesign.factory.factorymethod;

/**
 * @author univ
 * @date 2020/12/2 10:22 上午
 * @description 抽象的对象工厂，生产Product对象
 */
public interface ProductFactory {
    /**
     * 创建对象，由子类实现
     * @return 返回的还是product类型，由子类返回具体的产品类型
     */
    Product createProduct();
}

/**
 * 具体的工厂-子工厂只创建一种对象
 */
class ProductAFactory implements ProductFactory {

    @Override
    public Product createProduct() {
        return new ProductA();
    }
}

/**
 * 具体的工厂-子工厂只创建一种对象
 */
class ProductBFactory implements ProductFactory {

    @Override
    public Product createProduct() {
        return new ProductB();
    }
}
