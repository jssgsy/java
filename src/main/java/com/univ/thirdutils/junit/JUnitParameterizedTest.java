package com.univ.thirdutils.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * created by Univ
 * 16/5/16 19:12
 * 测试Junit的Parameterized运行器(其继承自Suit类)
 *
 * Parameterized运行器的作用是让一个方法可以一次运行多组测试数据。
 * 如下所示,测试expected是否等于input1+input2,如果按照平时的方法,则需要每次给一组测试数据,如22,21,1,然后运行测试;
 * 接着再给定一组测试数据,再运行。如此循环。显然应该能够一个方法测试所有数据。
 * @RunWith(Parameterized.class)正是因此而生。
 * 步骤:
 * 1. 预期的结果与输入都必须定义成测试类的成员变量;
 * 2. 提供一个对应的构造函数(或者直接使用@Parameter修饰上述的成员变量,且成员变量必须是public修饰,最好使用构造方法);
 * 3. 提供一个静态的存放数据的方法;
 *
 * 注意,此时每个测试方法都会构造出n个JUnitParameterizedTest实例(数量与测试数据一样多),这不重要。
 */
@RunWith(Parameterized.class)
public class JUnitParameterizedTest {

    @Parameters
    public static Collection data(){
        //这里有多少组数据,就产生多少个测试类(这里是JUnitTest)的实例。每组数据都对应一个构造函数,参数也一一对应
        return Arrays.asList(new Object[][]{
                {22,21,1},
                {5,2,3},
                {5,2,3}
        });
    }

//    @Parameterized.Parameter
    public int expected;

    //这里必须赋值,value默认值为0
//    @Parameterized.Parameter(value = 1)
    public int input1;

    //这里必须赋值,value默认值为0
//    @Parameterized.Parameter(value = 2)
    public int input2;

    public JUnitParameterizedTest(int expected, int input1, int input2) {
        this.expected = expected;
        this.input1 = input1;
        this.input2 = input2;
    }

    @Test
    public void test1(){
        assertEquals(expected,input1,input2);
    }

    @Test
    public void test2(){
        System.out.println("[univ]:------ " + expected);//输出22,5,5(测试之前已经被赋值)
    }

}
