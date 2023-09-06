package com.univ.annotation;

import org.junit.Test;

import java.lang.annotation.*;

/**
 * @author univ
 * date 2023/9/6
 */
public class AnnotatedElementTest {

    @Test
    public void getAnnotation() {
        AE_Direct a1 = CChild.class.getAnnotation(AE_Direct.class);
        System.out.println(a1.annotationType());
        System.out.println("===111===");

        // 说明getAnnotation能获取到继承来的注解信息
        AE_Parent a2 = CChild.class.getAnnotation(AE_Parent.class);
        System.out.println(a2.annotationType());
        System.out.println("===222===");

        AE_Parent a3 = CChild.class.getDeclaredAnnotation(AE_Parent.class);
        // 输出为null，说明getDeclaredAnnotation确实只能获取到直接作用于其上的注解
        System.out.println(a3);
        System.out.println("===333===");

        AE3_Repeatable a4 = CChild.class.getAnnotation(AE3_Repeatable.class);
        System.out.println(a4);
        // 输出为null：这是自然，因为并没有作用于其上
        System.out.println("===444===");

        AE_Direct[] as1 = CChild.class.getAnnotationsByType(AE_Direct.class);
        for (AE_Direct a : as1) {
            System.out.println(a);
        }
        System.out.println("===555===");

        AE3_Repeatable[] as2 = CChild.class.getAnnotationsByType(AE3_Repeatable.class);
        for (AE3_Repeatable a : as2) {
            System.out.println(a);
        }
        System.out.println("===666===");

    }

    @Test
    public void getAnnotations() {
        Annotation[] annotations = CChild.class.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }

    @Test
    public void getDeclaredAnnotation() {
        AE_Direct aeDirect = CChild.class.getDeclaredAnnotation(AE_Direct.class);
        System.out.println(aeDirect);

        AE_Parent aeParent = CChild.class.getDeclaredAnnotation(AE_Parent.class);
        // 输出为null，说明确实拿不到继承过来的注解
        System.out.println(aeParent);
    }

    @Test
    public void getDeclaredAnnotations() {
        Annotation[] declaredAnnotations = CChild.class.getDeclaredAnnotations();
        for (Annotation annotation : declaredAnnotations) {
            System.out.println(annotation);
        }
    }

    @Test
    public void getDeclaredAnnotationsByType() {
        Annotation[] ae2s = CChild.class.getAnnotationsByType(AE3_Repeatable_Container.class);
        for (Annotation ae2 : ae2s) {
            System.out.println(ae2);
        }

        System.out.println("=========");
        Annotation[] ae3s = CChild.class.getDeclaredAnnotationsByType(AE3_Repeatable.class);
        for (Annotation ae3 : ae3s) {
            System.out.println(ae3);
        }
    }


}

// 可被继承
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@interface AE_Parent {
    String value() default "AE_Parent_name";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface AE_Direct {
    String value() default "AE_Direct_name";
}

// 定义一个可重复使用的注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(AE3_Repeatable_Container.class)
@interface AE3_Repeatable {
    String value() default "AE3_Repeatable_name";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface AE3_Repeatable_Container {
    AE3_Repeatable[] value();
}

/**
 * 用来继承给子类
 */
@AE_Parent
class CParent {}

@AE_Direct
@AE3_Repeatable_Container({
        @AE3_Repeatable("AE3_Repeatable_name_1"),
        @AE3_Repeatable("AE3_Repeatable_name_2")
})
class CChild extends CParent {}

