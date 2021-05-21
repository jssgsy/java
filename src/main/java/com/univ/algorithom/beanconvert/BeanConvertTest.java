package com.univ.algorithom.beanconvert;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

import org.junit.Test;

/**
 * @author univ
 * @date 2021/5/21 2:20 下午
 * @description
 */
public class BeanConvertTest {

    @Test
    public void basic() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        Source source = new Source();
        source.setAge(10);
        source.setName("univ");
        source.setSuccess(100);

        Destination destination = BeanConvertUtil.mapSourceToDestination(source);
        System.out.println(destination);
    }

    @Test
    public void fn() throws IntrospectionException {
        BeanInfo sourceBeanInfo = Introspector.getBeanInfo(Source.class);
        PropertyDescriptor[] propertyDescriptors = sourceBeanInfo.getPropertyDescriptors();

        Arrays.stream(propertyDescriptors).forEach(t -> {
            System.out.println(t.getName());
        });
    }
}
