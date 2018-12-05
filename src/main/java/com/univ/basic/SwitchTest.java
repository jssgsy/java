package com.univ.basic;

import org.junit.Test;

/**
 * @author univ
 * @datetime 2018/12/5 9:52 PM
 * @description switch注意事项
 */
public class SwitchTest {

    /**
     * 当switch中的参数为Object类型时，则其值不能为null，否则抛出异常
     */
    @Test
    public void test() {
        Integer l = null;
        switch (l) {
            case 1:
                System.out.println("hello");
            case 2:
                System.out.println("hello");
            case 3:
                System.out.println("hello");
        }
    }
}
