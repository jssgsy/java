package com.univ.proxy;
/** 
 * @author univ 
 * @date 2016年1月14日 下午3:57:32 
 * @version v1.0
 * @Description: 需要被代理的接口的实现类
 */
public class HelloImpl implements HelloI {

	public void sayHello() {
		System.out.println("hello,world.");
	}

    @Override
    public void sayGoodbye() {
        System.out.println("good bye");
    }

}

