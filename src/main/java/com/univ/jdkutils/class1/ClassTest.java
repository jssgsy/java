package com.univ.jdkutils.class1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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



    /**
     * 反射-method相关
     */
    @Test
    public void reflectMethod() {
        /**
         * 本类及父类(包含接口)的public方法(注意只有public方法)
         */
        Method[] methods = C1.class.getMethods();
        System.out.println("getMethods(): ");
        for (Method method : methods) {
            System.out.println("\t" + method.getName());
        }

        /**
         * 本类中定义的所有方法(包括public、protect、package、private等)，但不包含父类(包含接口)中的任何方法
         */
        Method[] declaredMethods = C1.class.getDeclaredMethods();
        System.out.println("getDeclaredMethods(): ");
        for (Method declaredMethod : declaredMethods) {
            System.out.println("\t" + declaredMethod.getName());
        }
    }

    @Test
    public void reflectConstructor() {
        /**
         * 本类的public构造(注意只有public方法)
         */
        Constructor<?>[] constructors = C1.class.getConstructors();
        System.out.println("getConstructors(): ");
        for (Constructor<?> constructor : constructors) {
            System.out.println("\t" + constructor);
        }

        /**
         * 本类的所有构造(包括public、protect、package、private等)
         */
        Constructor<?>[] declaredConstructors = C1.class.getDeclaredConstructors();
        System.out.println("getDeclaredConstructors(): ");
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println("\t" + declaredConstructor);
        }
    }

    /**
     * 反射-field相关
     */
    @Test
    public void reflectField() {
        Field[] fields = C1.class.getFields();
        System.out.println("getFields(): ");
        for (Field field : fields) {
            System.out.println("\t" + field);
        }

        Field[] declaredFields = C1.class.getDeclaredFields();
        System.out.println("getDeclaredFields(): ");
        for (Field declaredField : declaredFields) {
            System.out.println("\t" + declaredField);
        }
    }


}


interface I1{
    String fnI1(Integer i);
}
class F1{
    Integer age;

    /**
     * public方法
     */
    public Integer publicFnF1(String str){return 1;}

    /**
     * 非public方法
     */
    protected Integer protectedFnF1(String str){return 1;}

    private F1(Integer age) { this.age = age; }
    public F1() {}

    // 字段相关
    public Integer pubFieldF1;
    protected String protectedFieldF1;
    private String privateFieldF1;


}
class C1 extends F1 implements I1 {
    @Override
    public String fnI1(Integer i) {
        return "fnI1";
    }
    private void privateC1(){}

    private C1(Integer age) { }
    public C1() {}

    // 字段相关
    public Integer pubFieldC1;
    protected String protectedFieldC1;
    private String privateFieldC1;
}
