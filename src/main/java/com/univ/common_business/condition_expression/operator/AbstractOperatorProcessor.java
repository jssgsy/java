package com.univ.common_business.condition_expression.operator;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.univ.common_business.condition_expression.enums.DataTypeEnum;
import com.univ.common_business.condition_expression.enums.OperatorEnum;
import com.univ.common_business.condition_expression.expression.ConcreteCondition;

/**
 * 单个操作符
 * @author univ
 * 2022/2/23 5:55 下午
 */
public abstract class AbstractOperatorProcessor {

    /**
     * <p>操作符对表达式的要求：操作符应该只关心操作数的数据类型以及值，同时只做自己职责范围内的事，明确好边界。</p>
     *
     * 边界：
     *  1. 表达式中的操作符必须和此操作符一样；<br>
     *  2. 此操作符要被数据类型支持；<br>
     *  3. 此操作符对值的个数是否有要求；<br>
     *
     *  其中的顺序是一致的，用模板模式
     *
     * mytodo:可以考虑用窄参数
     * @param concreteCondition
     * @return
     */
    public final boolean valid(ConcreteCondition concreteCondition) {
        String operator = concreteCondition.getOperator();
        OperatorEnum operatorEnum = OperatorEnum.getByCode(operator);
        if (!operatorEnum.equals(getOperatorEnum())) {
            System.out.println("表达式【" + JSONObject.toJSONString(concreteCondition) + "】的操作符不为【" + getOperatorEnum().name() + "】");
            return false;
        }
        String dataType = concreteCondition.getDataType();
        DataTypeEnum dataTypeEnum = DataTypeEnum.getByType(dataType);
        if (!supportedByDataType(dataTypeEnum)) {
            System.out.println("表达式【" + JSONObject.toJSONString(concreteCondition) + "】中操作符【" + getOperatorEnum().name() + "】不被数据类型【" + dataTypeEnum.getDataType() + "】支持");
            return false;
        }
        boolean success = validValues(concreteCondition.getValues());
        if (!success) {
            System.out.println("表达式【" + JSONObject.toJSONString(concreteCondition) + "】中值【" + concreteCondition.getValues() + "】的个数不符合操作符【" + getOperatorEnum().name() + "】的要求");
        }
        return success;
    }

    /**
     * 此操作符是否被某个数据类型支持，如操作符【like】就不能作用于数据类型【integer】上
     * @param dataTypeEnum
     * @return
     */
    public final boolean supportedByDataType(DataTypeEnum dataTypeEnum) {
        return dataTypeEnum.getSupportedOperatorList().contains(getOperatorEnum());
    }

    /**
     * 此操作符对值的要求
     * @param values
     * @return
     */
    protected abstract boolean validValues(List<String> values);

    /**
     * 对应的操作符
     * @return
     */
    protected abstract OperatorEnum getOperatorEnum();
}
