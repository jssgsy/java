package com.univ.patterndesign.observer;

import org.junit.Test;

public class JUnitTest {

    @Test
    public void test() {

        // 使用时需要直接使用具体的Subject了，因为导致触发通知的状态变更在这里(somethingChanged方法)
        ConcreteSubject subject = new ConcreteSubject();
        ObserverA observerA = new ObserverA();
        ObserverB observerB = new ObserverB();

        // 注册观察者
        subject.addObserver(observerA);
        subject.addObserver(observerB);

        subject.somethingChanged();
    }
}
