package com.univ.patterndesign.observer;

/**
 * 具体都对象
 */
public interface Observer {
    // 当被观察者对象发生变化时，通过此方法通知观察者
    // 是拉模式，即通知发生时把被观察者对象作为参数传递，由观察者在这里按需获取自己的数据；
    void update(Subject subject) ;
}
