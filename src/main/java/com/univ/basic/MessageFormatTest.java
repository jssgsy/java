package com.univ.basic;

import java.text.MessageFormat;

import org.junit.Test;

/**
 * @author univ
 * @datetime 2018/12/7 9:11 PM
 * @description MessageFormat是java.text包下的工具类，用来格式化字符串
 */
public class MessageFormatTest {
    @Test
    public void test() {
        /*
        占位符用{0},{1}等形式表示
         */
        String format = MessageFormat.format("hello, {0}, hello {1}", "apple", "orange");
        System.out.println(format);


        // 对象形式，注意，此时对象需要有toString方式
        String format1 = MessageFormat.format("the obj is {0}", new A1());
        System.out.println(format1);
    }
}

class A1 {
    private String name = "univ";
    private int age = 20;

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

