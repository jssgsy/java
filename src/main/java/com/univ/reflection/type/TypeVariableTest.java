package com.univ.reflection.type;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import org.junit.Test;

/**
 * @author univ
 * @date 2021/3/23 4:43 下午
 * @description
 */
public class TypeVariableTest <E extends Number, T extends Object> {

    @Test
    public void test() {
        TypeVariable<Class<TypeVariableTest>>[] typeParameters = TypeVariableTest.class.getTypeParameters();
        for (TypeVariable<Class<TypeVariableTest>> typeParameter : typeParameters) {
            System.out.println("getName()输出: " + typeParameter.getName());

            System.out.println("getBounds()输出: ");
            Type[] bounds = typeParameter.getBounds();
            for (Type bound : bounds) {
                System.out.println("    " + bound.getTypeName());
            }
            Class<TypeVariableTest> genericDeclaration = typeParameter.getGenericDeclaration();
            System.out.println("getGenericDeclaration()输出: " + genericDeclaration.getName());

            System.out.println("getAnnotatedBounds()输出: ");
            AnnotatedType[] annotatedBounds = typeParameter.getAnnotatedBounds();
            for (AnnotatedType annotatedBound : annotatedBounds) {
                System.out.println("    " + annotatedBound.getType().getTypeName());
            }
            System.out.println("------换行换行------");
        }
    }
}
