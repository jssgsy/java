package com.univ.clone;

import java.util.Date;

import org.junit.Test;

public class JUnit {
	
	@Test
	public void test() throws InterruptedException, CloneNotSupportedException{
		//测试单个类的clone
		Employee e = new Employee("new name", 4000, new Date());		
		Employee e1 = e.clone();		
		e1.getHireDate().setHours(3);
		System.out.println("e: " + e.getHireDate());
		System.out.println("e1: " + e1.getHireDate());
		
		//测试继承类的clone（方法和单个类的clone一样）
		/*Manager m = new Manager("aaa", 10000, new Date(), "five", new Date());		
		Manager m1 = m.clone();
		m1.getD().setHours(3);
		System.out.println("d: " + m.getD());
		System.out.println("d: " + m1.getD());*/
	}

}
