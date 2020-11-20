package com.univ.patterndesign.singleton;

/**
 * @author univ
 * @datetime 2018/11/5 9:33 AM
 * @description 单例模式-双重检查锁的写法
 */
public class Singleton {

    private static volatile Singleton instance;

    /**
     * 静止外部直接实例化
     */
    private Singleton() {
    }

    public static Singleton getInstance() {
        if (null == instance) {
            synchronized (Singleton.class) {
                if (null == instance) {
                    // 分成三步：1. 分配内存空间、2. 初始化对象、3. 将内存空间的地址赋值给对应的引用
                    // 其中2和3可能会发生指令重排序，所以需要有volatile修饰，禁止其重排序
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
