package com.univ.cglib.basic;

import org.junit.Test;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author univ
 * 2021/6/17 4:05 下午
 */
public class BasicCglibTest {

    /**
     * 演示cglib的常见基础用法
     */
    @Test
    public void test1() {
        Enhancer enhancer = new Enhancer();
        // 设置要代理的类
        // 注意这里的方法名，验证了cglib是通过继承来达到代理的目的：为要代理的类生成一个子类作为代理类，所以说不能代理final方法
        enhancer.setSuperclass(DemoServiceImpl.class);

        // 设置回调，类型jdk动态代理中的InvocationHandler
        enhancer.setCallback(new DemoServiceImplInterceptor());

        // 创建一个代理对象
        DemoServiceImpl demoService = (DemoServiceImpl) enhancer.create();
        int result = demoService.add(10, 20);
        System.out.println("result: " + result);
    }
}
