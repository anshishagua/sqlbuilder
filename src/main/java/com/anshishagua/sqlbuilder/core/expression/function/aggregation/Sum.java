package com.anshishagua.sqlbuilder.core.expression.function.aggregation;

import com.anshishagua.sqlbuilder.core.expression.Expression;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午3:47
 */

public class Sum implements Aggregation {
    private Expression expression;

    public Sum(Expression expression) {
        Objects.requireNonNull(expression);

        this.expression = expression;
    }

    public static Sum of(Expression expression) {
        return new Sum(expression);
    }

    @Override
    public String toSQL() {
        return String.format("SUM(%s)", expression.toSQL());
    }
}