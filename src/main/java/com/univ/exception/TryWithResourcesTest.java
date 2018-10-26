package com.univ.exception;

import org.junit.Test;

public class TryWithResourcesTest {
    @Test
    public void test1() {
        /*
        1. try还是原来的try，需要catch或者抛出；
        2. try()中书写打开资源的代码，{}中可以直接调用()中的变量；
        3. 打开的资源会被自动关闭，只要其实现了AutoClosable接口；
        4. 资源关闭的顺序和资源打开的顺序相反
         */
        try (A a = new A(); B b = new B()){
            a.fn();
            b.fn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/**
 * 必须实现AutoCloseable接口
 */
class A implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println("A is closed...");
    }

    public void fn(){
        System.out.println("A:fn");
    }
}

/**
 * 必须实现AutoCloseable接口
 */
class B implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println("B is closed...");
    }

    public void fn(){
        System.out.println("B:fn");
    }
}
