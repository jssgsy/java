package com.univ.thirdutils.eventbus.basic;

import org.greenrobot.eventbus.EventBus;

/**
 * 最简单的消息发布与订阅
 */
public class EventBusTest {

    public static void main(String[] args) {
        // 注册订阅者，这里采用最原始的方法，即在实例时注册，或是和spring结合使用(单例)，则可以实现的更优雅
        SingEventSubscribe helloSubscribe = new SingEventSubscribe();

        // 发布消息到bus中
        EventBus.getDefault().post(new SingEvent());
    }




}


