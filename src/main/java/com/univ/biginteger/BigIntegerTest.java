package com.univ.biginteger;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * created by Univ
 * 16/6/7 10:32
 *
 * 1. 所有参加大数运算的数都必须是大数;
 * 2. 大数BigInteger对象产生的两种方法:
 *      1. 通过BigInteger.valueOf(long val)获取,注意valueOf的参数没有String类型,即不能从字符串转换;
 *      2. 利用构造函数,此时可以传入一个字符串;
 * 3. 大数BigDecimal对象的获取方法是一样的;
 */
public class BigIntegerTest {

    @Test
    public void powTest(){
//        BigInteger x = BigInteger.valueOf(2);//利用BigInteger.valueOf获取BigInteger对象
        BigInteger x = new BigInteger("2");//利用构造函数传入String获取BigInteger对象

        int y = 80;
        BigInteger result = pow(x,y);
        System.out.println(result);
    }

    private BigInteger pow(BigInteger x, int y) {
        BigInteger result = BigInteger.valueOf(1) ;
        for (int i = 0; i < y; i++) {
            result = result.multiply(x);
        }
        return result;
    }

    /**
     * 两种构造BigDecimal对象的方法:
     *  1. 利用BigDecimal.valueOf(double)或者其他类型;
     *  2. 利用构造函数,传入String;
     */
    @Test
    public void bigdecimal(){
        BigDecimal b = BigDecimal.valueOf(2.44444444);
        BigDecimal b1 = new BigDecimal("2.44444444");
        System.out.println(b);
        System.out.println(b1);

    }
}
