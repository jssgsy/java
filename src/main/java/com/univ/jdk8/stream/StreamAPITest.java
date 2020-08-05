package com.univ.jdk8.stream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
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
     * map与flatMap方法
     * 对stream的操作都是对其中的“对象”进行操作，这种的“对象”指的是单一的对象，此时用map方法就足够了，
     * 如果stream中的对象是集合类型，则此时没法(不方便)操作，所以stream中的元素为集合类型时，需要使用flatMap方法将其中的元素“扁平(flat)”为单一的对象
     *
     * map：适合stream中元素为单个的对象；
     * flatMap：适合stream中元素为集合类型的对象；（适合处理嵌套的对象）
     *
     * 注：Optional类中也有map与flatMap方法，含义同这里
     */
    @Test
    public void map() {
        // 此时stream中是单一的对象，可直接操作
        objList.stream().map(t -> {t.setAge(t.getAge() * 10);return t;}).forEach(t -> System.out.print(t + " "));
        System.out.println();

        List<List<Integer>> list = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5, 6)
        );

        // 注意，此时stream中的元素是集合类型，此时在map方法中进行操作就很麻烦
        list.stream().map(t -> t.stream().map(x -> x * 10)).forEach(t -> System.out.println(Arrays.toString(t.toArray()) + " "));

        // 将每个元素(集合)中的元素收集起来形成一个新的集合
        List<Integer> ids = list.stream().flatMap(t -> t.stream()).collect(Collectors.toList());
        System.out.println(ids);

        // 这里利用flatMap方法将集合t中的元素平铺(flat)出来形成一个新stream，之后再进行相应操作
        list.stream().flatMap(t -> t.stream()).map(x -> x * 10).forEach(y -> System.out.print(y + " "));
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
        // groupingBy参数说明：
        // 1. 第一个参数：使用什么key作为分组；
        // 2. 第二个参数：重要！分组后想怎么处理，默认用的toList，即将分到一组的数据放到一个list中，也可以对分到一组的数据作其它操作(如toSet，求和、平均值等等)
        Map<Integer, List<A>> collect = list.stream().collect(Collectors.groupingBy(A::getAge));
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
     * 指定第二个参数
     */
    @Test
    public void groupingByAdvance() {
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

        // 默认分组后是将分到同一组的归为一个list
        Map<String, List<A>> collect = list.stream().collect(Collectors.groupingBy(A::getName));
        System.out.println(collect);

        // 取每个分组的数量
        Map<Integer, Long> countMap = list.stream().collect(Collectors.groupingBy(A::getAge, Collectors.counting()));
        System.out.println(countMap);
        
        // 分组后求统计信息，如count,sum,min,max,average等。
        Map<Integer, IntSummaryStatistics> summaryMap = list.stream().collect(Collectors.groupingBy(A::getAge, Collectors.summarizingInt(A::getAge)));
        System.out.println(summaryMap);

        // 分组后求分组数据中age字段的值
        Map<Integer, Double> averageMap = list.stream().collect(Collectors.groupingBy(A::getAge, Collectors.averagingInt(A::getAge)));
        System.out.println(averageMap);

        // 分组后求分组数据中age字段的和,summingInt已经表明要求和的是int类型字段
        Map<Integer, Integer> sumMap = list.stream().collect(Collectors.groupingBy(A::getAge, Collectors.summingInt(A::getAge)));
        System.out.println(sumMap);

        // 分组后对分组数据map下（将分组后的数据中的name字段放到一个list中）
        Map<Integer, List<String>> mapMap = list.stream().collect(Collectors.groupingBy(A::getAge, Collectors.mapping(A::getName, Collectors.toList())));
        System.out.println(mapMap);

        // 分组后取分组数据的最大值，类似的有求最小值minBy
        // 注意，maxBy与minBy与summingInt不同，不能直接传要作用的字段，maxBy与minBy需要比较器才能确定谁大谁小
        Map<Integer, Optional<A>> maxMap = list.stream().collect(Collectors.groupingBy(A::getAge, Collectors.maxBy((t1, t2) -> {
            if (t1.getAge() < t2.getAge()) {
                return -1;
            } else if (t1.getAge() == t2.getAge()) {
                return 0;
            }
            return 1;
        })));
        System.out.println(maxMap);

    }

    /**
     * 将一个集合转成一个map：Collectors.toMap
     * 有用
     */
    @Test
    public void testToMap() {
        Map<Integer, A> map = objList.stream().collect(Collectors.toMap(A::getAge, Function.identity()));
        map.forEach((k, v) -> {
            System.out.print("key: " + k + "   ");
            System.out.print("value: " + v + "   ");
            System.out.println();
        });
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

    /**
     * min：按给定的规则或者stream中最小的元素
     */
    @Test
    public void min() {
        Optional<A> min = objList.stream().min((first, second) -> first.getAge() - second.getAge() > 0 ? -1 : 1);
        System.out.println(min.orElse(new A()));
    }

    /**
     * max：按给定的规则或者stream中最小的元素
     */
    @Test
    public void max() {
        Optional<A> min = objList.stream().max((first, second) -> first.getAge() - second.getAge() > 0 ? -1 : 1);
        System.out.println(min.orElse(new A()));
    }

    /**
     * 从集合元素中计算出一个总值
     * 在轮回计算中，每次计算出的结果都将作为下一次计算的第一个值(first)
     */
    @Test
    public void reduce() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        // 求和
        Optional<Integer> reduce = list.stream().reduce((first, second) -> {
            System.out.println("first: " + first);
            System.out.println("second: " + second);
            return first + second;
        });
        System.out.println(reduce.get());

        List<String> stringList = Arrays.asList("aaa", "bbb", "ccc");

        // 给定一个初始值再计算总值
        String r1 = stringList.stream().reduce("000", (first, second) -> {
            System.out.println("first: " + first);
            System.out.println("second: " + second);
            return first + second;
        });
        System.out.println(r1);// 000aaabbbccc

        String r2 = stringList.stream().reduce((first, second) -> first + "-" + second).get();
        System.out.println(r2);// aaa-bbb-ccc
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