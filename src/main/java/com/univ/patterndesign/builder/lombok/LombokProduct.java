package com.univ.patterndesign.builder.lombok;

import lombok.ToString;

/**
 * @author univ
 * 2021/10/20 8:09 下午
 * 演示lombok底层的处理。与simple包下简化的UnivProduct大同小异
 * @see com.univ.patterndesign.builder.simple.UnivProduct
 *
 */
@ToString
public class LombokProduct {

    private String name1;
    private String name2;
    private String name3;
    // 很多其它部分

    /**
     * 生成private的构造函数，不让外界实例化；
     * 入参是所有的属性(也可以实现为ProductBuilder，不过lombok用的是前者)
     * @param name1
     * @param name2
     * @param name3
     */
    private LombokProduct(String name1, String name2, String name3) {
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
    }

    /**
     * 获取一个builder
     * @return
     */
    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    /**
     * LombokProduct的建造者
     */
    public static class ProductBuilder {
        // 将LombokProduct的所有属性复制过来
        private String name1;
        private String name2;
        private String name3;
        // 很多其它部分

        public ProductBuilder name1(String name1) {
            this.name1 = name1;
            return this;
        }

        public ProductBuilder name2(String name2) {
            this.name2 = name2;
            return this;
        }

        public ProductBuilder name3(String name3) {
            this.name3 = name3;
            return this;
        }

        /**
         * 生成最终的产品
         * @return
         */
        public LombokProduct build() {
            return new LombokProduct(name1, name2, name3);
        }

    }
}
