package com.univ.common_business.condition_expression.operator;

import java.util.List;

import com.univ.common_business.condition_expression.enums.OperatorEnum;

/**
 * @author univ
 * 2022/2/23 5:58 下午
 */
public class EqOperatorProcessor extends AbstractOperatorProcessor {

    @Override
    protected boolean validValues(List<String> values) {
        return 1 == values.size();
    }

    @Override
    protected OperatorEnum getOperatorEnum() {
        return OperatorEnum.EQ;
    }
}
