package com.univ.common_business.condition_expression.datatype;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.univ.common_business.condition_expression.enums.DataTypeEnum;
import com.univ.common_business.condition_expression.expression.ConcreteCondition;

/**
 * 重点：要求所有子类必须是无状态的，参见{@link }
 * @author univ
 * 2022/2/23 5:35 下午
 */
public abstract class AbstractDataTypeProcessor {

    /**
     * <p>此数据类型对表达式的要求，如integer型的i=['a']是错的，同时i=[1, 2]也是错的，i=[1]是对的</p>
     *
     * 要求：<br>
     *     1. 表达式中的数据类型必须和当前数据类型一致；<br>
     *     2. 当前数据类型对值的类型的要求；<br>
     *
     * @param concreteCondition
     * @return
     */
    public final boolean valid(ConcreteCondition concreteCondition) {
        String dataType = concreteCondition.getDataType();
        DataTypeEnum dataTypeEnum = DataTypeEnum.getByType(dataType);
        if (!dataTypeEnum.equals(getDataTypeEnum())) {
            System.out.println("表达式【" + JSONObject.toJSONString(concreteCondition) + "】中的数据类型不为【" + getDataTypeEnum().name()+ "】");
            return false;
        }
        boolean success = validValues(concreteCondition.getValues());
        if (!success) {
            System.out.println("表达式【" + JSONObject.toJSONString(concreteCondition) + "】中的数据类型【" + getDataTypeEnum().name()+ "】校验值【" + concreteCondition.getValues() + "】失败");
        }
        return success;
    }

    /**
     * 当前数据类型对值类型的要求
     * @param values
     * @return
     */
    protected abstract boolean validValues(List<String> values);

    /**
     * 对应的数据类型枚举
     * @return
     */
    protected abstract DataTypeEnum getDataTypeEnum();
}
