package com.univ.patterndesign.state;

/**
 * 初始状态，即创建线程后的状态
 * @author univ
 * 2021/6/15 11:22 上午
 */
public class New extends State {
    
    @Override
    public void start(MockThread mockThread) {
        System.out.println("New#start------状态转移为runnable了");
        mockThread.transferState(MockThread.runnable);
    }

    @Override
    public void sleep(MockThread mockThread) {
        System.out.println("error:New状态的线程不可sleep");
    }

    @Override
    public void sleepTimeout(MockThread mockThread, long timeout) {
        System.out.println("error:New状态的线程不可sleepTimeout");
    }

    @Override
    public void run(MockThread mockThread) {
        System.out.println("error:New状态的线程不可run");
    }

    @Override
    public void mockNotify(MockThread mockThread) {
        System.out.println("error:New状态的线程不可notify");
    }

    @Override
    public void getLock(MockThread mockThread) {
        System.out.println("error:New状态的线程不可getLock");
    }

    @Override
    public void unlock(MockThread mockThread) {
        System.out.println("error:New状态的线程不可unlock");
    }
}
