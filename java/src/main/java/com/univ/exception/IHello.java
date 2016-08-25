package com.univ.exception;

/**
 * Created by Univ on 16/8/25.
 */

/**
 * 演示接口方法抛出异常
 *
 * 1. 接口类中的方法应该尽可能抛出顶层的异常类型,这样实现类才能抛出更具体的异常类型;
 * 2. 接口方法抛出异常与父类方法抛出异常类似(父类方法抛出异常,子类覆写方法也可以不抛出异常);
 * 3. 运行时异常也可以捕获;
 */
public interface IHello {

    String hello() throws Exception;

    String hello1() throws Exception;

    String hello2() throws RuntimeException;
}
