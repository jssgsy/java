package com.univ.patterndesign.observer;

import java.util.ArrayList;

/**
 * 被观察者对象
 */
public class Subject {
    // 维护一个观察者列表
    private ArrayList<Observer> observers;

    public Subject() {
        this.observers = new ArrayList<>();
    }

    public Subject(ArrayList<Observer> observers) {
        this.observers = observers;
    }

    // 新增(注册)观察者
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // 移除观察者
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        System.out.println("subject通知所有人开始---------------");
        for (Observer observer : observers) {
            observer.update(this);
        }
        System.out.println("subject通知所有人结束---------------");
    }

}
