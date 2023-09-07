package com.univ.reflection.annotatedType;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author univ
 * date 2023/9/7
 */
public class AnnotatedParameterizedTypeTest {

    private List<@M1("m1") @M2("m2") String> list1 = new ArrayList<>();

    private Map<@M1("m1") String, @M2("m2") Integer> map1 = new HashMap<>();

    @Test
    public void parameterizedAnnotatedForList1() throws NoSuchFieldException {
        Field field = AnnotatedParameterizedTypeTest.class.getDeclaredField("list1");
        AnnotatedType annotatedType = field.getAnnotatedType();

        System.out.println("字段【list1】对应的annotatedType.getType(): " + annotatedType.getType());
        // 因为list1是ParameterizedType，因此获取到的就是AnnotatedParameterizedType
        // 转成实际的AnnotatedParameterizedType，用来获取其实际参数
        AnnotatedParameterizedType annotatedParameterizedType = (AnnotatedParameterizedType) annotatedType;
        // 被注解的类型只有String一个
        AnnotatedType[] arguments = annotatedParameterizedType.getAnnotatedActualTypeArguments();
        for (AnnotatedType argument : arguments) {
            System.out.println("argument的类型: " + argument.getType());

            // 再获取注解的信息，使用的是父类AnnotatedElement的能力
            // 特别注意，要在这里调用，而不是外围，此时被注解的参数才是AnnotatedElement
            Annotation[] annotations = argument.getAnnotations();
            System.out.println("类型上的注解信息：");
            for (Annotation annotation : annotations) {
                System.out.println("\t" + annotation);
            }
        }
        // 此时相当于获取list1元素本身的注解，当然是空
        Annotation[] annotations = annotatedParameterizedType.getAnnotations();
        System.out.println(Arrays.toString(annotations));
    }

    @Test
    public void parameterizedAnnotatedForMap1() throws NoSuchFieldException {
        AnnotatedType map1 = AnnotatedParameterizedTypeTest.class.getDeclaredField("map1").getAnnotatedType();
        // 此时也是一个AnnotatedParameterizedType,直接强转
        AnnotatedParameterizedType annotatedParameterizedType = (AnnotatedParameterizedType) map1;
        AnnotatedType[] actualTypeArguments = annotatedParameterizedType.getAnnotatedActualTypeArguments();
        for (AnnotatedType actualTypeArgument : actualTypeArguments) {
            // 取参数的实际类型
            System.out.println("actualTypeArgument: " + actualTypeArgument.getType());
            Annotation[] annotations = actualTypeArgument.getAnnotations();
            System.out.println("\t此类型上的注解有：" + Arrays.toString(annotations));
        }
    }

}
