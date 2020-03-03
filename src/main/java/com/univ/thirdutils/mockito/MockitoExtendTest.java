package com.univ.thirdutils.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author univ
 * @date 2020/2/17 8:37 PM
 * @description 继承关系的mockito用法
 *
 * 重要总结：单纯的Mockito功能还是有些弱，涉及到私有方法，父类方法等方面不能解决。
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoExtendTest {

    @InjectMocks
    @Spy
    private Child child;

    /**
     * 为了单测父类A，必须有具体的子类来承接
     * 此时B可以使用@InjectMock，为注入父类的属性提供了可能(父类有构造方法，且调用子类方法前会先调用父类构造方法)
     */
    @InjectMocks
    private B b;

    /**
     * 此时会被注入到b的抽象父类中，然后就可以mock掉c相关方法了
     */
    @Mock
    private C c;

    /**
     * 此时会被注入到b的抽象父类中，然后就可以mock掉d相关方法了
     */
    @Mock
    private D d;

    /**
     * mock掉父类的非同名public方法
     */
    @Test
    public void test() {
        // 这里mock的是child.fn，所以fn1()方法中调用父类方法时不能使用super.fn()!，其实就相当于是mock自己的public方法一样
        Mockito.doReturn(false).when(child).fn();
        child.fn1();
    }

    /**
     * 测试抽象类
     * 注：
     *  0. 抽象类是不能被实例化的，因而对应的测试类无法用@InjectMock修饰，所以要单测父类方法必须由子类来承接；
     *  1. 抽象类是有构造函数的(而mockito优先使用构造方法进入mock注入)
     */
    @Test
    public void testAbstractParentClass() {
        b = Mockito.spy(b);
        Mockito.when(c.cFn()).thenReturn("C cfn() mock的返回值");
        Mockito.when(d.dFn()).thenReturn("D dfn() mock的返回值");
        // 执行父类方法
        b.afn();
    }
}


class Parent {

    public boolean fn() {
        System.out.println("parent fn()");
        return true;
    }
}

class Child extends Parent {

    // 注意，如果子类方法名和父类方法名相同(即fn1),则mockito回天乏术，此时需要借助power mock
    public boolean fn1() {
        System.out.println("Child fn1()");
        fn();
        // super.fn(); // 重要：这里不能使用super.fn()，否则失效(应该)
        return true;
    }

}

/**
 * 抽象类
 */
abstract class A {

    /**
     * 注意这里有字段，则测试时需要注入，注意看是如何注入的
     */
    private C c;

    private D d;

    public void afn(){
        System.out.println("A afn()");
        String cFn = c.cFn();
        System.out.println("依赖的C cfn()返回值：" + cFn);
        String dFn = d.dFn();
        System.out.println("依赖的D dFn()返回值：" + dFn);
    }

}

// 为了单测抽象的父类，必须有一个子类来承接
class B extends A {


}

class C {

    public String cFn() {
        return "do not matter";
    }

}

class D {
    public String dFn() {
        return "do not matter";
    }
}