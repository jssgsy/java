package com.univ.multithread.threadgroup;

import org.junit.Test;

/**
 * @author univ
 * @date 2021/4/14 5:44 下午
 * @description
 */
public class ThreadGroupTest {

    private void print(ThreadGroup currentThreadGroup) {
        ThreadGroup parent = currentThreadGroup.getParent();
        System.out.println("当前线程所属线程组为：" + currentThreadGroup);
        while (null != parent) {
            System.out.println("\t父线程组为： " + parent);
            parent = parent.getParent();
        }
    }

    /**
     * 线程组的数据结构
     */
    @Test
    public void test() {
        ThreadGroup threadGroup1 = Thread.currentThread().getThreadGroup();
        print(threadGroup1);
        ThreadGroup group2 = new ThreadGroup("univ_group");
        print(group2);
    }

    /**
     * 将一个线程放到线程组中
     */
    @Test
    public void test2() {
        ThreadGroup threadGroup1 = Thread.currentThread().getThreadGroup();
        // 将一个线程放到线程组中
        new Thread(threadGroup1, "自定义线程1");
    }

}
