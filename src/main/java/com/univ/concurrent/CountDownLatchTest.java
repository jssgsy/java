package com.univ.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author univ
 * @datetime 2018/11/7 7:16 PM
 * @description
 * 1. CountDownLatch、CyclicBarrier、Semaphore均是jdk1.5中引入的，是一个并发编程辅助类；
 * 2. CountDownLatch的使用场景：任务A需要在任务B、C、D任务执行完之后再执行，(A，B，C，D均由不同的线程完成)，此时可用CountDownLatch完成。其实就是，线程A需要在线程B、C、D执行完后才能执行；
 * 3. CountDownLatch的用法很简单，下例模拟了主线程必须在其它线程执行完后才能继续执行；
 * 4. CountDownLatch不可重用；(参见CyclicBarrierTest)
 */


/**
 *
 * 1. 构造器中的计数值（count）实际上就是闭锁需要等待的线程数量。这个值只能被设置一次，而且CountDownLatch没有提供任何机制去重新设置这个计数值。
 * 2. 注意：CountDownLatch的使用场景是一个线程需要等待其它线程执行后(其实就是调用countDown方法)再执行，线程间不一定具有父子关系；
 * 3. CountDownLatch的核心方法：
 *     a. await();调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
 *     b. await(long timeout, TimeUnit unit);和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
 *     c. countDown();将count值减1
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        for (int i = 0; i < 2; i++) {   // 启动两个线程
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "开始运行");
                // 此线程执行结束通知主线程，countDown方法调用一次表示某个线程执行结束了
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "此句仍然会运行");
            }).start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            // 调用wait方法会让执行此方法的线程(即这里的主线程)暂停直到其它2个线程(构造函数中设置的)执行完成再执行
            countDownLatch.await();
            System.out.println("主线程：" + Thread.currentThread().getName() + " 执行结束了");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
