package com.univ.patterndesign.chain.variant;

import org.junit.Test;

/**
 * @author univ
 * @date 2020/12/16 11:23 上午
 * @description 纯粹责任链模式的变体-过滤器
 */
public class FilterChainTest {

    @Test
    public void test() {
        BaseHandlerA firstHandler = new BaseHandlerA();
        BaseHandlerB handlerB = new BaseHandlerB();
        BaseHandlerC handlerC = new BaseHandlerC();
        BaseHandlerD handlerD = new BaseHandlerD();

        firstHandler.setSuccessor(handlerB);
        handlerB.setSuccessor(handlerC);
        handlerC.setSuccessor(handlerD);

        firstHandler.handRequest();
    }
}

abstract class BaseHandler {

    protected BaseHandler successor;

    public void setSuccessor(BaseHandler successor) {
        this.successor = successor;
    }

    public abstract void handRequest() ;
}

class BaseHandlerA extends BaseHandler {

    @Override
    public void handRequest() {
        // 处理器先自己处理请求
        // ...
        System.out.println("BaseHandlerA处理请求了");

        // 将请求交给下一个处理器
        successor.handRequest();
    }
}

class BaseHandlerB extends BaseHandler {

    @Override
    public void handRequest() {
        // 处理器看看自己是否要处理
        // if(true) 处理
        System.out.println("BaseHandlerB处理请求了");

        // 将请求交给下一个处理器
        successor.handRequest();
    }
}

class BaseHandlerC extends BaseHandler {

    @Override
    public void handRequest() {
        // 处理器也可以啥都不作，直接给到下一个处理器

        // 将请求交给下一个处理器
        successor.handRequest();
    }
}

class BaseHandlerD extends BaseHandler {

    @Override
    public void handRequest() {
        // 最后一个处理器
        System.out.println("整个请求处理结束了");
    }
}