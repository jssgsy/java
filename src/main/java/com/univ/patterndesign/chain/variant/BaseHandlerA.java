package com.univ.patterndesign.chain.variant;

/**
 * @author univ
 * 2021/6/21 8:34 下午
 */
class BaseHandlerA implements BaseHandler {

    @Override
    public void preHandler() {
        System.out.println("BaseHandlerA#preHandler---");
    }

    @Override
    public void postHandler() {
        System.out.println("BaseHandlerA#postHandler---");
    }
}
