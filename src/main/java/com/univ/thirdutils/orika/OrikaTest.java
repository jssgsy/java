package com.univ.thirdutils.orika;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;

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

    /**
     * orika的转换器：定义在两种类型中转换的规则。
     * 现实需求：
     * 1. 将数据库中的int，tinyint转换成java bean中的enum，
     * 2. int与boolean互转;
     * orika就是用来做这样事情的。
     * 用法：
     * 1. orika默认提供了很多转换器，如DateTimeFormatter， BidirectionalConverter(双向转换)等等;
     * 2. 自定义转换器，需要继承orika提供的CustomConverter接口(单向转换)(还可以继承orika提供的现成的转换器）
     * 3. 如果实现自定义转换器，则一般继承BidirectionalConverter实现双向转换即可
     */
    @Test
    public void test3() {
        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        // 全局注册
        mapperFactory.getConverterFactory().registerConverter(new MyConvertor());

        mapperFactory.classMap(D.class, E.class)
                .field("num", "color")
                .byDefault()
                .register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();

        // 演示int类型转换成某个具体的enum类型
        E e = mapperFacade.map(new D(), E.class);
        System.out.println(e);

        // 演示某个具体的enum类型转换成int类型
        Integer i = mapperFacade.map(Color.BLUE, Integer.class);
        System.out.println(i);
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


/**
 * 继承自BidirectionalConverter便可实现双向转换，缓过泛型
 */
class MyConvertor extends BidirectionalConverter<Integer, Color>{

    /**
     * 将Integer转换成Color类型
     * @param source
     * @param destinationType
     * @param mappingContext
     * @return
     */
    @Override
    public Color convertTo(Integer source, Type<Color> destinationType, MappingContext mappingContext) {
        switch (source) {
        case 1:
            return Color.RED;
        case 2:
            return Color.GREEN;
        default:
            return Color.BLUE;
        }
    }

    /**
     * 将Color类型转换成int
     * @param source
     * @param destinationType
     * @param mappingContext
     * @return
     */
    @Override
    public Integer convertFrom(Color source, Type<Integer> destinationType, MappingContext mappingContext) {
        return source.ordinal();
    }
}

class D {
    private int num = 1;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "D{" +
                "num=" + num +
                '}';
    }
}

class E {
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "E{" +
                "color=" + color +
                '}';
    }
}

enum Color {

    RED(1),GREEN(2),BLUE(3);
    private int code;

    Color(int code) {
        this.code = code;
    }
}