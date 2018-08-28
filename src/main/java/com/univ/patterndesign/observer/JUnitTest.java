package com.univ.patterndesign.observer;

import org.junit.Test;

public class JUnitTest {

    @Test
    public void test() {

        Subject subject = new Subject();
        ObserverA observerA = new ObserverA();
        ObserverB observerB = new ObserverB();

        // 注册观察者
        subject.addObserver(observerA);
        subject.addObserver(observerB);

        subject.notifyObservers();
    }
}
