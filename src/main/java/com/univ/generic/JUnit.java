package com.univ.generic;

import org.junit.Test;

public class JUnit {

	@Test
	public void test() {

		Employee e = new Employee("employee");
		//System.out.println(e.getName());
		
		Manager m = new Manager("manager");
		
		Generic g2 = new Generic();
		
		Pair<Employee> p = new Pair<Employee>();
		g2.show(p);
		
		Pair<Manager> p1 = new Pair<Manager>();
		g2.show(p1);
	}
}
