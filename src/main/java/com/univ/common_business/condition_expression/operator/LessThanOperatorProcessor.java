package com.univ.common_business.condition_expression.operator;

import java.util.List;

import com.univ.common_business.condition_expression.enums.OperatorEnum;

/**
 * @author univ
 * 2022/3/17 4:02 下午
 */
public class LessThanOperatorProcessor extends AbstractOperatorProcessor {

    @Override
    protected boolean validValues(List<String> values) {
        return 1 == values.size();
    }

    @Override
    protected OperatorEnum getOperatorEnum() {
        return OperatorEnum.LT;
    }
}
