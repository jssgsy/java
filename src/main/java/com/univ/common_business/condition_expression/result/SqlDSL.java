package com.univ.common_business.condition_expression.result;

import com.univ.common_business.condition_expression.expression.ConcreteCondition;
import com.univ.common_business.condition_expression.expression.ConditionExpression;
import com.univ.common_business.condition_expression.expression.ConditionGroup;

/**
 * @author univ
 * 2022/2/24 4:49 下午
 */
public class SqlDSL implements PlatformDSL {

    @Override
    public String generate(ConditionExpression conditionExpression) {
        if (conditionExpression instanceof ConcreteCondition) {
            return generateForConcreteCondition((ConcreteCondition) conditionExpression);
        }
        if (conditionExpression instanceof ConditionGroup) {
            return generateForConditionGroup((ConditionGroup) conditionExpression);
        }
        throw new UnsupportedOperationException("不支持的条件表达式类型");
    }



}
