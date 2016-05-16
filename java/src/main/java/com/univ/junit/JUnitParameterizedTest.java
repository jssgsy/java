package com.univ.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * created by Univ
 * 16/5/16 19:12
 * 测试Junit相关功能
 */
@RunWith(Parameterized.class)
public class JUnitParameterizedTest {

    int expected;
    int input1;
    int input2;

    public JUnitParameterizedTest(int expected, int input1, int input2) {
        this.expected = expected;
        this.input1 = input1;
        this.input2 = input2;
    }

    @Parameterized.Parameters
    public static Collection data(){
        //这里有多少组数据,就好产生多少个测试类(这里是JUnitTest)的实例。即组数据都对应一个构造函数,参数也一一对应
        return Arrays.asList(new Object[][]{
                {22,21,1},
                {5,2,3},
                {5,2,3}

        });
    }

    @Test
    public void test1(){
        assertEquals(expected,input1,input2);
    }


}
