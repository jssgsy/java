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
@Target({ElementType.FIELD, ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
	//下面定义两个方法，注意，不是fields
	String value() default "default_value";
	int age() default 1;
}


