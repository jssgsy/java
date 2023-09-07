package com.univ.reflection.annotatedType;

import org.junit.Test;

import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedWildcardType;
import java.util.Arrays;
import java.util.List;

/**
 * @author univ
 * date 2023/9/7
 */
public class AnnotatedWildcardTest {

    List<@M1 ? extends Number> list;

    @Test
    public void fn() throws NoSuchFieldException {
        AnnotatedType annotatedType = AnnotatedWildcardTest.class.getDeclaredField("list").getAnnotatedType();
        System.out.println("从字段上获取的AnnotatedType: " +annotatedType);

        AnnotatedParameterizedType annotatedParameterizedType = (AnnotatedParameterizedType) annotatedType;
        AnnotatedType[] actualTypeArguments = annotatedParameterizedType.getAnnotatedActualTypeArguments();
        for (AnnotatedType actualTypeArgument : actualTypeArguments) {
            // 获取其中的注解信息
            System.out.println("泛型?上的注解有：");
            System.out.println("\t" + Arrays.toString(actualTypeArgument.getAnnotations()));

            System.out.println();
            // 强转成AnnotatedWildcardType，可调用其中的方法
            System.out.println("actualTypeArgument: " + actualTypeArgument);
            AnnotatedWildcardType annotatedWildcardType = (AnnotatedWildcardType) actualTypeArgument;
            AnnotatedType[] annotatedUpperBounds = annotatedWildcardType.getAnnotatedUpperBounds();
            System.out.println("上界为：");
            System.out.println("\t" + Arrays.toString(annotatedUpperBounds));

            AnnotatedType[] annotatedLowerBounds = annotatedWildcardType.getAnnotatedLowerBounds();
            System.out.println("下界为：");
            System.out.println("\t" + Arrays.toString(annotatedLowerBounds));
        }
    }

}
