package com.univ.jdk8.defaultmethod;

import org.junit.Test;

/**
 * 接口中可以有默认的方法实现了
 */
public class DefaultMethodTest {

    @Test
    public void test1() {
        A a = new B();

        // 调用接口中的默认方法，需要使用其实现类进行
        System.out.println(a.add(34, 65));

        // 调用接口的静态方法，竟然是用点号而不是::
        System.out.println(F.fn());
        
    }
}

interface A {
    /**
     * jdk1.8新增的默认实现方法
     * 1. 语法上仅仅只是在方法实现前面加一个default关键字
     * 2. 注意，方法的修饰符默认依然是(也只能是)public的
     * 3. 接口的默认方法不用强制子类进行实现，当然子类进行实现也是可以的
     * 4. 默认方法也可以被子接口继承
     */
    default int add(int a, int b) {
        return a + b;
    }
}

class B implements A {

    /**
     * 实现也可以实现接口中的默认方法
     */
    @Override
    public int add(int a, int b) {
        return a * a + b * b;
    }
}

/**
 * 可以不实现接口A中的默认方法，此时相当于是默认继承了
 */
class C implements A {

}

// 子接口默认继承父接口的默认方法
interface D extends A {

}

// 子接口可以重写父接口的默认方法
interface E extends A {
    @Override
    default int add(int a, int b) {
        return 0;
    }
}

// 子接口可以重新将父接口的默认方法置为抽象的，此时所有F的实现类都要实现add方法
interface F extends A {
    @Override
    int add(int a, int b);

    // 接口允许静态方法，静态方法一般用作工具方法使用，之所以新增是为了更好的代码组织
    static int fn() {
        return 10;
    }
}