package com.univ.thirdutils.powermockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author univ
 * @date 2020/2/19 10:38 AM
 * @description
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({PrivateMethod.class, StaticMethod.class})
public class PowerMockitoTest {
    
    @Test
    public void testPrivateMethod() throws Exception {
        // 注意，不要使用Mockito.spy，一个可能的原则是，在需要mock私有方法，静态方法时，用PowerMockito代替Mockito的方法
        PrivateMethod privateMethod = PowerMockito.spy(new PrivateMethod());
        PowerMockito.doReturn("mock值fn1").when(privateMethod, "fn1");
        // 可以同时mock多个私有方法
        PowerMockito.doReturn("mock值fn2").when(privateMethod, "fn2");
        privateMethod.fn();
    }

    @Test
    public void testStaticMethod() throws Exception {
        StaticMethod staticMethod = PowerMockito.spy(new StaticMethod());
        // 重点：表明要对StaticMethod中的静态方法进行mock
        PowerMockito.mockStatic(StaticMethod.class);
        // 可以同时mock多个私有方法
        PowerMockito.when(StaticMethod.staticMethod1()).thenReturn("mock的静态方法值1");
        PowerMockito.when(StaticMethod.staticMethod2()).thenReturn("mock的静态方法值2");
        staticMethod.fn();
    }
    
}

class PrivateMethod {
    public void fn(){
        System.out.println("进入fn---");
        String fn1 = privateMethod1();
        System.out.println("私有fn1方法的结果: " + fn1);
        String fn2 = privateMethod2();
        System.out.println("私有fn2方法的结果: " + fn2);
        System.out.println("离开fn---");

    }

    private String privateMethod1() {
        System.out.println("privateMethod1");
        return "真实值fn1";
    }

    private String privateMethod2() {
        System.out.println("privateMethod1");
        return "真实值fn2";
    }
}

class StaticMethod {

    public void fn(){
        System.out.println("进入实例方法fn()-----");

        String result = staticMethod1();
        System.out.println("内部调用的静态方法staticMethod1返回值为：" + result);

        String result2 = staticMethod2();
        System.out.println("内部调用的静态方法staticMethod2返回值为：" + result2);

        System.out.println("离开实例方法fn()-----");
    }

    public static String staticMethod1(){
        System.out.println("进入staticMethod1-------");

        System.out.println("离开staticMethod1-------");
        return "staticMethod1真实值";
    }

    public static String staticMethod2(){
        System.out.println("进入staticMethod2-------");

        System.out.println("离开staticMethod2-------");
        return "staticMethod2真实值";
    }
}
