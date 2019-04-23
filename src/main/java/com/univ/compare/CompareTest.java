package com.univ.compare;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

/**
 * Comparable接口
 * 1. 实现了Comparable接口的对象，其组成的数组或者集合可被传入给Arrays.sort或者Collections.sort方法进行排序；
 * 2. 可理解成静态排序，即在开始时就已经确定了排序方法，相当于“内部比较器”；
 * 3. 若一个类实现了Comparable接口，就意味着“该类支持排序”
 *
 * Comparator接口
 * 1. 要达到的作用与Comparable相同，只是不要求数组(集合)对象实现此接口，而是动态传入给Arrays.sort或者Collections.sort方法进行排序;
 * 2. 可理解成动态排序，即在运行时才提供排序的方法，相当于“外部比较器”；
 * 3. 需要控制数组(集合)中某个类的次序，而该类本身不支持排序(即没有实现Comparable接口)，用Comparator；
 * 4. 经常用在TreeSet、TreeMap中
 */
public class CompareTest {

	@Test
	public void test() {
		Student[] stu = new Student[]{new Student("aaa", 2), new Student("bbb", 3)};
		System.out.println(Arrays.toString(stu));
		// 使用Comparable接口升序
		Arrays.sort(stu);
		System.out.println(Arrays.toString(stu));

		// 使用Comparator接口降序
		Arrays.sort(stu, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.getAge() - o2.getAge() > 0 ? -1 : 1;
			}
		});
		System.out.println(Arrays.toString(stu));


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
	}

    /**
     * 默认的Comparator
     */
	@Test
    public void test2() {
        String[] arr = {"zhang", "san", "li", "si"};
        Arrays.sort(arr, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(arr));
    }
}
