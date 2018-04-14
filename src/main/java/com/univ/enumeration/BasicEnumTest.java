package com.univ.enumeration;

import org.junit.Test;


/** 
 * @author: liuml
 * @date: 2016年3月6日 下午2:39:07 
 * @version: 1.0 
 * @description: 
 */

public class BasicEnumTest {

    /**
     * 演示enum类对象的方法
     */
    @Test
    public void enumInstanceMethod(){

        /**
         * toString():重要
         *  Returns the name of this enum constant, as contained in the declaration.
         *  An enum type should override this method when a more "programmer-friendly" string form exists.
         */
        String toStr = Color.GREEN.toString();
        System.out.println("Color.GREEN.toString()为: " + toStr);//GREEN


        /**
         * name():不重要
         *  Returns the name of this enum constant, exactly as declared in its enum declaration.
         *  绝大部分情况下不要使用此方法,应该使用toString方法。参考javadoc说明
         */
        String name = Color.GREEN.name();
        System.out.println("Color.GREEN.name()为: " + name);//GREEN


        /**
         * ordinal():返回此对象在enum类中定义的索引顺序,从0开始。不重要
         *  程序员永远也不要调用此方法。参考javadoc。
         */
        int index = Color.GREEN.ordinal();
        System.out.println("Color.GREEN.ordinal()为: " + index);//1
    }

    /**
     * 演示enum类的方法
     */
    @Test
	public void enumMethod(){

        //valueOf():根据字符串获取某个enum实例,这是enum类的方法
        Color c = Color.valueOf("GREEN");
        System.out.println("字符串GREEN代表的Color对象为: " + c.toString());//GREEN

        //values():获取在enum中定义的所有常量,这是enum类的方法
        Color[] values = Color.values();
        for (Color c1: values) {
            System.out.println(c1.toString());//RED,GREEN,BLUE
        }
    }

    /**
     * 枚举常量在switch中的使用方法
     */
    @Test
    public void enumInSwitch(){
        Color a = Color.BLUE;
        enumSwitch(a);
    }

    /**
     * case中的GREEN不用（也没法）写成A.GREEN,只能是Color中某个定义好的值
     * @param color
     */
	public void enumSwitch(Color color) {
		switch (color) {
			case GREEN:
				System.out.println("green");
				break;
			case RED:
				System.out.println("red");
				break;
			default:
				System.out.println("no color");
		}
	}
}

enum Color{
	RED,GREEN,BLUE
}//RED,GREEN,BLUE都是Color的实例



