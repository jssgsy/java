package com.univ.patterndesign.builder.simple;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author univ
 * @date 2021/5/26 7:24 下午
 * @description
 */
public class UnivProductTest {

    @Test
    public void test() {
        // 链式调用
        UnivProduct univProduct = new UnivProduct.Builder()
                .age(30)    // 每个值有默认值，客户端也可以自己设置
                .arr(Arrays.asList("hello", "new arr"))
                .build();
        System.out.println(univProduct);// UnivProduct(name=default_name, age=30, arr=[hello, new arr])
    }
}
