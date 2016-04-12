package com.univ.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import org.junit.Test;

/** 
 * @author: liuml
 * @date: 2016年3月21日 下午2:48:10 
 * @version: 1.0 
 * @description: 
 * 重点是突出方法的使用，这里异常处理可以改进.
 * 测试的是InputStream方法，OutputStream的write方法也是一样：
 * 	void write(byte[] b)：将字节数组b的内容写入到输出流中；
 * 	void write(byte[] b,int offset,intlen):将字节数组b的[offset,offset+len)的内容写入到输出流中；
 * 
 */

public class IoTest {

	/*
	 * 测试InputStream类的int read()
	 * 	返回的是所读到字节的ascii值，如如果读到a，则返回97;
	 * 	处理效率低,很少使用
	 */
	@Test
	public void read() throws IOException{
		InputStream is = new FileInputStream("d:\\myclass\\lmlTest.txt");
		int next;
		while(-1 != (next = is.read())){
			System.out.println(next + ": " + (char)next);
		}
		is.close();//记得关闭流
	}
	
	/*
	 * int read(byte[] b) 
	 * 	将输入流中的内容读入到b中，返回的是读到的实际的字节数
	 */
	@Test
	public void readByte() throws IOException{
		InputStream is = new FileInputStream("d:\\myclass\\lmlTest.txt");
		byte[] b = new byte[4];
		int len;
		while(-1 != (len = is.read(b))){//一次性尝试读入b.lenght长度的字节数，但不一定能读到那么多
			System.out.println(len);
		}
		System.out.println(Arrays.toString(b));
		is.close();
	}
	
	/*
	 * int read(byte[] b, int off, int len)  
	 * 	将输入流中最多 len 个数据字节读入 byte 数组。尝试读取 len 个字节，但读取的字节也可能小于该值
	 * 这里完成的功能是一次性将一个字节文件的内容复制到一个数组中
	 */
	@Test
	public void readByte2() throws IOException{
		InputStream is = new FileInputStream("d:\\myclass\\lmlTest.txt");
		byte[] b = new byte[is.available()];
		int len = 0;
		while(-1 != (len = is.read(b, 0, b.length))){//其实等价于is.read(b);
			System.out.println(len);
		}
		System.out.println(Arrays.toString(b));
		is.close();
	}
	
	/**
	 * 将字节文件读入到字节数组中
	 * @throws IOException
	 */
	@Test
	public void fromBytefileToBytearray() throws IOException{
		InputStream is = new FileInputStream("d:\\myclass\\lmlTest.txt");
		byte[] b = new byte[is.available()];
		is.read(b);
		System.out.println(Arrays.toString(b));
		is.close();
	}
	
	/**
	 * 将字节文件读入到字节文件中
	 * @throws IOException
	 */
	@Test
	public void fromBytefileToBytefile() throws IOException{
		InputStream is = new FileInputStream("d:\\myclass\\lmlTest.txt");
		OutputStream os = new FileOutputStream("d:\\myclass\\out.txt");
		byte[] b = new byte[is.available()];
		is.read(b);
		os.write(b);
		is.close();
		os.close();
	}
	
}


