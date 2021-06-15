package com.univ.patterndesign.state;

import org.junit.Test;

/**
 * 这里模拟线程线程的转换
 * @author univ
 * 2021/6/15 11:27 上午
 */
public class StateTest {

    @Test
    public void test() {
        MockThread thread = new MockThread();

        // 示例一：new--->runnable--->blocked--->runnable--->terminated
        thread.setInitialState(MockThread.newState);
        thread.start();
        thread.getLock();
        thread.unlock();
        thread.run();
        System.out.println("\n");

        // 示例二：new--->runnable--->waiting--->runnable--->terminate
        thread.setInitialState(MockThread.newState);
        thread.start();
        thread.sleep();
        thread.run();
        System.out.println("\n");

        // 错误示范
        thread.setInitialState(MockThread.newState);
        thread.getLock();
        thread.sleep();
        thread.unlock();
        thread.mockNotify();
        System.out.println("\n");
    }
}
