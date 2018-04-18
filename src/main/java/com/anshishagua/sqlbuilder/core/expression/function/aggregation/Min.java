package com.anshishagua.sqlbuilder.core.expression.function.aggregation;

import com.anshishagua.sqlbuilder.core.expression.Expression;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午3:48
 */

public class Min implements Aggregation {
    private Expression expression;

    public Min(Expression expression) {
        Objects.requireNonNull(expression);

        this.expression = expression;
    }

    public static Min of(Expression expression) {
        return new Min(expression);
    }

    @Override
    public String toSQL() {
        return String.format("MIN(%s)", expression.toSQL());
    }
}