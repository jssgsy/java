package com.univ.jdk8.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Lambda表达式测试
 * 时常对比一下匿名类
 * 1. 始终记住，java是一门强类型语言，所有对象都必须有类型；
 */
public class LambdaTest {
    @Test
    public void test1() {

        // 使用类名类的方式
        fn(new A() {
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        }); // 输出：30

        /**
         * 使用lambda表达式的方式
         * 1. (int a, int b) -> {return a * a + b * b;}就是一个lambda表达式；
         * 2. fn方法的参数是接口A类型，由此可知lambda也只是一个实现了A接口的类型，其中：
         *  a. (int a, int b)就是A接口中calculate方法的形参；
         *  b. {return a * a + b * b;}就是A接口中calculate方法的具体实现！重要！
         */
        fn((int a, int b) -> {return a * a + b * b;});  // 输出：500

        
        // lambda表达式的用途
        // 1. 集合遍历
        List<Integer> list = Arrays.asList(3, 4, 5, 6);
        // forEach方法是jdk8中Iterable接口新增的方法，可以查看一下其源码
        list.forEach(t -> System.out.println(t));

        // 2. 结合流式api使用,stream方法是Collection接口中新增的默认方法
        list.stream().filter(t -> t % 2 == 0).map(t -> t * 2).forEach(t -> System.out.println(t));
    }
    
    public void fn(A a) {
        // 这里calculate的具体实现由外部调用者传入，即看起来是外部传入方法给了fn
        System.out.println(a.calculate(10, 20));
    }
}

interface A {
    int calculate(int a, int b);
}


