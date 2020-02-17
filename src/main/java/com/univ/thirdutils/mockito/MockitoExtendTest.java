package com.univ.thirdutils.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author univ
 * @date 2020/2/17 8:37 PM
 * @description 继承关系的mockito用法
 *
 * 重要总结：单纯的Mockito功能还是有些弱，涉及到私有方法，父类方法等方面有什么不能解决。
 */
@RunWith(MockitoJUnitRunner.class)
public class MockitoExtendTest {

    @InjectMocks
    @Spy
    private Child child;

    @Test
    public void test() {
        // 这里mock的是child.fn，所以fn1()方法中调用父类方法时不能使用super.fn()!
        Mockito.doReturn(false).when(child).fn();
        child.fn1();
    }
}


class Parent {

    public boolean fn() {
        System.out.println("parent fn()");
        return true;
    }
}

class Child extends Parent {

    // 注意，如果子类方法名和父类方法名相同(即fn1),则mockito回天乏术，此时需要借助powermock
    public boolean fn1() {
        System.out.println("Child fn1()");
        fn();
        // super.fn(); // 重要：这里不能使用super.fn()，否则失效(应该)
        return true;
    }

}