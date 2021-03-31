package com.univ.reflection.basic;

import java.lang.reflect.Modifier;

import org.junit.Test;

/**
 * @author univ
 * @date 2021/3/31 10:22 上午
 * @description
 * 什么是修饰符：即修饰类、接口、方法等的public static abstract volatile；
 *
 * 使用方法：
 * 1. 通过Class.getModifiers(), Member.getModifiers()获取Class、Constructor、Method、Field等的修饰符；
 * 2. 利用Modifier操作这些修饰符
 */
public class ModifierTest {

    public static final String str = "aaa";

    @Test
    public void test() {
        // 1. 先通过Class.getModifiers()获取modifiers
        int modifiers = ModifierTest.class.getModifiers();

        // 2. 利用Modifier操作修饰符
        // 是否是(包含)public修饰符
        System.out.println("ModifierTest is public：" + Modifier.isPublic(modifiers));
        System.out.println("ModifierTest is abstract：" + Modifier.isAbstract(modifiers));
        // 用字符串的形式表示修饰符
        System.out.println("ModifierTest的修饰符为：" + Modifier.toString(modifiers));

        // 补充
        // 能放在类上的修饰符
        System.out.println("能放在类上的修饰符：" + Modifier.toString(Modifier.classModifiers()));
        System.out.println("能放在接口上的修饰符：" + Modifier.toString(Modifier.interfaceModifiers()));
        System.out.println("能放在方法上的修饰符：" + Modifier.toString(Modifier.methodModifiers()));
        System.out.println("能放在字段上的修饰符：" + Modifier.toString(Modifier.fieldModifiers()));
        System.out.println("能放在参数上的修饰符：" + Modifier.toString(Modifier.parameterModifiers()));
    }
}
