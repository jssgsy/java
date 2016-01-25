package com.univ.generic;

public class Generic{

	public  void show(Pair<? extends Employee> e) {
		//System.out.println(e.getName());
		System.out.println("Generic.show()");
		
	}

}
