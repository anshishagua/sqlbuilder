package com.anshishagua.sqlbuilder.core.expression.arithmetic;

import com.anshishagua.sqlbuilder.core.expression.Expression;
import com.anshishagua.sqlbuilder.core.expression.ExpressionType;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午2:33
 */

public interface Arithmetic extends Expression {
    default ExpressionType getType() {
        return ExpressionType.NUMERIC;
    }
}