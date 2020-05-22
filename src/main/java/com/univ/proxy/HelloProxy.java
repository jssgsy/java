package com.univ.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/** 
 * @author univ 
 * @date 2016年1月14日 下午3:59:09 
 * @version v1.0
 * @Description:这个名字取得不太好，因为这里并不是代理类（代理类对象由Proxy生成），是一个调用处理器
 * 实际使用中，一般用匿名函数了，不然用静态的类来代码就失去了动态代理的灵活性(动态)
 *
 *
 * Each proxy instance has an associated invocation handler.
 */

public class HelloProxy implements InvocationHandler {

	private Object target;//被代理的对象
	
	public HelloProxy(Object target){
		this.target = target;
	}
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		before();
		Object result = method.invoke(target, args);
		after();
		return result;
	}
	
	public void before(){
		System.out.println("before say hello...");
	}
	
	public void after(){
		System.out.println("after say hello...");
	}

}

