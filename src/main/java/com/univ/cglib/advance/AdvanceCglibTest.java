package com.univ.cglib.advance;

import java.lang.reflect.Method;

import org.junit.Test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author univ
 * 2021/6/17 4:37 下午
 */
public class AdvanceCglibTest {

    /**
     * 演示cglib代理接口
     */
    @Test
    public void proxyInterface() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(DemoService.class);

        // 因为这里演示的是通过cglib代理接口，因此需要一个具体的DemoService实例
        DemoService demoServiceImpl = new DemoService() {
            @Override
            public int add(int i, int j) {
                return i + j;
            }
        };

        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before---");
                // 这里演示的是代理接口，因为就不能直接调用invokeSuper方法了，而需要调用invoke，此时的jdk动态代理的方法基本一模一样了
                // Object result = proxy.invokeSuper(obj, args);

                // demoServiceImpl:要被代理的实例
                Object result = proxy.invoke(demoServiceImpl, args);
                System.out.println("after---");
                return result;
            }
        });

        DemoService demoService = (DemoService) enhancer.create();
        int result = demoService.add(10, 20);
        System.out.println("result: " + result);
    }
}
