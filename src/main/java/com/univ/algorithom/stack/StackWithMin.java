package com.univ.algorithom.stack;

import java.util.Stack;

import org.junit.Test;

/**
 * 要求：定义一个栈，要求包含min(取最小元素)方法，且要求pop、push、pop方法的时间复杂度都是O(1)
 * 思路1(直观)：将最小元素暂存，但当pop掉的正好是最小元素时，后续操作没法进行，又或者每个pop时再求一遍最小元素，但此时pop时间复杂度就不是O(1)了。此思路否决。
 *
 * 思路2(智慧)：由思路1可知，只暂存一个最小元素是不可行的，因此想到暂存所有最小元素。再转换下即是：
 *  引入一个暂且栈minStack，其栈顶元素就是当前栈中的最小元素！！！
 *  接下来就是如何给minStack赋值的问题。见{@link #push(Comparable)} ()}与{@link #pop()}方法
 *
 *
 * @author univ
 * 2022/5/2 3:30 下午
 */
public class StackWithMin<E extends Comparable<E>> {

    private Stack<E> valueStack = new Stack<>();

    /**
     * 特别注意此含义：每个栈顶元素都是当前同等大小valueStack中的最小值
     */
    private Stack<E> minStack = new Stack<>();

    public E min() {
        if (!isEmpty()) {
            return minStack.peek();
        }
        System.out.println("栈为空了，设默认最小值为0");
        return null;
    }

    public void push(E element) {
        valueStack.push(element);
        // 时刻保持与minStack有相同的元素
        if (minStack.isEmpty()) {   //
            minStack.push(element);
            return;
        }
        E latestMin = minStack.peek();
        // 核心：如果新push的值比最小值还小，说明加入此元素后最小值就是此元素
        if (element.compareTo(latestMin) < 0) {
            minStack.push(element);
        } else {
            // 否则，加入此元素后最小值还是之前的最小值
            minStack.push(latestMin);
        }
    }

    public E pop() {
        E pop = valueStack.pop();
        // 同时minStack也需要出栈，保持其含义：此时的valueStack中的最小元素
        minStack.pop();
        return pop;
    }

    public boolean isEmpty() {
        return valueStack.isEmpty();
    }

    @Test
    public void test() {
        StackWithMin<Integer> stackWithMin = new StackWithMin<>();
        stackWithMin.push(6);
        stackWithMin.push(7);
        stackWithMin.push(4);
        stackWithMin.push(19);
        System.out.println("最小元素为：" + stackWithMin.min());
        stackWithMin.push(34);
        stackWithMin.push(3);
        System.out.println("最小元素为：" + stackWithMin.min());

        stackWithMin.pop();
        System.out.println("最小元素为：" + stackWithMin.min());

        stackWithMin.push(8);
        System.out.println("最小元素为：" + stackWithMin.min());

        while (!stackWithMin.isEmpty()) {
            stackWithMin.pop();
            System.out.println("最小元素为：" + stackWithMin.min());
        }
    }

}
