package com.univ.threadlocal;

import org.junit.Test;

/**
 * InheritableThreadLocal：父线程向子线程传递ThreadLocal中的数据
 * 应用：MDC
 * @author univ
 * 2022/1/27 10:23 上午
 */
public class MyInheritableThreadLocal {

    // 注意，实际的对象是InheritableThreadLocal
    ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

    @Test
    public void test() {
        threadLocal.set(100);
        System.out.println("父线程：" + Thread.currentThread().getName() + " 的值为：" + threadLocal.get());
        new Thread(() -> {
            // 此时能获取到父线程中设置的值
            System.out.println("子线程：" + Thread.currentThread().getName() + " 的值为：" + threadLocal.get());
        }).start();
    }
}
