package com.univ.annotation;

import java.io.IOException;
import java.lang.annotation.*;

/**
 * @author univ
 * date 2023/9/6
 */
public class InheritedTest {

    public static void main(String[] args) throws InterruptedException, IOException, NoSuchFieldException {
        System.out.println(Parent.class.isAnnotationPresent(A1.class)); // true
        System.out.println(Child.class.isAnnotationPresent(A1.class)); // true
        System.out.println(Another.class.isAnnotationPresent(A1.class)); // false
    }

}

// @Inherited要生效，则@Target必须包含ElementType.TYPE
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface A1 {
    String name() default "a1_name";
}

@A1
class Parent {}
class Child extends Parent {}
class Another {}
