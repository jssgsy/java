package com.univ.patterndesign.chain.pure;

import org.junit.Test;

/**
 * @author univ
 * @datetime 2018/11/6 2:57 PM
 * @description 纯粹的责任链模式
 */
public class PureChainTest {

    @Test
    public void test() {
        AbstractHandler a = new ConcreteAbstractHandlerA();
        AbstractHandler b = new ConcreteAbstractHandlerB();
        AbstractHandler c = new ConcreteAbstractHandlerC();
        AbstractHandler d = new ConcreteAbstractHandlerD();
        // 设置后继者
        a.setSuccessor(b);
        b.setSuccessor(c);
        c.setSuccessor(d);
        d.setSuccessor(null);
        a.handRequest();
    }
}









