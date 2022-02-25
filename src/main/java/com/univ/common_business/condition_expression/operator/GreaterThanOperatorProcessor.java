package com.univ.common_business.condition_expression.operator;

import java.util.List;

import com.univ.common_business.condition_expression.enums.OperatorEnum;

/**
 * @author univ
 * 2022/2/23 6:05 下午
 */
public class GreaterThanOperatorProcessor extends AbstractOperatorProcessor {
    @Override
    protected boolean validValues(List<String> values) {
        return 1 == values.size();
    }

    @Override
    protected OperatorEnum getOperatorEnum() {
        return OperatorEnum.GT;
    }

}
