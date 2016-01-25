package com.univ.clone;

import java.text.DateFormat;
import java.util.Date;

import org.junit.Test;

public class JUnit {
	
	@Test
	public void test() throws InterruptedException, CloneNotSupportedException{
		System.out.println();
		Employee e = new Employee("new name", 4000, new Date());		
		Employee e1 = e.clone();		
		
		System.out.println(e.getHireDate());
		System.out.println(e1.getHireDate());
		
		Thread.sleep(2000);
		e1.getHireDate().setHours(3);
		//e1.setHireDate(new Date());
		System.out.println(e.getHireDate());
		System.out.println(e1.getHireDate());
		/*System.out.println("before: ");
		System.out.println(e.getName());
		System.out.println(e1.getName());
		
		System.out.println(e.getSalary());
		System.out.println(e1.getSalary());
		
		System.out.println(e.getHireDate());
		System.out.println(e1.getHireDate());
		
		
		Thread.sleep(3000);
		System.out.println("after set: ");
		e1.setHireDate(new Date());
		e1.setName("aaa");
		e1.setSalary(5000);
		
		System.out.println(e.getName());
		System.out.println(e1.getName());
		
		System.out.println(e.getSalary());
		System.out.println(e1.getSalary());
		
		System.out.println(e.getHireDate());
		System.out.println(e1.getHireDate());*/
	
		/*Manager m = new Manager("aaa", 10000, new Date(), "five", new Date());		
		
		Manager m1 = m.clone();
		
		Thread.sleep(2000);
		m1.setName("new manager");
		m1.setSalary(20000);
		m1.setHireDate(new Date());
		m1.setRange("six");
		m1.setD(new Date());
		System.out.println("before:");
		System.out.println("name: " + m.getName());
		System.out.println("salary: " + m.getSalary());
		System.out.println("hireDate: " + m.getHireDate());
		System.out.println("rank: " + m.getRange());
		System.out.println("d: " + m.getD());
		System.out.println("------------------------------------");
		System.out.println("after:");
		System.out.println("name: " + m1.getName());
		System.out.println("salary: " + m1.getSalary());
		System.out.println("hireDate: " + m1.getHireDate());
		System.out.println("rank: " + m1.getRange());
		System.out.println("d: " + m1.getD());
		System.out.println("------------------------------------");*/
		
		//SimpleDateFormat sdf = new SimpleDateFormat();		
		Date d = new Date();
		DateFormat.getTimeInstance();
		;
		System.out.println();
		
		
		
	}

}
