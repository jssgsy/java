package com.univ.algorithom.sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * 归并排序：分治思想，一个大数组不断细分成两个子数组，然后不停将两个小数组合并成有序，最终形成大数组。
 * 时间复杂度：nlog(n)
 * 空间复杂度：n(需要借助额外的数组空间)
 *
 * @author univ
 * 2022/4/27 2:57 下午
 */
public class MergeSort {

    @Test
    public void testMergeSort() {
        int[] arr = { 1, 34, 23, 10, 6, 14, 24, 9, 4, 17 };
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public void mergeSort(int[] arr, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSort(arr, lo, mid);
        mergeSort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    @Test
    public void testMerge() {
        int[] arr = { 1, 3, 6, 10, 14, 19,  1, 2, 4, 7, 8, 11, 12};
        merge(arr, 0, 5, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    /**
     * 合并，其中[lo, mid]已有序，[mid + 1, hi]已有序
     * @param arr
     * @param lo
     * @param mid
     * @param hi
     * @return
     */
    public void merge(int[] arr, int lo, int mid, int hi) {
        int len1 = mid - lo + 1;
        int len2 = hi - mid;
        int len = len1 + len2;
        int[] tmp = new int[len];

        int k = 0;  // 用来给结果数组赋值。注，不能从lo开始，因为结果数组赋值始终从0开始
        int i = lo;
        int j = mid + 1;
        while (k < len) {
            if (i > mid || j > hi) {
                break;
            }
            if (arr[i] <= arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }
        if (i > mid) {
            while (j <= hi) {
                tmp[k++] = arr[j++];
            }
        }
        if (j > hi) {
            while (i <= mid) {
                tmp[k++] = arr[i++];
            }
        }
        // 复制回原数据
        for (i = lo; i <= hi; i ++) {
            arr[i] = tmp[i - lo];
        }
    }

    @Test
    public void testMergeTwoArr() {
        int[] arr1 = { 1, 3, 6, 10, 14, 19 };
        int[] arr2 = { 1, 2, 4, 7, 8, 11, 12, 15 };
        int[] arr = mergeTwoArr(arr1, arr2);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 将两个有序数组合并成一个大的有序数组
     *
     * 核心特点：需要额外借助一个数组空间(这也不如快速排序的原因所在)
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] mergeTwoArr(int[] arr1, int[] arr2) {
        int len1 = arr1.length;
        int len2 = arr2.length;
        // 借助一个额外的数组空间
        int[] arr = new int[len1 + len2];
        int k = 0;  // 用来给结果数组赋值
        int i = 0;  // 用来遍历arr1
        int j = 0;  // 用来遍历arr2
        while (k < len1 + len2) {
            if (i >= len1 || j >= len2) {
                break;
            }
            if (arr1[i] <= arr2[j]) {
                arr[k] = arr1[i];
                k++;
                i++;
            } else {
                arr[k] = arr2[j];
                k++;
                j++;
            }
        }
        if (i >= len1) {    // 说明arr1已经全部处理完
            while (j < len2) {
                arr[k] = arr2[j];
                k++;
                j++;
            }
        }
        if (j >= len2) {
            while (i < len1) {
                arr[k] = arr1[i];
                i++;
                k++;
            }
        }
        return arr;
    }
}
