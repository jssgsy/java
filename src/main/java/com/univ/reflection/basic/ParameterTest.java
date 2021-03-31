package com.univ.reflection.basic;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

import org.junit.Test;

/**
 * @author univ
 * @date 2021/3/31 5:16 下午
 * @description
 */
public class ParameterTest {

    @Test
    public void test() throws NoSuchMethodException {
        Method method = ParameterTest.class.getDeclaredMethod("fn", ServiceMock.class, String.class, Integer[].class);
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            System.out.println("参数名getName(获取不到真实名)： " + parameter.getName());
            // 这里取不到，为何？
            System.out.println("参数名【" + parameter.getName() + "】" + "修饰符为：" + Modifier.toString(parameter.getModifiers()));
            if (null != parameter.getAnnotations() && parameter.getAnnotations().length > 0) {
                Annotation[] annotations = parameter.getAnnotations();
                System.out.println("参数名【" + parameter.getName() + "】" + "的注解如下：");
                for (Annotation annotation : annotations) {
                    System.out.println("\t" + annotation);
                }
            }
            if (parameter.isVarArgs()) {
                System.out.println("参数名【" + parameter.getName() + "】" + "是一个变长参数");
            }
        }
    }

    public void fn(@I1 @I2 ServiceMock mock, @I3 final String str, Integer... iArr) {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    @interface I1 {
        String value() default "i1_name";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)

    @interface I2 {}
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    @interface I3 {}

    class ServiceMock {}
}


