package com.univ.common_business.condition_expression.datatype;

import java.util.Arrays;
import java.util.List;

import com.univ.common_business.condition_expression.enums.DataTypeEnum;

/**
 * @author univ
 * 2022/2/25 2:44 下午
 */
public class BooleanDataTypeProcessor extends AbstractDataTypeProcessor {

    @Override
    protected boolean validValues(List<String> values) {
        return 1 == values.size() && Arrays.asList("true", "false").contains(values.get(0));
    }

    @Override
    protected DataTypeEnum getDataTypeEnum() {
        return DataTypeEnum.bool;
    }

}
