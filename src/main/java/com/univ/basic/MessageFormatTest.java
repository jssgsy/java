package com.univ.basic;

import org.junit.Test;

import java.text.MessageFormat;
import java.time.LocalDateTime;

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
        String format = MessageFormat.format("hello, {0}, the price is {1}, the date is {2}", "apple", 1.2, LocalDateTime.now());
        System.out.println(format);

        // 对象形式，注意，此时对象需要有toString方式
        String format1 = MessageFormat.format("the obj is {0}", new A1());
        System.out.println(format1);

        // 指定占位符的类型
        // 错误版本
//        MessageFormat.format("hello {0, number}", "abc");
        // 字符串形式的数字也不行
//        MessageFormat.format("hello {0, number}", "2");

        // 正确版本
        String format2 = MessageFormat.format("hello {0, number}", 2);
        System.out.println(format2);

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

