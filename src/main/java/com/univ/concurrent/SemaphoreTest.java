package com.univ.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @author univ
 * @datetime 2018/11/7 8:42 PM
 * @description
 * 1. CountDownLatch、CyclicBarrier、Semaphore均是jdk1.5中引入的，是一个并发编程辅助类；
 * 2. Semaphore的含义是，只有M个资源，如果N(N>M)个人要用，则只能同时满足M个人的需求，其它人要等其它人用完后才能用
 * 3. Semaphore用法很简单
 */

/*
核心方法：
Semaphore(int permits);
acquire();试图获取一个许可，如果没有可用的许可，则阻塞直到有可用的许可
acquire(int permits);
release();Releases a permit, returning it to the semaphore.
release(int permits);
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        int N = 10;
        int M = 6;
        // 同时只有6个资源能被使用,相当于往一个池子中放入了6个资源
        Semaphore semaphore = new Semaphore(M);

        for (int i = 0; i < N; i++) {
            new Thread(() -> {

                try {
                    // 申请使用一个资源
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "获得一个资源");
                    // 睡眠一下，模拟出只能同时使用六个资源
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "处理完了，释放资源");
                    // 使用完资源后要释放，相当于把资源又放回池子中
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
