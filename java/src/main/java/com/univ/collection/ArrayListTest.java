package com.univ.collection;

/**
 * Univ
 * 16/9/17 16:46
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 测试jdk中ArrayList的常用方法
 */
public class ArrayListTest {

    /**
     * ArrayList的toArray的正确用法
     *      ArrayList提供了两个toArray()方法:
     *      1. public <T> T[] toArray(T[] a) :这是正确的用法,推荐使用
     *      2. public Object[] toArray();不推荐使用,Object[]不能强制向下转换成其他类型数组
     */
    @Test
    public void test1() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1);
        Integer[] objects = list.toArray(new Integer[0]);//正确的用法
        System.out.println(Arrays.toString(objects));

       /* Integer[] arr = (Integer[]) list.toArray();//这是错误的用法,编译通过,运行抛异常
        System.out.println(Arrays.toString(arr));*/

    }
}
