package com.univ.thirdutils.mockito;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author univ
 * @date 2020/5/8 2:48 PM
 * @description 常见的mockito使用场景-日常使用经验搜集
 *
 * 注：在验证时，注意@RunWith(MockitoJUnitRunner.class)与@RunWith(PowerMockRunner.class)@PrepareForTest的切换
 */
// @RunWith(MockitoJUnitRunner.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest({ClassWithPrivateMethod.class/*, ClassWithPrivateMethod2.class*/})
public class CommonScenarioMockitoTest {

    @InjectMocks
    private ClassWithPrivateMethod classWithPrivateMethod;

    @Mock
    private Mo mo;

    @InjectMocks
    private ClassWithPrivateMethod2 classWithPrivateMethod2;

    /**
     * 场景：一个单独的类(没有继承任何其它类)，其中有私有方法，想达到的效果是
     *  a. 使用反射来单测private方法；
     *
     * 此时使用@RunWith(MockitoJUnitRunner.class)与@RunWith(PowerMockRunner.class)即可
     * (使用PowerMockRunner时可以不用使用@PrepareForTest，因为需要其的目的是需要mock掉private, static等方法)均可
     */
    @Test
    public void testWithPrivateMethodScenario1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<? extends ClassWithPrivateMethod> clz = classWithPrivateMethod.getClass();
        Method method = clz.getDeclaredMethod("pFn", String.class);
        method.setAccessible(true);
        Mockito.when(mo.fn()).thenReturn("这是mo.fn()被mock后的值");
        String result = (String) method.invoke(classWithPrivateMethod, "xxx");
        Assert.assertEquals(result, "xxx");
    }

    /**
     * 场景：一个有继承关系的类，其中有私有方法，想达到的效果是
     *  a. 使用反射来单测private方法；
     *
     * 注：
     *  1. 此时只能使用@RunWith(MockitoJUnitRunner.class)
     *  2. 如果使用@RunWith(PowerMockRunner.class)@PrepareForTest({ClassWithPrivateMethod2.class})，则classWithPrivateMethod2所依赖的所有mock对象(如这里的Mo)均为null；
     *      a. 很诡异(应该是因为@PrepareForTest生成操作字节码类时造成的，但当此类没有继承关系时却又是可以的(如ClassWithPrivateMethod)，需要深入了解@PrepareForTest的作用)
     *      b. 这样总结：当使用@PrepareForTest之后，mock与spy等的注入可能会失效！！！
     *          b1. 因此使用@PrepareForTest，需要考虑手动注入相应的依赖对象
     *
     * 注：单纯此种测试情况，只使用@RunWith(PowerMockRunner.class)而不用@PrepareForTest也是可以的
     */
    @Test
    public void testWithPrivateMethodScenario2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<? extends ClassWithPrivateMethod2> clz = classWithPrivateMethod2.getClass();
        Method method = clz.getDeclaredMethod("pFn", String.class);
        method.setAccessible(true);

        /**
         * 手动注入依赖时，可以使用@PrepareForTest({ClassWithPrivateMethod2.class})
         */
        // classWithPrivateMethod2.setMo(mo);

        Mockito.when(mo.fn()).thenReturn("这是mo.fn()被mock后的值");
        /**
         * 如果使用@RunWith(PowerMockRunner.class)@PrepareForTest({ClassWithPrivateMethod2.class})修饰，则此时pFn方法中的mo对象是null。会有NPE
         */
        String result = (String) method.invoke(classWithPrivateMethod2, "xxx");
        Assert.assertEquals(result, "xxx");
    }

    /**
     * 场景：类中有公共方法，且此公共方法依赖了某个私有方法，想达到的效果是
     *  a. 使用反射来单测private方法（参见上面的testWithPrivateMethodScenario1()）；
     *  b. 测试public方法时mock掉private方法
     *
     * 注：
     *  1. 涉及到mock私有方法，此时只有使用@RunWith(PowerMockRunner.class)；
     */
    @Test
    public void testWithPrivateMethodScenario3() throws Exception {
        /**
         * 这句话很重要，要mock掉private方法即需要使用PowerMockito
         * 注：
         *  1. 不要同时使用@InjectMocks与@Spy修饰同一变量(官网不推荐这样使用)，此时会需要实例化此变量。
         *  2. 应该像此例使用PowerMockito.spy()方法.差别在于，@Spy是mockito中的类，则PowerMockito.spy是PowerMockito中的方法，作用不完成相等于mockito中的spy方法
         */
        classWithPrivateMethod = PowerMockito.spy(classWithPrivateMethod);

        PowerMockito.doReturn("这里私有方法pFn被mock后返回的值").when(classWithPrivateMethod, "pFn", ArgumentMatchers.anyString());

        String fn = classWithPrivateMethod.fn(10);
        System.out.println(fn);
    }

    /**
     * 场景：mock异步方法。
     * 以spring中的asyncTaskExecutor为例，代码如下：
     * asyncTaskExecutor.submit(() -> {
              // 业务代码
              fn();
          });
     * Q：如何模拟submit的入参Runnable中的run方法？
     * A：借助doAnswer
     *
     * 好处：
     * 1. 达到代码覆盖；
     * 2. 特别是后续结果依赖这里的异步处理结果时很重要；
     *
     * 重要说明：
     * 1. 其实这里是将异步代码在测试线程中给执行了；
     * 2. 重要：异步代码中难免会调用其它方法(如这里的fn)，这里的方法同样可以被mock掉，就像外围mock一样，这样就不用一定将这里的异步代码放到一个方法中；
     */
    @Test
    public void testAsync() {
        // 模拟spring中的AsyncTaskExecutor类
        class AsyncTaskExecutor {
            public void submit(Runnable runnable){}
        }

        // 单测
        AsyncTaskExecutor asyncTaskExecutor = Mockito.mock(AsyncTaskExecutor.class);
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((Runnable) invocation.getArgument(0)).run();
                return null;
            }
        }).when(asyncTaskExecutor).submit(Mockito.any(Runnable.class));
    }


}

class Bbb {

}

class ClassWithPrivateMethod {

    private Mo mo;
    
    public String fn(int i){
        System.out.println("进入到公共方法fn()中了");
        String pFn = pFn("private");
        System.out.println("这是依赖的私有方法pFn被mock后的返回值");
        return "这是公共方法fn的返回值";
    }

    private String pFn(String str) {
        System.out.println("进入到方法pFn中了");
        String fn = mo.fn();
        System.out.println("依赖的mo.fn()返回值为：" + fn);
        return str;
    }
}

// 注：此类有继承关系
class ClassWithPrivateMethod2 extends Bbb {

    private Mo mo;

    private String pFn(String str) {
        System.out.println("进入到方法pFn中了");
        String fn = mo.fn();
        System.out.println("依赖的mo.fn()返回值为：" + fn);
        return str;
    }

    public void setMo(Mo mo) {
        this.mo = mo;
    }
}

/**
 * 被用来mock的对象
 */
class Mo {

    public String fn(){
        return "这是Mo.fn()方法的返回值";
    }
}