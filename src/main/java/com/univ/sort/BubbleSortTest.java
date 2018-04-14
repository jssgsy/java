package com.univ.sort;

/**
 * Univ
 * 16/10/12 08:25
 */

import org.junit.Test;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSortTest {


    @Test
    public void test1(){

        int[] arr = {40,7,7, 3, 6, 5};
        bubbleSort(arr,1,5);
        System.out.println(Arrays.toString(arr));


    }

    /**
     * 冒泡排序
     *
     * 关于索引i的取值:
     *  一趟下来，最大的元素就位(放到末尾)。i表示总共需要的趟数。如两个元素只需要一趟即可，
     *  三个元素只需要两趟，四个元素需要三趟...,len个元素需要(len-1)趟。
     *  所以i的索引是从0而不是low开始。
     *
     * 关于索引j的取值:
     *  因为要比较a[j+1]与a[j]的大小,所以j+1不能越界,所以j+1<high-i
     * @param a 待排序的数组
     * @param low   范围从索引low处开始,inclusive
     * @param high  范围到所以high结束,inclusive
     */
    private void bubbleSort(int[] a, int low, int high) {
        int len = high - low + 1;
        for (int i = 0; i < len - 1; i++) {//外围总共只需要循环len-1次(所以是小于号)
            for (int j = low; j <= high - 1 - i; j++) {//这里j的下标是重点
                if (a[j+1] < a[j]) {
                    int temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                }
            }
        }
    }
}
