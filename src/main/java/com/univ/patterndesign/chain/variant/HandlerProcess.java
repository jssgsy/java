package com.univ.patterndesign.chain.variant;

import java.util.ArrayList;
import java.util.List;

/**
 * 新引入的角色-用来管理所有的处理器，对外也暴露此类，将所有组合处理器的逻辑封装在此
 * @author univ
 * 2021/6/21 8:38 下午
 */
public class HandlerProcess {

    /**
     * 所有的处理器
     */
    private List<BaseHandler> handlerList;

    /**
     * 实际使用中，这里的工作可以放到项目启动阶段，如通过配置文件等
     * 当然也可扩展为支持排序
     * @param baseHandler
     */
    public void addHandler(BaseHandler baseHandler) {
        if (null == handlerList || handlerList.size() == 0) {
            handlerList = new ArrayList<>();
        }
        handlerList.add(baseHandler);
    }

    /**
     * 这里示例的是分成了preHandler和postHandler，可根据实际情况调整，如只定义一个方法，在此方法中定义处理是否要前置或后置处理的逻辑(如Dubbo的Filter)
     * 另外一个重点：如何将Filter与目标对象结合起来，责任链模式并不区分此两者：只关注有一系列对象可能会参与到对请求的处理
     */

    /**
     * 用此方法和postHandler对外提供服务
     */
    public void preHandler() {
        for (BaseHandler handler : handlerList) {
            handler.preHandler();
        }
    }

    public void postHandler() {
        for (BaseHandler handler : handlerList) {
            handler.postHandler();
        }
    }
}
