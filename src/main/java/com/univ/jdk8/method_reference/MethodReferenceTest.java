package com.univ.jdk8.method_reference;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.Function;

/**
 * 方法引用
 * 1. 方法引用是一个语法糖，用来简化lambda表达式；
 * 2. 核心：要实现的函数式接口中那个方法已经有现成的实现(方法)存在，直接引用现成的，不用重新实现一遍
 * 3. 形式：即可以引用实例方法，也可以引用静态方法，也可以引用构造方法，还可以引用数组，不论哪种方式均以::的调用方式；
 * 4. 方法引用的要求：只要函数式接口中的那个方法的形参和要引用的已经存在的方法保持一致即可；
 */
public class MethodReferenceTest {

    /**
     * 引用现有的方法
     */
    @Test
    public void test() {
        // 常规lambda表达式
        say((str) -> {
            System.out.println(str);
        }); // hello

        /**
         * 1. 上面lambda方法体要完成的功能是 打印出形参str 而已，而此功能已经被System.out对象的println方法实现了（重点！），此时使用方法引用来直接引用已经有的功能(方法)，而不用重新实现一遍(虽然这里也只是简单的调用一个而已);
         */
        say(System.out::println);   // hello
    }

    private void say(A a) {
        a.fn("hello");
    }
    //--------------------------------------------------------------------

    /**
     * 自定义现有的方法
     */
    @Test
    public void test2() {
        // 常规lambda表达式
        say2(str -> str + "...");   // world...

        // 使用方法引用的形式，表示this的sing方法形参与接口B中的vFn方法形参一致,且想用sing方法就作为vFn方法的实现
        say2(this::sing);   // world...
    }

    // 和函数式接口A的fn方法保持形参一致
    private String sing(String str) {
        // 这里可以对str进行一系列操作，这里省略
        return str + "...";
    }
    
    private void say2(B b) {
        System.out.println(b.vFn("world"));;
    }
    //--------------------------------------------------------------------

    // 演示几种方法引用的形式
    @Test
    public void test3() {
        // 1. 引用实例方法，见上述test2方法
        
        // 2. 引用静态方法
        say2(MethodReferenceTest::staticFn);    // world o o
        
        // 3. 引用构造函数，常规的lambda表达式
        /**
         * Function接口的apply方法以Integer作为形参，以StringBuffer作为返回值
         * 正好StringBuffer有一个构造函数接受一个int型入参，而其构造函数返回StringBuffer对象
         */
        Function<Integer, StringBuffer> function = n -> new StringBuffer(n);

        // 引用构造函数
        Function<Integer, StringBuffer> function1 = StringBuffer::new;

        // 4. 引用数组
        Function<Integer, int[]> function2 = int[]::new;
        say3(function2);    // [10, 20, 30]

    }
    
    public static String staticFn(String str) {
        return str + " o o ";
    }
    
    private void say3(Function<Integer, int[]> function) {
        int[] arr = function.apply(3);
        arr[0] = 10;
        arr[1] = 20;
        arr[2] = 30;
        System.out.println(Arrays.toString(arr));
    }

}

/**
 * 自定义一个函数式接口
 */
@FunctionalInterface
interface A {
    void fn(String str);
}


@FunctionalInterface
interface B {
    String vFn(String str);
}

@FunctionalInterface
interface C {
    void cFn();
}