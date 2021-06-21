package com.univ.patterndesign.chain.pure;

/**
 * @author univ
 * 2021/6/21 7:30 下午
 */
public class ConcreteAbstractHandlerA extends AbstractHandler {

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
