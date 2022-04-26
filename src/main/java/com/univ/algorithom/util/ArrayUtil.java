package com.univ.algorithom.util;

/**
 * @author univ
 * 2022/4/26 3:44 下午
 */
public class ArrayUtil {

    /**
     * 交换两个位置处的值
     * @param arr
     * @param i
     * @param j
     */
    public static void exec(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
