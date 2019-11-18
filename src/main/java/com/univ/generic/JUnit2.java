package com.univ.generic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

/**
 * @author: liuml
 * @date: 2016年3月30日 下午7:36:04
 * @version: 1.0
 * @description:
 */
/**
 * 1. Integer是Number子类，但泛型Box<Integer>不是Box<Number>的子类,原因见下getData()方法;
 * 2. Box<?>是Box<Number>和Box<Integer>等的父类；见getData2()方法;
 */
public class JUnit2 {

	
	@Test
	public void test1() {
		Box<Number> p1 = new Box<>(100);
		Box<Integer> p2 = new Box<>(200);
		// p1 = p2;//报错：Type mismatch: cannot convert from Box<Integer> to Box<Number>
		getData(p1);
		//getData(p2);//这是错误的
		
		getData2(p1);//这是可以的
		getData2(p2);//这也是可以的
	}
	
	/*
	 * 如果这里的b既可接收Box<Number>又可接收Box<Integer>，那么b.getData()返回的是Number还是Integer，显然和泛型的
	 * 初衷违背，所以Box<Integer>不是Box<Number>()的子类。此时的解决方法是利用类型通配符，见getData2()，其实从类型擦除角度考虑更直观，
	 * 擦除后，Box<Number>与Box<Integer>都是Box类型
	 */
	public static void getData(Box<Number> b) {
		System.out.println("data :" + b.getData());
	}
	
	/*
	 * 这里的b可以接收Box<Number>又可接收Box<Integer>等
	 */
	public static void getData2(Box<?> b) {
		System.out.println("data :" + b.getData());
	}
	
	/*
	 * 注意，因为类型擦除，下面的两个方法不能同时存在
	 */
	/*public void test(List<String> li) {
		System.out.println("Sting");
	}

	public void test(List<Integer> li) {
		System.out.println("Integer");
	}*/
	
	
	/*
	 * 通配符？表示该集合存储的元素类型未知，可以是任何类型。往集合中加入元素需要是一个未知元素类型的子类型，
	 * 正因为该集合存储的元素类型未知，所以我们没法向该集合中添加任何元素。唯一的例外是null， 
	 * 因为null是所有类型的子类型，所以尽管元素类型不知道，但是null一定是它的子类型。
	 */
	@Test
	public void test2(){
		Collection<?> c = new ArrayList<String>();
		//c.add(new Box()); //compile time error，不管加入什么对象都出错，除了null外。  
		c.add(null); //OK
	}
	
	/*
	 * 通配符问题
	 *
	 * 关于<? super T>与<? extends T>小结：PECS(producer extend consumer super)
            <? super T>：只是往集合中插入元素，类似消费者
            <? extends T>：只能从集合中取得数据，生产者。不能插入(不知道具体是哪个子类)
	 */
	@Test
	public void test3(){
		List<Shape> shapes = new ArrayList<>();
		List<? super Cicle> cicleSupers = shapes; // 这表示cicleSupers列表存储的元素为Cicle的超类，因此我们可以往其中加入Cicle对象或者Cicle的子类对象
		
		cicleSupers.add(new Cicle()); //可以添加Cicle对象
		
		//cicleSupers.add(new Shape()); //不能加入Cicle的父类对象，
		//原因在于列表cicleSupers存储的元素类型为Cicle的超类，但是具体是Cicle的什么超类并不清楚。
		//但是我们可以确定的是只要是Cicle或者Circle的子类，则一定是与该元素类别兼容。
		
		cicleSupers.add(new Cicle2()); //可以添加Cicle子类的对象
		// 此时cicleSupers中的元素都是Cicle及其父类，所以获取时只能用Object类型接收
        for (Object obj : cicleSupers) {
            System.out.println(obj);
        }
        Cicle c1 = (Cicle) cicleSupers.get(0);
        Cicle2 c2 = (Cicle2) cicleSupers.get(1);
        // 这里是不ok的，不能由父类转向子类 java.lang.ClassCastException: com.univ.generic.Cicle cannot be cast to com.univ.generic.Cicle2
        // Cicle2 c3 = (Cicle2) cicleSupers.get(0);


        List<Shape> s = Arrays.asList(new Shape(), new Cicle(), new Cicle2());
		List<? extends Shape> s1 = s;//此时s1既不能添加Shape的父类对象也不能添加其子类对象,甚至不能添加Shape自身的对象，因为只知道是其或其子类，但不知道具体是哪个子类(重要：如果？是Circle，则此时就不能插入其它类，违反类型安全，所以不允许插入)
		//s1.add(new Shape());//不能添加Shape自身的对象
		//s1.add(new Object());//不能添加Shape的父类对象的对象
		//s1.add(new Cicle());//不能添加Shape子类的对象

        System.out.println("----------------");
        for (Shape shape : s1) {
            System.out.println(shape);
        }
        /*com.univ.generic.Shape@5aaa6d82
        com.univ.generic.Cicle@73a28541
        com.univ.generic.Cicle2@6f75e721*/

	}
	
}

class Shape{
	
}
class Cicle extends Shape{
	
}

class Cicle2 extends Cicle{
	
}

class Box<T> {
	private T data;
	public Box() {
	}
	
	public Box(T data) {
		setData(data);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
