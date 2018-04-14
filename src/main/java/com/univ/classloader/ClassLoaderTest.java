package com.univ.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

/**
 * @author: liuml
 * @date: 2016年3月20日 下午6:57:26
 * @version: 1.0
 * @description: 通过用户自定义的类装载器，你的程序可以装载在编译时并不知道或者尚未存在的类或者接口
 *               1.想获得加载某个类的类加载器需要通过此类的Class对象获得；
 * 补充：
 * 1. 获取AppClassLoader的方法 ：ClassLoader.getSystemClassLoader()
 * 2. 获取加载某个类(Sample)的类加载器：
 * 	Class clazz = Sample.class;
 * 	clazz.getClassLoader;
 */

public class ClassLoaderTest {

	//测试自定义类加载器
	@Test
	public void test1() {
		MyClassLoader myClassLoader = new MyClassLoader();
		try {
			//此时类路径下不能有Sample.java，否则将被AppClassLoader加载到
			Class clazz = myClassLoader.loadClass("com.univ.AAA");
			System.out.println("加载ArrayTest的类加载器为： " + clazz.getClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

class MyClassLoader extends ClassLoader {//1.继承ClassLoader

	public MyClassLoader() {
		super();//此时调用父类ClassLoader中的构造函数，默认将ApplicationClassLoader作为父类加载器
	}
	
	public MyClassLoader(ClassLoader parent) {
		super(parent);//显示指定此类加载器的父类加载器
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {//2.覆写findClass()方法
        byte[] data = null;  //不能直接使用data = name.getBytes();?
        FileInputStream fis = null;  
		try {
			fis = new FileInputStream("d:\\myclass\\AAA.class");//假设编译后的ClassLoaderTest.class文件放在d盘的myclass目录下
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ByteArrayOutputStream abos = new ByteArrayOutputStream();
		int ch = 0;
		try {
			while (-1 != (ch = fis.read())) {
				abos.write(ch);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        data = abos.toByteArray();   //把输出流中的字节转换成一个字节数组  
        return this.defineClass(name,data, 0, data.length);  
    }  
	//这里讲字节文件内容读入到字节数组中有更简单的方法
	// byte[] data = new byte[fis.avaiable()];fis.read(b);
	//defineClass(byte[] b, int off, int len)已经被废弃，用下面的方法取代：
    // defineClass(String name, byte[] b, int off, int len),其中name是要加载的类的全路径名（加载的虽然是.class文件，但这里的全路径名指的是.java文件的全路径名+类名）
}
