package com.univ.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/** 
 * @author univ 
 * @date 2016年1月14日 下午4:01:48 
 * @version v1.0
 * @Description: 
 */
public class Test {

	public static void main(String[] args){
		final HelloImpl helloImpl = new HelloImpl();//helloImpl的类型是HelloImpl或者HelloI都可以
		
		/**
		 * helloProxy的类型是HelloProxy或者InvocationHandler都可以;
		 * 通过构造函数传参说明将要代理对象helloImpl;
		 */
		//HelloProxy helloProxy = new HelloProxy(helloImpl);
		
		/**
		 * 注意对二个参数不能写成HelloI.class.getInterfaces();
		 * 用helloProxy去代理HelloI中的所有方法(因为helloProxy底部是helloImpl，所以实际是代理helloImpl的方法)
		 */
//		HelloI proxy = (HelloI) Proxy.newProxyInstance(HelloI.class.getClassLoader(), new Class[]{HelloI.class}, helloProxy);
//		proxy.sayHello();
		
		//使用下面的方法能力更好理解动态代理
		HelloI proxy = (HelloI) Proxy.newProxyInstance(HelloI.class.getClassLoader(), new Class[]{HelloI.class}, new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("before...");
				Object obj = method.invoke(helloImpl, args);
				System.out.println("after...");
				return obj;
			}
		}) ;
		proxy.sayHello();;
		
		
	}
}

