package com.univ.reflection.basic;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;

/**
 * @author univ
 * @date 2021/3/31 10:41 上午
 * @description
 *
 * Q: Member接口是什么?
 * A: Member是Field、Method、Constructor的抽象
 */
public class MemberTest {
    protected String fieldName = "aaa";
    @Test
    public void test() {
        Field[] declaredFields = MemberTest.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            // 此Member的name
            System.out.println("Member#getName输出: " + declaredField.getName());
            // 此Member在哪个类中定义的
            System.out.println("Member#getDeclaringClass输出: " + declaredField.getDeclaringClass());
            // 此Member有哪些修饰符
            System.out.println("Member#getModifiers输出: " + Modifier.toString(declaredField.getModifiers()));
        }
    }
}
