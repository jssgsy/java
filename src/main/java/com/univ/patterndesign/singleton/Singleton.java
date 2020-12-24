package com.univ.patterndesign.singleton;

/**
 * @author univ
 * @datetime 2018/11/5 9:33 AM
 * @description 单例模式-双重检查锁的写法
 */
public class Singleton {

    /**
     * 需要使用volatile的原因：禁止重排序
     *  参考下面实例一个对象时的三个步骤。
     *  当线程一执行到instance = new Singleton()时，如果发生了重排序，第3步到第2步前面了，此时instance就不为null了，
     *  因此如果线程二来执行时，会在外围的if判断中为false，因此此时会返回一个未初始化完成的instance对象。
     */
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
