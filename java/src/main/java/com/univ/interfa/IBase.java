package com.univ.interfa;
/** 
 * @author univ 
 * @date 2016年1月20日 上午9:58:01 
 * @version v1.0
 * @Description: 接口继承的演示
 */
public interface IBase {
	String baseA();
}

/**
 * 子接口可以不实现父接口的方法，相当于是继承了父接口的方法
 */
interface IDerive extends IBase{
	int deriveA();
}


class A implements IDerive{

	/**
	 * 实现子接口的类除了实现子接口中的方法外还必须实现子接口的父接口中的方法
	 */
	public int deriveA() {
		return 0;
	}

	public String baseA() {
		return null;
	}
	
}

//-------------------------------------------------------------------------

/**
 * 抽象类实现接口时可以不用实现接口中的方法
 */
abstract class B implements IBase{
	
}
/**
 * 当然此时子类继承B时需要实现IBase中的方法
 */
class BB extends B {
	public String baseA() {
		return null;
	}
}
