package com.univ.common_business.condition_expression.expression;

import java.util.ArrayList;
import java.util.List;

import com.univ.common_business.condition_expression.constant.ConditionExpressionRelatedConst;
import com.univ.common_business.condition_expression.datatype.AbstractDataTypeProcessor;
import com.univ.common_business.condition_expression.enums.DataTypeEnum;
import com.univ.common_business.condition_expression.enums.LogicalOperatorEnum;
import com.univ.common_business.condition_expression.enums.OperatorEnum;
import com.univ.common_business.condition_expression.operator.AbstractOperatorProcessor;

import lombok.Setter;

/**
 * @author univ
 * 2022/2/22 7:36 下午
 */
@Setter
public class ConditionExpressionHelper {

    private ConditionExpression conditionExpression;

    /**
     * 加入另一个表达式，操作符为and
     * @param conditionExpression
     */
    public void and(ConditionExpression conditionExpression) {
        // 要求一定是一个and条件组才能执行and操作，编程上给封装起来
        makeNewAndConditionGroupIfNeed();
        convertToConditionGroup().getConditions().add(conditionExpression);
    }

    private void makeNewAndConditionGroupIfNeed() {
        if (null == this.conditionExpression) {
            this.conditionExpression = createDefaultAndConditionGroup();
            return;
        }
        if (!isAndConditionGroup()) {
            ConditionGroup andConditionGroup = (ConditionGroup) createDefaultAndConditionGroup();
            andConditionGroup.getConditions().add(this.conditionExpression);
            this.conditionExpression = andConditionGroup;
        }
    }

    /**
     * 是否为一个逻辑操作符为And的条件组
     * @return
     */
    private boolean isAndConditionGroup() {
        return null != conditionExpression &&
                conditionExpression instanceof ConditionGroup &&
                ((ConditionGroup) conditionExpression).getLogicOperator().equals(LogicalOperatorEnum.AND.getCode());
    }

    /**
     * 默认的And条件组
     * @return
     */
    private ConditionExpression createDefaultAndConditionGroup() {
        ConditionGroup conditionGroup = new ConditionGroup();
        conditionGroup.setConditions(new ArrayList<>());
        conditionGroup.setType(ConditionExpressionRelatedConst.TYPE_GROUP);
        conditionGroup.setLogicOperator(LogicalOperatorEnum.AND.getCode());
        return conditionGroup;
    }

    /**
     * 加入另一个表达式，操作符为or
     * @param conditionExpression
     */
    public void or(ConditionExpression conditionExpression) {
        // 要求一定是一个or条件组才能执行or操作，编程上给封装起来
        makeNewOrConditionGroup();
        convertToConditionGroup().getConditions().add(conditionExpression);
    }

    private void makeNewOrConditionGroup() {
        if (null == this.conditionExpression) {
            this.conditionExpression = createDefaultOrConditionGroup();
            return;
        }
        if (!isOrConditionGroup()) {
            ConditionGroup orConditionGroup = (ConditionGroup) createDefaultOrConditionGroup();
            orConditionGroup.getConditions().add(this.conditionExpression);
            this.conditionExpression = orConditionGroup;
        }
    }

    private ConditionGroup convertToConditionGroup() {
        return (ConditionGroup) this.conditionExpression;
    }

    /**
     * 是否为操作符为or的条件组
     * @return
     */
    private boolean isOrConditionGroup() {
        return null != conditionExpression &&
                conditionExpression instanceof ConditionGroup &&
                ((ConditionGroup) conditionExpression).getLogicOperator().equals(LogicalOperatorEnum.OR.getCode());
    }

    /**
     * 默认的or条件组
     * @return
     */
    private ConditionExpression createDefaultOrConditionGroup() {
        ConditionGroup conditionGroup = new ConditionGroup();
        conditionGroup.setConditions(new ArrayList<>());
        conditionGroup.setType(ConditionExpressionRelatedConst.TYPE_GROUP);
        conditionGroup.setLogicOperator(LogicalOperatorEnum.OR.getCode());
        return conditionGroup;
    }

    /**
     * 获取最终的表达式
     * @return
     */
    public ConditionExpression getConditionExpression() {
        if (this.conditionExpression instanceof ConditionGroup) {
            return this.conditionExpression;
        }
        ConditionGroup conditionGroup = new ConditionGroup();
        conditionGroup.setConditions(new ArrayList<>());
        conditionGroup.getConditions().add(this.conditionExpression);
        conditionGroup.setLogicOperator(LogicalOperatorEnum.AND.getCode());

        return conditionGroup;
    }

    // mytodo:后续看是否要拆出去，因为这里已经有成员变量conditionExpression了，在这里放一个static方法不妥
    public static ConcreteCondition createConcreteCondition(String fieldName, String dataType, String operator, List<String> values) {
        ConcreteCondition concreteCondition = new ConcreteCondition();
        concreteCondition.setFieldName(fieldName);
        concreteCondition.setDataType(dataType);
        concreteCondition.setOperator(operator);
        concreteCondition.setValues(values);
        return concreteCondition;
    }

    /**
     * 验证条件表达式是否有效。
     * 仅支持验证{@link ConcreteCondition}与{@link ConditionGroup}
     * @param conditionExpression
     * @return
     */
    public static boolean validConditionExpression (ConditionExpression conditionExpression) {
        if (conditionExpression instanceof ConcreteCondition) {
            return validConcreteCondition((ConcreteCondition) conditionExpression);
        }
        if (conditionExpression instanceof ConditionGroup) {
            return validConditionGroup((ConditionGroup) conditionExpression);
        }
        throw new UnsupportedOperationException("不支持的条件表达式类型");
    }

    /**
     * 是否是一个合法的末级表达式，如inter型的字段a操作符不能为like, 操作符为=时值不能有多个
     * 由AbstractDataTypeProcessor与AbstractOperatorProcessor一起完成校验
     * @param concreteCondition
     * @return
     */
    public static boolean validConcreteCondition(ConcreteCondition concreteCondition) {
        String operator = concreteCondition.getOperator();
        String dataType = concreteCondition.getDataType();
        OperatorEnum operatorEnum = OperatorEnum.getByCode(operator);
        DataTypeEnum dataTypeEnum = DataTypeEnum.getByType(dataType);
        AbstractDataTypeProcessor dataTypeProcessor = dataTypeEnum.getDataTypeProcessor();
        AbstractOperatorProcessor operatorProcessor = operatorEnum.getOperatorProcessor();
        return dataTypeProcessor.valid(concreteCondition) && operatorProcessor.valid(concreteCondition);
    }

    /**
     * 验证是否是一个合格的条件组
     * @param conditionGroup
     * @return
     */
    public static boolean validConditionGroup(ConditionGroup conditionGroup) {
        String logicOperator = conditionGroup.getLogicOperator();
        if (!LogicalOperatorEnum.valid(logicOperator)) {
            throw new IllegalArgumentException("此逻辑操作符非法");
        }
        return conditionGroup.getConditions().stream().allMatch(ConditionExpressionHelper::validConditionExpression);
    }
}
