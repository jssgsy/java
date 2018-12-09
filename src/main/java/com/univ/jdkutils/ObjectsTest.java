package com.univ.jdkutils;

import java.util.Objects;

import org.junit.Test;

/**
 * @author univ
 * @datetime 2018/12/9 2:34 PM
 * @description Objects类是java.util包下的工具类
 */
public class ObjectsTest {

    @Test
    public void test1() {
        Integer i = null;

        /**
         * 如果为null则抛NPL
         */
        Objects.requireNonNull(i);

        /**
         * 在抛NPL时可附加异常信息
         */
        Objects.requireNonNull(i, "i 不能为null值");

        /**
         * 在抛NPL时可附加异常信息，不过这里的异常信息的返回可通过函数来更细粒度的控制
         */
        Objects.requireNonNull(i, () -> "i 不能为null");
    }
}
