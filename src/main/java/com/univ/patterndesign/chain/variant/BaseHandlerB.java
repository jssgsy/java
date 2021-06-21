package com.univ.patterndesign.chain.variant;

/**
 * @author univ
 * 2021/6/21 8:34 下午
 */
class BaseHandlerB implements BaseHandler {

    @Override
    public void preHandler() {
        System.out.println("BaseHandlerB#preHandler---");
    }

    @Override
    public void postHandler() {
        System.out.println("BaseHandlerB#postHandler---");
    }
}
