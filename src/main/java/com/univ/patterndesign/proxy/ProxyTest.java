package com.univ.patterndesign.proxy;

import org.junit.Test;

/**
 * 这里说的代理模式，指静态代理
 * 1. 为其它对象提供一种代理以控制对这个对象的访问；
 * 2. 代理模式与装饰器模式在UML类图上极为相似，不同点可能更多的是从语义上进行区分，
 *  * 装饰器模式是动态地对象添加功能，添加功能后还是那个对象，
 *  * 代理模式强调让别人代理做一些与自己业务关系不大的事情，如记录日志、设置缓存；
 *
 * @author univ
 * @datetime 2018/11/6 2:01 PM
 */
public class ProxyTest {

    @Test
    public void test() {
        Subject subject = new Proxy(new RealSubject());
        subject.request();
    }
}

/**
 * 被代理的类
 */
interface Subject {
    void request();
}

class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("实际对象的request方法被调用");
    }

}

/**
 * 这就是所谓的静态代表：需要为代理类编写源码
 * 注：代理类也需要实现Subject接口
 */
class Proxy implements Subject {

    /**
     * 真正的对象(被代理的对象)，其实这里也可以是父类Subject，不过这里强调的是静态的代理，即在编译期间就确认了要代理的是哪个对象
     */
    private RealSubject realSubject;

    // 代理模式这里好像偏向于手具体的代理对象
    // private Subject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        preProxy();
        realSubject.request();
        postProxy();
    }

    private void preProxy() {
        System.out.println("代理之前");
    }

    private void postProxy() {
        System.out.println("代理之后");
    }
}
