package com.univ.algorithom.ratelimiter;

import org.junit.Test;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author univ
 * @date 2021/4/1 8:00 下午
 * @description
 *
 * 参考笔记：https://a82b7684.wiz06.com/wapp/pages/view/share/s/2EaTq42BJQUg2ptAMq2SlqEf3q2Oy-10m4Aa2Bmpet10wy4w
 */
public class RateLimiterTest {

    /**
     * 速率：一秒生成1个permit，也就是接口限速QPS为1
     */
    RateLimiter rateLimiter = RateLimiter.create(1);

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 6; i++) {
            if (i == 3) {
                Thread.sleep(4000L);
            }
            fn(i);
        }
    }

    public void fn(int i) {
        // 每次请求获取3个permit
        double result = rateLimiter.acquire(3);
        System.out.println("第【" + i + "】次请求，上一次预消费了时间(秒) :" + result);
    }
}
