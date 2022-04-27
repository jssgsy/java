package com.univ.algorithom.sort;

import java.util.Arrays;

import org.junit.Test;

import com.univ.algorithom.util.ArrayUtil;

/**
 * 将一个数组划分为三个区域，小于arr[0]部分，等于arr[0]部分，大于arr[0]部分
 * 注：
 * 1. 小于arr[0]的部分并不要求有序，大于arr[0]的部分也不要求有序；
 * 2. 之所以研究此模型，是因为在快速排序时，如果元素有较多重复元素，则可加快排序速度
 *
 * @author univ
 * 2022/4/26 3:38 下午
 */
public class ThreeWayPartition {

    @Test
    public void test() {
        int[] arr = {10, 2, 19, 10, 14, 12, 8, 20, 6, 17};
        threeWayPartition(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 将数组三向切分
     * @param arr
     * @param lo
     * @param hi
     */
    public void threeWayPartition(int[] arr, int lo, int hi) {
        // 以arr[0]来进行切分
        int v = arr[lo];
        // 切成三份，则需要两个指针作区分
        int lt = lo;    // lt(不包含)左边的均小于arr[lo]，初始假设没有值比arr[lo]小
        int gt = hi;    // gt(不包含)右边的均大于arr[lo]，初始假设没有值比arr[lo]大
        // 注：由上面定义可知，[lt, gt]内的元素均等于arr[lo]
        int i = lo + 1; // 遍历指针
        while (i <= gt) {   // i == gt时还不能结束循环
            if (arr[i] < v) {   // 发现一个更小的元素，则与lt处交换
                ArrayUtil.exec(arr, i, lt);
                lt++;
                i++;
            } else if (arr[i] > v) {    // 发现一个更大的元素，则与gt处交换
                ArrayUtil.exec(arr, i, gt);
                gt--;
                // 注，这里i不能自增，因为从gt处拿过来的值不一定比arr[lo]大，其未知
            } else {
                i++;
            }
        }
        System.out.println("索引小于" + lt + "的值均小于" + v);
        System.out.println("索引大于" + gt + "的值均大于" + v);
    }


}
