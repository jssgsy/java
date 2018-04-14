package com.univ.json;
/** 
 * @author: liuml
 * @date: 2015年8月29日 上午10:15:28 
 * @version: 1.0 
 * @description: 仅仅只是供excludeNull方法测试使用
 */

public class Entity {
	private String name;
	private int age;
	private String sex;
	private Teacher teacher;
	
	private static String address = "address";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public static String getAddress() {
		return address;
	}
	public static void setAddress(String address) {
		Entity.address = address;
	}
	
}


