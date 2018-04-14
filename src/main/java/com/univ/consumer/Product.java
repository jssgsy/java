package com.univ.consumer;

public class Product {//存放共享资源的类要不要实现Runnable？不需要

	private String name;
	private int age;
	private boolean flag;//用以指示产品是否存在
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}


/*
 * 多线程的一个前提：需要体现出多条语句操作共享资源。
 * Q:假设有n个线程，如果将存放有共享资源的类实现Runnable，并把所有线程对共享资源的操作放在此类的run()中，
 *  	那么当其中某个线程阻塞后，其他的线程都没获得执行权？
 * A:no,虽然此时处理共享资源的代码上了锁，但当某个线程阻塞(wait())时，此线程将会释放此锁，便于其他线程抢占资源。
 * 		所以，应该将存放有共享资源的类实现Runnable，并把所有线程对共享资源的操作放在此类的run()中。
 * 		而不是将有多少线程就创建多少类去实现Runnable接口，并在各自的run()方法中实现对共享资源的读取，因为这显然是破坏了多线程的前提:
 * 		“相同的代码操作共享资源”.
 * 	不过：这里，不同线程要访问不同的
 * 
 * 
 * 
 * 
 * Q:原来是需要有多少种类的线程，就创建多少实现Runnable的类，然后在各自的run()方法中实现对共享资源的处理，
 * 因此也需要在各自的类中取引用共享资源所在的类。
 * 
 * Q:锁的是什么？
 * A:锁的是操作共享资源的多条语句！并且不同的线程必须是同一把锁。
 * 
 * notify(),nofityAll(),wait()需通过锁对象来调用！
 */
