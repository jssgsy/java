package com.univ.annotation;
/** 
 * @author: liuml
 * @date: 2015年7月24日 上午9:59:57 
 * @version: 1.0 
 * @description: 
 */

public class Student {	
	
	private String name;	
	
	@MyAnnotation(value="studentaaaName")
	public void setName(String name) {
		this.name = name;
	}
	
	/*public String getName() {
		return name;
	}*/
	public void test(){
		
	}
}


