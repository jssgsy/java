package com.univ.algorithom.leetcode;

import java.util.Arrays;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * 力扣42：接雨水
 * @see <a href='https://leetcode.cn/problems/trapping-rain-water/'>官网</a>
 * @author univ
 * 2022/5/11 2:40 下午
 */
public class NumberOfRain {

    /**
     * {@link #numberOfRain(int[])} ()}
     */
    @Test
    public void testNumberOfRain() {
        int[] arr1 = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        int[] arr2 = { 4, 2, 0, 3, 2, 5 };
        for (int[] arr : Arrays.asList(arr1, arr2)) {
            System.out.println("数组" + JSONObject.toJSONString(arr) + " 可接雨水：" + numberOfRain(arr));
        }
    }

    /**
     * 思路：其实还是分解思想的应用：能接的所有雨水即每个格上能接雨水量的总和
     * 遍历数组(每一格)，【依次计算当前格上可以接多少雨水】(一定要感性认识)。
     * 进而转化为：
     *  Q：如何计算当前格上能接多少雨水？
     *  A：如果当前格比左边最高的格式和右边最高的格式都低，说明此格可以接雨水；
     *
     * @param arr
     * @return
     */
    public int numberOfRain(int[] arr) {
        int count = 0;
        if (null == arr || arr.length <= 2) {   // 小于2肯定不能接雨水
            return count;
        }
        int leftMax = arr[0];   // 迭代时，当前元素左边所有元素中的最大值
        int rightMax = 0;   // 迭代时，当前元素右边所有元素中的最大值
        int indexOfRightMax = 0;    // 迭代时，当前元素右边所有元素中最大值的索引
        for (int i = 1; i < arr.length - 1; i++) {
            if (i >= indexOfRightMax) { // 加这个是一个小优化，没必要每轮都去算右侧的最大值，有必要时才去
                indexOfRightMax = i + 1;    // 容易遗漏点：需要重新计算时需要重置，不能用以前的值
                rightMax = arr[indexOfRightMax];
                for (int j = i + 2; j < arr.length; j++) {  // 找到当前节点(i)右边所有元素中的最大值；
                    if (arr[j] > rightMax) {
                        rightMax = arr[j];
                        indexOfRightMax = j;
                    }
                }
            }
            int min = Math.min(leftMax, rightMax);
            if (arr[i] < min) { // 如果当前格的高度比左边和右边最高的元素都要小(被夹在中间)说明一定能接雨水；
                count = count + (min - arr[i]); // (min - arr[i])就是此格能接的雨水量
            }
            leftMax = Math.max(leftMax, arr[i]);
        }
        return count;
    }
}
