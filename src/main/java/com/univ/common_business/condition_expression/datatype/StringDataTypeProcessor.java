package com.univ.common_business.condition_expression.datatype;

import java.util.List;

import com.univ.common_business.condition_expression.enums.DataTypeEnum;
import com.univ.common_business.condition_expression.operator.AbstractOperatorProcessor;

/**
 * 与{@link AbstractOperatorProcessor}组合才能完成对表达式的最终检验
 *
 * @author univ
 * 2022/2/24 3:24 下午
 */
public class StringDataTypeProcessor extends AbstractDataTypeProcessor {
    @Override
    protected boolean validValues(List<String> values) {
        return true;
    }

    @Override
    protected DataTypeEnum getDataTypeEnum() {
        return DataTypeEnum.string;
    }

}
