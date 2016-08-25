package com.univ.exception;

/**
 * Univ
 * 16/8/25 16:36
 */

public class HelloImpl implements IHello {

    /**
     * 接口方法中抛出异常,但实现类方法却可以不抛出异常;
     * @return
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

}


