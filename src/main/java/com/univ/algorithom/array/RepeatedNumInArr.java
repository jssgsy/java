package com.univ.algorithom.array;

import java.util.Arrays;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.univ.algorithom.util.ArrayUtil;

/**
 * 《剑指offer》面试题51：数组中重复的数字<br>
 *  在一个长度为n的数组里的所有数字都在0到n-1的范围内。数组中某些数字是重复的，请找出数组中任意一个重复的数字。
 *  例如给定数组{2, 3, 1, 0, 2, 5, 3}，则对应的输出应是重复的数字2或者3
 *
 *  参见：RepeatedNumInArr.png
 *
 * @author univ
 * 2022/5/4 4:35 下午
 */
public class RepeatedNumInArr {

    @Test
    public void test() {
        int[] arr1 = { 2, 3, 1, 0, 2, 5, 3 };
        int[] arr2 = { 2, 3, 1, 0};
        int[] arr3 = { 2, 3, 1, 0, 0};
        int[] arr4 = { 2};
        for (int[] arr : Arrays.asList(arr1, arr2, arr3, arr4)) {
            System.out.println("输入数组：" + JSONObject.toJSONString(arr) + "，其中重复数字为：" + findArbitrarilyRepeatedNumInArr(arr));
        }
    }

    /**
     * 思路1(自然)：先将数组排序，最快的排序算法时间复杂度为nlog(n)；
     * 思路2(自然)：借助hash表，时间复杂度是log(n)，因为hash表的查找是O(1)，但需要O(n)的空间；
     *
     * 思路3(本题采用：精妙但不想到)：既然nlog(n)的复杂度不行，那肯定要寻找O(n)的复杂度解法。
     *  想到了应由题中的『数字都在0到n-1范围内』入手，但想不到如下几步：
     *  1. 所有数字均可当为数组的下标使用；
     *  2. 如果这个数组中没有重复的数字，那么当数组排序后数字i将出现在下标为i的位置；
     *  3. 根据2再推出，可在循环过程中不停将遇到的数字放到其应在的位置上；
     *
     * @param arr
     * @return -1表示没有重复元素
     */
    int findArbitrarilyRepeatedNumInArr(int[] arr) {
        // 为便于测试，这里以返回值-1表示没有重复数字，实际上应使用异常
        int result = -1;
        if (null == arr || arr.length == 1) {
            return result;
        }
        int i = 0;
        while (i < arr.length) {
            int ele = arr[i];
            if (ele != i) { // 当前元素没在其应在的位置上，则将其放到其应在的位置上
                if (ele == arr[ele]) {  // 发现相同元素了
                    result = ele;
                    break;
                }
                ArrayUtil.exec(arr, i, ele);
            } else {
                i++;
            }
        }
        return result;
    }
}
