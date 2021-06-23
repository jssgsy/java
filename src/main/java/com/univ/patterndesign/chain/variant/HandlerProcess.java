package com.univ.patterndesign.chain.variant;

import java.util.ArrayList;
import java.util.List;

/**
 * 新引入的角色-用来管理所有的处理器(拦截器)，对外也暴露此类，将所有组合处理器的逻辑封装在此
 * spring mvc 的HandlerInterceptor就是类似这样的用法
 * @author univ
 * 2021/6/21 8:38 下午
 */
public class HandlerProcess {

    /**
     * 所有的处理器
     */
    private List<BaseInterceptor> handlerList;

    /**
     * 实际使用中，这里的工作可以放到项目启动阶段，如通过配置文件等
     * 当然也可扩展为支持排序
     * @param baseInterceptor
     */
    public void addHandler(BaseInterceptor baseInterceptor) {
        if (null == handlerList || handlerList.size() == 0) {
            handlerList = new ArrayList<>();
        }
        handlerList.add(baseInterceptor);
    }

    /**
     * 这里示例的是分成了preHandler和postHandler，可根据实际情况调整，
     * 同时也可将preHandler返回类型定义为boolen，返回false时不再处理后续拦截器以及目标方法。
     *  这里突出显示【拦截器】的语义：对目标方法的调用作拦截，作一些检查(决定是否往后调用)、在前后做一些事情
     *
     */

    /**
     * 用此方法和postHandler对外提供服务
     */
    public void preHandler() {
        for (BaseInterceptor handler : handlerList) {
            handler.preHandler();
        }
    }

    public void postHandler() {
        for (BaseInterceptor handler : handlerList) {
            handler.postHandler();
        }
    }
}
