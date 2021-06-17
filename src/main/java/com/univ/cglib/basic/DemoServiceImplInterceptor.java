package com.univ.cglib.basic;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 用来拦截DemoServiceImpl的调用
 *
 * @author univ
 * 2021/6/17 4:07 下午
 */
public class DemoServiceImplInterceptor implements MethodInterceptor {

    /**
     * @param obj 即代理类，和jdk代理类似
     * @param method
     * @param args
     * @param proxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before---");
        /**
         * 将方法调用委托给实际被代理的类，即父类，因此方法名为invokeSuper，且入参为代理类obj，而不是实际被代理的类
         * 注：因为cglib是通过继承实现的，即代理类是被代理类的子类
         */
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("after---");
        return result;
    }
}
