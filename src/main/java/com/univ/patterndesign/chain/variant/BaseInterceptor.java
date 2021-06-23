package com.univ.patterndesign.chain.variant;

/**
 * 模拟拦截器
 * @author univ
 * 2021/6/21 8:33 下午
 */
interface BaseInterceptor {

    /**
     * 目标方法调用之前要处理的逻辑
     * 这里可改造成：如果返回false就不再往后传递了
     * 不要拘泥于形式，根据业务场景灵活使用
     */
    void preHandler();

    /**
     * 目标方法调用之后要处理的逻辑
     */
    void postHandler();
}
