package com.univ.common_business.condition_expression.enums;

import java.util.Arrays;
import java.util.List;

import com.univ.common_business.condition_expression.datatype.AbstractDataTypeProcessor;
import com.univ.common_business.condition_expression.datatype.BooleanDataTypeProcessor;
import com.univ.common_business.condition_expression.datatype.DoubleDataTypeProcessor;
import com.univ.common_business.condition_expression.datatype.IntegerDataTypeProcessor;
import com.univ.common_business.condition_expression.datatype.StringDataTypeProcessor;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 但其实数据类型本身就是语言提供的，没必要额外再定义，这里纳入要支持的类型即可
 * @author univ
 * 2022/2/23 5:44 下午
 */

/**
 * 设计思考
 * 1. 每个数据类型有自己支持的操作符；
 * 2. 每个操作符也有对于values的要求
 */
@Getter
@AllArgsConstructor
public enum DataTypeEnum {

    // 数据类型本身就是语言提供的，没必要额外再定义，这里纳入要支持的类型即可,mytodo:如何获取内置数据类型的字面值？
    integer("integer",
            new IntegerDataTypeProcessor(),
            Arrays.asList(OperatorEnum.EQ, OperatorEnum.NOT_EQ, OperatorEnum.GT, OperatorEnum.LT, OperatorEnum.between)
    ),
    string("string",
            new StringDataTypeProcessor(),
            Arrays.asList(OperatorEnum.EQ, OperatorEnum.like)
    ),
    bool("boolean",
            new BooleanDataTypeProcessor(),
            Arrays.asList(OperatorEnum.trueX)
    ),
    doubleX("double",
            new DoubleDataTypeProcessor(),
            Arrays.asList(OperatorEnum.EQ, OperatorEnum.NOT_EQ)
    ),

    ;
    private String dataType;
    // private Class<?> actualClass;
    private AbstractDataTypeProcessor dataTypeProcessor;

    /**
     * 也可以定义到具体的DataTypeProcessor中，这样可保持枚举的简洁，不过放这里有集中管理的作用
     */
    private List<OperatorEnum> supportedOperatorList;

    /**
     * 此操作符是否支持指定的操作符
     * @param operatorEnum
     * @return
     */
    public boolean supportOperator(OperatorEnum operatorEnum) {
        return this.supportedOperatorList.contains(operatorEnum);
    }

    public static DataTypeEnum getByType(String type) {
        return Arrays.stream(values()).filter(t -> t.getDataType().equals(type)).findFirst().orElse(null);
    }
}
