package com.univ.innerclass;

import org.junit.Test;

/**
 * Univ
 * 16/8/23 13:56
 */


/**
 * 内部类的一些基本用法
 */
public class MainTest {


    /**
     * 演示创建非静态内部类实例的方法
     *      OuterClass.InnerClass ic = oc.new InnerClass();
     *
     * 创建非静态内部类实例之前,要求外部类实例已经存在。
     */
    @Test
    public void createInnerClass(){
        OuterClass oc = new OuterClass();
        OuterClass.InnerClass ic = oc.new InnerClass();
        System.out.println(ic.getName());

        //非静态内部类可以修改外部类的属性
        ic.setName("aaa");
        System.out.println(ic.getName());

        //非静态内部类可以修改外部类的属性
        ic.setName2("vvvv");
        System.out.println(ic.getName());

    }

    /**
     * 演示创建非静态内部类实例的方法.
     *  这种方法比使用oc.new InnerClass()创建内部对象更直观些。
     */
    @Test
    public void createInnerClass2(){
        OuterClass oc = new OuterClass();
        OuterClass.InnerClass ic = oc.createInnerClass();
        System.out.println(ic.getName());
    }

    /**
     * 演示创建静态内部类的方法1
     *      OuterClass.InnerStaticClass ic = new OuterClass.InnerStaticClass();
     *
     * 创建静态内部类之前不用外部类实例存在
     */
    @Test
    public void createStaticInnerClass(){
        OuterClass.InnerStaticClass ic = new OuterClass.InnerStaticClass();
        System.out.println(ic.getAge());

        //静态内部类可以访问外部类的静态属性或者静态方法
        System.out.println(ic.getAddress());
    }

    /**
     * 演示创建静态内部类的方法2(利用外部类实例创建静态内部类实例)
     *      OuterClass.InnerStaticClass ic = oc.createInnerStaticClass();
     *
     * 这种方法不应该使用,因为静态内部类对象并不依赖外部类实例而存在。
     */
    @Test
    public void createStaticInnerClass2(){
        OuterClass oc = new OuterClass();
        OuterClass.InnerStaticClass ic = oc.createInnerStaticClass();
        System.out.println(ic.getAge());
    }

}
