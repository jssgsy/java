package com.univ.jdk8.method_reference;

import java.util.Arrays;
import java.util.function.Function;
import org.junit.Test;

/**
 * 方法引用
 * 1. 方法引用是一个语法糖，用来简化lambda表达式；
 * 2. 核心：要实现的函数式接口中那个方法已经有现成的实现(方法)存在，直接引用现成的，不用重新实现一遍
 * 3. 形式：即可以引用实例方法，也可以引用静态方法，也可以引用构造方法，还可以引用数组，不论哪种方式均以::的调用方式；
 * 4. 方法引用的要求：只要函数式接口中的那个方法的形参和要引用的已经存在的方法保持一致即可；
 */
public class MethodReferenceTest {

    /**
     * 静态方法引用
     */
    @Test
    public void staticMethodReference() {
        // 常规lambda表达式
        say((str) -> {
            System.out.println(str);
        }); // hello

        /**
         * 1. 上面lambda方法体要完成的功能是 打印出形参str 而已，而此功能已经被System.out对象的println方法实现了，
         * 且其方法签名正好和A中fn的一样（重点！），因此可直接使用复用println方法，而不用重新实现一遍了;
         */
        say(System.out::println);   // hello
    }

    /**
     * 静态方法引用比较好理解，因为没有上下文，只要静态方法的签名与方法入参FunctionalInterface的方法签名一致即可
     */
    @Test
    public void staticMethodReference2() {
        say2(MethodReferenceTest::staticFn);    // world o o
    }


    private void say(A a) {
        a.fn("hello");
    }
    //--------------------------------------------------------------------

    /**
     * 实例方法
     */
    @Test
    public void instanceMethodReference() {

        // 使用方法引用的形式，表示this的sing方法形参与接口B中的vFn方法形参一致,且想用sing方法就作为vFn方法的实现
        // 这里传入的是实例方法，所以在sing方法中是可以使用this中的其它变量、方法
        say2(this::sing);   // world...

        // 重点：这里的this可以是任意对象，也就是说对象不同，就有了不同的上下文。如
        I i = new I();
        say2(i::str);

        // 这里要好好理解
        // String的toLowerCase方法其实是没有入参的，但为什么这里能匹配？
        // 答: 方法在实际调用的时候，第一个隐含参数总是传入this，类似于静态方法：public static String toLowerCase(String this)
        // 重点：say2方法入参B中的方法是String类型，因此这里的this必须要是String类型的，因此才可以使用String的toLowerCase方法
        say2(String::toLowerCase);

        /*
         * 好好理解下面这句为啥不可以
         * IDE提示：Non-static method cannot be referenced from a static context
         * 显然是说，这里I::str是static context，而say2是Non-static method，也就是说
         * 但I类中的str方法是一个实例方法，并不是一个静态方法
         */
//        say2(I::str);
    }

    public static String singV2() {
        return "aa";
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

    /**
     * 构造函数方法引用
     */
    @Test
    public void constructionMethodReference() {
        
        // 3. 引用构造函数，常规的lambda表达式
        /**
         * Function接口的apply方法以Integer作为形参，以StringBuffer作为返回值
         * 正好StringBuffer有一个构造函数接受一个int型入参，而其构造函数返回StringBuffer对象
         */
        Function<Integer, StringBuffer> function = n -> new StringBuffer(n);

        /*
        引用构造函数,Function接口中的方法签名是：R apply(T t)，这里定义了T为Integer，R是StringBuffer，所以只要是接收int(Integer)类型为参数，返回类型为StringBuffer的lambda表达式即可
         */
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

class I {

    private String name = "zs";
    public String str(String p) {
        return name + p;
    }
}