package com.univ.common_business.condition_expression.test;

import java.util.Arrays;

import org.junit.Test;

import com.univ.common_business.condition_expression.expression.ConcreteCondition;
import com.univ.common_business.condition_expression.expression.ConditionExpressionHelper;
import com.univ.common_business.condition_expression.result.SqlDSL;

/**
 * @see com.univ.common_business.condition_expression.result.PlatformDSL
 * @author univ
 * 2022/2/24 9:56 上午
 */
public class PlatformDSLTest {

    /**
     * 验证末级表达式是否有效
     */
    @Test
    public void validConcreteCondition() {
        ConcreteCondition concreteCondition = ConditionExpressionHelper.createConcreteCondition("name", "string", "eq", Arrays.asList("aaa"));
        ConcreteCondition concreteCondition2 = ConditionExpressionHelper.createConcreteCondition("name", "string", "eq", Arrays.asList("aaa"));
        ConcreteCondition concreteCondition3 = ConditionExpressionHelper.createConcreteCondition("age", "integer", "eq", Arrays.asList("123"));
        ConcreteCondition concreteCondition4 = ConditionExpressionHelper.createConcreteCondition("age", "integer", "like", Arrays.asList("12"));

        System.out.println("concreteCondition: " + ConditionExpressionHelper.validConcreteCondition(concreteCondition));
        System.out.println("concreteCondition2: " + ConditionExpressionHelper.validConcreteCondition(concreteCondition2));
        System.out.println("concreteCondition3: " + ConditionExpressionHelper.validConcreteCondition(concreteCondition3));
        System.out.println("concreteCondition4: " + ConditionExpressionHelper.validConcreteCondition(concreteCondition4));
    }

    /**
     * 验证条件表达式是否有效
     */
    @Test
    public void validConditionExpression() {
        ConcreteCondition concreteCondition = ConditionExpressionHelper.createConcreteCondition("name", "string", "eq", Arrays.asList("aaa"));
        ConcreteCondition concreteCondition2 = ConditionExpressionHelper.createConcreteCondition("name", "string", "eq", Arrays.asList("aaa", "bbb"));
        ConcreteCondition concreteCondition3 = ConditionExpressionHelper.createConcreteCondition("age", "integer", "eq", Arrays.asList("aaa"));

        ConditionExpressionHelper expressionHelper = new ConditionExpressionHelper();
        expressionHelper.setConditionExpression(concreteCondition);
        expressionHelper.and(concreteCondition2);
        expressionHelper.or(concreteCondition3);
        System.out.println("条件组: " + ConditionExpressionHelper.validConditionExpression(expressionHelper.getConditionExpression()));
    }

    /**
     * 对末级表达式生成字符串形式的表达式
     */
    @Test
    public void generateConcrete() {
        SqlDSL sqlDSL = new SqlDSL();
        ConcreteCondition concreteCondition = ConditionExpressionHelper.createConcreteCondition("age", "integer", "gt", Arrays.asList("12"));
        System.out.println("concreteCondition: " + ConditionExpressionHelper.validConcreteCondition(concreteCondition));
        System.out.println("结果为： " + sqlDSL.generate(concreteCondition));
    }

    /**
     * 对条件表达式生成字符串形式的表达式
     */
    @Test
    public void generateGroup() {
        SqlDSL sqlDSL = new SqlDSL();
        ConcreteCondition concreteCondition = ConditionExpressionHelper.createConcreteCondition("name", "string", "eq", Arrays.asList("zhangsan"));
        ConcreteCondition concreteCondition1 = ConditionExpressionHelper.createConcreteCondition("age", "integer", "gt", Arrays.asList("10"));
        ConcreteCondition concreteCondition2 = ConditionExpressionHelper.createConcreteCondition("address", "string", "like", Arrays.asList("hubei"));
        ConditionExpressionHelper conditionExpressionHelper = new ConditionExpressionHelper();
        conditionExpressionHelper.setConditionExpression(concreteCondition);
        conditionExpressionHelper.and(concreteCondition1);
        conditionExpressionHelper.or(concreteCondition2);

        System.out.println(sqlDSL.generate(conditionExpressionHelper.getConditionExpression()));
    }
}
