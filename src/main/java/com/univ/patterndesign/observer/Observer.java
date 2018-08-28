package com.univ.patterndesign.observer;

/**
 * 具体都对象
 */
public interface Observer {
    // 当被观察者对象发生变化时，通过此方法通知观察者
    void update(Subject subject) ;
}
