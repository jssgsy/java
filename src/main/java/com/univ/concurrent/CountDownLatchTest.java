package com.univ.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author univ
 * @datetime 2018/11/7 7:16 PM
 * @description
 * 1. CountDownLatch、CyclicBarrier、Semaphore均是jdk1.5中引入的，是一个并发编程辅助类；
 * 2. CountDown的使用场景：任务A需要在任务B、C、D任务执行完之后再执行，(A，B，C，D均由不同的线程完成)，此时可用CountDownLatch完成。其实就是，线程A需要在线程B、C、D执行完后才能执行；
 * 3. CountDownLatch的用法很简单，下例模拟了主线程必须在其它线程执行完后才能继续执行；
 */

/*
1. 构造器中的计数值（count）实际上就是闭锁需要等待的线程数量。这个值只能被设置一次，而且CountDownLatch没有提供任何机制去重新设置这个计数值。

2. 与CountDownLatch的第一次交互是主线程等待其他线程。主线程必须在启动其他线程后立即调用CountDownLatch.await()方法。这样主线程的操作就会在这个方法上阻塞，直到其他线程完成各自的任务。

3. CountDownLatch的核心方法：
    a. await();调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
    b. await(long timeout, TimeUnit unit);和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
    c. countDown();将count值减1
 */


public class CountDownLatchTest {

    public static void main(String[] args) {

        // 构造函数就是需要等待的线程数量
        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "正在运行 : " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 此线程执行结束通知主线程，countDown方法调用一次表示某个线程被执行结束了,注意不要写在for中了
            countDownLatch.countDown();
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在运行 : " + i);
            }
            // 此线程执行结束通知主线程，countDown方法调用一次表示某个线程被执行结束了
            countDownLatch.countDown();
        }).start();

        try {
            // 调用wait方法会让执行此方法的线程(即这里的主线程)暂停直到其它2个线程(构造函数中设置的)执行完成再执行
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName() + " 执行结束了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
