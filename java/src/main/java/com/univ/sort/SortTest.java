package com.univ.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Univ
 * 16/7/28 20:34
 */
public class SortTest {

    /**
     * 基本数据类型的测试。
     *
     * Arrays中提供了基本数据类型数组排序:Arrays.sort(arr1,arr2)
     */
    @Test
    public void basicTypeSort(){
        //int 类型数组排序
        int[] arrInt = {3, 5, 1, 39, 34, -3};
        Arrays.sort(arrInt);
        System.out.println(Arrays.toString(arrInt));

        //double类型数组排序
        double[] arrDouble = {2.4, 1.2, 5, 36.2, 34.24, -3.5};
        Arrays.sort(arrDouble);
        System.out.println(Arrays.toString(arrDouble));

        //String类型数组排序
        String[] arrStr = {"abd", "aefa", "feaf", "fjeifa", "iu", "aa", "iy", "etf"};
        Arrays.sort(arrStr);
        System.out.println(Arrays.toString(arrStr));
    }

    /**
     * 基本类型数组的比较判断。
     * Arrays中提供了基本数据类型数组排序:Arrays.equals(arr1,arr2)
     */
    @Test
    public void basicTypeArrEquals(){
        //int 类型数组相等比较
        int[] arrInt1 = {3, 5, 1, 39, 34, -3};
        int[] arrInt2 = {3, 39, 34, -3};
        System.out.println(Arrays.equals(arrInt1,arrInt2));


        //String类型数组排序
        String[] arrStr1 = {"abd", "fjeifa", "iu", "aa", "iy", "etf"};
        String[] arrSt2 = {"abd", "aefa", "feaf", "fjeifa", "iu", "aa", "iy", "etf"};
        System.out.println(Arrays.equals(arrStr1,arrSt2));
    }
}
