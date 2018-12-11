package com.univ.thirdutils.mockito;

import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author univ
 * @datetime 2018/12/10 9:38 PM
 * @description
 *
 * 一般使用 Mockito 需要执行下面三步
 *  1. 模拟并替换测试代码中外部依赖：使用mock方法或者相应的注解
 *  2. 执行测试代码
 *  3. 验证测试代码是否被正确的执行
 */
/*
mockito的常用方法：
1. mock：mock出一个对象；
2.  when(mock.someMethod()).thenReturn(X);设定当执行mock.someMethod()方法时返回X
 */
public class MockitoTest {

    @Test
    public void test1() {
        // 需要啥对象就调用mock方法mock出此对象
        DemoSerivce demoSerivce = Mockito.mock(DemoSerivce.class);

        DemoDao demoDao = Mockito.mock(DemoDao.class);
        Demo demo = new Demo();
        demo.setAge(20);
        demo.setName("aaa");
        // 设定，当代码中调用emoDao.getById方法，且并传入任何int类型的参数时返回demo对象
        Mockito.when(demoDao.getById(Mockito.anyInt())).thenReturn(demo);

        // 设定，当代码中调用demoSerivce.sayHello方法，且并传入参数为1时返回good
        //Mockito.when(demoSerivce.sayHello(1)).thenReturn("good");

        /**
         * 1. 核心：内部调用了demoDao.getById，此方法是需要向数据库获取的，在测试环境下，往往不能或者很难从数据库、缓存、dubbo等获取数据源，单元测试的核心就在于mock掉这些依赖于环境的对象，并设定将这些对象的方法被调用时返回指定的结果，这样就可以独立于这些环境对象来测试自己的业务流程！单元测试测试的是自己的业务流程
         * 2. 上面已经设定了当调用demoDao.getById时会返回demo，因此当业务方法sayHello中依赖了demoDao.getById时就能使流程往下走
         */
        String s = demoSerivce.sayHello(1);
        System.out.println(s);
    }

}

/**
 * 模拟service
 */
class DemoSerivce {

    private DemoDao demoDao;

    public String sayHello(int id) {
        // 业务逻辑处理

        // 从数据库、索引，dubbo等获取数据源
        Demo demo = demoDao.getById(id);
        System.out.println(demo);

        // 业务逻辑处理
        
        return "hello";
    }
}

/**
 * 模拟dao
 */
interface DemoDao {

    Demo getById(int id);

    List<Demo> getByDemo(Demo demo);

    List<Demo> getByNameAndAge(String name, int age);
}

/**
 * 模拟实体
 */
class Demo{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}