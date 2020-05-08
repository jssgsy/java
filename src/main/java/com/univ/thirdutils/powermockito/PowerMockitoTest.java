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
        PowerMockito.doReturn("mock值fn1").when(privateMethod, "privateMethod1");
        // 可以同时mock多个私有方法
        PowerMockito.doReturn("mock值fn2").when(privateMethod, "privateMethod2");
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

    /**
     * 子类方法与父类方法不同名,mockito也可用相同的方法完成
     */
    @Test
    public void testExtend1() {
        // 注意要定义在使用之前
        class Parent {
            public String fn(){
                System.out.println("进入父类fn---");
                System.out.println("离开父类fn---");
                return "父类fn()真实值";
            }
        }
        class Child extends Parent {
            public String fn1(){
                System.out.println("进入子类fn1---");
                String fn = fn(); // 注意此时不能使用super.fn()方法，否则PowerMockito.doReturn("mock值").when(spy).fn();失效
                System.out.println("调用父类fn()的返回值为：" + fn);
                System.out.println("离开子类fn1---");
                return "父类fn()真实值";
            }
        }

        Child spy = PowerMockito.spy(new Child());
        // 只要调用父类方法时没加super关键字就行，其实和Mockito是一样的用法
        PowerMockito.doReturn("mock值").when(spy).fn();
        spy.fn1();
    }

    /**
     * 子类方法与父类方法同名, 此时mockito无能为力，需要power mockito出场
     */
    @Test
    public void testExtend2(){
        Child spy = PowerMockito.spy(new Child());

        // 注意要定义在使用之前，注意，实践表明，此时不能将类定义在方法中，否则失效
        PowerMockito.suppress(PowerMockito.methodsDeclaredIn(Parent.class));
        spy.ff();
    }

    class Parent {
        public String ff(){
            System.out.println("进入父类ff---");
            System.out.println("离开父类ff---");
            return "父类ff()真实值";
        }
    }
    class Child extends Parent {
        @Override
        public String ff() {
            System.out.println("进入子ff---");
            String ff = super.ff(); // 注意这里带了super关键字
            System.out.println("调用父类fn()的返回值为：" + ff);
            System.out.println("离开子ff---");
            return "父类ff()真实值";
        }
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

