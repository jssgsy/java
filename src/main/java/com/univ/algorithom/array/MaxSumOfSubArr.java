package com.univ.algorithom.array;

import java.util.Arrays;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * 《剑指offer》面试题31：连续子数组的最大和<br>
 * 输入一个整形数组，数组里有正数也有负数。数组中一个或连续的多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为O(n).
 *
 * @author univ
 * 2022/5/4 4:33 下午
 */
public class MaxSumOfSubArr {

    @Test
    public void testMaxSumOfSubArr() {
        int[] arr0 = { -3, -1};
        int[] arr1 = { -3, -1, -2, 1};
        int[] arr2 = { 3, 10};
        int[] arr3 = { 3, 10, -1};
        int[] arr4 = { 3, 10, -1, 2};
        int[] arr5 = { 3, 10, -4};
        int[] arr6 = { 1, -2, 3, 10, -4, 7, 2, -5};
        int[] arr7 = { 1, -2, 3, 10, -4, -5, 7, 2, -5};
        for (int[] arr : Arrays.asList(arr0, arr1, arr2, arr3, arr4, arr5, arr6, arr7)) {
            System.out.println("输入数组为：" + JSONObject.toJSONString(arr));
            System.out.println("一定准确的值为：" + largestSumOfSubArr(arr) + ",  v2版本的值为：" + largestSumOfSubArr_v2(arr));
            System.out.println();
        }
    }

    /**
     * 求最大子数组和
     * 思路1(最直观，非最优)：遍历数，即枚举所有的子数组，然后分别计算其和，最终挑出其最大值。
     *  长度为n的数组，总共有 n * (n + 1) / 2 种子数组
     *
     * @param arr
     * @return 最大子数组的和
     */
    int largestSumOfSubArr(int[] arr) {
        int max = arr[0]; // 注，第一个数可能是负数，因为不能设为0
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sumOfSubArr = 0;
                for (int k = i; k <= j; k++) {  // 求某个子数组的和
                    sumOfSubArr += arr[k];
                }
                if (sumOfSubArr > max) {
                    max = sumOfSubArr;
                }
            }
        }
        return max;
    }

    /**
     * 求最大子数组和(很不直观)
     * 思路2：主要是对研究加一个负数时的规律
     *
     * 补充：
     * 1. 虽然实现了，但很不直观，同时细节也较多，有多种情况要考虑，容易遗漏
     * 2. 虽然完全不涉及递归，但思路上有些递归的意味在，体现在为何得到的就是最大子数组和，为何不会有其它子数组的和更大。
     * @param arr
     * @return
     */
    int largestSumOfSubArr_v2(int[] arr) {
        int sumOfThisSubArr = arr[0];   // 此轮处理的连续的子数组的和，无脑加
        int largestSumOfThisSubArr = arr[0];    // 此轮处理的连续的子数组的最大值，亦即碰到负数前的和
        int largestSum = arr[0];    // 所有子数组中最大的和，即最终要求的结果
        for (int i = 1; i < arr.length; i++) {
            int ele = arr[i];
            if (ele <= 0) {     // 是负数
                sumOfThisSubArr += ele;
                /**
                 * 重点4：加一个负数，则sumOfThisSubArr一定比largestSumOfThisSubArr小，所以largestSumOfThisSubArr不用动。
                 * 但若largestSum是个负数，则再来一个负数，则最大的值应为二者中的大值
                 */
                largestSum = Math.max(largestSum, ele);
            } else {    // 是正数
                sumOfThisSubArr += ele;
                /**
                 * 重点1： 为true表明此轮子数组加上ele后还没有ele本身大(ele之前有负数)，说明此轮数组不可能是和最大的子数组，因为其和都小于ele了。
                 * 所以以ele为头，重新开始一轮子数组，要做的事就是重置sumOfThisSubArr、largestSumOfThisSubArr与largestSum三个值
                 */
                if (sumOfThisSubArr <= ele) {
                    /**
                     * 重点3：要放在下句前面，因为虽然此轮子数组结束了，但其最大值可能大于缓存的最大值
                     * 所以在重新赋值largestSumOfThisSubArr前要先计算出largestSum
                     *
                     */
                    largestSum = Math.max(largestSumOfThisSubArr, largestSum);
                    sumOfThisSubArr = largestSumOfThisSubArr = ele;// 以ele重新开头了，那sumOfThisSubArr与largestSumOfThisSubArr显然就是ele的值
                } else {
                    /**
                     * 重点2：加上一个数却还小于之前此轮子数组中最大的和，说明加上此数后和更小了，即说明此元素不能加入到最大和数组中了。
                     * 此时要作的事就是新开一个子数组，当然是以当前元素为头，然后暂存此前的最大子数组的和
                     */
                    if (sumOfThisSubArr < largestSumOfThisSubArr) {// 要重新开一个子数组
                        largestSum = Math.max(largestSumOfThisSubArr, largestSum);
                        sumOfThisSubArr = largestSumOfThisSubArr = ele;
                    } else {
                        largestSumOfThisSubArr = sumOfThisSubArr;
                        // 注：这里largestSum不变
                    }
                }
            }
        }
        return Math.max(largestSumOfThisSubArr, largestSum);
    }
}
