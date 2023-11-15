package com.univ.patterndesign.observer;

/**
 * 具体的观察者B
 */
public class ObserverB implements Observer{
    @Override
    public void update(Subject subject) {

        System.out.println("ObserverB 被通知到了");
    }
}
