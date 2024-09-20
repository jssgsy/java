package com.univ.reflection.basic;

import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * @author univ
 * date 2024/9/20
 */
@Data
public class ConstructorTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 获取无参构造函数
        System.out.println("---下面是有参构造函数的输出---");
        Constructor<ConstructorTest> c = ConstructorTest.class.getConstructor();
        System.out.println("c.getName(): " + c.getName());
        System.out.println("c.getModifiers(): " + Modifier.toString(c.getModifiers()));
        System.out.println("c.getDeclaringClass(): " + c.getDeclaringClass());
        ConstructorTest instance = c.newInstance();
        System.out.println(instance);

        System.out.println();
        System.out.println("---下面是有参构造函数的输出---");
        // 是int.class不能用Integer.class
        Constructor<ConstructorTest> c1 = ConstructorTest.class.getConstructor(String.class, int.class);
        for (Class<?> parameterType : c1.getParameterTypes()) {
            System.out.println("parameterType: " + parameterType.getName());
        }
        ConstructorTest instance1 = c1.newInstance("hello", 10);
        System.out.println(instance1);
    }

    private String name = "default_name";
    // 原始类型
    private int age = 20;

    public ConstructorTest() {
    }

    public ConstructorTest(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
