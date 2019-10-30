package com.univ.jdkutils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.Test;

/**
 * @author univ
 * @datetime 2018/12/9 2:34 PM
 * @description Objects类是java.util包下的工具类
 * 1. 各种形式的requireNonNull；
 * 2. equals：只用来对象间的比较（不包含数组）；
 * 3. deepEquals：是equals的超体，既能用来对象间的比较，也能用来进行数组是否相等；如果知道是数组类型，则判断是否相等可使用Arrays.equals方法，
 *  所以deepEquals不常用；
 * 4. compare：自定义比较算法
 */
public class ObjectsTest {

    @Test
    public void requireNonNull() {
        Integer i = null;

        /**
         * 如果为null则抛NPL
         */
        Objects.requireNonNull(i);

        /**
         * 在抛NPL时可附加异常信息
         */
        Objects.requireNonNull(i, "i 不能为null值");

        /**
         * 在抛NPL时可附加异常信息，不过这里的异常信息的返回可通过函数来更细粒度的控制
         */
        Objects.requireNonNull(i, () -> "i 不能为null");
    }
    
    @Test
    public void equals(){
        String s1 = "aaa";
        String s2 = "aaa";
        // // 此时arr1与arr2会被当作对象来比较，而不会逐个比较对象的元素是否相等
        boolean success = Objects.equals(s1, s2);
        System.out.println(success);// true

        String[] arr1 = { "aaa", "bbb" };
        String[] arr2 = { "aaa", "bbb" };

        // 在知道是数组时可使用：Arrays.equals
        System.out.println(Objects.equals(arr1, arr2)); // false

        // 注意与equals的区别，会逐个比较对象的元素是否相等
        System.out.println(Objects.deepEquals(arr1, arr2)); // true

        // 补充
        List<Integer> integers = Arrays.asList(1, 3, 4);
        List<Integer> integers2 = Arrays.asList(1, 3, 4);
        System.out.println(Objects.equals(integers, integers2));// true,原因是list有改写equals方法
    }
    
    @Test
    public void compare() {
        int success = Objects.compare(2, 3, (first, second) -> {
            if (first < second) {
                return -1;
            } else if (first == second) {
                return 0;
            } else {
                return 1;
            }
        });
        System.out.println(success);
    }
}
