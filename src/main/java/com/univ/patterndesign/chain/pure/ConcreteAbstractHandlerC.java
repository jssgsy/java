package com.univ.patterndesign.chain.pure;

import org.jetbrains.annotations.Contract;

/**
 * @author univ
 * 2021/6/21 7:31 下午
 */
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
