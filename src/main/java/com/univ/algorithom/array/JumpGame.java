package com.univ.algorithom.array;

import java.util.Arrays;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * 力扣：跳跃游戏
 * @see <a href='https://leetcode.cn/problems/jump-game/'>官网</a>
 * @author univ
 * 2022/5/11 8:57 上午
 */
public class JumpGame {

    @Test
    public void test() {
        int[] arr1 = { 2, 3, 1, 1, 4 };
        int[] arr2 = { 3,2,1,0,4 };
        for (int[] arr : Arrays.asList(arr1, arr2)) {
            System.out.println("数组" + JSONObject.toJSONString(arr) + "是否能从第一个位置跳到末尾： " + canJumpToTheEnd(arr));
        }
    }

    /**
     * 力扣55：跳跃游戏
     * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个下标。
     *
     * 如输入nums = [2,3,1,1,4]，则返回true
     * 如输入nums = [3,2,1,0,4]，则返回false;
     *
     * 题目意思为，从位置0开始，是否能通过连续的跳跃到达末尾；
     * 思路：
     *  1. 以初始位置(0)为例，假如值为2，则从此位置最多可以往前跳2步，即从此位置可以跳到[0, 2]中间的任意一个节点；
     *  2. 比如位置1处的值为3，则表示从此节点最多可以往前跳3步，即从此位置可以跳到[1, 1 + 3 = 4]处中间的任意一个节点；
     *  3. 循环一次数组，只要最终能到达最远的位置大于末尾即说明可以达到；
     *
     * 重点(难点)：何时能判定到达不了；
     *  遍历过程中，如果当前位置(i)已经大于等于i(包含)之前的所有元素能跳跃最远的地方，则说明始终到达不了；
     *  如上面的第二个数组，遍历到第4个元素0时，前面4个元素3，2，1，0最远也只能跳到4处，说明没法往前跳了，因此肯定到达不了；
     *
     * @param arr
     * @return
     */
    boolean canJumpToTheEnd(int[] arr) {
        if (null == arr) {
            return false;
        }
        int maxFar = 0; // 能跳到最远的地方(下标)
        for (int i = 0; i < arr.length; i++) {
            // i + arr[i]：位置i处的节点最多可以到达的位置
            maxFar = Math.max(maxFar, i + arr[i]);
            if (maxFar >= arr.length - 1) {// 能跳的最远位置已经大于末尾了则说明可以到达
                return true;
            }

            if (i >= maxFar) {  // 难点：i及i以前的所有位置最远也只能跳到i处，说明所有[0, i]处的元素没法跳到i的后面，显然到达不了末尾
                return false;
            }
        }
        return false;
    }
}
