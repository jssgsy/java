package com.univ.multithread.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

/**
 * @author univ
 * @date 2019/7/24 4:47 PM
 * @description
 * 核心对象：
 *  Executor：能执行提交任务的对象(接口)；抽象级别高；
 *  ExecutorService：接口，是Executor的子接口，抽象级别高，先理解成线程池(ThreadPoolExecutor是其实现类之一)；
 *  Executors：工具类
 *
 *  Executor的作用：用来替代显示的new Thread().start()。
 *
 *
 *  ThreadPoolExecutor常用来自定义线程池
 */
public class ExecutorServiceTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        Future<Integer> result = threadPool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("当前线程名称:" + Thread.currentThread().getName());
                Thread.sleep(3000l);
                System.out.println("休眠结束");
                return 10;
            }
        });
        System.out.println("before get----");
        // 会阻塞直到执行完成
        System.out.println(result.get());
        System.out.println("after get----");
    }



}
