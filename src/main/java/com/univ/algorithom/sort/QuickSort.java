package com.univ.algorithom.sort;

import java.util.Arrays;

import org.junit.Test;

import com.univ.algorithom.util.ArrayUtil;

/**
 * 快速排序：分治思想
 * 相较于归并排序的优点：不需要额外的辅助空间。同时时间复杂度亦为nlog(n)
 *
 * @author univ
 * 2022/4/27 9:09 上午
 */
public class QuickSort {

    /**
     * 打印出排序过程
     */
    @Test
    public void testQuickSortWithTraceInfo() {
        int[] arr = { 15, 9, 20, 16, 18, 7, 30, 4, 15 };
        quickSort2(arr, 0, arr.length - 1);
        System.out.println("\n最终结果为:" + Arrays.toString(arr));
    }

    @Test
    public void testQuickSort() {
        int[] arr = { 15, 9, 20, 16, 18, 7, 30, 4, 15 };
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后结果为:" + Arrays.toString(arr));
    }

    /**
     * 与{@link QuickSort#quickSort(int[], int, int)}等价，只是多了一些打印信息
     * @param arr
     * @param lo
     * @param hi
     */
    public void quickSort2(int[] arr, int lo, int hi) {
        System.out.print("输入数组为：");
        print(arr, lo, hi);
        System.out.println();
        if (lo >= hi) {
            System.out.println("此数组不能再细分了");
            return ;
        }
        int partition = partition2(arr, lo, hi);
        quickSort2(arr, lo, partition - 1);
        quickSort2(arr, partition + 1, hi);
    }

    /**
     * 与{@link QuickSort#partition(int[], int, int)}等价，只是多了打印信息
     * @param arr
     * @param lo
     * @param hi
     */
    public int partition2(int[] arr, int lo, int hi) {
        int v = arr[lo];
        int gt = hi;
        int i = lo + 1;
        while (i <= gt) {
            if (arr[i] <= v) {
                i++;
            } else {
                ArrayUtil.exec(arr, i, gt);
                gt--;
            }
        }
        ArrayUtil.exec(arr, lo, i - 1);
        System.out.print("数组被切分成：" );
        print(arr, lo, i - 2);
        System.out.print(" | " + arr[i - 1] + " | ");
        print(arr, i, hi);
        System.out.println();
        return i - 1;
    }

    private void print(int[] arr, int from, int to) {
        for (int i = from; i <= to; i++) {
            System.out.print(" " + arr[i] + " ");
        }
    }

    /**
     * 快速排序
     * @param arr
     * @param lo
     * @param hi
     */
    public void quickSort(int[] arr, int lo, int hi) {
        if (lo >= hi) { // 别忘了递归终止条件
            return;
        }
        int partition = partition(arr, lo, hi);
        quickSort(arr, lo, partition - 1);
        quickSort(arr, partition + 1, hi);
    }

    /**
     * 将arr[lo]放入应该在的位置，并返回此位置索引。
     * 实现参考了{@link ThreeWayPartition#threeWayPartition(int[], int, int)}
     *
     * @param arr
     * @param lo
     * @param hi
     */
    public int partition(int[] arr, int lo, int hi) {
        // 以arr[0]来进行切分
        int v = arr[lo];
        int gt = hi;    // gt(不包含)右边的均大于arr[lo]，初始假设没有值比arr[lo]大
        int i = lo + 1; // 遍历指针, 小于i的索引的元素均【小于等于】arr[lo]
        while (i <= gt) {   // i == gt时还不能结束循环
            if (arr[i] <= v) {   // 发现一个更小的元素，往后遍历
                i++;
            } else {    // 发现一个更大的元素，与gt交换
                ArrayUtil.exec(arr, i, gt);
                gt--;
                // 注，这里i不能自增，因为从gt处拿过来的值不一定比arr[lo]大，其未知
            }
        }
        ArrayUtil.exec(arr, lo, i - 1);
        return i - 1;
    }

}
