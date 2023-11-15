package com.univ.patterndesign.observer;

import java.util.ArrayList;

// 抽象的被观察者对象，这里用抽象类实现(也可以用接口)
public abstract class Subject {
    // 维护一个观察者列表
    private ArrayList<Observer> observers = new ArrayList<>();

    // 新增(注册)观察者
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // 移除观察者
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // 通知观察者流程应该是一致的，放父类
    public void notifyObservers() {
        System.out.println("subject通知所有人开始---------------");
        for (Observer observer : observers) {
            observer.update(this);
        }
        System.out.println("subject通知所有人结束---------------");
    }

    // 假设如具体的被观察者在这里有不同，留给子类实现
    abstract void somethingChanged() ;
}
