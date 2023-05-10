package com.univ.generic.basic;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

/**
 * 单纯的泛型作为返回类型
 * 前提：一般指的是泛型的包裹类型，如List<T>, CustomerType<T>等；
 *
 * 作用：
 *  返回的不是某个具体类型，此时当然可以使用Object作为返回类型，但这样使用者就需要来强转了，
 *  此时使用泛型作为返回类型，使用者就不需要强转了
 *
 * 实际应用：mybatis-plus
 *  db操作不外乎CRUD，但每个查询返回的却可能是不同类型的实体，因此很多通用方法(如getOne)都使用泛型作为返回类型
 *
 */
public class TasReturnType {

    public static <T> List<T> list() {
        return new ArrayList<T>();
    }

    public static void integerFn(List<Integer> list) {}
    public static void stringFn(List<String> list) {}

    @Test
    public void test() {
        // 默认是Object
        List<Object> list = TasReturnType.list();

        // 可以用任意类型来接收！如下述的Integer与Boolean
        // 重点：相当于，这里用参数的类型指定了泛型方法的返回类型
        List<Integer> l1 = TasReturnType.list();
        List<Boolean> l2 = TasReturnType.list();

        // 此时因为integerFn的参数类型为List<Integer>，因此TasReturnType.list()知道其返回的应该是List<Integer>
        integerFn(TasReturnType.list());

        // 此时因为integerFn的参数类型为List<String>，因此TasReturnType.list()知道其返回的应该是List<String>
        stringFn(TasReturnType.list());

    }
}
