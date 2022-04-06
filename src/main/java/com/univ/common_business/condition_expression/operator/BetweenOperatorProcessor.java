package com.univ.common_business.condition_expression.operator;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.univ.common_business.condition_expression.enums.OperatorEnum;

/**
 * @author univ
 * 2022/3/17 4:11 下午
 */
public class BetweenOperatorProcessor extends AbstractOperatorProcessor {

    @Override
    protected boolean validValues(List<String> values) {
        if (CollectionUtils.isEmpty(values) || 2 != values.size()) {
            return false;
        }
        // values.get(0).compareTo(values.get(1)) < 0; 不对第一个值和第二个值大小作比较
        return true;
    }

    @Override
    protected OperatorEnum getOperatorEnum() {
        return OperatorEnum.between;
    }
}
