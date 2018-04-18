package com.anshishagua.sqlbuilder.core.comparison;

import com.anshishagua.sqlbuilder.core.PredicatePriority;
import com.anshishagua.sqlbuilder.core.expression.Expression;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午3:20
 */

public class IsNull implements Comparison {
    private Expression expression;

    public IsNull(Expression expression) {
        Objects.requireNonNull(expression);

        this.expression = expression;
    }

    @Override
    public String toSQL() {
        return String.format("%s IS NULL", expression.toSQL());
    }

    @Override
    public int getPriority() {
        return PredicatePriority.IS_NULL_PRIORITY;
    }
}