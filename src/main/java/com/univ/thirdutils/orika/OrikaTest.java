package com.univ.thirdutils.orika;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import lombok.Data;
import lombok.NoArgsConstructor;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;

/**
 * 注意：
 * 1. 用来进行属性拷贝的bean务必给各属性提供getter/setter方法，否则拷贝不成功！
 * 2. 注册转换器registerConverter与classMap中的customize的区别：
 *  注册转换器：用来处理将一个类型转换成另一个类型（比如内部类的转换，用customize不行）；
 *  customize：要转换的字段间不是简单的复制，此时可用来做一些定制化的转换
 * 3. 要转换的数据对象最好不要定义成内部类，容易踩坑
 *
 * @author univ
 * @datetime 2018/11/27 11:45 AM

 */
public class OrikaTest {

    private MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

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
     * 不用mapperFactory.classMap也是ok的，如果两个要转换的类字段不同时才用
     */
    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        SourceBean sourceBean = new SourceBean("source_bean_name", 10, 175, list);
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        DestinationBean d = mapperFacade.map(sourceBean, DestinationBean.class);
        System.out.println(d);

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

    /**
     *  List<A> ---> List<B>
     *  单个对象转换使用map方法，
     *  一个list转换成另一个list，使用mapAsList方法
     */
    @Test
    public void test4() {
        SourceBean s1 = new SourceBean("aaa", 10, 10, Arrays.asList(1,2,3,4));
        SourceBean s2 = new SourceBean("bbb", 20, 20, Arrays.asList(10,20,30,40));
        SourceBean s3 = new SourceBean("ccc", 30, 30, Arrays.asList(100,200,300,400));

        List<SourceBean> sourceBeans = Arrays.asList(s1, s2, s3);
        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(SourceBean.class, DestinationBean.class)
                .field("name", "name2")
                .field("list", "listNew")
                .field("height", "width")
                .byDefault()
                .register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        /**
         * 调用mapAsList方法
         * 注意第二个参数是DestinationBean.class，不是List<DestinationBean>.class
         */
        List<DestinationBean> destinationBeans = mapperFacade.mapAsList(sourceBeans, DestinationBean.class);
        System.out.println(destinationBeans);

    }

    /**
     * 映射行为自定义
     */
    @Test
    public void test5() {
        DefaultMapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(SourceBean.class, DestinationBean.class)
                .customize(new CustomMapper<SourceBean, DestinationBean>() {
                    // 自定义的内容会在映射完成之后再执行，注意观察输出
                    @Override
                    public void mapAtoB(SourceBean sourceBean, DestinationBean destinationBean, MappingContext context) {
                        System.out.println("mapAtoB");
                        super.mapAtoB(sourceBean, destinationBean, context);
                        destinationBean.setName2(sourceBean.getName() + "_postfix");
                    }
                    // 这里用默认的行为即可
                    @Override
                    public void mapBtoA(DestinationBean destinationBean, SourceBean sourceBean, MappingContext context) {
                        super.mapBtoA(destinationBean, sourceBean, context);
                    }
                }).field("name", "name2")
                .byDefault()
                .register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        SourceBean sourceBean = new SourceBean("univ", 10, 10, Arrays.asList(1, 2, 3));
        DestinationBean destinationBean = mapperFacade.map(sourceBean, DestinationBean.class);
        System.out.println(destinationBean);

    }

    /**
     * 继承时转换
     * 和预料中的一样，子类会直接继承父类中的字段进行转换，如果子类有覆盖字段则以子类中的为准
     *
     */
    @Test
    public void test6() {
        mapperFactory.classMap(Derived.class, DerivedDest.class)
                .byDefault()
                .register();
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        Derived d = new Derived();
        DerivedDest derivedDest = mapperFacade.map(d, DerivedDest.class);
        System.out.println(derivedDest);

    }

    /**
     * 演示显示指定父类的映射关系，哪些情况下子类的映射也会命中
     */
    @Test
    public void test7() {

        // 显示指定父类的映射
        mapperFactory.classMap(SourceBean.class, DestinationBean.class)
                // 为便于观察结果，这里利用输出来观察
                .customize(new CustomMapper<SourceBean, DestinationBean>() {
                    @Override
                    public void mapAtoB(SourceBean sourceBean, DestinationBean destinationBean, MappingContext context) {
                        super.mapAtoB(sourceBean, destinationBean, context);
                        System.out.println("---mapAtoB---");
                    }

                    @Override
                    public void mapBtoA(DestinationBean destinationBean, SourceBean sourceBean, MappingContext context) {
                        super.mapBtoA(destinationBean, sourceBean, context);
                        System.out.println("---mapBtoA---");
                    }
                })
                .byDefault()
                .register();

        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        SourceBean sourceBean = new SourceBean();
        sourceBean.setAge(18);
        DestinationBean destinationBean = new DestinationBean();
        ChildSourceBean childSourceBean = new ChildSourceBean();
        ChildDestBean childDestBean = new ChildDestBean();

        // 以下均命中
        mapperFacade.map(sourceBean, DestinationBean.class);// ---mapAtoB---
        mapperFacade.map(sourceBean, ChildDestBean.class);// ---mapAtoB---

        mapperFacade.map(childSourceBean, DestinationBean.class);// ---mapAtoB---
        mapperFacade.map(childSourceBean, ChildDestBean.class);// ---mapAtoB---

        mapperFacade.map(destinationBean, SourceBean.class);// ---mapBtoA---
        mapperFacade.map(destinationBean, ChildSourceBean.class);//---mapBtoA---

        mapperFacade.map(childDestBean, SourceBean.class);// ---mapBtoA---
        mapperFacade.map(childDestBean, ChildSourceBean.class);//---mapBtoA---
    }

}

@Data
@NoArgsConstructor
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
}

@Data
class DestinationBean {
    private String name2;
    private Integer age;
    private List<Integer> listNew;
    private Integer width;
}

@Data
class ChildSourceBean extends SourceBean {
    private String childName;
}

@Data
class ChildDestBean extends DestinationBean {
    private String childName;
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

@Data
class B {
    private String name2;
}

@Data
class C {
    private B b = new B();
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


// ----------------------继承时转换----------------------
@Data
class Base {
    private Long id = 10l;
    private String name = "liu";
    private List<Integer> list = Arrays.asList(1, 2, 3, 4);
}
@Data
class Derived extends Base {
    /**
     * 父类中也有id字段
     */
    private Long id = 20l;
    private String address = "hubei";
}
@Data
class DerivedDest {
    private Long id;
    private String name;
    private List<Integer> list;
    private String address;
}
// ----------------------继承时转换----------------------