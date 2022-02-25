package com.univ.common_business.condition_expression.test;

import java.io.IOException;
import java.util.Collections;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.univ.common_business.condition_expression.expression.ConditionExpression;
import com.univ.common_business.condition_expression.expression.ConditionExpressionHelper;

/**
 * @author univ
 * 2022/2/21 8:20 下午
 */
public class ConditionExpressionTest {

    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 几个重点：
     * 1. 最外层一定是group；
     * 2.
     * @throws IOException
     */
    @Test
    public void test() throws IOException {

        // 最外层的type必定是group
        String json = "{\n"
                + "    \"type\":\"group\",\n"
                + "    \"logicOperator\":\"or\",\n"
                + "    \"conditions\":[\n"
                + "        {\n"
                + "            \"type\":\"group\",\n"
                + "            \"logicOperator\":\"and\",\n"
                + "            \"conditions\":[\n"
                + "                {\n"
                + "                    \"type\":\"condition\",\n"
                + "                    \"fieldName\":\"brand\",\n"
                + "                    \"operator\":\"eq\",\n"
                + "                    \"values\":[\n"
                + "                        \"111\"\n"
                + "                    ]\n"
                + "                },\n"
                + "                {\n"
                + "                    \"type\":\"condition\",\n"
                + "                    \"fieldName\":\"nationalIndustry\",\n"
                + "                    \"operator\":\"eq\",\n"
                + "                    \"values\":[\n"
                + "                        \"国际组织\"\n"
                + "                    ]\n"
                + "                }\n"
                + "            ]\n"
                + "        }\n"
                + "    ]\n"
                + "}";
        ObjectMapper objectMapper = new ObjectMapper();
        ConditionExpression conditionExpression = objectMapper.readValue(json, ConditionExpression.class);
        System.out.println(objectMapper.writeValueAsString(conditionExpression));

        String leafJson = "{\n"
                + "    \"type\":\"condition\",\n"
                + "    \"fieldName\":\"name\",\n"
                + "    \"operator\":\"eq\",\n"
                + "    \"values\":[\n"
                + "        \"zhangsan\"\n"
                + "    ]\n"
                + "}";
        conditionExpression.and(objectMapper.readValue(leafJson, ConditionExpression.class));
        System.out.println(objectMapper.writeValueAsString(conditionExpression));

        String groupJson = "{\n"
                + "    \"type\":\"group\",\n"
                + "    \"conditions\":[\n"
                + "        {\n"
                + "            \"type\":\"group\",\n"
                + "            \"conditions\":[\n"
                + "                {\n"
                + "                    \"type\":\"condition\",\n"
                + "                    \"fieldName\":\"brand\",\n"
                + "                    \"operator\":\"eq\",\n"
                + "                    \"values\":[\n"
                + "                        \"111\"\n"
                + "                    ]\n"
                + "                },\n"
                + "                {\n"
                + "                    \"type\":\"condition\",\n"
                + "                    \"fieldName\":\"nationalIndustry\",\n"
                + "                    \"operator\":\"eq\",\n"
                + "                    \"values\":[\n"
                + "                        \"国际组织\"\n"
                + "                    ]\n"
                + "                }\n"
                + "            ],\n"
                + "            \"logicOperator\":\"and\"\n"
                + "        },\n"
                + "        {\n"
                + "            \"type\":\"condition\",\n"
                + "            \"fieldName\":\"name\",\n"
                + "            \"operator\":\"eq\",\n"
                + "            \"values\":[\n"
                + "                \"zhangsan\"\n"
                + "            ]\n"
                + "        }\n"
                + "    ],\n"
                + "    \"logicOperator\":\"or\"\n"
                + "}";
        conditionExpression.and(objectMapper.readValue(groupJson, ConditionExpression.class));
        System.out.println(objectMapper.writeValueAsString(conditionExpression));
    }

    /**
     * 编程的方式构造条件表达式
     * @throws JsonProcessingException
     */
    @Test
    public void fn1() throws JsonProcessingException {
        ConditionExpressionHelper expressionHelper = new ConditionExpressionHelper();
        expressionHelper.and(ConditionExpressionHelper.createConcreteCondition("name", "string", "eq", Collections.singletonList("zhangsan")));
        expressionHelper.and(ConditionExpressionHelper.createConcreteCondition("age", "integer", "eq", Collections.singletonList("20")));
        expressionHelper.or(ConditionExpressionHelper.createConcreteCondition("address", "string", "like", Collections.singletonList("hubei")));
        expressionHelper.and(ConditionExpressionHelper.createConcreteCondition("id", "long", "lt", Collections.singletonList("30")));
        System.out.println(objectMapper.writeValueAsString(expressionHelper.getConditionExpression()));
    }


}
