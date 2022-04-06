package com.univ.common_business.condition_expression.datatype;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.univ.common_business.condition_expression.enums.DataTypeEnum;

/**
 * @author univ
 * 2022/2/24 3:09 下午
 */
public class IntegerDataTypeProcessor extends AbstractDataTypeProcessor {

    @Override
    protected boolean validValues(List<String> values) {
        if (CollectionUtils.isEmpty(values)) {
            // int型的值不能为空
            return false;
        }
        for (String value : values) {
            try {
                // 元素中的所有类型都必须可转成integer
                Integer.valueOf(value);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected DataTypeEnum getDataTypeEnum() {
        return DataTypeEnum.integer;
    }

}
