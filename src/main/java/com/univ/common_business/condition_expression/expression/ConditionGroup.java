package com.univ.common_business.condition_expression.expression;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.univ.common_business.condition_expression.constant.ConditionExpressionRelatedConst;

import lombok.Data;

/**
 * @author univ
 * 2022/2/18 3:55 下午
 */
@Data
public class ConditionGroup implements ConditionExpression{

    private String type = ConditionExpressionRelatedConst.TYPE_GROUP;

    /**
     * 这里重要。条件组下是一个【条件表达式】的数组，而这里的【条件表达式】即可以是一个ConcreteCondition也可以是一个ConditionExpression
     */
    private List<ConditionExpression> conditions;

    /**
     * and、or
     */
    private String logicOperator;

    @Override
    public void and(ConditionExpression conditionExpression) {
        if (CollectionUtils.isEmpty(conditions)) {
            conditions = new ArrayList<>();
        }
        conditions.add(conditionExpression);
    }

    @Override
    public void or(ConditionExpression conditionExpression) {
        if (CollectionUtils.isEmpty(conditions)) {
            conditions = new ArrayList<>();
        }
        conditions.add(conditionExpression);
    }
}
