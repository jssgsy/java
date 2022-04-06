package com.univ.common_business.condition_expression.datatype;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.univ.common_business.condition_expression.enums.DataTypeEnum;

/**
 * @author univ
 * 2022/2/25 2:44 下午
 */
public class BooleanDataTypeProcessor extends AbstractDataTypeProcessor {

    @Override
    protected boolean validValues(List<String> values) {
        return CollectionUtils.isEmpty(values) || (1 == values.size() && StringUtils.isEmpty(values.get(0)));
    }

    @Override
    protected DataTypeEnum getDataTypeEnum() {
        return DataTypeEnum.bool;
    }

}
