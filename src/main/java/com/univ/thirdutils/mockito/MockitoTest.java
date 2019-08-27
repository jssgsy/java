package com.univ.thirdutils.mockito;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

/**
 * @author univ
 * @datetime 2018/12/10 9:38 PM
 * @description mockito使用注解
 */
/*
mockito的常用方法：
1. mock：mock出一个对象；
2. when(mock.someMethod()).thenReturn(X);设定当执行mock.someMethod()方法时返回X
 */
// @RunWith(MockitoJUnitRunner.class)
public class MockitoTest {

    @Before
    public void setUp() {
        // 有了此句代码，则会自动mock出被@Mock, @Spy, @Captor, @InjectMocks注解的对象
        // 此时使用@Mock, @Spy, @Captor, @InjectMocks时不用再显示new了
        MockitoAnnotations.initMocks(this);

        // 在测试类上使用@RunWith(MockitoJUnitRunner.class)也能达到一样的效果
    }

    /**
     * 通过@InjectMocks模拟的对象就有了（spy）的功能，即会被真正执行，适合用来模拟单测的对象
     */
    @Spy // 是可以同时和@InjectMocks使用的，此时便可实现@Spy拥有的mock部分方法的能力
    @InjectMocks
    private DemoService demoService;
    /**
     * 注意补充：一般不要@Spy与@InjectMocks结合使用，实际工程中，很有可能有private方法，此时需要通过反映的方式进行单测，
     * 此时若@Spy与@InjectMocks一起使用则反射则不要用。为了达到能用反射对private方法进行单测，同时能mock掉依赖的public方法(假设fn2内部调用public的fn1方法)，做法如下：
     * 1. 使用@InjectMocks；
     * 2. 在fn2方法中，demoService = spy(demoService);doReturn("fn1_result").when(demoService).fn1();
     */

    // 此时对象demoDao会被装配到对象demoService中，这个就是@InjectMocks的作用之一
    @Mock
    private DemoDao demoDao;

    @Test
    public void test1() {
        // mock掉外部依赖
        Mockito.when(demoDao.getName(Mockito.anyInt())).thenReturn("aaa");
        demoService.fn();
    }

    /**
     * 同时使用@Spy与@InjectMocks来实现只mock部分方法。重要！
     * 和@Spy一样，此时要使用
     */
    @Test
    public void test2() {
        Mockito.doReturn("xxx").when(demoService).fn1();

        // 要测试的是fn2方法，但其内部依赖了fn1(当然fn1也会有专门的单测)，所以在这里不用再测试fn1了，将其mock掉，专注单测fn2方法
        String s = demoService.fn2();
        assertEquals("xxx", s);
    }

}

/**
 * 模拟service
 */
class DemoService {
    private DemoDao demoDao;

    public void fn() {
        int i = 1;
        int j = 1;
        String name = demoDao.getName(100);
        System.out.println(name);
    }

    public String fn1() {
        // 有复杂的逻辑，经过计算得出的值
        return "aaa";
    }

    public String fn2() {
        int i = 1;
        // 不用去实际调用fn1，fn1也只是一个依赖而已，需要mock掉
        fn1();
        int j = 1;
        return fn1();
    }
}

/**
 * 模拟dao
 */
interface DemoDao {
    String getName(int id);
}