package com.univ.algorithom.ratelimiter;

import org.junit.Test;

import com.google.common.base.Stopwatch;

/**
 * @author univ
 * @date 2021/4/1 7:00 下午
 * @description
 *
 * Q：什么是StopWatch？
 * A：StopWatch就是一个秒表，也就是一个计时器
 *
 * 重点：既然是一个秒表，那操作就只有如下几个：
 * 1. 启动秒表：开始计时
 * 2. 读取秒数：未停止时亦可读
 * 2. 停止秒表：停止计时
 * 3. 复位：归位(停止秒表、清空计时)
 */
public class StopWatchTest {

    @Test
    public void test() throws InterruptedException {
        // Stopwatch只能通过静态方法获取
        Stopwatch stopwatch = Stopwatch.createUnstarted();

        // 1. 启动秒表
        stopwatch.start();
        Thread.sleep(1000L);

        // 秒表未停止时亦可读取秒数(与真实世界相同)
        System.out.println("秒表还在计时中，本次计时(秒)：" + stopwatch.elapsed().getSeconds());

        Thread.sleep(2000L);
        // 2. 停止秒表
        stopwatch.stop();
        // 3. 读取秒数
        System.out.println("秒表已停止，本次计时(秒)：" + stopwatch.elapsed().getSeconds());

        // 4. 重置秒表，此时做两件事：1. 停止秒表，2. 读数归零
        stopwatch.reset();
        System.out.println("秒表已归位，本次计时(秒)：" + stopwatch.elapsed().getSeconds());
    }
}
