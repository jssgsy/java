package com.univ.common_business.condition_expression.enums;

import java.util.Arrays;

import com.univ.common_business.condition_expression.operator.AbstractOperatorProcessor;
import com.univ.common_business.condition_expression.operator.EqOperatorProcessor;
import com.univ.common_business.condition_expression.operator.GreaterThanOperatorProcessor;
import com.univ.common_business.condition_expression.operator.LikeOperatorProcessor;
import com.univ.common_business.condition_expression.operator.NotEqOperator;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * mytodo:如何处理此枚举与AbstractOperator的关系？一个是可枚举，一个是类
 *
 * @author univ
 * 2022/2/23 5:08 下午
 */
@Getter
@AllArgsConstructor
public enum OperatorEnum {

    EQ("eq", "=", "等于", new EqOperatorProcessor()),
    NOT_EQ("not_eq", "<>", "等于", new NotEqOperator()),
    // LT("lt", "<", "小于", new LessThanOperator()),
    GT("gt", ">", "大于", new GreaterThanOperatorProcessor()),
    like("like", "like", "模糊匹配", new LikeOperatorProcessor()),

    ;

    public static OperatorEnum getByCode(String code) {
        return Arrays.stream(values()).filter(t -> t.getFrontOperator().equals(code)).findFirst().orElseThrow(() -> new IllegalArgumentException("没有此操作符【" + code + "】"));
    }

    /**
     * 操作符对应的前端形式
     */
    private String frontOperator;

    /**
     * 操作符对应的后端形式
     */
    private String endOperator;

    /**
     * 描述
     */
    private String desc;

    /**
     * 对应的操作符处理器
     */
    private AbstractOperatorProcessor operatorProcessor;

}
