package com.univ.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author univ
 * @datetime 2018/11/7 8:04 PM
 * @description
 * 1. CountDownLatch、CyclicBarrier、Semaphore均是jdk1.5中引入的，是一个并发编程辅助类；
 * 2. CyclicBarrier与CountDownLatch类似，只是侧重点不同，CountDownLatch是线程A要在线程B、C、D执行完之后再执行，而CyclicBarrier是线程A、B、C、D都达到某个状态(比如都满足了某个条件，重点)时再开始执行；
 * 3. CyclicBarrier的用法很简单；
 * 4. CyclicBarrier是可重用的，CountDownLatch不能重用；
 */

/*
核心方法：
1. CyclicBarrier(int parties)；CyclicBarrier(int parties, Runnable barrierAction)；参数parties指让多少个线程，参数barrierAction为当这些线程都达到状态(条件)时会执行的内容（任选一个线程进行执行）；
2. await()；用来挂起当前线程，直至所有线程都到达barrier状态再执行后续任务
3. await(long timeout, TimeUnit unit);当前线程等待一定的时间，如果还有线程没有到达barrier状态就直接让到达barrier的线程执行后续任务
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        // 构造函数的参数就是要达到某个状态(条件)时的线程数量，第二个参数(非必填)会在达到条件时立马阻塞执行，先前其它线程的await方法后的代码
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            System.out.println("栅栏被解除立马执行");
            try {
                Thread.sleep(6000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    // 模拟达到某个条件，比写文件结束
                    if (true) {
                        System.out.println(Thread.currentThread().getName() + "达到条件了");
                        // 此时此线程会暂停，直到其它几个线程也达到条件后才接着执行
                        cyclicBarrier.await();
                        System.out.println(Thread.currentThread().getName() + "这句在所有其它线程达到条件后才会执行");
                    }
                    System.out.println(Thread.currentThread().getName() + "执行结束了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        System.out.println(Thread.currentThread().getName() + "执行结束了");// 注：第一句被执行

        // 所谓CyclicBarrier可重用，是指可以将之前的CyclicBarrier对象用至其它需要协同的线程上,这个意义上CountDownLatch显然是不能被重用的，因为一旦一个线程执行结束了，则减1，当所有线程都结束后已经变成0了，显然不能再用于下一组需要协调的线程上
        /*new Thread(() -> {
            // 满足条件
            try {
                System.out.println(Thread.currentThread().getName() + " CyclicBarrier可以被重用");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "执行结束了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            // 满足条件
            try {
                System.out.println(Thread.currentThread().getName() + " CyclicBarrier可以被重用");
                cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "执行结束了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();*/

    }
}
