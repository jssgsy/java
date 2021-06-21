package com.univ.patterndesign.chain.pure;

/**
 * 抽象的请求处理器
 * @author univ
 * 2021/6/21 7:30 下午
 */
abstract class AbstractHandler {

    /**
     * 后继处理者
     */
    protected AbstractHandler successor;

    public void setSuccessor(AbstractHandler successor) {
        this.successor = successor;
    }

    /**
     * 需要处理的请求
     *
     * 注：这里是着重责任链模式本身思想的演示，实际使用过程中，这里一般都会有入参，表示请求上下文
     */
    public abstract void handRequest();
}
