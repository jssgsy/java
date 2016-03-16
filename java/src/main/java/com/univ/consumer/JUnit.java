package com.univ.consumer;

import org.junit.Test;

public class JUnit {

	@Test
	public void test(){
		
		Product stu = new Product();		
		
		Runnable consumer = new Consumer(stu);
		Runnable producer = new Producer(stu);
		
		Thread t1 = new Thread(consumer,"One: ");
		Thread t2 = new Thread(producer,"Two: ");
		
		
		t1.start();
		t2.start();
		
//		while(true){
//			
//		}
	}
}
