package com.univ.thirdutils.orika;

import org.junit.Test;

import ma.glasnost.orika.impl.generator.VariableRef;
import ma.glasnost.orika.metadata.Property;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeFactory;
import ma.glasnost.orika.property.IntrospectorPropertyResolver;

/**
 * 用来测试orika的基础小类，供查看源码时备用
 * @author univ
 * 2021/11/2 10:16 上午
 */
public class OrikaFrameworkTest {

    /**
     * 1. 获取Property(orika定义的)实例-需借助PropertyResolver，默认实现为IntrospectorPropertyResolver
     */
    @Test
    public void testProperty() {
        IntrospectorPropertyResolver propertyResolver = new IntrospectorPropertyResolver();
        Property property = propertyResolver.getProperty(SourceBean.class, "name");
        // 看看property的常见方法
        System.out.println("getter: " + property.getGetter());// getter: getName()
        System.out.println("setter: " + property.getSetter());// setter: setName(%s)
    }

    /**
     * 2. 获取VariableRef-需有上述的Property属性
     */
    @Test
    public void testVariableRef() {
        IntrospectorPropertyResolver propertyResolver = new IntrospectorPropertyResolver();
        Property property = propertyResolver.getProperty(SourceBean.class, "name");

        // ??? 这里第二个入参是什么意思？？？
        VariableRef variableRef = new VariableRef(property, "source");
        String result = variableRef.assignIfPossible("age");// source.setName(((java.lang.String)age))
        System.out.println(result);//

    }

    /**
     * 3. 获取Type(orika定义的)实例-借助TypeFactory.valueOf即可
     */
    @Test
    public void testType() {
        Type<SourceBean> type = TypeFactory.valueOf(SourceBean.class);
        boolean anEnum = type.isEnum();
        System.out.println(anEnum);
    }
}
