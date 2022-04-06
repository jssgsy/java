package com.univ.common_business.condition_expression.datatype;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.univ.common_business.condition_expression.enums.DataTypeEnum;

/**
 * @author univ
 * 2022/3/17 3:42 下午
 */
public class DoubleDataTypeProcessor extends AbstractDataTypeProcessor {

    @Override
    protected boolean validValues(List<String> values) {
        if (CollectionUtils.isEmpty(values)) {
            // double型的值不能为空
            return false;
        }
        for (String value : values) {
            try {
                // 必须要能转成double类型
                Double.valueOf(value);
            } catch (Exception exception) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected DataTypeEnum getDataTypeEnum() {
        return DataTypeEnum.doubleX;
    }
}
