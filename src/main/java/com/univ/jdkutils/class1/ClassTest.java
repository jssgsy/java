package com.univ.jdkutils.class1;

import org.junit.Test;

/**
 * @author univ
 * @date 2021/3/25 8:00 下午
 * @description
 */
public class ClassTest {

    String strClazz = "java.lang.String";

    @Test
    public void isInstance() throws ClassNotFoundException {
        C1 c1 = new C1();
        System.out.println(c1 instanceof C1); //true
        System.out.println(c1 instanceof F1); //true
        System.out.println(C1.class.isInstance(c1)); //true
        System.out.println(F1.class.isInstance(c1)); //true

        String str = new String("aaa");
        // 此时没法直接使用静态的instanceof操作符
        System.out.println(Class.forName(strClazz).isInstance(str)); //true
    }

    @Test
    public void isAssignableFrom() {
        // C1是不是C1的父类
        System.out.println(C1.class.isAssignableFrom(C1.class));
        // F1是不是C1的父类
        System.out.println(F1.class.isAssignableFrom(C1.class));
        // 接口I1是不是C1的父类
        System.out.println(I1.class.isAssignableFrom(C1.class));


    }
    interface I1{}
    static class F1{}
    static class C1 extends F1 implements I1 {}
}

