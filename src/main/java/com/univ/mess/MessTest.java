package com.univ.mess;

import org.junit.Test;

import java.io.*;
import java.util.*;


/**
 * created by Univ
 * 16/6/18 14:28
 */
public class MessTest {

    /**
     * 栈的常用方法:
     * push();//入栈,Pushes an item onto the top of this stack.
     * pop()://出栈,并返回出栈元素
     * peek()://获取栈顶元素,但不出栈
     */
    @Test
    public void test1() {

        Stack<Integer> stack = new Stack<>();
        stack.push(12);
        stack.push(14);
        stack.push(16);

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();//出栈,并返回出栈元素
            System.out.println(pop);
        }
    }

    @Test
    public void test2() throws IOException {


        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 22, 30, 5);

        Collections.sort(list);
        System.out.println(list);










    }






}


