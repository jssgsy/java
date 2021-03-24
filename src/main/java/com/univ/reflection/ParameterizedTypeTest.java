package com.univ.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * @author univ
 * @date 2021/3/23 4:00 下午
 * @description
 */
public class ParameterizedTypeTest {
    // 不是ParameterizedType
    int i = 1;
    // 不是ParameterizedType
    String str = "str";
    // 不是ParameterizedType
    Set set = new HashSet<>();
    // 不是ParameterizedType
    Integer[] arr = new Integer[20];

    //是ParameterizedType
    List<String> list = Arrays.asList("aaa", "bbb");
    //是ParameterizedType
    Map<Integer, String> map = new HashMap<>();
    //是ParameterizedType
    List<?> list2 = new ArrayList<>();
    //是ParameterizedType
    List<? extends Number> list3 = new ArrayList<>();
    @Test
    public void test() {
        Field[] declaredFields = ParameterizedTypeTest.class.getDeclaredFields();
        for (Field field : declaredFields) {
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType) {
                System.out.println("字段【" + field.getName() + "】的类型是ParameterizedType，输出如下：");
                ParameterizedType pT = (ParameterizedType) genericType;
                System.out.println("getRawType()输出: " + pT.getRawType().getTypeName());
                System.out.println("getOwnerType()输出: " + pT.getOwnerType());
                Type[] actualTypeArguments = pT.getActualTypeArguments();
                System.out.println("getActualTypeArguments()输出: ");
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println("    " + actualTypeArgument.getTypeName());
                }
                System.out.println("---------换行换行换行-----------");
            }
        }
    }
}
