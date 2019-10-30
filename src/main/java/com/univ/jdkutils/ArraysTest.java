package com.univ.jdkutils;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author univ
 * @date 2019/10/30 7:35 PM
 * @description
 * 1. fill：将数组元素填充为同一个值
 * 2. setAll：是fill方法的超体，可按一定规则(重点：基于数组下标)填充数组元素
 * 3. sort：对数组元素排序，用自定义算法
 * 4. parallelPrefix：一般用在大数组中（有更好的性能），对数组中的每个元素，给定两个元素，第一个元素不变，第二个元素的值更新为lambda计算后的值
 */
public class ArraysTest {
    
    @Test
    public void binarySearch() {
        
        Integer[] arr1 = {1, 2, 3};
        int index = Arrays.binarySearch(arr1, 2);
        System.out.println(index);// 1

    }

    /**
     * 将数组按照某种规则(重点：基于数组下标)填值
     */
    @Test
    public void setAll() {

        Integer[] arr1 = new Integer[5];
        // 将数组元素都置为3
        Arrays.setAll(arr1, t -> 3);
        System.out.println(Arrays.toString(arr1));// [3, 3, 3, 3, 3]
        Arrays.fill(arr1, 4);
        System.out.println(Arrays.toString(arr1));// [4, 4, 4, 4, 4]

        // 将数组元素置为其下标值
        Arrays.setAll(arr1, t -> t);
        System.out.println(Arrays.toString(arr1));// [0, 1, 2, 3, 4]
    }

    @Test
    public void sort() {
        Integer[] arr = { 10, 32, 2, 43, 3, 8, 19, 9 };
        // 自然排序：升序
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));// [2, 3, 8, 9, 10, 19, 32, 43]

        // 倒序
        Arrays.sort(arr, (first, second) -> {
            if (first < second) {
                return 1;
            } else if (first == second) {
                return 0;
            } else {
                return -1;
            }
        });
        System.out.println(Arrays.toString(arr));// [43, 32, 19, 10, 9, 8, 3, 2]
    }

    @Test
    public void parallelPrefix() {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        Arrays.parallelPrefix(arr, (x, y) -> {
            if (x < y)
                return x + y;
            else
                return x - y;
        });
        System.out.println(Arrays.toString(arr));//[1, 3, 0, 4, 9, 3, 10, 2, 11, 1]
        
    }
}
