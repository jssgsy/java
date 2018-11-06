package com.univ.patterndesign.singleton;

/**
 * @author univ
 * @datetime 2018/11/5 9:33 AM
 * @description 单例模式
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
                    // 此句不是原子的，所以需要有volatile修饰
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
