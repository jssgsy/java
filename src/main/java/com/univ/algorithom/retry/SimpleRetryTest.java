package com.univ.algorithom.retry;

import java.util.concurrent.Callable;
import java.util.function.Predicate;

import org.junit.Test;

/**
 * @author univ
 * @date 2021/4/6 4:19 下午
 * @description 自实现的简单的重试机制
 *
 * 重点一：重试时机：
 * 1. 抛出异常时；
 * 2. 接口返回值不符合预期时
 *
 * 重点二：注意是如何兼容抛出异常时也重试的。
 *
 * 重点三：这里直接借助了Callable与Runnable作为要重试的逻辑的载体；
 *
 * 重点四：体会这里递归的妙用
 *
 */
public class SimpleRetryTest {

    /**
     * 待重试方法-重试时机-返回false
     *
     * @return
     */
    public boolean fnWithBoolean() {
        System.out.println("fnWithBoolean被调用了-重试时机-返回了false");
        // return false;

        // 放开这句，也会重试
        throw new RuntimeException("fnWithBoolean抛出异常了");
    }

    /**
     * 待重试方法-重试时机-抛出异常
     * @return 此返回值没任何意义，仅仅是为了call方法能统一三种场景
     */
    public Object fnWithException() {
        System.out.println("fnWithException被调用了-重试时机：抛出异常");
        throw new RuntimeException("fnWithException抛异常了");
    }

    /**
     * 待重试方法-重试时机-返回值不符合预期
     * @return
     */
    public Integer fnWithReturnResult() {
        System.out.println("fnWithReturnResult被调用了-重试时机：返回值不符合预期");
        return 10;
    }

    /**
     * 场景1：接口返回值为false时重试
     *
     * 兼容了异常时也重试
     */
    @Test
    public void testWithBoolean() {
        SimpleRetryHelper retryHelper = new SimpleRetryHelper();
        retryHelper.callWithBoolean(this::fnWithBoolean);
    }

    /**
     * 场景2：接口抛出异常时重试
     */
    @Test
    public void testWithException() {
        SimpleRetryHelper retryHelper = new SimpleRetryHelper();
        retryHelper.callWithException(this::fnWithException);
    }

    /**
     * 场景3：接口返回值不符合预期时重试。兼容了场景1
     *
     * 兼容了异常时也重试
     */
    @Test
    public void testWithReturnResult() {
        SimpleRetryHelper retryHelper = new SimpleRetryHelper(3, new SleepRetryStrategy(2000L));
        retryHelper.callWithReturnResult(this::fnWithReturnResult, t -> {
            Integer t1 = (Integer) t;
            return t1 > 10;
        });
    }

    /**
     * 上述场景一、二、三的结合体
     */
    @Test
    public void testCall() {
        SimpleRetryHelper retryHelper = new SimpleRetryHelper(3);
        // 模拟场景1
        retryHelper.call(this::fnWithBoolean, t -> {
            // 发生异常时t为null
            if (null == t) {
                return false;
            }
            return (Boolean) t;
        });
        System.out.println("-----换行-----");

        // 模拟场景2
        retryHelper = new SimpleRetryHelper(3);
        retryHelper.call(this::fnWithException, null);
        System.out.println("-----换行-----");

        // 模拟场景3
        retryHelper = new SimpleRetryHelper(3);
        retryHelper.call(this::fnWithReturnResult, t -> {
            // 发生异常时结果t为null
            if (null == t) {
                return false;
            }
            Integer t1 = (Integer) t;
            return t1 > 10;
        });
    }

    /**
     * 重试策略：重试前需要做的事情，如什么也不做，或者休眠一会再重试等
     */
    interface RetryStrategy {
        void beforeRetry();
    }

    class DefaultRetryStrategy implements RetryStrategy {

        @Override
        public void beforeRetry() {
            System.out.println("默认的重试策略-DefaultRetryStrategy-doNothing");
        }
    }

    class SleepRetryStrategy implements RetryStrategy {

        private Long sleepTimeInMillSeconds;

        public SleepRetryStrategy(Long sleepTimeInMillSeconds) {
            this.sleepTimeInMillSeconds = sleepTimeInMillSeconds;
        }

        @Override
        public void beforeRetry() {
            if (sleepTimeInMillSeconds <= 0) {
                return;
            }
            try {
                System.out.println("重试策略-SleepRetryStrategy休眠【" + sleepTimeInMillSeconds + "】毫秒");
                Thread.sleep(sleepTimeInMillSeconds);
            } catch (InterruptedException e) {
                System.out.println("重试策略-SleepRetryStrategy抛出异常了");
            }
        }
    }

    /**
     * 重试类
     *
     * 非线程安全：即不要将此类作为共享资源使用，亦即不要当作单例对象使用
     */
    class SimpleRetryHelper {
        /**
         * 最大重试次数，不包括首次的调用
         */
        int maxRetryNum = 3;

        /**
         * 当前是第几次重试
         */
        int currentRetryNum = 0;

        /**
         * 重试策略
         */
        private RetryStrategy retryStrategy;

        public SimpleRetryHelper() {
            retryStrategy = new DefaultRetryStrategy();
        }

        public SimpleRetryHelper(int maxRetryNum) {
            this.maxRetryNum = maxRetryNum;
            retryStrategy = new DefaultRetryStrategy();
        }

        public SimpleRetryHelper(int maxRetryNum, RetryStrategy retryStrategy) {
            this.maxRetryNum = maxRetryNum;
            this.retryStrategy = retryStrategy;
        }

        /**
         * 目标方法返回false时表明需要重试，但此时没法覆盖到目标方法发生异常时的重试
         *
         * 但如果目标方法返回的是非
         * @param callable
         * @throws Exception
         */
        public void callWithBoolean(Callable<Boolean> callable) {
            Boolean result = null;
            // 兼容抛出异常时也重试
            try {
                result = callable.call();
            } catch (Exception exception) {
                retryWithBoolean(callable);

                return;
            }
            if (null == result || !result) {
                retryWithBoolean(callable);
            }
        }

        /**
         * 当接口的调用返回结果不符合预期时重试
         * @param callable  可能会重试的目标逻辑
         * @param predicate 是否要重试的判断逻辑，也可作为一个类字段，这样的好处是不用每次使用时都传入一遍
         * @throws Exception
         */
        public void callWithReturnResult(Callable callable, Predicate predicate) {
            Object result = null;
            // 兼容抛出异常时也重试
            try {
                result = callable.call();
            } catch (Exception exception) {
                retryWithReturnResult(callable, predicate);

                // 这句不要忘了，否则下面的语句可能会执行
                return;
            }

            if (!predicate.test(result)) {
                retryWithReturnResult(callable, predicate);
            }
        }

        /**
         * 上述三种场景的结合体，这样对外只需要暴露一个方法而已
         *
         * 方法作用：执行callable，执行过程中发生异常，或者执行结果不符合预期时重试。
         *
         * 注：因为这里的predicate只在需要根据返回值是否满足条件而重试，因此可以将其作为类的成员变量，这样对外暴露可以少一个参数
         * @param callable Callable有返回值，因此可兼容抛出异常时的无返回值情况
         * @param predicate
         */
        public void call(Callable callable, Predicate predicate) {
            Object result;
            try {
                result = callable.call();
            } catch (Exception exception) {
                // 兼容异常情况，此时和返回值已经没关系了
                retryCall(callable, predicate);

                // 这句不要忘了，否则下面的语句可能会执行
                return;
            }
            if (null != predicate && !predicate.test(result)) {
                retryCall(callable, predicate);
            }
        }

        private void retryCall(Callable callable, Predicate predicate) {
            if (currentRetryNum >= maxRetryNum) {
                return;
            }
            currentRetryNum++;
            System.out.println("当前重试第【" + currentRetryNum + "】次，且上次调用不符合预期(可能是返回值有误，可能抛出了异常)，需要再次重试。。。");
            Object result;
            try {
                result = callable.call();
            } catch (Exception exception) {
                retryCall(callable, predicate);

                return;
            }
            if (null != predicate && !predicate.test(result)) {
                retryCall(callable, predicate);
            }
        }

        private void retryWithReturnResult(Callable callable, Predicate predicate){
            if (currentRetryNum >= maxRetryNum) {
                return;
            }
            currentRetryNum++;
            System.out.println("当前重试第【" + currentRetryNum + "】次，且上次调用返回值不符合预期，需要再次重试。。。");
            retryStrategy.beforeRetry();
            Object result = null;
            // 兼容抛出异常时也重试
            try {
                result = callable.call();
            } catch (Exception exception) {
                retryWithReturnResult(callable, predicate);

                return;
            }

            if (!predicate.test(result)) {
                retryWithReturnResult(callable, predicate);
            }
        }

        /**
         * 目标方法抛出任何异常时重试
         * @param runnable
         */
        public void callWithException(Runnable runnable) {
            try {
                runnable.run();
            } catch (Exception e1) {
                // 此时要重试
                retryWithException(runnable);
            }
        }

        private void retryWithBoolean(Callable<Boolean> callable) {
            if (currentRetryNum >= maxRetryNum) {
                return;
            }
            currentRetryNum++;
            System.out.println("当前重试第【" + currentRetryNum + "】次，且上次调用返回了false，需要再次重试。。。");
            Boolean result = null;
            // 这样便兼容了抛出异常时也重试
            try {
                result = callable.call();
            } catch (Exception exception) {
                retryWithBoolean(callable);
            }
            if (null == result || !result) {
                retryWithBoolean(callable);
            }
        }

        private void retryWithException(Runnable runnable) {
            if (currentRetryNum >= maxRetryNum) {
                System.out.println("已达最大重试次数，currentRetryNum：" + currentRetryNum + " maxRetryNum: " + maxRetryNum);
                return;
            }
            currentRetryNum++;
            try {
                System.out.println("当前重试第【" + currentRetryNum + "】次，且上次调用发生了异常，需要再次重试。。。");
                runnable.run();
            } catch (Exception e) {
                // 利用递归达到重试的效果！
                retryWithException(runnable);
            }
        }

    }

}
