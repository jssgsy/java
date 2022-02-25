package com.univ.common_business.condition_expression.expression;

import java.util.List;

import com.univ.common_business.condition_expression.constant.ConditionExpressionRelatedConst;
import com.univ.common_business.condition_expression.enums.OperatorEnum;

import lombok.Data;

/**
 * 具体的末级的表达式，如a=1， b like zhangsan
 * 更多的是面向前端：用来接收前端的json串
 * @author univ
 * 2022/2/18 3:54 下午
 */
@Data
public class ConcreteCondition implements ConditionExpression {

    /**
     * json串有此值则表示这是一个具体的末级表达式
     */
    private String type = ConditionExpressionRelatedConst.TYPE_CONDITION;

    private String fieldName;

    /**
     * 为方便接收前端的json，这里就不定义成枚举了
     * @see com.univ.common_business.condition_expression.enums.DataTypeEnum
     */
    private String dataType;

    /**
     * 为方便接收前端的json，这里就不定义成枚举了
     * @see OperatorEnum
     */
    private String operator;

    private List<String> values;

    @Override
    public void and(ConditionExpression conditionExpression) {
        throw new UnsupportedOperationException("ConcreteCondition不支持添加and操作");
    }

    @Override
    public void or(ConditionExpression conditionExpression) {
        throw new UnsupportedOperationException("ConcreteCondition不支持添加or操作");
    }
    // 其它必要字段，如数据类型
}
