package com.univ.clone;

import java.util.Date;

public class Employee implements Cloneable{

	private String name;
	private int salary;
	private Date hireDate;
	
	public Employee(){}
	
	public Employee(String name, int salary, Date hireDate){
		this.name = name;
		this.salary = salary;
		this.hireDate = hireDate;
	}

	public Employee clone() throws CloneNotSupportedException{
		Employee em = null;
		em = (Employee) super.clone();
		em.hireDate = (Date) hireDate.clone();
		return em;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	
	
}
