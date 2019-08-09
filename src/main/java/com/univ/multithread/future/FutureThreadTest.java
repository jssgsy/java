package com.univ.multithread.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.junit.Test;

/**
 * @author univ
 * @date 2019/7/22 10:11 AM
 * @description 演示如何使用FutureTask来创建线程执行
 */
public class FutureThreadTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<Integer> callable = new HelloCallable();

        /**
         * FutureTask实现了Runnable与Future<V>，即FutureTask代表了一个有返回值的Runnable
         * 内部是持有了一个Callable的实例，任务的执行会委托至此，所以需要作为构造函数传入。
         */
        FutureTask<Integer> task = new FutureTask<>(callable);
        Thread thread = new Thread(task);
        // 最终线程的启动还是使用的thread.start方法
        thread.start();
        // 获取任务执行返回的结果，这也是使用Callable的原因所有
        System.out.println(task.get());

        // 以上代码等价于,当然一般不这么用，因为就不能获取到结果了
        new Thread(new FutureTask<Integer>(new Callable<Integer>() {

            @Override
            public Integer call() throws Exception {
                System.out.println("线程名a：" + Thread.currentThread().getName());
                return 10;
            }
        }){

        }).start();

        // 注意使用Callable与Runnable语法上的区别，如下：
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程名b：" + Thread.currentThread().getName());
            }
        }).start();
    }

    @Test
    public void test() throws ExecutionException, InterruptedException {
        FutureTask<Integer> result = new FutureTask<>(() -> {
            System.out.println("当前线程名为：" + Thread.currentThread().getName());
            Thread.sleep(3000l);
            System.out.println("休眠结束");
            return 20;
        });
        new Thread(result).start();
        System.out.println("获取结果前---");
        // 此时会一直阻塞直到计算完成
        System.out.println(result.get());
        System.out.println("获取结果后---");
    }

}

/**
 * Callable接口与Runnable接口一样，只是Callable有返回值
 */
class HelloCallable implements Callable<Integer> {

    /**
     * call方法就相当于Runnable中的call方法
     * @return
     * @throws Exception
     */
    @Override
    public Integer call() throws Exception {
        System.out.println("HelloCallable#线程名：" + Thread.currentThread().getName());
        return 10;
    }
}