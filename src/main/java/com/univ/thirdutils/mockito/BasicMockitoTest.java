package com.univ.thirdutils.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author univ
 * @date 2019/6/25 3:23 PM
 * @description 最基本的mockito的使用方法
 *
 *  一般使用 Mockito 需要执行下面三步
 *  1. 模拟并替换测试代码中外部依赖：使用mock方法或者相应的注解
 *  2. 执行测试代码
 *  3. 验证测试代码是否被正确的执行
 *
 * 核心：单测，针对的是一个个独立的方法及其中的业务逻辑，其中所有依赖的方法都应视为外部依赖，应该mock掉。
 *  即使是调用的内部的其它方法，此方法也应该给mock掉，因为其自有单测来保证
 *
 *  所谓打桩(stub)：when.thenReturn就是打桩，表示用另外一个东西代替，类似代理
 */
public class BasicMockitoTest {

    /**
     * 1. 被mock对象可以理解成一个代理，其方法的返回值可以指定
     * 2. 只要使用了一个参数匹配器(argument matcher)，则所有参数都需要使用参数匹配器
     */
    @Test
    public void test1() {
        // mock一个OrdinaryObj对象（也可以mock具体的类），此时其方法不会实际被调用
        Ordinary ordinary = mock(Ordinary.class);
        boolean b = ordinary.isOrdinary();
        // 返回相应的零值(By default, for all methods that return a value,
        // a mock will return either null, a primitive/primitive wrapper value,
        // or an empty collection, as appropriate. For example 0 for an int/Integer and false for a boolean/Boolean)
        System.out.println(b);

        // 这个是ok的，anyString(), anyInt()都是参数匹配器
        verify(ordinary).getList(anyString(), anyInt());

        // 会抛异常，因为12不是参数匹配器
        // verify(demoDao).getByNameAndAge(anyString(), 12);
    }

    /**
     * 根据不同的输入返回不同的值
     */
    @Test
    public void test2() {
        Ordinary ordinary = mock(Ordinary.class);
        when(ordinary.getType(1)).thenReturn("aaa");
        when(ordinary.getType(2)).thenReturn("bbb");
        assertEquals("竟然失败了", "aaa", ordinary.getType(1));
        assertEquals("竟然失败了", "bbb", ordinary.getType(2));

        /**
         * 返回值不依赖于入参
         * anyString()
         * anyint()
         * ...
         * any()
         */
        when(ordinary.getType(anyInt())).thenReturn("hello");
        assertEquals("竟然失败了", "hello", ordinary.getType(3));
    }

    /**
     * 多次调用，返回不同的结果，可应用在测试根据返回值的不同走不同分支时，是一种简便写法
     */
    @Test
    public void test3() {
        Ordinary ordinary = mock(Ordinary.class);
        // 第一次调用ordinary.getType(1)时返回"aaa"，第二次调用时返回"bbb"
        when(ordinary.getType(1)).thenReturn("aaa").thenReturn("bbb");

        assertEquals("aaaa", ordinary.getType(1));
        assertEquals("bbb", ordinary.getType(1));

        // 等价的写法
        when(ordinary.getType(1)).thenReturn("aaa", "bbb");
    }

    /**
     * 调用某个方法时抛异常，用在某个方法不允许被调用
     * doThrow(ExceptionX).when(x).methodCall
     */
    @Test
    public void test4() {
        Ordinary ordinary = mock(Ordinary.class);
        // 注意语法形式，对象和方法是分开的，与when.doReturn不同
        doThrow(new RuntimeException("此方法不能被调用")).when(ordinary).isOrdinary();

        // 下行代码会抛异常
        // ordinary.isOrdinary();
    }

    /**
     * 验证方法是否被调用
     */
    @Test
    public void test5() {
        Ordinary ordinary = mock(Ordinary.class);

        // 调用方法
        ordinary.getById(1);
        ordinary.isOrdinary();
        ordinary.isOrdinary();
        ordinary.isOrdinary();

        // 验证当传1时getById方法是否被调用
        verify(ordinary).getById(1);

        // 验证isOrdinary方法被调用了三次，否则抛异常
        verify(ordinary, times(3)).isOrdinary();

        // 验证getName()方法一次都没被调用过
        // ordinary.getName();
        verify(ordinary, never()).setId();

        /*
        类似的方法有：
            atLeastOnce();// 至少被调用一次
            atLeast(3); // 至少被调用3次
            atMost(3);  // 最多被调用3次
         */
    }

    /**
     * spy 重要
     *  mock方法是模拟出一个“替代”对象，而spy是模拟出一个“真实”的对象，即其方法会真正被调用
     *  spy方法可以包装一个真实的Java对象, 并返回一个包装后的新对象. 若没有特别配置的话, 对这个新对象的所有方法调用, 都会委派给实际的 Java 对象
     */
    @Test
    public void test6() {
        // 模拟出一个真正的OrdinaryObject对象，一般用在具体的类而不是接口上，因为就是想执行其真实代码
        OrdinaryObject ordinary = spy(OrdinaryObject.class);
        // 此时会调用真实的fn方法，可调试进入
        ordinary.fn();

        // 被spy的对象也可以mock其方法，但不要对spy对象调用when.thenReturn，而是使用doReturn.when模式
        List list = new LinkedList();
        List spy = spy(list);
        //Impossible: real method is called so spy.get(0) throws IndexOutOfBoundsException (the list is yet empty)
        // 意思是说，这种形式下，spy.get(0)会实际被调用一次(在thenReturn前)，此时就报错了，所以需要doReturn.when的形式
        when(spy.get(0)).thenReturn("foo");
        //You have to use doReturn() for stubbing
        Mockito.doReturn("foo").when(spy).get(0);
    }

    /**
     * 重点：spy的真实应用，即部分方法mock，其它部分实际执行
     * 假设要测试service中的方法fn，fn又调用了此service中的其它方法fn1，那么在测试fn时，fn1其实也是一个依赖，需要mock掉！
     * (fn1有针对自己的单测，但在测试fn时，其就是一个外部依赖)
     */
    @Test
    public void test7() {
        OrdinaryObject ordinary = spy(OrdinaryObject.class);
        // 注意，记得使用doReturn.when(obj).method的形式，不要使用when.return的方式
        doReturn("重点关注，关注要单测的方法的逻辑，其它所有调用都mock掉，不论是外部还是内部").when(ordinary).fn1();
        ordinary.fn1();
    }

    /**
     * 自定义参数匹配器，其实就一个lambda表达式，注意版本要在2.1.0及以上
     */
    @Test
    public void test8() {
        Ordinary ordinary = mock(Ordinary.class);
        // 只有当参数是1时才返回aaa
        when(ordinary.getType(1)).thenReturn("aaa");
        assertEquals("aaa", ordinary.getType(1));
        assertEquals(null, ordinary.getType(2));

        // 参数是任意int时都返回bbb
        // when(ordinary.getType(anyInt())).thenReturn("bbb");

        // 当参数是偶数时才返回ccc
        when(ordinary.getType(argThat(t -> t % 2 == 0))).thenReturn("ccc");
        assertEquals("ccc", ordinary.getType(2));
        assertEquals(null, ordinary.getType(3));
    }

}

/**
 * 基本对象
 */
interface Ordinary {

    boolean isOrdinary();

    Ordinary getById(int id);

    String getType(Integer code);

    String getName(Ordinary demo);

    List getList(String name, int age);

    void setId();
}

class OrdinaryObject {

    public String fn() {
        int i = 0;
        int j = 0;
        int k = 0;
        return "aaa";
    }

    public void fn1() {
        String a = "aaa";
        // 此方法
        String fn = fn();
        String b = "bbb" + fn;
    }
}

