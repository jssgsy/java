package com.univ.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

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

    private List<A> objList = Arrays.asList(
            new A("aaa", 10),
                new A("bbb", 20),
                new A("ccc", 30),
                new A("ddd", 40),
                new A("eee", 50),
                new A("fff", 60)
        );

    @Test
    public void test() {
        List<Integer> list = Arrays.asList(2, 3, 4, 5, 6, 7, 8);
        // map方法
        list.stream().map(x -> x * x).forEach(x -> System.out.print(x + " "));  // 4 9 16 25 36 49 64
        System.out.println();

        // filter方法
        list.stream().filter(x -> x % 2 == 0).forEach(x -> System.out.print(x + " "));  // 2 4 6 8
        System.out.println();
        // 注意，原来的集合并没有发生变化
        System.out.println(list);
        System.out.println();

        // count方法
        long count = list.stream().filter(x -> x % 2 == 0).count();
        System.out.println("count: " + count);

    }

    /**
     * map与flatmap方法
     */
    @Test
    public void map() {
        List<Integer> list = Arrays.asList(2, 3, 4, 5, 6, 7, 8);
        list.stream().map(t -> t * 10).forEach(t -> System.out.print(t + " "));
        System.out.println();
        list.stream().flatMap(t -> Stream.of(t * 10)).forEach(t -> System.out.print(t + " "));
        list.forEach(t -> System.out.print(t + " "));

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

        // 按照对象的age字段分组,age值是map中的key，value是包含那些age值相等的项组成的列表
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
        List<A> list1 = collect.get(40);
        list1.forEach(System.out::print);
    }

    /**
     * of方法：生成一个stream
     */
    @Test
    public void of() {
        Stream.of(1, 10, 34, 56l, "hello").forEach(System.out::println);
    }

    /**
     * concat方法,连接两个stream中的元素形成一个新的stream
     */
    @Test
    public void concat() {
        Stream.concat(Stream.of(4, 5, 6), Stream.of(1, 2, 3)).forEach(System.out::println);
    }

    /**
     * 去除stream中的重复元素
     */
    @Test
    public void distinct() {
        Stream.of(1, 3, 4, 3, 5, 3, 2, 4, 10).distinct().forEach(System.out::println);
    }

    /**
     * sorted方法，对stream中的元素进行排序
     * sorted()默认升序，
     * sorted(Comparator):指定排序规则
     */
    @Test
    public void sorted() {
        Stream.of(1, 4, 3, 10, 5)
                .sorted()
                .forEach(t -> System.out.print(t + " "));
        // 打印结果
        // 1 3 4 5 10
        System.out.println();
        
        Stream.of(1, 4, 3, 10, 5).sorted((o1, o2) -> {
            if (o1 < o2) {
                return 1;
            } else if (01 == 02) {
                return 0;
            } else {
                return -1;
            }
        }).forEach(t -> System.out.print(t + " "));// 10 5 4 3 1
    }

    /**
     * noneMatch：stream中的所有元素都不满足某种条件返回true
     * 注意，返回的是bool类型
     */
    @Test
    public void noneMatch() {
        // stream中的所有元素都不等于iii时返回true
        boolean b1 = objList.stream().noneMatch(t -> t.getName().equals("iii"));
        System.out.println(b1); // true

        boolean b2 = objList.stream().noneMatch(t -> t.getName().equals("aaa"));
        System.out.println(b2); // false
    }

    /**
     * anyMatch：stream中的任一元素满足某种条件时就返回true
     */
    @Test
    public void anyMatch() {
        boolean b1 = objList.stream().anyMatch(t -> t.getName().equals("iii"));
        System.out.println(b1); // false

        boolean b2 = objList.stream().anyMatch(t -> t.getName().equals("aaa"));
        System.out.println(b2); // true
    }

    /**
     * allMatch：stream中的所有元素都满足某种条件时才返回true
     */
    @Test
    public void allMatch() {
        // 所有的元素都不等于iii时才返回true
        boolean b1 = objList.stream().allMatch(t -> !t.getName().equals("iii"));
        System.out.println(b1); // true

        boolean b2 = objList.stream().allMatch(t -> t.getName().equals("aaa"));
        System.out.println(b2); // false
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

@Data
@AllArgsConstructor
class Demo {
    private Integer id;
}