package com.univ.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author univ 
 * @date 2016年1月14日 下午4:01:48 
 * @version v1.0
 * @Description: 
 */
public class JdkProxyTest {

	public static void main(String[] args){
		final HelloImpl helloImpl = new HelloImpl();//helloImpl的类型是HelloImpl或者HelloI都可以
		
		/**
		 * 重点：需要将实际要代理的类传递给helloIHandler，这里是通过构造函数
		 */
        /*HelloIHandler helloIHandler = new HelloIHandler(helloImpl);
		HelloI proxy = (HelloI) Proxy.newProxyInstance(HelloI.class.getClassLoader(), new Class[]{HelloI.class}, helloIHandler);
		proxy.sayHello();*/

		//使用下面的方法能力更好理解动态代理
        // 第二个参数是数组，说明可以代理多个接口
		HelloI proxy = (HelloI) Proxy.newProxyInstance(HelloI.class.getClassLoader(), new Class[]{HelloI.class}, new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("before...");
				// 注意：这里的helloImpl是被代理的实例，要被代理的实例是没法自动生成的，因为有哪些字段，有哪些方法，方法的实现都是不固定的，因此由外部传入
				Object obj = method.invoke(helloImpl, args);
				System.out.println("after...");
				return obj;
			}
		}) ;
		// 接口中的所有方法都会被代理到
		proxy.sayHello();
		proxy.sayGoodbye();
	}
}

