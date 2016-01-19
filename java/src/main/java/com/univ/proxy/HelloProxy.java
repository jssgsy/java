package com.univ.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/** 
 * @author univ 
 * @date 2016年1月14日 下午3:59:09 
 * @version v1.0
 * @Description: HelloI接口中方法的代理类
 * 代理类只需要实现InvocationHandler即可
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

