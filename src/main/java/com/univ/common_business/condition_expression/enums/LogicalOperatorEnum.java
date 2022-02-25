package com.univ.common_business.condition_expression.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author univ
 * 2022/2/22 7:55 下午
 */
@Getter
@AllArgsConstructor
public enum LogicalOperatorEnum {
    AND("and", ""),
    OR("or", ""),
    ;

    private String code;

    private String desc;

    public static LogicalOperatorEnum getByCode(String code) {
        return Arrays.stream(values()).filter(t -> t.getCode().equals(code)).findFirst().orElse(null);
    }

    public static boolean valid(String code) {
        return Arrays.stream(values()).anyMatch(t -> t.getCode().equals(code));
    }
}
