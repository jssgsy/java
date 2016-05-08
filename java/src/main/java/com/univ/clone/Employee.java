package com.univ.clone;

import java.util.Date;

/*
 * 八种基本类型及String类型的字段在调用super.clone()时都能确保得到正确复制。
 * 而其他类型（包括Date类型）字段，则需要逐个调用clone()方法来进行正确的复制。
 * 使对象正确clone步骤:
 * 1. 实现Cloneable接口；
 * 2. 覆写Object类的clone方法，并使其为public；
 * 3. 在clone方法中调用super.clone()方法，如果有除八种基本类型及String类型以为的类型的属性，
 * 还需要调用此属性的clone()方法(要确保此属性类型具有正确的clone功能)。
 */
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

	//Date类实现了Clonable接口
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
