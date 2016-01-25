package com.univ.jsonCycle;

import java.text.DateFormat;
import java.util.Date;

/** 
 * @author: liuml
 * @date: 2015年8月2日 下午6:39:52 
 * @version: 1.0 
 * @description: 用来测试json中常见的循环引用的异常。
 */

public class Category {
	private String name;
	private float price;
//	private String date = DateFormat.getDateInstance().format(new Date());
	private Category parent;//父类别
	private Category child;//子类别
	
	public Category() {
		
	}
	public Category(String name, float price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public Category(String name, float price, Category parent) {
		super();
		this.name = name;
		this.price = price;
		this.parent = parent;
	}
	
	public Category(String name, float price, Category parent, Category child) {
		super();
		this.name = name;
		this.price = price;
		this.parent = parent;
		this.child = child;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public Category getChild() {
		return child;
	}
	public void setChild(Category child) {
		this.child = child;
	}
//	public String getDate() {
//		return date;
//	}
//	public void setDate(String date) {
//		this.date = date;
//	}
//	
	
	
}


