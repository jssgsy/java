package com.univ.multithread.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

/**
 * 演示如何捕获线程中未显示捕获(try-catch)的异常；
 *
 * 基础知识
 * 1. 如果某个线程中抛出了异常，而此线程又没有经过处理，则此异常会一直往上抛，直至此线程终结；
 * 2. 默认情况下，一个线程是没法捕捉到另一个线程中发生的异常的；
 * 3. 如果要捕获子线程中的未处理异常，则只能借助RunnableFuture;
 *
 * 解决方案：jdk提供的异常捕获类{@link Thread.UncaughtExceptionHandler}
 * 核心入口(从此)：{@link Thread#dispatchUncaughtException(Throwable)}：线程发生了未捕获异常时，由JVM来调用的方法
 * 由此入口可很好的验证如下几点：
 *
 * 1. 即可以为单个线程设置异常捕获处理器(实例变量：Thread.uncaughtExceptionHandler)；
 * 2. 也可以为整个线程设置默认的异常捕获处理器(静态变量：Thread.defaultUncaughtExceptionHandler)；
 * 3. 线程组{@link ThreadGroup}继承自UncaughtExceptionHandler；
 * 4. 线程池{@link java.util.concurrent.ThreadPoolExecutor}要异常捕获类，不能使用默认的{@link java.util.concurrent.ThreadFactory}(默认的什么也不做)，而要提供自定义的实现
 * 5. 总体流程：当线程出现未捕获异常时，如果此线程没有设置单独的异常捕获处理器(uncaughtExceptionHandler)时，
 *      那么就使用ThreadGroup(本身就是一个异常捕获处理器)，ThreadGroup内部会使用全局默认的异常捕获处理器(defaultUncaughtExceptionHandler),
 *      如果全局默认的异常捕获处理器也为null，则直接将异常信息打印在控制台；
 *
 *
 * @author univ
 * 2022/5/7 8:30 下午
 */
public class UnCaughtExceptionTest {

    @Test
    public void setNoUncaughtExceptionHandler() {
        // 是捕获不到其它线程的异常的
        try {
            new Thread(() -> {
                throw new RuntimeException("线程【" + Thread.currentThread().getName() + "】抛出异常了");
            }).start();
        } catch (Exception exception) {
            System.out.println("捕获到子线程异常了");
        }
    }

    /**
     * 单个线程设置异常捕获处理器
     */
    @Test
    public void setUncaughtExceptionHandlerLocally() {

        Thread t1 = new Thread(() -> {
            throw new RuntimeException("线程【" + Thread.currentThread().getName() + "】抛出异常了");
        });
        // 单独为此线程设置异常捕获处理器
        t1.setUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("线程【" + Thread.currentThread().getName() +
                    "】捕获到了线程【" + thread.getName() + "】中的异常：" + exception.getMessage());
        });
        t1.start();

        // 没有设置异常捕获处理器的线程是没法被捕获
        new Thread(() -> {
            throw new RuntimeException("线程【" + Thread.currentThread().getName() + "】抛出异常了，捕获不到的");
        }).start();
    }

    /**
     * 全局设置异常捕获处理器
     */
    @Test
    public void setUncaughtExceptionHandlerGlobally() {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> System.out.println("线程【" + Thread.currentThread().getName() +
                "】捕获到了线程【" + t.getName() + "】中的异常：" + e.getMessage()));
        new Thread(() -> {
            throw new RuntimeException("线程【" + Thread.currentThread().getName() + "】抛出异常了");
        }).start();

        new Thread(() -> {
            throw new RuntimeException("线程【" + Thread.currentThread().getName() + "】抛出异常了");
        }).start();
    }

    /**
     * 线程池设置未捕获异常处理器
     * 核心：本质和上面一样还是线程的创建，所以只要找到线程池中线程创建的源头即可。
     *
     * 注意点：
     * 1. 线程池的execute方法有未捕获异常时能被设置的异常捕获器处理；
     * 2. 线程池的submit方法有未捕获异常时不能被设置的异常捕获器处理，因为异常被转移到了RunnableFuture结果中，不会被JVM捕获到；
     */
    @Test
    public void testThreadPoolExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(), new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread thread = new Thread(r);
                // 重点：需要自定义提供 UncaughtExceptionHandler
                thread.setUncaughtExceptionHandler((t, e) -> {
                    System.out.println("线程【" + Thread.currentThread().getName() +
                            "】捕获到了线程【" + t.getName() + "】中的异常：" + e.getMessage());
                });
                return thread;
            }
        });
        // 线程池execute方法中的未捕获异常会被捕获
        executor.execute(() -> {
            throw new RuntimeException("线程【" + Thread.currentThread().getName() + "】抛出异常了");
        });

        // 线程池submit方法中的未捕获异常不会被捕获，因为已经被转成了RunnableFuture
        Future<?> result = executor.submit(() -> {
            int i = 10 / 0;
        });
        try {
            // 此时才会被捕获到。
            // 重点：通过RunnableFuture的方式可以捕获到子线程的异常
            result.get();
        } catch (Exception exception) {
            System.out.println("线程【" + Thread.currentThread().getName() + "】捕获了子线程中的未捕获异常: " + exception.getMessage());
        }

    }

    /**
     * 线程池ExecutorService
     *
     * 由Executors获得的线程池其实就是ThreadPoolExecutor，因此其处理方法与之一模一样；
     */
    @Test
    public void testExecutorService() {
        /**
         * 从Executors创建的线程池都是{@link java.util.concurrent.ThreadPoolExecutor}实例
         * 在实例化ThreadPoolExecutor时，会将这里传入的ThreadFactory实例会给ThreadPoolExecutor
         */
        ExecutorService executorService = Executors.newFixedThreadPool(5, new ThreadFactory() {
            @Override
            public Thread newThread(@NotNull Runnable r) {
                Thread thread = new Thread(r);
                thread.setUncaughtExceptionHandler((t, e) -> {
                    System.out.println("线程【" + Thread.currentThread().getName() +
                            "】捕获到了线程【" + t.getName() + "】中的异常：" + e.getMessage());
                });
                return thread;
            }
        });
        // 直接被捕获
        executorService.execute(() -> {
            throw new RuntimeException("线程【" + Thread.currentThread().getName() + "】抛出异常了");
        });

        Future<?> result = executorService.submit(() -> {
            int i = 10 / 0;
        });
        try {
            // 此时才会被捕获到
            result.get();
        } catch (Exception exception) {
            System.out.println("线程池的submit中的未捕获异常被捕获到了: " + exception.getMessage());
        }
    }
}
