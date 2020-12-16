package com.univ.patterndesign.chain.pure;

import org.jetbrains.annotations.Contract;
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

abstract class AbstractHandler {

    // 引用后继者
    protected AbstractHandler successor;

    // 设置后继者
    public void setSuccessor(AbstractHandler successor) {
        this.successor = successor;
    }

    // 处理请求的方法
    public abstract void handRequest();
}

class ConcreteAbstractHandlerA extends AbstractHandler {

    @Override
    public void handRequest() {
        if (canSay()) {
            System.out.println("A handRequest");
        } else if (null != successor) {
            System.out.println("A can't handRequest");
            successor.handRequest();
        } else {
            System.out.println("A既不能处理也没有传递给其它对象");
        }
    }

    // 根据业务判断是否需要传递给下一个对象进行处理
    private boolean canSay() {
        return false;
    }
}

class ConcreteAbstractHandlerB extends AbstractHandler {

    @Override
    public void handRequest() {
        if (canSay()) {
            System.out.println("B handRequest");
        } else if (null != successor) {
            System.out.println("B can't handRequest");
            successor.handRequest();
        } else {
            System.out.println("B既不能处理也没有传递给其它对象");
        }
    }

    @Contract(pure = true)
    private boolean canSay() {
        return false;
    }
}

class ConcreteAbstractHandlerC extends AbstractHandler {

    @Override
    public void handRequest() {
        if (canSay()) {
            System.out.println("C handRequest");
        } else if (null != successor) {
            System.out.println("C can't handRequest");
            successor.handRequest();
        } else {
            System.out.println("C既不能处理也没有传递给其它对象");
        }
    }

    @Contract(pure = true)
    private boolean canSay() {
        return false;
    }
}

class ConcreteAbstractHandlerD extends AbstractHandler {

    @Override
    public void handRequest() {
        if (canSay()) {
            System.out.println("D handRequest");
        } else if (null != successor) {
            System.out.println("no one can handRequest");
        } else {
            System.out.println("D既不能处理也没有传递给其它对象");
        }
    }

    @Contract(pure = true)
    private boolean canSay() {
        return false;
    }
}

