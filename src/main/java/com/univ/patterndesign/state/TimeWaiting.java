package com.univ.patterndesign.state;

/**
 * 有限等待状态
 * @author univ
 * 2021/6/15 11:32 上午
 */
public class TimeWaiting extends State {
    @Override
    public void start(MockThread mockThread) {
        System.out.println("error:TimeWaiting状态的线程不可start");
    }

    @Override
    public void sleep(MockThread mockThread) {
        System.out.println("error:TimeWaiting状态的线程不可sleep");
    }

    @Override
    public void sleepTimeout(MockThread mockThread, long timeout) {
        System.out.println("TimeWaiting#sleepTimeout---");
        mockThread.transferState(MockThread.runnable);
    }

    @Override
    public void run(MockThread mockThread) {
        System.out.println("error:TimeWaiting状态的线程不可run");
    }

    @Override
    public void mockNotify(MockThread mockThread) {
        System.out.println("error:TimeWaiting状态的线程不可mockNotify");
    }

    @Override
    public void getLock(MockThread mockThread) {
        System.out.println("error:TimeWaiting状态的线程不可getLock");
    }

    @Override
    public void unlock(MockThread mockThread) {
        System.out.println("error:TimeWaiting状态的线程不可unlock");
    }
}
