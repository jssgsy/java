package com.univ.spi;

/**
 * @author univ
 * @date 2019/4/2 9:04 PM
 * @description
 */
public class PhpHelloService implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("hello, php service");
    }
}
