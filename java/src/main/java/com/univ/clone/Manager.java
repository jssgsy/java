package com.univ.clone;

import java.util.Date;

public class Manager extends Employee {

	private String range;
	private Date d ;
	
	
	public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}

	public Manager(String name, int salary, Date hireDate,String range, Date d){
		super(name,salary,hireDate);
		this.range = range;
		this.d = d;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}
	
	@Override
	public Manager clone() throws CloneNotSupportedException{
		return (Manager) super.clone();
		
	}
}
