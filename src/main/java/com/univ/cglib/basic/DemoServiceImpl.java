package com.univ.cglib.basic;

/**
 * 要被代理的类，注，不是接口
 * @author univ
 * 2021/6/17 4:04 下午
 */
public class DemoServiceImpl {

    public int add(int i, int j) {
        return i + j;
    }
}
