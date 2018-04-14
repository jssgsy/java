package com.univ.basic;

import org.junit.Test;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * Univ
 * 16/9/29 14:35
 */

/**
 * 演示jdk中的Properties类的使用方法
 */
public class PropertiesTest {

    @Test
    public void fn() throws IOException {
        /**
         * 1. 使用iterator遍历
         *  keySet():以Set形式返回所有的key;
         *  entrySet():以Set形式返回所有的key-value;
         */
        Properties p1 = System.getProperties();
        Set<Object> keys = p1.keySet();
        Iterator<Object> iterator = keys.iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = p1.getProperty(key);
            System.out.println(key + " = " + value);
        }

        /**
         * 2. 使用Enumeration遍历
         *  propertyNames():以Enumeration的形式返回所有的key;
         *  elements():以Enumeration的形式返回所有的value;
         */
        Properties p2 = System.getProperties();
        Enumeration<?> enumeration = p2.elements();
        while (enumeration.hasMoreElements()) {
            Object element = enumeration.nextElement();
            System.out.println(element);
        }
    }

}
