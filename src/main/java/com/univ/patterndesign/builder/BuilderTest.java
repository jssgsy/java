package com.univ.patterndesign.builder;

import org.junit.Test;

/**
 * @author univ
 * @datetime 2018/11/6 4:11 PM
 * @description 建造者模式
 */
public class BuilderTest {
    @Test
    public void test() {
        Director director = new Director();
        Builder builder = new ConcreteBuilder1();
        // 指导者指导建造者进行建造
        director.guide(builder);

        Product product = builder.getResult();
        System.out.println(product);
    }

}

/*
1. 建造者模式，将一个复杂对象(Product)的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。即：将建造复杂对象的过程和组成对象的部件解耦
2. 感觉是将一个大对象进行拆分，然后逐个进行建造，建造完成后拼接成一个完整的对象；
3. 主要用于创建一些复杂的对象，这个对象内部的构建顺序通常是稳定的，但对象外部的构建通常面临着复杂的变化
 */
// 具体的物品，很庞大的对象
class Product {

    /**
     * 这里用String形式来模拟产品的组成部门，实际上当然可以是对象形式(一般都是对象形式)
     */
    private String head;
    private String eye;
    private String body;
    private String foot;

    public void setHead(String head) {
        this.head = head;
    }

    public void setEye(String eye) {
        this.eye = eye;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setFoot(String foot) {
        this.foot = foot;
    }

    @Override
    public String toString() {
        return "Product{" +
                "head='" + head + '\'' +
                ", eye='" + eye + '\'' +
                ", body='" + body + '\'' +
                ", foot='" + foot + '\'' +
                '}';
    }
}

// 建造者模式的抽象定义，定义产品的各个部分的创建
abstract class Builder {

    // 将一个大产品拆分成小部件然后逐个进行构建
    public abstract void buildHead(String head);
    public abstract void buildEye(String eye);
    public abstract void buildBody(String body);
    public abstract void buildFoot(String foot);

    // 获取最后建造完毕的产品
    public abstract Product getResult();
}

class ConcreteBuilder1 extends Builder {

    // 要最终建造的产品,需要先初始化为一个空壳
    protected Product product = new Product();

    @Override
    public void buildHead(String head) {
        product.setHead(head);
    }

    @Override
    public void buildEye(String eye) {
        product.setEye(eye);
    }

    @Override
    public void buildBody(String body) {
        product.setBody(body);
    }

    @Override
    public void buildFoot(String foot) {
        product.setFoot(foot);
    }

    @Override
    public Product getResult() {
        return product;
    }
}

// 指导者，用来指导建造整个产品的流程、顺序
class Director {

    public void guide(Builder builder) {
        builder.buildBody("body");
        builder.buildHead("head");
        builder.buildEye("eye");
        builder.buildFoot("foot");
    }
}
