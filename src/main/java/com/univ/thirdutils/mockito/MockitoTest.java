package com.univ.thirdutils.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
    @InjectMocks
    private DemoService demoService;

    // 此时对象demoDao会被装配到对象demoService中，这个就是@InjectMocks的作用之一
    @Mock
    private DemoDao demoDao;

    @Test
    public void test1() {
        // mock掉外部依赖
        Mockito.when(demoDao.getName(Mockito.anyInt())).thenReturn("aaa");
        demoService.fn();
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
}

/**
 * 模拟dao
 */
interface DemoDao {
    String getName(int id);
}