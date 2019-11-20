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

    private Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
            .retryIfResult(Predicates.isNull()) // Callable返回null就重试
            .retryIfResult(aBoolean -> Objects.equals(aBoolean, Boolean.FALSE)) // Callable返回false就重试
            .retryIfRuntimeException()  // Callable抛出运行时异常就重试
            .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .build();


    @Test
    public void basic() {
        try {
            Boolean call = retryer.call(() -> {
                System.out.println("enter ......");
                // throw new RuntimeException("runtime exception...");
                return false;
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (RetryException e) {
            e.printStackTrace();
        }
    }
}
