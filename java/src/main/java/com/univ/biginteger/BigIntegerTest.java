package com.univ.biginteger;

import org.junit.Test;

import java.math.BigInteger;

/**
 * created by Univ
 * 16/6/7 10:32
 *
 * 1. 所有参加大数运算的数都必须是大数;
 * 2. 大数的产生只能通过BigInteger.valueOf(long val)获取,参数没有String类型,即不能从字符串转换;
 */
public class BigIntegerTest {

    @Test
    public void powTest(){
        BigInteger x = BigInteger.valueOf(2);
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
}
