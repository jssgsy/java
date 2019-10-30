package com.univ.jdk8.functional_interface;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import org.junit.Test;

/**
 * 函数式接口
 */
public class FunctionalInterfaceTest {

    @Test
    public void basic() {
        int factor = 10;
        // lambda中可以使用类成员与外部变量
        basic(t -> t * 2 * factor);

        basic(t -> {
            // lambda中虽然可以使用类成员与外部变量，但会自动将其转为final
            // variable used in lambda expression should be final or effectively final
            // factor += 2;    // error
            return t * 2 * factor;
        });
    }
    private void basic(Function<Integer, Integer> function) {
        System.out.println(function.apply(10));
    }

    /**
     * 两个入参，一个返回值
     * 特点：两个入参与返回值类型均可不同
     */
    @Test
    public void biFunction() {
        biFunctionParam((x, y) -> x.equals("aaa") && y == 13);
    }

    private void biFunctionParam(BiFunction<String, Integer, Boolean> biFunction) {
        Boolean aaa = biFunction.apply("aaa", 113);
        System.out.println(aaa);
    }

    /**
     * 就是一个BiFunction
     * 只是此时两个入参与返回值类型均一致
     */
    @Test
    public void binaryOperator() {
        binaryOperatorParam((x, y) -> x + "_" + y);
    }

    /**
     * 注意，定义BinaryOperator，只需一个类型，其继承自BiFunction<T, T>。BinaryOperator的声明中也只有一个泛型参数
     * 如下定义是错误的：BinaryOperator<String, String, String> binaryOperator
     * @param binaryOperator
     */
    private void binaryOperatorParam(BinaryOperator<String> binaryOperator) {
        String result = binaryOperator.apply("aaa", "bbb");
        System.out.println(result);
    }

    @Test
    public void cascading() {
        /**
         * Q：此方法想要一种怎样的行为？
         * A：此行为想，给定某个整形参数t，返回其平方值
         */
        basicFunctionInterface(t -> {
            System.out.println("t: " + t);
            return t * t;
        });

        /**
         * Q：此方法想要一种怎样的行为？
         * A：此行为想，给定某个参数t，对这个参数t再施加某种行为(嵌套)B，（此时外层lambda的结果仍然是一个lambda）
         *      Q：行为B是怎样的？
         *      A：行为B想，给定一个参数i，返回参数i的平方值
         */
        cascadFunctionInterface(t -> i -> i * i);

        // 造价于
        cascadFunctionInterface(t -> (i -> i * i));
    }


    private void basicFunctionInterface(Function<Integer, Integer> function) {
        System.out.println(function.apply(10));
    }

    private void cascadFunctionInterface(Function<Integer, Function<Integer, Integer>> cascadFunction) {
        Function<Integer, Integer> tmp = cascadFunction.apply(10);
        Integer apply = tmp.apply(20);
        System.out.println(apply);
    }
}

/**
 * 1. 只有一个方法的接口称之为函数式接口
 */
interface A {
    int fn();
}

/**
 * 2. jdk8中新增了FunctionalInterface注解，用来表示是一个函数式接口，此时接口中若多于1个抽象方法，则编译器报错
 */
@FunctionalInterface
interface B {
    int fn2();
}

/**
 * 3. jdk8中新增的默认方法与静态方法不在FunctionalInterface的要求中
 */
@FunctionalInterface
interface C {
    int fn3();

    default int fn4() {
        return 10;
    }

    static int fn5() {
        return 20;
    }
}

/**
@FunctionalInterface
interface D {
    // 这些方法不能是Object类的public签名方法
    boolean equals(Object ojb);
}*/