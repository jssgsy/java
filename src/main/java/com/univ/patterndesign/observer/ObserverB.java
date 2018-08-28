package com.univ.patterndesign.observer;

/**
 * 具体的观察者B
 */
public class ObserverB implements Observer{

    /**
     * 这里的参数就是被观察者传递给观察者的数据
     * @param subject
     */
    @Override
    public void update(Subject subject) {

        System.out.println("ObserverB 被通知到了");
    }
}
