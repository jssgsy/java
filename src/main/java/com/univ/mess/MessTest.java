package com.univ.mess;

import java.util.Enumeration;
import javax.naming.CompositeName;
import javax.naming.InvalidNameException;
import javax.naming.Name;

import org.junit.Test;

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
    public void test1() throws InterruptedException, InvalidNameException {

        Name objectName = new CompositeName("java:comp/env/jdbc");
        Enumeration<String> elements = objectName.getAll();
        while(elements.hasMoreElements()) {
            System.out.println(elements.nextElement());
        }


    }

}


