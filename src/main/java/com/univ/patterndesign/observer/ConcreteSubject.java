package com.univ.patterndesign.observer;

/**
 * @author univ
 * date 2023/11/15
 */
public class ConcreteSubject extends Subject{
    @Override
    void somethingChanged() {
        System.out.println("ConcreteSubject状态变化了");
        super.notifyObservers();
    }
}
