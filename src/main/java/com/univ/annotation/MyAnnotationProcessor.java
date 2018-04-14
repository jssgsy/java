package com.univ.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** 
 * @author: liuml
 * @date: 2015年7月24日 上午10:04:47 
 * @version: 1.0 
 * @description: MyAnnotation处理器.
 * 1）注解可以作用在类，方法，属性上，对应的可以通过类，方法，属性.getDeclaredAnnotations()和getAnnotations()来获得此类，方法，属性上的注解。
 * 2）method.getDeclaredAnnotations()获得是此方法上的所有注解（就像1）所言），
 * 		而annotation.getDeclaredAnnotations()获得的是此自定义注解上的注解，如@Target,@retention等。
 * 3）annotation.annotationType()：此注解的类型，是全限定名如（interface univ.annotation.MyAnnotation）
 * 4）取得某个annotation后可以转换成自定义的类型，如MyAnnotation myAnnotation = (MyAnnotation)annotation;
 * 		此时便可以进行myAnnotation.value()，myAnnotation.age()等操作
 * 5）method.isAnnotationPresent(MyAnnotation.class);用以测试某个方法是否被某个注解标记
 * 		 MyAnnotation annotation = method.getAnnotation(MyAnnotation.class); 
 * 		 System.out.println("method = " + method.getName() + " ; value = " + annotation.value() + " ; 
 * 							age = " + annotation.age()); //此时可以获得这个方法上的注解，且可以取得注解的参数和参数值
 */

public class MyAnnotationProcessor {
	public static void main(String[] args) throws Exception {
        Class<?> c = Class.forName("com.univ.annotation.Student");
       
        Method[] methods = c.getDeclaredMethods();
        Annotation[] annotations = null;
        for (Method method : methods) {
        	if(method.isAnnotationPresent(MyAnnotation.class)){
        		MyAnnotation annotation = method.getAnnotation(MyAnnotation.class); 
        		 System.out.println("method = " + method.getName()  
                         + " ; value = " + annotation.value() + " ; age = "  
                         + annotation.age());  
        	}
        	/*annotations = method.getDeclaredAnnotations();        	
        	if(annotations != null){//说明此方法上有注解,即使没有注解的方法调用getDeclaredAnnotations()返回的也不是为null
        		System.out.println("被注解的方法为： " + method.getName());
        		for(Annotation annotation : annotations){//对于此方法上的每一个注解
        			//找出此注解的类型，是全限定名，如interface univ.annotation.MyAnnotation
            		System.out.println("注解类型为："+annotation.annotationType());
            		
            		MyAnnotation myAnnotation = (MyAnnotation)annotation;
            		System.out.println(myAnnotation.value());
            		System.out.println(myAnnotation.age());  
            	}
        	}*/
        	
        }
    }
}

//找出此注解类型内部定义的所有方法
/*Method[] ans = annotation.annotationType().getDeclaredMethods();
for(Method an : ans){
	System.out.println("自定义注解中声明的方法有："+an.getName());
}
//找出此注解类型内部定义的所有field
Field[] fields = annotation.annotationType().getDeclaredFields();
for(Field field : fields){
	System.out.println("自定义注解中声明的字段有："+field.getName());
}*/
