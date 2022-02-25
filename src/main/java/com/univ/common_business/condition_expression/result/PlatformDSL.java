package com.univ.common_business.condition_expression.result;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.univ.common_business.condition_expression.enums.OperatorEnum;
import com.univ.common_business.condition_expression.expression.ConcreteCondition;
import com.univ.common_business.condition_expression.expression.ConditionExpression;
import com.univ.common_business.condition_expression.expression.ConditionExpressionHelper;
import com.univ.common_business.condition_expression.expression.ConditionGroup;

/**
 * @author univ
 * 2022/2/24 4:47 下午
 */
public interface PlatformDSL {

    /**
     * 将条件表达式拼凑起来
     * 为条件表达式生成不同平台的dsl
     * 仅支持验证{@link com.univ.common_business.condition_expression.expression.ConcreteCondition}
     * 与{@link com.univ.common_business.condition_expression.expression.ConditionGroup}
     * @param conditionExpression
     * @return mytodo:固定用string类型好像也可以
     */
    String generate(ConditionExpression conditionExpression);

    /**
     * 将具体的末级表达式拼凑起来
     * @param concreteCondition
     * @return
     */
    default   String generateForConcreteCondition(ConcreteCondition concreteCondition) {
        if (!ConditionExpressionHelper.validConcreteCondition(concreteCondition)) {
            System.out.println("此表达式不合法:" + JSONObject.toJSONString(concreteCondition));
            throw new UnsupportedOperationException("此表达式不合法");
        }
        String fieldName = concreteCondition.getFieldName();
        String operator = concreteCondition.getOperator();
        List<String> values = concreteCondition.getValues();
        OperatorEnum operatorEnum = OperatorEnum.getByCode(operator);
        // mytodo:这里的values肯定要额外处理，要根据不同的操作符取值，不能固定取第一个元素
        return "(" + fieldName + " " + operatorEnum.getEndOperator() + " " + values.get(0) + ")";// 加个空格
    }

    /**
     * 将条件组拼凑起来
     * @param conditionGroup
     * @return
     */
    default String generateForConditionGroup(ConditionGroup conditionGroup) {
        String logicOperator = conditionGroup.getLogicOperator();
        List<ConditionExpression> conditions = conditionGroup.getConditions();
        StringBuilder stringBuilder = new StringBuilder("(");

        for (ConditionExpression condition : conditions) {
            stringBuilder.append(generate(condition)).append(" ");// 加个空格
            stringBuilder.append(logicOperator).append(" ");// 加个空格
        }
        // 要删除最后的操作符
        int i = stringBuilder.lastIndexOf(logicOperator);
        stringBuilder.delete(i - 1, stringBuilder.length());// 逻辑操作符之前有一个空格要删除掉
        stringBuilder.append(")");
        return stringBuilder.toString();
    }
}
