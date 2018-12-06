package com.univ.jdk8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 流式api，链式调用
 * jdk8中在Collection接口中新增了stream方法，所以所有的集合类都可以使用
 * 流式api一般结合集合、lambda表达式使用；
 * java.util.Stream类常见的几个方法：
 * 1. map：将一个值计算后更新为另一个值；
 * 2. filter：将一个值进行判断，为true则取出来；
 * 3. forEach
 */
public class StreamAPITest {
    @Test
    public void test() {
        List<Integer> list = Arrays.asList(2, 3, 4, 5, 6, 7, 8);
        // map方法
        list.stream().map(x -> x * x).forEach(x -> System.out.print(x + " "));  // 4 9 16 25 36 49 64

        // filter方法
        list.stream().filter(x -> x % 2 == 0).forEach(x -> System.out.print(x + " "));  // 2 4 6 8
        System.out.println();

        // count方法
        long count = list.stream().filter(x -> x % 2 == 0).count();
        System.out.println("count: " + count);

    }

    @Test
    public void groupingBy() {
        List<A> list = Arrays.asList(
                new A("aaa", 10),
                new A("bbb", 20),
                new A("ccc", 20),
                new A("ddd", 40),
                new A("eee", 30),
                new A("fff", 20),
                new A("ggg", 40),
                new A("ggg", 50),
                new A("ggg", 10)
        );

        // 按照对象的age字段分组
        Map<Integer, List<A>> collect = list.stream().collect(Collectors.groupingBy(t -> t.getAge()));
        System.out.println(collect);
        /*
            {
                50=[A{name='ggg', age=50}],
                20=[A{name='bbb', age=20}, A{name='ccc', age=20}, A{name='fff', age=20}],
                40=[A{name='ddd', age=40}, A{name='ggg', age=40}],
                10=[A{name='aaa', age=10}, A{name='ggg', age=10}], 30=[A{name='eee', age=30}]
            }
         */

    }
}

class A {
    private String name;
    
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public A(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public A() {
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
