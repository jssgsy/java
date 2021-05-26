package com.univ.patterndesign.builder.simple;

import java.util.Arrays;
import java.util.List;

import lombok.ToString;

/**
 * @author univ
 * @date 2021/5/26 7:17 下午
 * @description
 *
 * 典型的简化的建造者模式。具体的步骤就是下面注释的1.2.3
 */
@ToString
public class UnivProduct {

    /**
     * 建造者模式适用于构建复杂的对象，亦即意味着很可能有很多字段。
     * 这里用几个简单类型模拟
     */
    private String name;
    private Integer age;
    private List<String> arr;

    /**
     * 3. 构造函数入参为构造者：用来反哺建造完成的字段
     * @param builder
     */
    public UnivProduct(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.arr = builder.arr;
    }

    // 外部要用，直接定义成public
    public static class Builder {

        // 1. 将要创建的对象的所有属性复制过来：将创建过程隐藏在Builder中
        private String name;
        private Integer age;
        private List<String> arr;

        // 2.1 为这些字段提供setter方法：这样外部可以在需要的介入
        // 2.2 每个设值方法都返回this:供外链式调用；
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder age(Integer age) {
            this.age = age;
            return this;
        }
        public Builder arr(List<String> arr) {
            this.arr = arr;
            return this;
        }

        /**
         * 构造器的无参(默认构造函数)：在这里设定一种默认产品
         */
        public Builder() {
            this.name = "default_name";
            this.age = 18;
            this.arr = Arrays.asList("aaa", "bbb", "ccc");
        }

        /**
         * 返回要创建的最终产品
         * 为产品类中提供一个入参为Builder的构造函数：用来将Builder建造好字段反哺给要建造的产品
         * @return
         */
        UnivProduct build() {
            return new UnivProduct(this);
        }
    }
}
