package com.univ.thirdutils.retry;

import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;

/**
 * @author univ
 * @date 2019/11/20 2:45 PM
 * @description
 * 1. 构造retryer对象；
 * 2. 使用retryer.call(callable);
 */
public class GuavaRetryingTest {

    @Test
    public void testBoolean() throws ExecutionException, RetryException {
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(Predicates.isNull()) // Callable返回null就重试
                .retryIfResult(result -> Objects.equals(result, Boolean.FALSE)) // Callable返回false就重试
                .retryIfRuntimeException()  // Callable抛出运行时异常就重试
                .withWaitStrategy(WaitStrategies.fixedWait(2, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))   // 如果Stop的话就直接抛出RetryException异常了
                .build();
        Boolean call = retryer.call(() -> {
            System.out.println("目标方法被调用了");
            // 模拟返回false要重试
            return false;
        });
    }

    @Test
    public void testResult() {
        Retryer<Integer> retryer = RetryerBuilder.<Integer>newBuilder()
                .retryIfResult(result -> result < 10)   // 返回值大于10的时候重试
                // 实际使用时不要忘了设置StopStrategy，不然可能永远没法停止(默认值就是永不停止重试)
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .build();
        try {
            retryer.call(() -> {
                System.out.println("目标方法被调用了");
                return 9;
            });
        } catch (ExecutionException | RetryException e) {
            System.out.println("尽了最大努力也没法获取目标接口调用的期望值，那就抛出异常");
        }
    }
}
