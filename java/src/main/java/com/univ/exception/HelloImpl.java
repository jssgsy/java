package com.univ.exception;

import org.junit.Test;

/**
 * Univ
 * 16/8/25 16:36
 */

public class HelloImpl implements IHello {

    /**
     * 接口方法中抛出异常,不过实现类方法却可以不抛出异常;
     * 注意:在调用此方法时
     *  1.如果对象的静态类型是接口类型,则却必须捕获或者抛出异常。见test1();
     *  2.如果对象的静态类型是实现类类型,则不必捕获或者抛出异常。见test2();
     *
     * 总结:
     *  接口类方法抛出异常,而实现类方法不抛出异常,则在调用此方法时,是否需要捕获或抛出异常取决于调用方法的对象的静态类型。
     *      如果是声明了抛出异常的接口类型(IHello),则需要捕获或抛出;
     *      如果是声明不用抛出异常的实现类类型(HelloImpl),则不需要捕获或抛出;
     *
     */
    @Override
    public String hello() {
        return null;
    }

    @Override
    public String hello1() throws Exception {
        return null;
    }

    /**
     * 实现类只能抛出接口方法中声明可抛出的异常类型及其子类型;
     * @return
     * @throws RuntimeException
     */
    @Override
    public String hello2() throws RuntimeException /*Error:Exception*/{
        return null;
    }

    @Test
    public void test1(){
        IHello hello = new HelloImpl();
        try {
            /**
             * 虽然实现类中hello()方法并没有抛出异常,但因这里hello的静态类型是接口类型IHello,
             * 所以调用时必须捕获或者抛出接口IHello中声明抛出的异常
             */
            hello.hello();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        HelloImpl hello1 = new HelloImpl();
        /**
         * hell1的静态类型是HelloImpl,而在HelloImpl的hello()方法中没有声明抛出异常,
         * 所以这里调用不用捕获或者抛出异常
         */
        hello1.hello();
    }

}


