package com.univ.jdkutils.introspector;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PropertyDescriptorTest {

    @Test
    public void test() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Person person = new Person();
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor("name", Person.class);
        System.out.println("属性名为：" + propertyDescriptor.getName());

        System.out.println("属性类型的Class为：" + propertyDescriptor.getPropertyType());

        // 获取getter方法
        Method readMethod = propertyDescriptor.getReadMethod();
        // 用getter方法获取值
        System.out.println("属性name的旧值为：" + readMethod.invoke(person));
        // 获取setter方法
        Method writeMethod = propertyDescriptor.getWriteMethod();
        // 用setter方法设值
        writeMethod.invoke(person, "abc");

        System.out.println("属性name的新值为：" + readMethod.invoke(person));
    }

    @Getter
    @Setter
    @ToString
    class Person {
        private String name;
    }
}
