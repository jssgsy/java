package com.univ.reflection.type;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author univ
 * @date 2021/3/24 2:59 下午
 *
 * @description 各种类型的测试
 */
public class TotalTypeTest<T extends Number & Serializable, E> {

    int i = 1;

    int[] iArr = new int[3];

    String[] strArr = new String[3];

    List list = new ArrayList();

    List<String> list1 = new ArrayList<>();

    List<T> list2 = new ArrayList<>();

    List<E> list3 = new ArrayList<>();

    List<?> list4 = new ArrayList<>();

    List<? extends Number> list5 = new ArrayList<>();

    Map<String, Integer> map = new HashMap<>();

    Map<E, T> map2 = new HashMap<>();

    @Test
    public void test() {
        Field[] fields = TotalTypeTest.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("---开始处理字段【" + field.getName() + "】---");
            Type genericType = field.getGenericType();
            printType(genericType);
            System.out.println();
        }
    }

    private void printType(Type type){
        if (type instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) type;
            System.out.println("GenericArrayType#getGenericComponentType: " + genericArrayType.getGenericComponentType());
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            System.out.println("ParameterizedType#getTypeName: " + parameterizedType.getTypeName());
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            System.out.println("ParameterizedType#getActualTypeArguments: ");
            for (Type argument : actualTypeArguments) {
                printType(argument);
            }
        } else if (type instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type;
            System.out.println("TypeVariable#getTypeName: " + typeVariable.getTypeName());
            Type[] bounds = typeVariable.getBounds();
            for (Type bound : bounds) {
                System.out.println("\tTypeVariable#getBounds: " + bound.getTypeName());
            }
            System.out.println("TypeVariable#getGenericDeclaration: " + typeVariable.getGenericDeclaration());
        } else if (type instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type;
            System.out.println("WildcardType#getTypeName: " + wildcardType.getTypeName());
            Type[] lowerBounds = wildcardType.getLowerBounds();
            for (Type lowerBound : lowerBounds) {
                System.out.println("\tWildcardType#getLowerBounds: " + lowerBound.getTypeName());
            }
            Type[] upperBounds = wildcardType.getUpperBounds();
            for (Type upperBound : upperBounds) {
                System.out.println("\tWildcardType#getUpperBounds: " + upperBound.getTypeName());
            }
        } else {
            System.out.println("此字段为普通字段: " + type);
        }
    }
}
