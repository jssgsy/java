package com.univ.reflection.basic;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author univ
 * date 2024/9/20
 */
public class FieldTest {
    private String name = "default_name";

    /**
     * 调用get或者set之前务必先调用field.setAccessible(true);
     */
    @Test
    public void getAndSet() throws NoSuchFieldException, IllegalAccessException {
        FieldTest obj = new FieldTest();
        Field field = FieldTest.class.getDeclaredField("name");
        // 应该就在内部，所以这里不调用也ok，但实际中这里不要少
        field.setAccessible(true);
        String name = (String) field.get(obj);
        System.out.println("old name: " + name);

        field.set(obj, "hello");
        String name2 = (String) field.get(obj);
        System.out.println("new name: " + name2);
    }
}
