package com.univ.threadlocal;
/** 
 * @author univ 
 * @date 2016年1月20日 下午4:05:29 
 * @version v1.0
 * @Description: 
 * Thread同步机制的比较
 * 	ThreadLocal和线程同步机制都是为了解决多线程中相同变量的访问冲突问题。
　　	在同步机制中，通过对象的锁机制保证同一时间只有一个线程访问变量。这时该变量是多个线程共享的，使用同步机制要求程序慎密地分析什么时候对变量进行读写，
		什么时候需要锁定某个对象，什么时候释放对象锁等繁杂的问题，程序设计和编写难度相对较大。
　	而ThreadLocal则从另一个角度来解决多线程的并发访问。ThreadLocal会为每一个线程提供一个独立的变量副本，从而隔离了多个线程对数据的访问冲突。
		因为每一个线程都拥有自己的变量副本，从而也就没有必要对该变量进行同步了。ThreadLocal提供了线程安全的共享对象，在编写多线程代码时，可以把不安全的变量封装进ThreadLocal。
	概括起来说，对于多线程资源共享的问题，同步机制采用了“以时间换空间”的方式，
	而ThreadLocal采用了“以空间换时间”的方式。前者仅提供一份变量，让不同的线程排队访问，而后者为每一个线程都提供了一份变量，因此可以同时访问而互不影响。
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
	
	/*
	 * 下面的做法是错误的,这样仍然没法解决多线程的问题，因为age直接暴露了，因此在使用threadlocal时，对共享变量的取值只能通过threadLocal.get()
	 * threadLocal.set(age++);
	 */
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

