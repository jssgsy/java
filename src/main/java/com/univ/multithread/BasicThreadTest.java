package com.univ.multithread;

import org.junit.Test;

/**
 * @author
 * @date @time
 */
public class BasicThreadTest {
    /**
     * 看多线程交叉直接执行
     * @throws InterruptedException
     */
    @Test
    public void test1() throws InterruptedException {
        new Thread(this::run).start();

        new Thread(this::run).start();

        // junit测试多线程时，主线程会先行结束，不会等子线程先执行完，所以这里让主线程休眠足够多的时间以便所有子线程执行完毕
        Thread.sleep(1000);
    }

    private void run() {
        for (int i = 0 ; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

