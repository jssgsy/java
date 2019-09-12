package com.univ.thirdutils.eventbus.basic;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * 订阅者，订阅者必须被注册到EventBus中才能接收到消息
 */
public class SingEventSubscribe {

    SingEventSubscribe() {
        /**
         * 使用EventBus的register方法来注册订阅者
         */
        EventBus.getDefault().register(this);
    }

    /**
     * 订阅方法必须使用@Subscribe注解，且为public，无返回值，只有一个事件类型入参
     * @param singEvent
     */
    @Subscribe
    public void doWithEvent(SingEvent singEvent) {
        System.out.println("事件名称为：" + singEvent.getEvent_name());
    }

}
