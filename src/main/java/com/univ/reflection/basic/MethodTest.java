package com.univ.reflection.basic;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.junit.Test;

/**
 * @author univ
 * @date 2021/3/31 4:42 下午
 * @description
 */
public class MethodTest {

    @Test
    public void test() throws NoSuchMethodException {

        Method method = MethodTest.class.getDeclaredMethod("fn", String.class, Integer.class);
        Parameter[] parameters = method.getParameters();
        // 依次返回每个参数的所有注解
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameters.length; i++) {
            Annotation[] annotations = parameterAnnotations[i];
            Parameter parameter = parameters[i];
            System.out.println("参数【" + parameter + "】上的注解为: " );
            for (Annotation annotation : annotations) {
                System.out.println("\t" + annotation);
            }
        }
    }

    public String fn(@I1("haha") @I2 String str, @I3 Integer i1) {
        return "fn";
    }

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
}



