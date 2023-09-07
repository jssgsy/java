package com.univ.reflection.annotatedType;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedParameterizedType;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.AnnotatedTypeVariable;
import java.util.Arrays;
import java.util.List;

/**
 * @author univ
 * date 2023/9/7
 */
public class AnnotatedTypeVariableTest<T> {

    private List<@M1("m1") T> list2;

    @Test
    public void typeVariableForList() throws NoSuchFieldException {
        AnnotatedType map1 = AnnotatedTypeVariableTest.class.getDeclaredField("list2").getAnnotatedType();
        // 注意：此时仍然是一个AnnotatedParameterizedType，因为map1本身是一个ParameterizedType；
        AnnotatedParameterizedType annotatedParameterizedType = (AnnotatedParameterizedType) map1;
        AnnotatedType[] actualTypeArguments = annotatedParameterizedType.getAnnotatedActualTypeArguments();
        for (AnnotatedType typeArgument : actualTypeArguments) {
            System.out.println("argumentType: " + typeArgument.getType());
            Annotation[] annotations = typeArgument.getAnnotations();
            System.out.println("\t此类型上的注解有：" + Arrays.toString(annotations));

            // 重要：取参数的实际类型，其就是AnnotatedTypeVariable
            System.out.println("typeArgument的实际类型是：" + typeArgument);
            AnnotatedTypeVariable typeVariable = (AnnotatedTypeVariable) typeArgument;
            AnnotatedType[] annotatedBounds = typeVariable.getAnnotatedBounds();
            System.out.println("此typeArgument的bounds情况如下：");
            for (AnnotatedType annotatedBound : annotatedBounds) {
                System.out.println("\t" + annotatedBound.getType());
            }
        }
    }

}
