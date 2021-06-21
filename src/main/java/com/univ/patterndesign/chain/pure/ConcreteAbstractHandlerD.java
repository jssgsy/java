package com.univ.patterndesign.chain.pure;

import org.jetbrains.annotations.Contract;

/**
 * @author univ
 * 2021/6/21 7:31 下午
 */
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
