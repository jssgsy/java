package com.univ.common_business.condition_expression.operator;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.univ.common_business.condition_expression.enums.OperatorEnum;

/**
 * @author univ
 * 2022/3/17 4:18 下午
 */
public class TrueOperatorProcessor extends AbstractOperatorProcessor {

    @Override
    protected boolean validValues(List<String> values) {
        return CollectionUtils.isEmpty(values) || (1 == values.size() && StringUtils.isEmpty(values.get(0)));
    }

    @Override
    protected OperatorEnum getOperatorEnum() {
        return OperatorEnum.trueX;
    }
}
