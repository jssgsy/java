package com.univ.jdkutils.introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;

import org.junit.Test;

/**
 * @author univ
 * 2021/7/22 5:00 下午
 */
public class IntrospectorTest {

    /**
     * 1. getBeanInfo()会分析父类的字段、方法
     * 2. getPropertyDescriptors()返回的字段中必然包含class属性，且其没有writeMethod，实际使用中注意去除
     *
     * @throws IntrospectionException
     */
    @Test
    public void testGetPropertyDescriptors() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if ("class".equals(propertyDescriptor.getName())) {
                continue;
            }
            System.out.println(propertyDescriptor.getName() + " : " + propertyDescriptor.getReadMethod().getName() + " - " + propertyDescriptor.getWriteMethod().getName());
        }

    }

    /**
     * 包含父类的read、write方法，同时能获取到Object类的所有方法
     *
     * @throws IntrospectionException
     */
    @Test
    public void testGetMethodDescriptors() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        for (MethodDescriptor methodDescriptor : methodDescriptors) {
            System.out.println(methodDescriptor.getName());
        }
    }
}
