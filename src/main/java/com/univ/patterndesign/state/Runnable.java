package com.univ.patterndesign.state;

/**
 * 可运行状态
 * @author univ
 * 2021/6/15 11:28 上午
 */
public class Runnable extends State {

    @Override
    public void start(MockThread mockThread) {
        System.out.println("error:Runnable状态的线程不可start");
    }

    @Override
    public void sleep(MockThread mockThread) {
        System.out.println("Runnable#sleep---状态转移为waiting了");
        mockThread.transferState(MockThread.waiting);
    }

    @Override
    public void sleepTimeout(MockThread mockThread, long timeout) {
        System.out.println("Runnable#sleepTimeout---状态转移为time_waiting了");
        mockThread.transferState(MockThread.timeWaiting);
    }

    @Override
    public void run(MockThread mockThread) {
        System.out.println("Runnable#run---状态转移为terminated了");
        mockThread.transferState(MockThread.terminated);
    }

    @Override
    public void mockNotify(MockThread mockThread) {
        System.out.println("error:Runnable状态的线程不可mockNotify");
    }

    @Override
    public void getLock(MockThread mockThread) {
        System.out.println("Runnable#getLock---状态转移为blocked了");
        mockThread.transferState(MockThread.blocked);
    }

    @Override
    public void unlock(MockThread mockThread) {
        System.out.println("error:Runnable状态的线程不可unlock");
    }
}
