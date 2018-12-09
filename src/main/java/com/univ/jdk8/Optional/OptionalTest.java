package com.univ.jdk8.Optional;

import java.util.Optional;

import org.junit.Test;

/**
 * @author univ
 * @datetime 2018/12/9 2:00 PM
 * @description 学习java.util中的Optional工具类
 * Optional工具类作用：简化及增强if-else语法的书写
 *
 * 核心：
 *  1. Optional将实际的值给包装了一下；
 *  2. 三种产生Optional的方法：
 *      Optional.ofNullable(v)：v可以为null
 *      Optional.of(v)：v不可以为null，否则npl
 *      Optional.empty()：一般不会显示调用，除非是返回optional对象
 *  3. Optional的一大好处是，尽管链式调用，不用担心NPL
 *
 */
public class OptionalTest {

    @Test
    public void test1() {
        // 原始写法
        Integer i = null;
        if (i == null) {
            System.out.println("I is null");
        }

        Optional<Integer> optional = Optional.ofNullable(i);
        // 只有当i不为null时，lambda表达式才会被执行
        optional.ifPresent((t) -> System.out.println("i is not null"));

    }

    @Test
    public void orElse() {
        Integer i = 1;
        // i不为null则返回i，否则返回默认值10
        Integer i1 = Optional.ofNullable(i).orElse(10);
        System.out.println(i1);
        
        // 等价于
        /*if (i == null) {
            return 10;
        } else {
            return i;
        }*/

        /**
         * orElseGet方法
         * 与orElse方法一样，只是这里的“默认值”可以通过函数来返回，可通过更细的粒度返回
         */
        Integer i2 = Optional.ofNullable(i).orElseGet(() -> 10);
        System.out.println(i2);

        /**
         * orElseThrow方法
         * 与orElse方法一样，只是当i不存在时抛出一个异常
         */
        Optional.ofNullable(i).orElseThrow(() -> new NullPointerException());
    }
    
    @Test
    public void filter() {
        Integer i = null;
        /*
        注意，filter，map等方法返回的仍然是Optional对象
        filter:如果i不为null且满足条件，则返回此optional，否则返回空optional
         */
        Optional<Integer> optional = Optional.ofNullable(i).filter(t -> t % 2 == 0);
        System.out.println(optional.orElse(10000));

        /**
         * 注意，调用map方法时如果Optional内部的值为null，则map会返回empty optional对象
         * Optional的一大好处是，尽管链式调用，不用担心NPL
         */
        Integer i2 = Optional.ofNullable(i).map(t -> t * 2).orElse(10000);
        System.out.println(i2);

        // 还有很多其它的方法，慢慢探索
    }
}
