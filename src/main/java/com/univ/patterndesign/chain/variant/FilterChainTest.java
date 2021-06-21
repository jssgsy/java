package com.univ.patterndesign.chain.variant;

import java.util.List;

import org.junit.Test;

/**
 * @author univ
 * @date 2020/12/16 11:23 上午
 * @description 纯粹责任链模式的变体-过滤器
 */
public class FilterChainTest {

    /**
     * 用一个列表来保存所有的处理器
     */
    private List<BaseHandler> handlerList;

    @Test
    public void test() {
        BaseHandlerA handlerA = new BaseHandlerA();
        BaseHandlerB handlerB = new BaseHandlerB();
        BaseHandlerC handlerC = new BaseHandlerC();
        BaseHandlerD handlerD = new BaseHandlerD();

        /**
         * 此时客户端就使用HandlerProcess了，实际使用中，如下代码一般在应用启动时就已完成，如Dubbo的Filter
         */
        HandlerProcess handlerProcess = new HandlerProcess();
        handlerProcess.addHandler(handlerA);
        handlerProcess.addHandler(handlerB);
        handlerProcess.addHandler(handlerC);
        handlerProcess.addHandler(handlerD);

        handlerProcess.preHandler();
        System.out.println("目标对象方法被调用了");
        handlerProcess.postHandler();

    }
}









