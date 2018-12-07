package com.univ.thirdutils.orika;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

/**
 * @author univ
 * @datetime 2018/11/27 11:45 AM
 * @description
 * 注意：
 * 1. 用来进行属性拷贝的bean务必给各属性提供getter/setter方法，否则拷贝不成功！
 */
public class OrikaTest {

    /*
    1. 获取MapperFactory；
    2. 使用MapperFactory声明拷贝规则（在哪两个类之间进行属性拷贝，将源bean的哪个属性拷贝到目标bean的哪个属性中）
    3. 获取MapperFacade；
    4. 获取MapperFacade的map方法进行拷贝；
     */
    @Test
    public void basic() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        SourceBean sourceBean = new SourceBean("source_bean_name", 10, 175, list);

        // 使用orika
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        // 指明要在哪两个类之间进行属性拷贝，将SourceBean类型的对象拷贝到DestinationBean类型的对象中
        mapperFactory.classMap(SourceBean.class, DestinationBean.class)
                .field("name", "name2") // 显示指定将源bean哪个属性的值拷贝到目标bean的哪个属性上，默认是同名属性，见输出中的age值
                .field("list", "listNew")
                .byDefault()
                .register();

        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        DestinationBean destinationBean = new DestinationBean();
        mapperFacade.map(sourceBean, destinationBean);
        System.out.println(destinationBean);
        // DestinationBean{name2='source_bean_name', age=10, listNew=[1, 2, 3, 4, 5], width=null}
        /*
        如此可知：
        1. 虽然age字段没有通过field()进行指定，但仍然也被拷贝的，说明同名属性不用进行指定；
        2. 源bean中有height，目标bean中没有，所以不会拷贝，同样，目标bean中有width属性，但源bean中没有，其值为默认的空值；
         */

        // 也可以直接给第二个参数传class，此时返回一个转换后的class对应的实例
        DestinationBean destinationBean1 = mapperFacade.map(sourceBean, DestinationBean.class);

    }

    /**
     * 可以将某个字段直接映射成另一个类成员的属性
     */
    @Test
    public void test2() {
        A a = new A();
        DefaultMapperFactory defaultMapperFactory = new DefaultMapperFactory.Builder().build();
        defaultMapperFactory.classMap(A.class, C.class)
                .field("name", "b.name2")   // 将A类中的name字段映射成C类中b属性的name2字段
                .byDefault()
                .register();


        C c = new C();
        MapperFacade mapperFacade = defaultMapperFactory.getMapperFacade();
        mapperFacade.map(a,c);
        System.out.println(c);

    }
}

class SourceBean {
    private String name;
    private Integer age;
    private int height;
    private List<Integer> list;

    public SourceBean(String name, Integer age, int height, List<Integer> list) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.list = list;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}

class DestinationBean {
    private String name2;
    private Integer age;
    private List<Integer> listNew;
    private Integer width;

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Integer> getListNew() {
        return listNew;
    }

    public void setListNew(List<Integer> listNew) {
        this.listNew = listNew;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "DestinationBean{" +
                "name2='" + name2 + '\'' +
                ", age=" + age +
                ", listNew=" + listNew +
                ", width=" + width +
                '}';
    }
}

class A {
    private String name = "a name";

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class B {

    private String name2;

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName2() {
        return name2;
    }

    @Override
    public String toString() {
        return "B{" +
                "name2='" + name2 + '\'' +
                '}';
    }
}

class C {
    private B b = new B();

    public void setB(B b) {
        this.b = b;
    }

    public B getB() {
        return b;
    }



    @Override
    public String toString() {
        return "C{" +
                "b=" + b +
                '}';
    }
}

