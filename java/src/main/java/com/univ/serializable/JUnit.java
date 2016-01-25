package com.univ.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class JUnit {

	@Test
	public void test() throws IOException, ClassNotFoundException{
		// 由于我们要对对象进行序列化，所以我们先自定义一个类
		// 序列化数据其实就是把对象写到文本文件
		
		/*
		 * 序列化对象到文件中
		 */
		write();

		/*
		 * 从文件中反序列化对象出来
		 */
		read();
	}
	

	private static void write() throws IOException {
		// 创建序列化流对象
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
				"oos.txt"));

		// 创建对象
		Person p = new Person("林青霞", 27);

		// public final void writeObject(Object obj)
		oos.writeObject(p);

		// 释放资源
		oos.close();
	}
	
	private static void read() throws IOException, ClassNotFoundException {
		// 创建反序列化对象
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
				"oos.txt"));

		// 还原对象
		Person obj = (Person) ois.readObject();

		// 释放资源
		ois.close();

		// 输出对象
		System.out.println(obj);
	}
}
