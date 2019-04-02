package com.univ.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.junit.Test;

/**
 * @author univ
 * @date 2019/4/2 9:07 PM
 * @description
 */
public class SpiTest {

    @Test
    public void testSpi() {
        ServiceLoader<HelloService> impls = ServiceLoader.load(HelloService.class);
        // 调用next()方法时才会真正实例化具体的实现类
        Iterator<HelloService> iterator = impls.iterator();
        while (iterator.hasNext()) {
            HelloService helloService = iterator.next();
            helloService.sayHello();
        }

        // 下面的方法也能使用，其实质就是上面的用法
        for (HelloService helloService : impls) {
            helloService.sayHello();
        }
    }

}
