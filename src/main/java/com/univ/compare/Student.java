package com.univ.compare;


public class Student implements Comparable<Student>{

	private String name;
	private int age;
	
	public Student(String name, int age){
		this.name = name;
		this.age = age;
	}
	
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

	/**
	 * 要第一个元素少于、等于、大于第二个元素，则分别返回-1、0、1
	 * @param o 要和this比较的对象
	 * @return
	 */
	@Override
	public int compareTo(Student o) {
		return age - o.age < 0 ? -1 : 1;
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", age=" + age +
				'}';
	}
}
