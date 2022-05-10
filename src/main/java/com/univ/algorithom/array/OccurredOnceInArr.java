package com.univ.algorithom.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.univ.algorithom.basic.BitOperator;

/**
 * 《剑指offer》面试题40：数组中只出现一次的数字
 * @author univ
 * 2022/5/10 6:25 下午
 */
public class OccurredOnceInArr {

    @Test
    public void testOccurredOnce() {
        int[] arr1 = { 1 };
        int[] arr2 = { 1, 1, -2 };
        int[] arr3 = { -1, 1, 4, 1, -1 };
        int[] arr4 = { 1, 2, 3, 4, 4, 3, 2, 1, 12 };
        for (int[] arr : Arrays.asList(arr1, arr2, arr3, arr4)) {
            System.out.println("数组" + JSONObject.toJSONString(arr) + "中只出现一次的数为：" + occurredOnce(arr));
        }
    }

    /**
     * 数组中只出现一次的唯一一个数
     * 给定一个整形数组，其中除了一个数之外，其它所有数都是成对出现，找出这个只出现一次的数。
     *
     * 思路：是位运算的一种应用：
     *  任何数与自己作异或运算，结果均为0！
     *  所以可以直接将数组中所有的元素求位运算，最后得到的结果就是只出现一次的数；
     *
     * @param arr
     * @return
     */
    public int occurredOnce(int[] arr) {
        if (null == arr) {
            return -1;
        }
        if (arr.length == 1) {
            return arr[0];
        }

        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result = result ^ arr[i];
        }
        return result;
    }

    @Test
    public void testOccurredOnce_v2() {
        int[] arr1 = { 1, 2, 3, 3};
        int[] arr2 = { 1, 1, -2, 3, 4, -2 };
        for (int[] arr : Arrays.asList(arr1, arr2)) {
            System.out.println("数组" + JSONObject.toJSONString(arr) + "中只出现一次的数为：" + JSONObject.toJSONString(occurredOnce_v2(arr)));
        }
    }

    /**
     * 数组中只出现一次的两个数。
     * 给定一个整形数组，其中除了两个数外，其它所有数都是成对出现，找出这两个只出现一次的数。
     * @param arr
     * @return 只出现一次的两个数，用集合来收集一下
     */
    public List<Integer> occurredOnce_v2(int[] arr) {
        if (null == arr || 1 == arr.length) {
            return Collections.emptyList();
        }

        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result = result ^ arr[i];
        }

        int index = BitOperator.indexOfFirst1FromLeft(result);
        int result1 = 0;
        int result2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if (BitOperator.isBit1(arr[i], index)) {
                result1 = result1 ^ arr[i];
            } else {
                result2 = result2 ^ arr[i];
            }
        }
        return Arrays.asList(result1, result2);
    }

    @Test
    public void test() {
        System.out.println(BitOperator.isBit1(2, 30));
    }
}
