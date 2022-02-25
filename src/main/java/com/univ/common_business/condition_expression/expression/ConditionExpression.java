package com.univ.common_business.condition_expression.expression;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * 抽象的条件表达式
 * @author univ
 * 2022/2/18 3:54 下午
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConcreteCondition.class, name = "condition"),
        @JsonSubTypes.Type(value = ConditionGroup.class, name = "group")
})
public interface ConditionExpression {

    void and(ConditionExpression conditionExpression);

    void or(ConditionExpression conditionExpression);
}
