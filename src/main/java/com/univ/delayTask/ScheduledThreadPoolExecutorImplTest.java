package com.univ.delayTask;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 使用ScheduledThreadPoolExecutor来实现延迟任务
 * @author univ
 * date 2023/12/14
 */
public class ScheduledThreadPoolExecutorImplTest {

    @Test
    public void basic() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        // 设置了并发数为3，因此一次可同时执行三个任务，所以下面的h、e、l会同时输出
        scheduledExecutorService.schedule(() -> System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis() + " h h"), 4L, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(() -> System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis() + " e e"), 4L, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(() -> System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis() + " l l"), 4L, TimeUnit.SECONDS);
        // 当上述三个任务结束后就又能去执行另三个任务了，因此o、x会同时输出
        scheduledExecutorService.schedule(() -> System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis() + " o o"), 8L, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(() -> System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis() + " x x"), 8L, TimeUnit.SECONDS);
        while (true){}
    }


    /**
     * 有点奇怪，加入sleep后还可以执行任务，还需要深究
     */
    @Test
    public void withSleep() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
//        scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("h h"), 3l, 3l, TimeUnit.SECONDS);
        // 设置了并发数为3，因此一次可同时执行三个任务，所以下面的h、e、l会同时输出
        scheduledExecutorService.schedule(() -> {
            System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis() + " h h");
            sleep(10000);
            System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis() + " 休眠结束1");
        }, 4L, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(() -> {
            System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis() + " e e");
            sleep(9000);
            System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis() + " 休眠结束2");
        }, 4L, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(() -> System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis() + " l l"), 4L, TimeUnit.SECONDS);
        // 当上述三个任务结束后就又能去执行另三个任务了，因此o、x会同时输出
        scheduledExecutorService.schedule(() -> System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis() + " o o"), 8L, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(() -> System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis() + " x x"), 8L, TimeUnit.SECONDS);

        while (true){}
    }

    private void sleep(long milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ScheduledThreadPoolExecutor也能以固定延时执行
     */
    @Test
    public void test3() {
        System.out.println("现在的时间： " + System.currentTimeMillis() / 1000);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        // scheduleAtFixedRate：在初始的2s后，固定以5s的时间执行一次
        // 核心：以上一个任务开始的时间计时，period时间过去后，检测上一个任务是否执行完毕，如果上一个任务执行完毕，则当前任务立即执行，
        // 如果上一个任务没有执行完毕，则需要等上一个任务执行完毕后立即执行
        // 下面的示例为：第一个任务执行到了3s时看其是否结束，发现没有，因此等待，等第一个任务执行结束后(6s)立马开始第二个任务，再过6s立马开始第三个任务
//        scheduledExecutorService.scheduleAtFixedRate(() -> {System.out.println(System.currentTimeMillis() / 1000 + " xxx");sleep(6000);}, 2L, 3L, TimeUnit.SECONDS);

        // 下面的示例为：第一个任务执行到了3s时看其是否结束，发现结束了，因此执行第二个任务，再过3秒执行第三个任务
//        scheduledExecutorService.scheduleAtFixedRate(() -> {System.out.println(System.currentTimeMillis() / 1000 + " yyy");sleep(2000);}, 2L, 3L, TimeUnit.SECONDS);


        // scheduleWithFixedDelay: 在初始的3s后，固定以4s的时间执行一次
        // 核心：在一个任务结束后，等待固定的时间再执行。即受任务执行时长的影响即任务一定是串行的。
        // 下面的示例为：第一个任务执行结束(6s)后等待4s执行第二个任务，再过10s(6+4)执行第三个任务
//        scheduledExecutorService.scheduleWithFixedDelay(() -> {System.out.println(System.currentTimeMillis() / 1000 + " zzz"); sleep(6000);}, 3, 4, TimeUnit.SECONDS);

        // 下面的示例为：第一个任务执行结束(2s)后等待4s执行第二个任务，再过6s(2+4)执行第三个任务，再过6s(2+4)执行第四个任务
        scheduledExecutorService.scheduleWithFixedDelay(() -> {System.out.println(System.currentTimeMillis() / 1000 + " aaa"); sleep(2000);}, 3, 4, TimeUnit.SECONDS);

        while (true){}
    }


}
