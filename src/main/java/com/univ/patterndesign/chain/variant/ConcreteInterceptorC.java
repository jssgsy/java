package com.univ.patterndesign.chain.variant;

/**
 * @author univ
 * 2021/6/21 8:34 下午
 */
class ConcreteInterceptorC implements BaseInterceptor {

    @Override
    public void preHandler() {
        System.out.println("BaseHandlerC#preHandler---");
    }

    @Override
    public void postHandler() {
        System.out.println("BaseHandlerC#postHandler---");
    }
}
