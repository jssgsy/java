package com.univ.threadlocal;
/** 
 * @author univ 
 * @date 2016年1月20日 下午4:05:29 
 * @version v1.0
 * @Description: 
 */
public class MyThreadLocal implements Runnable{
	//不使用ThreadLocal的情形
	/*private int index;
	public int getIndex(){
		index++;
		return index;
	}
	
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + ": " + getIndex());			
		}		
	}*/
	
	
	/**
	 * 使用ThreadLocal的情形
	 * 将共享变量存放到ThreadLocal中(每个线程都保存一个共享变量的实例)
	 */
	private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
		protected Integer initialValue() {//初始值为null，因此这里需要显示赋初始值
			return 0;
		}
	};
	
	public Integer getIndex(){
		threadLocal.set(threadLocal.get()+1);
		return threadLocal.get();
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + ": " + getIndex());			
		}		
	}
	
	public static void main(String[] args) {
		MyThreadLocal m = new MyThreadLocal();
		Thread t1 = new Thread(m);
		Thread t2 = new Thread(m);
		Thread t3 = new Thread(m);
		t1.start();
		t2.start();
		t3.start();
	}
}

