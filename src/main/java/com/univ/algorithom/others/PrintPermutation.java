package com.univ.algorithom.others;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * 《剑指offer》面试题28：字符串的排列
 * 输入一个字符串，打印出所有字符的所有排列
 * 如abc的全排列为：abc,acb,bac,bca,cab,cba
 *
 * @author univ
 * 2022/5/3 9:54 下午
 */
public class PrintPermutation {

    @Test
    public void test() {
        Queue<String> queue = new ArrayDeque<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("d");
        queue.add("e");
        queue.add("f");
        queue.add("g");
        queue.add("h");
        List<String> collect = collectPermutation(queue);
        System.out.println("size: " + collect.size() + ", " + JSONObject.toJSONString(collect));
    }

    /**
     * 收集队列中所有字符的全排列。
     *
     * 思路：
     * 1. 首先由排列组合可知，n个数字的全排列种类总共为n的阶乘，即n!；
     * 2. 但这里要求的是打印出所有的排列(不能类似斐波那契求和)；
     * 3. 还是由排列组合知识可知，有f(abcd) = af(bcd) + bf(acd) + cf(abd) + df(abc)，而很明显这就是一个递归；
     *  3.1 可知，需要将abcd拆分成bcd、acd、abc等，因此想到用队列来存储这些字符；便于拆分；
     *  3.2 由上述2知，要求是打印，不好在递归过程中进行(递归的是子串，没法打印)，因此想到直接把全排列收集起来；
     *
     * @param queue
     * @return
     */
    public List<String> collectPermutation(Queue<String> queue) {
        if (queue.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> resultList = new ArrayList<>();
        if (queue.size() == 1) {
            resultList.add(queue.peek());// 注：此时不要poll
            return resultList;
        }
        int i = 1;
        int size = queue.size();
        while (i <= size) {
            String ele = queue.poll();
            List<String> list = collectPermutation(queue);  // 递归
            list.forEach(t -> { // 将此元素与子串相加得到这一轮的所有结果
                resultList.add(ele + t);
            });
            queue.add(ele);
            i++;
        }
        return resultList;
    }
}
