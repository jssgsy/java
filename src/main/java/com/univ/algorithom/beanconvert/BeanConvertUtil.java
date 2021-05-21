package com.univ.algorithom.beanconvert;

import java.beans.BeanInfo;
import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author univ
 * @date 2021/5/21 10:21 上午
 * @description
 *
 * 支持：
 * 1. pojo的继承；
 * 2. 支持自定义字段映射；
 *
 * 不支持：
 * 1. pojo对象嵌套
 */
public class BeanConvertUtil {

    /**
     * 支持自定义字段映射
     */
    public static Map<String, String> FIELD_MAP = new HashMap<String, String>() {{
        put("name", "nameNew");
        put("age", "ageNew");
    }};

    public static Destination mapSourceToDestination(Source source) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        // 设置值时需要知道目标字段的setter方法，反射做不到，因此需要用内省。
        BeanInfo sourceBeanInfo = Introspector.getBeanInfo(Source.class);
        PropertyDescriptor[] sourcePropertyDescriptors = sourceBeanInfo.getPropertyDescriptors();
        Map<String, PropertyDescriptor> sourceDescriptorMap = Arrays.stream(sourcePropertyDescriptors)
                .collect(Collectors.toMap(FeatureDescriptor::getName, Function.identity()));

        BeanInfo destBeanInfo = Introspector.getBeanInfo(Destination.class);
        PropertyDescriptor[] destPropertyDescriptors = destBeanInfo.getPropertyDescriptors();
        Map<String, PropertyDescriptor> destDescriptorMap = Arrays.stream(destPropertyDescriptors)
                .collect(Collectors.toMap(FeatureDescriptor::getName, Function.identity()));

        Destination destination = new Destination();

        for (PropertyDescriptor sourceField : sourcePropertyDescriptors) {
            String sourceFieldName = sourceField.getName();
            // 直接过滤到class字段
            if ("class".equals(sourceFieldName)) {
                continue;
            }
            String mappedFieldName = FIELD_MAP.getOrDefault(sourceFieldName, sourceFieldName);
            Class<?> sourceFieldType = sourceField.getPropertyType();
            for (PropertyDescriptor destField : destPropertyDescriptors) {
                if (destField.getName().equals(mappedFieldName) && destField.getPropertyType().isAssignableFrom(sourceFieldType)) {
                    // 获取目标字段的setter方法
                    Method setterMethod = destDescriptorMap.get(destField.getName()).getWriteMethod();

                    // 获取源字段的getter方法
                    Method getterMethod = sourceDescriptorMap.get(sourceFieldName).getReadMethod();

                    // 赋值
                    setterMethod.invoke(destination, getterMethod.invoke(source));
                    break;
                }
            }
        }
        return destination;
    }
}
