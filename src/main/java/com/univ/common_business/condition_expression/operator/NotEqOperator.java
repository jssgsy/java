package com.univ.common_business.condition_expression.operator;

import java.util.List;

import com.univ.common_business.condition_expression.enums.OperatorEnum;

/**
 * @author univ
 * 2022/2/25 2:45 下午
 */
public class NotEqOperator extends AbstractOperatorProcessor {

    @Override
    protected boolean validValues(List<String> values) {
        return 1 == values.size();
    }

    @Override
    protected OperatorEnum getOperatorEnum() {
        return OperatorEnum.NOT_EQ;
    }
}
