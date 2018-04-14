package com.univ.reflection;

import org.junit.Test;

import java.lang.reflect.*;

/**
 * Univ
 * 16/8/31 16:21
 */
public class ReflectionTest {


    /**
     * 获取Class对象
     */
    @Test
    public void test1() {

        //1.利用Class.forName()方法
        try {
            Class<?> clazz = Class.forName("com.univ.reflection.Sample");
            System.out.println(clazz.getName());//com.univ.reflection.Sample
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2.利用Sample类
        Sample.class.getName();//com.univ.reflection.Sample

        //3.利用Sample类对象,注意,getClass()是Object类中的方法
        new Sample().getClass().getName();//com.univ.reflection.Sample
    }

    /**
     * 利用反射获取定义所有(部分)的字段,不包括从父类继承的字段
     */
    @Test
    public void test2() throws NoSuchFieldException {
        Class<Sample> clazz = Sample.class;
        /**
         * 获取此类自身定义的所有的字段
         */
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            //int modifiers = field.getModifiers();//一般好像用不到字段的修饰符
            field.getType().getName();  //字段的类型
            field.getName();    //字段的名称
        }

        /**
         * 获取某个特定的字段: 字段的坐标就是其名称
         */
        Field pName = clazz.getDeclaredField("privateName");
        pName.getName();  //privateName
    }

    /**
     * 利用反射获取定义所有的方法(不包含构造函数)
     */
    @Test
    public void test3() throws Exception {
        Class<Sample> clazz = Sample.class;

        /**
         * 获取此类自身定义的所有的方法
         */
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            method.getName();   //方法的名称
            method.getParameterCount(); //方法参数的个数
            method.getGenericReturnType();  //方法返回值的类型

            //获取方法的所有参数
            Parameter[] parameters = method.getParameters();
            for (Parameter param : parameters) {
                //param.getName();//参数名称不重要(以arg0,arg1表示),重要的是类型
                param.getType().getName();
            }
        }

        /**
         * 获取某个特定的方法:方法的坐标(签名)是其名称和参数(包含个数与类型)
         */
        Method setPublicAge = clazz.getDeclaredMethod("setPublicAge", int.class);
        setPublicAge.getName(); //setPublicAge
    }

    /**
     * 利用反射获取定义的所有的构造函数
     */
    @Test
    public void test4() {
        Class<Sample> clazz = Sample.class;

        //获取所有的构造函数
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : declaredConstructors) {
            constructor.getName();
            Parameter[] constructorParameters = constructor.getParameters();
            for (Parameter param : constructorParameters) {
                param.getType().getName();
            }
        }

        //获取一个参数的构造函数:构造函数也是一个方法,因此其于普通方法的坐标是一样的。
        try {
            Constructor<Sample> constructor = clazz.getDeclaredConstructor(String.class);
            Parameter[] parameters = constructor.getParameters();
            for (Parameter p : parameters) {
                p.getType().getName();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    /**
     * 利用反射创建对象
     */
    @Test
    public void test5() throws Exception {
        Class<Sample> clazz = Sample.class;

        //1.利用newInstance()方法
        Sample sample = clazz.newInstance();
        System.out.println(sample.staticCount);

        //2.利用构造函数
        Constructor<Sample> constructor = clazz.getDeclaredConstructor(String.class);
        constructor.newInstance("aaa");
    }

    /**
     * 利用反射调用方法;
     */
    @Test
    public void test6() throws Exception {
        Class<Sample> clazz = Sample.class;
        Method setPublicAge = clazz.getDeclaredMethod("setPublicAge", int.class);

        Sample sample = new Sample();
        setPublicAge.invoke(sample, 23);
        sample.getPublicAge();  //23
    }
}
