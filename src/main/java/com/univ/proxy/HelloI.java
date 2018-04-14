package com.univ.proxy;
/** 
 * @author univ 
 * @date 2016年1月14日 下午3:56:50 
 * @version v1.0
 * @Description: 需要代理的接口
 * jdk提供的动态代理只能代理接口，不能代理没有接口的类。（CGLib可以做到）
 */
public interface HelloI {

	void sayHello();
}

