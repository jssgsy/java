package com.univ.patterndesign.state;

/**
 * 抽象状态
 *
 * 这里的每个方法都会导致状态的转换，即每个方法都是一个状态转移方法
 * @author univ
 * 2021/6/15 10:46 上午
 */
public abstract class State {

    /**
     * 此状态所属的实体：很重要，状态一定是依附某个实体存在的，不会单独存在，如这里是说：某个线程的状态
     *
     * 补充：另外一种做法是，将所属实体作为入参传递到这里的各种方法中(如start sleep等)，这里采用的此种方法
     * 只需明确：重点是状态一定是依附实体存在，且发生动作时状态需要持有实体的引用，因为可能要使用所属实体的信息
     */
    protected MockThread mockThread;

    /**
     * 此时状态变更为：Runnable
     * @param mockThread
     */
    public abstract void start(MockThread mockThread);

    /**
     * 此时状态变更为：Waiting
     * @param mockThread
     */
    public abstract void sleep(MockThread mockThread);

    /**
     * 此时状态变更为：Time_Waiting
     * 注：假定休眠的时间已过
     * @param mockThread
     * @param timeout
     */
    public abstract void sleepTimeout(MockThread mockThread, long timeout);

    /**
     * 此时状态变更为：Terminated
     * @param mockThread
     */
    public abstract void run(MockThread mockThread);

    /**
     * 此时状态变更为Runnable
     * @param mockThread
     */
    public abstract void mockNotify(MockThread mockThread);

    /**
     * 模拟获取到锁了
     * 此时状态变更为Blocked
     * @param mockThread
     */
    public abstract void getLock(MockThread mockThread);

    /**
     * 模拟释放锁了
     * 此时状态变更为Runnable
     * @param mockThread
     */
    public abstract void unlock(MockThread mockThread);
}
