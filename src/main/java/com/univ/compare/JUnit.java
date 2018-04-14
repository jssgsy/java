package com.univ.compare;

import java.util.Arrays;
import org.junit.Test;

public class JUnit {

	@Test
	public void test(){		
		Student[] stu = new Student[2];
		stu[0] = new Student("zhangsan",20);
		stu[1] = new Student("lisi",39);	
		Arrays.sort(stu);
		for(Student s : stu){
			System.out.println(s.getAge());
		}
		/*TreeSet<Student> ts = new TreeSet<Student>(new Comparator<Student>() {
			public int compare(Student o1, Student o2) {
				return o1.getAge() - o2.getAge();
			}
		});
		
		ts.add(s1);
		ts.add(s2);
		Iterator<Student> it = ts.iterator();
		while(it.hasNext()){
			System.out.println(it.next().getName());
		}*/
		
				
		/*TreeSet<String> ts = new TreeSet<String>();
		ts.add("avdfkads");
		ts.add("adaffad");
		ts.add("fetdsf");
		ts.add("hafd");
		ts.add("vdv");
		Iterator<String> it = ts.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}*/
		
		
		/*TreeSet<String> ts = new TreeSet<String>(new Comparator<String>() {
			public int compare(String o1, String o2) {				
				return o2.compareTo(o1);
			}
		});
		ts.add("avdfkads");
		ts.add("adaffad");
		ts.add("fetdsf");
		ts.add("hafd");
		ts.add("vdv");
		Iterator<String> it = ts.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}*/
		
	}
}
