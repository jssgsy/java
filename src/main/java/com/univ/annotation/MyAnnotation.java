package com.univ.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @author: liuml
 * @date: 2015年7月24日 上午9:54:35 
 * @version: 1.0 
 * @description: 用来测试自定义注解的用法
 */

/**
 * 1.使用@interface关键字定义注解;
 * 2.注解中的成员方法不能有参数,不能抛出异常;
 * 3.可以使用default指定默认值;
 * 4.如果注解中只有一个成员方法,最好定义成value,且在使用过程中可以直接赋值即可:
 *      可以使用@MyAnnotation("fafs")代替@MyAnnotation(value="fafs")
 * 5.方法的返回值类型只能是基本类型及String、Class,Enum类型;
 */
@Target({ElementType.FIELD, ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
	//下面定义两个方法，注意，不是fields
	String value() default "default_value";
	int age() default 1;
}


