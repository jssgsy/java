package com.univ.delayTask;

import lombok.Getter;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 使用延迟队列{@link DelayQueue}完成
 *
 * 要更好的使用{@link DelayQueue}则需要对DelayQueue源码有一定了解
 *
 * @author univ
 * date 2023/12/14
 */
public class DelayQueueImplTest {

    public static void main(String[] args) {
        UnivTask u1 = new UnivTask("aaa", 8L);
        UnivTask u2 = new UnivTask("bbb", 3L);

        DelayQueue<UnivTask> univTasks = new DelayQueue<>();
        univTasks.offer(u1);
        univTasks.offer(u2);

        new Thread(() -> {
            UnivTask take = null;
            try {
                while (true) {
                    // 重点：take方法内部会调用awaitNanos(delay)方法
                    // 所以task的延时一定要处理好
                    take = univTasks.take();
                    System.out.println("获取到任务了，" + take.getTaskContent());
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

}

@Getter
class UnivTask implements Delayed {
    private final String taskContent;

    /**
     * 任务多久后触发，单位为秒
     */
    private final Long triggerTime;

    public UnivTask(String taskContent, Long delayTime) {
        this.taskContent = taskContent;
        // 别忘了System.currentTimeMillis()，这样才能表达语义 多久之后
        this.triggerTime = System.currentTimeMillis() + delayTime * 1000;
    }

    /**
     * 任务还剩余多久可执行
     * @param unit the time unit DelayQueue内部使用的，看了下源码，固定是NANOSECONDS
     *             这是可理解的，说明精度还是挺高的
     */
    @Override
    public long getDelay(TimeUnit unit) {
        // 重点1：这里必须引入System.currentTimeMillis()，因为要让方法getDelay的返回值逐渐减少直至到为0
        //      否则返回值为固定值，则永远也不会执行；
        // 重点2：最后一个入参的单位千万不要是TimeUnit.SECONDS甚至是更大的单位，因为队列的take方法会awaitNanos(getDelay(NANOSECONDS))
        //      如果是TimeUnit.SECONDS，则会将任务启动的时间延迟1000倍
        return unit.convert(triggerTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return this.triggerTime.compareTo(((UnivTask) o).triggerTime);
    }
}


