package com.univ.jdk8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 流式api，链式调用
 * jdk8中在Collection接口中新增了stream方法，所以所有的集合类都可以使用
 * 流式api一般结合集合、lambda表达式使用；
 * java.util.Stream类常见的几个方法：
 * 1. map：将一个值计算后更新为另一个值；
 * 2. filter：将一个值进行判断，为true则取起来；
 * 3.
 */
public class StreamAPITest {
    @Test
    public void test() {
        List<Integer> list = Arrays.asList(2, 3, 4, 5, 6, 7, 8);
        // map方法
        list.stream().map(x -> x * x).forEach(x -> System.out.print(x + " "));  // 4 9 16 25 36 49 64

        // filter方法
        list.stream().filter(x -> x % 2 == 0).forEach(x -> System.out.print(x + " "));  // 2 4 6 8
    }
}
