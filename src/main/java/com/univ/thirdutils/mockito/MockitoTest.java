package com.univ.thirdutils.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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

    @Before
    public void setUp() {
        // 有了此句代码，则会自动mock出被@Mock, @Spy, @Captor, @InjectMocks注解的对象
        // 此时使用@Mock, @Spy, @Captor, @InjectMocks时不用再显示new了
        MockitoAnnotations.initMocks(this);
    }

    /**
     * 使用mock方法模拟的对象的方法不会被真正执行，所以测试时也进不了debug模式，
     * 通过@InjectMocks模拟的对象就有了（spy）的功能，即会被真正执行，适合用来模拟service层对象，毕竟单测测的就是service层对象的业务方法，如果不能走进去则单测便也没了意义。
     * 注意，这里要new Demo()一下
     */
    @InjectMocks
    private Demo demo = new Demo();

    @Mock
    private DemoDao demoDao;

    @InjectMocks
    private A a;

    // 此时对象b会被装配到对象a中
    @Mock
    private B b;

    @Test
    public void test1() {

        // 设定，当代码中调用demoDao.getById方法，且并传入任何int类型的参数时返回demo对象
        Mockito.when(b.getName(anyString())).thenReturn("aaa");

        /**
         * 1. 核心：内部调用了b.getName("hello")，假如此方法是需要向数据库获取的，在测试环境下，往往不能或者很难从数据库、缓存、dubbo等获取数据源，单元测试的核心就在于mock掉这些依赖于环境的对象，并设定将这些对象的方法被调用时返回指定的结果，这样就可以独立于这些环境对象来测试自己的业务流程！单元测试测试的是自己的业务流程
         * 2. 上面已经设定了当调用b.getName时会返回aaa，因此当业务方法business中依赖了b.getName时就能使流程往下走
         */
        a.business();
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

    /**
     * spy
     *  mock方法是模拟出一个“替代”对象，而spy是模拟出一个“真实”的对象，即其方法会真正被调用
     *  spy方法可以包装一个真实的Java对象, 并返回一个包装后的新对象. 若没有特别配置的话, 对这个新对象的所有方法调用, 都会委派给实际的 Java 对象
     */
    @Test
    public void test4() {

        // 模拟出一个真正的DemoService对象
        DemoService demoService = spy(DemoService.class);

        // 需要这句代码是因为DemoService内部有DemoDao对象，getById方法内部会调用到
        demoService.setDemoDao(demoDao);


        when(demoDao.getById(anyInt())).thenReturn(new Demo());

        // 此时会调用真正的sayHello方法，因此可以调试进入
        demoService.sayHello(23);
    }

    /**
     * 调试
     */
    @Test
    public void test5() {
        // 这里可以打断点进入调试,因为demo是被@InjectMocks注入的，有stub的功能
        demo.sayHello("world");
    }

    /**
     * doThrow(ExceptionX).when(x).methodCall
     *  含义是: 当调用了 x.methodCall 方法后, 抛出异常 ExceptionX
     */
    @Test
    public void test6() {
        Demo demo = mock(Demo.class);

        // 设定：当调用demo对象的sayHello方法时抛出异常
        doThrow(new RuntimeException("不能调用此方法")).when(demo).sayHello(anyString());

        // 调用sayHello方法，此时会抛异常
        // demo.sayHello("aaa");
    }

    /**
     * 演示 @InjectMocks与@Mock的重要作用
     */
    @Test
    public void test7() {
        // 在这里打断点

        // when(b.getName(anyString())).thenReturn("aaaa");
        a.business();
    }


}

/**
 * 模拟service
 */
class DemoService {

    private DemoDao demoDao;

    public String sayHello(int id) {
        // 业务逻辑处理

        // 从数据库、索引，dubbo等获取数据源
        Demo demo = demoDao.getById(id);
        System.out.println(demo);

        // 业务逻辑处理
        
        return "hello";
    }

    public void setDemoDao(DemoDao demoDao) {
        this.demoDao = demoDao;
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

/**
 * 业务类
 */
class A {
    private B b;
    // 这里是需要测试的业务逻辑
    public void business() {
        // 业务逻辑处理

        // 依赖的数据源对象
        String result = b.getName("hello");
        System.out.println(result);


        // 业务逻辑处理
    }
}

/**
 * 业务类A依赖的“数据源”对象
 */
class B {
    public String getName(String name) {
        return name;
    }

}