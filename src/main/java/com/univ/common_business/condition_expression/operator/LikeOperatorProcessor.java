package com.univ.common_business.condition_expression.operator;

import java.util.List;

import com.univ.common_business.condition_expression.enums.OperatorEnum;

/**
 * @author univ
 * 2022/2/23 6:06 下午
 */
public class LikeOperatorProcessor extends AbstractOperatorProcessor {
    @Override
    protected boolean validValues(List<String> values) {
        return 1 == values.size();
    }

    @Override
    protected OperatorEnum getOperatorEnum() {
        return OperatorEnum.like;
    }
}
