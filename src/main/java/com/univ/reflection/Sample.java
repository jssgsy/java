package com.univ.reflection;

/**
 * Univ
 * 16/8/31 16:21
 */

/**
 * 测试数据
 */
public class Sample {

    private String privateName;

    public int publicAge;

    public static Integer staticCount = 100;

    /**
     * 无参构造函数
     */
    public Sample() {
    }

    /**
     * 一个参数构造函数
     * @param privateName
     */
    public Sample(String privateName) {
        this.privateName = privateName;
    }

    /**
     * 两个参数构造函数
     * @param privateName
     * @param publicAge
     */
    public Sample(String privateName, int publicAge) {
        this.privateName = privateName;
        this.publicAge = publicAge;
    }


    public String getPrivateName() {
        return privateName;
    }

    public void setPrivateName(String privateName) {
        this.privateName = privateName;
    }

    public int getPublicAge() {
        return publicAge;
    }

    public void setPublicAge(int publicAge) {
        this.publicAge = publicAge;
    }

    public static Integer getStaticCount() {
        return staticCount;
    }

    public static void setStaticCount(Integer staticCount) {
        Sample.staticCount = staticCount;
    }
}
