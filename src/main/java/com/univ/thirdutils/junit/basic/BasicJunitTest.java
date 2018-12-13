package com.univ.thirdutils.junit.basic;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author univ
 * @datetime 2018/12/13 6:32 PM
 * @description junit的基本用法
 */
/*
JUnit provides overloaded assertion methods for all primitive types and Objects and arrays (of primitives or Objects). The parameter order is expected value followed by actual value. Optionally the first parameter can be a String message that is output on failure. There is a slightly different assertion, assertThat that has parameters of the optional failure message, the actual value, and a Matcher object. Note that expected and actual are reversed compared to the other assert methods.
 */
public class BasicJunitTest {

    @Before
    public void setUp() throws Exception {
        // System.out.println("这里的代码会在每个测试方法执行前执行");
    }

    @After
    public void tearDown() throws Exception {
        // System.out.println("这里的代码会在每个测试方法执行后执行");
    }

    @Test
    public void test1() {
        long l1 = 1l;
        long l2 = 1l;
        Assert.assertEquals(l1, l2);
        // 如果期望的与实际的不符，给出异常信息
        Assert.assertEquals("l1与l2不等", l1, l2);

        int[] arr1 = { 1, 2, 3, 4 };
        int[] arr2 = { 1, 2, 3, 4 };
        Assert.assertArrayEquals("arr1与arr2不相等", arr1, arr2);

        /*
            1. assertEquals(expected, actual)有各种类型的重载方法
            2. Assert.assertArrayEquals(expected, actual)也有各种类型的重载方法
         */

        // 判定为true或者false
        Assert.assertTrue("不为true时将随异常一起抛出", true);
        Assert.assertFalse("不为false时将随异常一起抛出", false);

        // 判定对象是否为null
        Object obj = null;
        Assert.assertNotNull("对象为null时将随异常一起抛出", new Object());
        Assert.assertNull("对象不为null时将随异常一起抛出", null);
    }
    
    
}
