package com.univ.patterndesign.chain;

import org.jetbrains.annotations.Contract;
import org.junit.Test;

/**
 * @author univ
 * @datetime 2018/11/6 2:57 PM
 * @description 责任链模式
 */
public class ChainTest {

    @Test
    public void test() {
        Handler a = new A();
        Handler b = new B();
        Handler c = new C();
        Handler d = new D();
        // 设置后继者
        a.setSuccessor(b);
        b.setSuccessor(c);
        c.setSuccessor(d);
        d.setSuccessor(null);
        a.handRequest();
    }
}

/*
1. 使多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系，将这些对象连成一条链，并沿着这条链条传递该请求，直到有一个对象处理它为止；
2. 注意，客户端需要设置谁是谁的后继对象；
3. 注意，如果当前对象不能请求又没法将请求传递给下一个对象时的异常处理
 */



abstract class Handler {
    /*
    直接将此方法实现在基类中，这样子类不用实现
    public abstract void setHandler(Handler handler);
     */
    // 后续者
    protected Handler successor;

    // 设置后继者
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    // 处理请求的方法
    public abstract void handRequest();
}

class A extends Handler {

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

class B extends Handler {

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

class C extends Handler {

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

class D extends Handler {

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

