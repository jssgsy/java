package com.univ.patterndesign.state;

/**
 * 模拟线程
 * @author univ
 * 2021/6/15 10:46 上午
 */
public class MockThread {

    /**
     * 当前线程的状态
     */
    private State state;

    /**
     * 状态是可枚举的，应该只有一个实例
     */
    public static final State newState = new New();
    public static final State runnable = new Runnable();
    public static final State blocked = new Blocked();
    public static final State waiting = new Waiting();
    public static final State timeWaiting = new TimeWaiting();
    public static final State terminated = new Terminated();

    /**
     * 状态转移
     * @param state
     */
    public void transferState(State state) {
        this.state = state;
    }

    public void setInitialState(State state) {
        this.state = state;
    }

    /**
     * 此时状态变更为：Runnable
     */
    public void start() {
        // 做业务相关的事情

        // 状态转移
        /**
         * 重点：需要将this传递给state中，表明state所依附的实体，同时state很可能需要使用到this中的信息
         */
        state.start(this);
    }

    /**
     * 此时状态变更为：Waiting
     */
    public void sleep() {
        // 做业务相关的事情

        // 将与状态相关的部分转交给状态类来处理
        state.sleep(this);
    }

    /**
     * 此时状态变更为：Time_Waiting
     * 注：假定休眠的时间已过
     * @param timeout
     */
    public void sleepTimeout(long timeout) {
        // 做业务相关的事情

        // 将与状态相关的部分转交给状态类来处理
        state.sleepTimeout(this, timeout);
    }

    /**
     * 此时状态变更为：Terminated
     */
    public void run() {
        // 做业务相关的事情

        // 将与状态相关的部分转交给状态类来处理
        state.run(this);
    }

    /**
     * 此时状态变更为Runnable
     */
    public void mockNotify() {
        // 做业务相关的事情

        // 将与状态相关的部分转交给状态类来处理
        state.mockNotify(this);
    }


    /**
     * 模拟获取到锁了
     * 此时状态变更为Blocked
     */
    public void getLock() {
        // 做业务相关的事情

        // 将与状态相关的部分转交给状态类来处理
        state.getLock(this);
    }

    /**
     * 模拟释放锁了
     * 此时状态变更为Runnable
     */
    public void unlock() {
        // 做业务相关的事情

        // 将与状态相关的部分转交给状态类来处理
        state.unlock(this);
    }
}
