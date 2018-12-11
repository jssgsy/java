package com.univ.thirdutils.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Test;
import org.mockito.Matchers;
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
 *
 * 注意：
 *  1. 被mock对象的方法不会被实际执行，所以不能进到mock对象方法内部进行调试(也不需要)；
 */
/*
mockito的常用方法：
1. mock：mock出一个对象；
2. when(mock.someMethod()).thenReturn(X);设定当执行mock.someMethod()方法时返回X
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
    
    @Test
    public void test2() {
        Demo demo = mock(Demo.class);

        /*
        根据不同的输入返回不同的值
         */
        when(demo.sayHello("aaa")).thenReturn("aaa");
        when(demo.sayHello("bbb")).thenReturn("bbb");
        assertEquals("竟然失败了", "aaa", demo.sayHello("aaa"));
        assertEquals("竟然失败了", "bbb", demo.sayHello("bbb"));

        /**
         * 返回值不依赖于入参
         * anyString()
         * anyint()
         * ...
         * any()
         */
        when(demo.sayHello(anyString())).thenReturn("hello");
        assertEquals("竟然失败了", "hello", demo.sayHello("xyz"));
    }

    /**
     * 验证函数是否被调用
     * Mockito 会跟踪 mock 对象里面所有的方法和变量。所以我们可以用来验证函数在传入特定参数的时候是否被调用。这种方式的测试称行为测试，行为测试并不会检查函数的返回值，而是检查在传入正确参数时候函数是否被调用。
     *
     */
    @Test
    public void test3() {
        Demo demo = mock(Demo.class);

        // 调用方法
        demo.sayHello("aaa");
        demo.getAge();
        demo.getAge();
        demo.getAge();

        // 验证当传“aaa”时sayHello方法是否被调用
        verify(demo).sayHello(Matchers.eq("aaa"));

        // 验证getAge()方法被调用了三次，否则抛异常
        verify(demo, times(3)).getAge();

        // 验证getName()方法一次都没被调用过
        // demo.getName();
        verify(demo, never()).getName();


        /*
        类似的方法有：
            atLeastOnce();// 至少被调用一次
            atLeast(3); // 至少被调用3次
            atMost(3);  // 最多被调用3次
         */
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

    public String sayHello(String name) {
        return "hello, " + name;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}