package com.anshishagua.sqlbuilder.core.expression.function;

import com.anshishagua.sqlbuilder.core.expression.Expression;
import com.anshishagua.sqlbuilder.core.expression.ExpressionType;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午3:53
 */

public interface Function extends Expression {
    default ExpressionType getType() {
        return ExpressionType.FUNCTION;
    }
}