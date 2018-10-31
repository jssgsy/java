package com.univ.enumeration;

import org.junit.Test;

public class StatusEnumTest {

    @Test
    public void test() {
        System.out.println(StatusEnum.STATUS_OFF.getKey());
        System.out.println(StatusEnum.STATUS_OFF.getValue());
    }
}

enum StatusEnum {
    STATUS_ON(1, "开启"),STATUS_OFF(0, "关闭");

    private Integer key;
    private String value;

    StatusEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}




