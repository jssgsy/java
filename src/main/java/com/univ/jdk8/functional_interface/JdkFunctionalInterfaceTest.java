package com.univ.jdk8.functional_interface;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import org.junit.Test;

/**
 * @author univ
 * @date 2019/1/9 2:15 PM
 * @description jdk中自带的常见函数式接口的用例
 *
 *
 * 注意，
 *  1. jdk中自带的常见函数式接口中有很多好用的默认方法,可用来链式调用；
 *  2. jdk中自带的常见函数式接口有很多变体，如BiPredicate、BiConsumer，BiFunction等，这些扩展了可接收的参数，又如IntPredicate、IntConsumer、IntFunction等，这些使泛型具体化了，只能用于int型
 *  3. 链式调用时，一般都是会对每个元素依次调用，而不是对整个集合调用完一次再对整个集合调用一次
 */
public class JdkFunctionalInterfaceTest {

    private List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    /**
     * Predicate：用来执行条件表达式
     */
    @Test
    public void predicate() {

        // 1. 最基础的用法
        list.stream().filter(t -> t % 2 == 0).forEach(t -> System.out.print(t + "  "));
        System.out.println();

        /*
        Predicate有用的默认用法，可用来链式调用，注意，返回类型都是Predicate，可用来链式调用
            and：default Predicate<T> and(Predicate<? super T> other)，需同时满足两个条件
            negate：default Predicate<T> negate()
            or：default Predicate<T> or(Predicate<? super T> other)
         */
        // 2.1 显示定义出Predicate对象
        Predicate<Integer> predicate = t -> {System.out.print(t + "_x "); return t % 2 == 0;};
        // 此时每个元素会先执行t -> t % 2 == 0，如果为true，然后执行t-> t > 5，
        // 重要注意，不是对所有元素执行t % 2 == 0后再对剩余的所有元素执行t > 5，可通过and方法源码观看
        list.stream().filter(predicate.and(t-> {System.out.print(t + "_y "); return t > 5; })).forEach(t -> System.out.print(t + "  "));
        System.out.println();

        // 2.2 等价于如下：
        list.stream().filter(((Predicate<Integer>)t -> t % 2 == 0).and(t-> t > 5)).forEach(t -> System.out.print(t + "  "));
        System.out.println();

        // 取反
        list.stream().filter(predicate.negate()).forEach(t -> System.out.print(t + "  "));
        System.out.println();

        // 多链式调用
        list.stream().filter(predicate.and(t-> t > 5).and(t -> t < 8)).forEach(t -> System.out.print(t + "  "));
        System.out.println();
    }

    /**
     * Consumer：消费入参，且没有返回结果，可用来改变入参的值
     */
    @Test
    public void consumer() {
        // 最简单的例子，直接将入参输出
        list.stream().forEach(t -> System.out.print(t + "  "));
        System.out.println();
        
        /*
        默认方法
            andThen：default Consumer<T> andThen(Consumer<? super T> after)：消费之后再消费这里
         */
        Consumer<Integer> consumer = t -> System.out.print(t + "  ");

        // 对每个元素先执行System.out.print(t + "  ")然后再执行System.out.print(t + "_andThen  ")，
        // 注意，不是对所有元素执行执行System.out.print(t + "  ")然后再执行System.out.print(t + "_andThen  ")，看源码
        list.stream().forEach(consumer.andThen(t -> System.out.print(t + "_andThen  ")));
        // 输出结果：1  1_andThen  2  2_andThen  3  3_andThen  4  4_andThen  5  5_andThen  6  6_andThen  7  7_andThen  8  8_andThen  9  9_andThen  10  10_andThen
    }

    /**
     * Function：消费入参，且返回一个结果(注意与Consumer的区别)
     */
    @Test
    public void function() {

        // 简单用法
        list.stream().map(t -> 2 * t).forEach(t -> System.out.print(t + "  "));
        System.out.println();

        /*
        默认方法
            compose：default <V> Function<V, R> compose(Function<? super V, ? extends T> before)，消费之前先消费这个
            andThen：default <V> Function<T, V> andThen(Function<? super R, ? extends V> after)，消费之后再消费这个
         */
        Function<Integer, Integer> function = t -> {System.out.print(t + "_x ");return t * 2;};
        // 注意，是每个元素先执行t + "_y "然后再执行t + "_x "，不是整个集合的所有元素先执行t + "_y "后再执行t + "_x "
        list.stream().map(function.compose(t -> {System.out.print(t + "_y ");return t * 3;})).forEach(t -> System.out.print(t + "  "));
        // 输出：1_y 3_x 6  2_y 6_x 12  3_y 9_x 18  4_y 12_x 24  5_y 15_x 30  6_y 18_x 36  7_y 21_x 42  8_y 24_x 48  9_y 27_x 54  10_y 30_x 60

    }

    /**
     * Supplier
     * 很简单，没有入参，只是简单的返回一个值
     */
    @Test
    public void supplier() {
        Optional.ofNullable(10).orElseGet(() -> 10);
    }

    /**
     * 以BiConsumer为例，演示核心函数式接口的变体
     * BiConsumer接收两个入参，而Consumer只接收一个入参
     */
    @Test
    public void biConsumer() {
        testBiConsumer("univ", 28, (name, age) -> System.out.println("姓名：" + name + " 年龄：" + age));
        // 输出：姓名：univ 年龄：28
    }
    
    private void testBiConsumer(String name, Integer age, BiConsumer<String, Integer> biConsumer){
        biConsumer.accept(name, age);
    }


}

