package com.univ.patterndesign.factory.simplefactory;

/**
 * @author univ
 * @date 2020/12/1 9:12 下午
 * @description 产品工厂
 */
public class ProductFactory {

    /**
     * 封装了根据入参选择创建的对象的逻辑。
     * @param productType
     * @return
     */
    public static Product createProduct(String productType) {
        if ("productA".equals(productType)) {
            return new ProductA();
        } else if ("productB".equals(productType)) {
            return new ProductB();
        } else {
            throw new UnsupportedOperationException("没有此种类型的对象");
        }
    }
}
