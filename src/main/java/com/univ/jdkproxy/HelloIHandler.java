package com.univ.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * HelloI接口方法被调用时将被转发(拦截、回调)至此。
 *
 * 当调用proxy的方法时，请求都会被委托给其关联的InvocationHandler对象的invoke方法上。
 * Each proxy instance has an associated invocation handler. When a method is invoked on a proxy instance,
 * the method invocation is encoded and dispatched to the invoke method of its invocation handler.
 *
 * 实际使用中，一般用匿名函数了，不然用静态的类来代码就失去了动态代理的灵活性(动态)
 * @author univ 
 * @date 2016年1月14日 下午3:59:09
 */
public class HelloIHandler implements InvocationHandler {

	private Object target;//被代理的对象

    /**
     * 重点：被代理的实例需要赋值给此代理类
     * @param target
     */
	public HelloIHandler(Object target){
		this.target = target;
	}

    /**
     * target的所有方法调用都会被拦截
     * @param proxy 即代理类，注意：不是target，而是target的代理类
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		before();
		Object result = method.invoke(target, args);
		after();
		return result;
	}
	
	public void before(){
		System.out.println("before target invoke...");
	}
	
	public void after(){
		System.out.println("after target invoke...");
	}

}

