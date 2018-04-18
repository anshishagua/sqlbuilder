package com.anshishagua.sqlbuilder.core.expression.function.aggregation;

import com.anshishagua.sqlbuilder.core.expression.ExpressionType;
import com.anshishagua.sqlbuilder.core.expression.function.Function;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午3:48
 */

public interface Aggregation extends Function {
    @Override
    default ExpressionType getType() {
        return ExpressionType.AGGREGATION;
    }
}