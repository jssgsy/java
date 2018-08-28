package com.univ.patterndesign.observer;

import java.util.ArrayList;

/**
 * 具体的观察者A
 */
public class ObserverA implements Observer{

    @Override
    public void update(Subject subject) {
        System.out.println("ObserverA 被通知到了");
    }
}
