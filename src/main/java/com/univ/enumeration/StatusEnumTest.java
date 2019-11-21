package com.univ.enumeration;

import java.util.Arrays;

import org.junit.Test;

public class StatusEnumTest {

    @Test
    public void test() {
        System.out.println(StatusEnum.STATUS_OFF.getKey());
        System.out.println(StatusEnum.STATUS_OFF.getValue());

        StatusEnum anEnum = StatusEnum.getByKey(10);

        // 对enum使用switch前注意要先判断非空，否则NPE
        if (null != anEnum) {
            switch (anEnum) {
            case STATUS_OFF:
                System.out.println("off");
                break;
            case STATUS_ON:
                System.out.println("on");
                break;
            }
        }

    }
}

enum StatusEnum {
    STATUS_ON(1, "开启"),
    STATUS_OFF(0, "关闭");

    public static StatusEnum getByKey(Integer key) {
        return Arrays.stream(values()).filter(t -> t.getKey().equals(key)).findFirst().orElse(null);
    }

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




