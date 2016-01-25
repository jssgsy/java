package com.univ.json;

import java.util.List;

/** 
 * @author: liuml
 * @date: 2015年7月24日 下午7:14:15 
 * @version: 1.0 
 * @description: 只是供JSonTest中的testBeanToJSON测试使用
 */

public class MyBean {

	private String id;
	private String name;
	
	private List cardNum;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public List getCardNum() {
		return cardNum;
	}
	public void setCardNum(List cardNum) {
		this.cardNum = cardNum;
	}
	
	
}


