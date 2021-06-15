package com.univ.patterndesign.state;

/**
 * 线程Terminated状态
 * @author univ
 * 2021/6/15 11:33 上午
 */
public class Terminated extends State {
    
    @Override
    public void start(MockThread mockThread) {
        System.out.println("error:Terminated状态的线程不可start");
    }

    @Override
    public void sleep(MockThread mockThread) {
        System.out.println("error:Terminated状态的线程不可sleep");
    }

    @Override
    public void sleepTimeout(MockThread mockThread, long timeout) {
        System.out.println("error:Terminated状态的线程不可sleepTimeout");
    }

    @Override
    public void run(MockThread mockThread) {
        System.out.println("error:Terminated状态的线程不可run");
    }

    @Override
    public void mockNotify(MockThread mockThread) {

    }

    @Override
    public void getLock(MockThread mockThread) {
        System.out.println("error:Terminated状态的线程不可getLock");
    }

    @Override
    public void unlock(MockThread mockThread) {
        System.out.println("error:Terminated状态的线程不可unlock");
    }
}
