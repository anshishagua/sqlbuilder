package com.anshishagua.sqlbuilder.core.expression.function.aggregation;

import com.anshishagua.sqlbuilder.core.expression.Expression;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午3:47
 */

public class Max implements Aggregation {
    private Expression expression;

    public Max(Expression expression) {
        Objects.requireNonNull(expression);

        this.expression = expression;
    }

    public static Max of(Expression expression) {
        return new Max(expression);
    }

    @Override
    public String toSQL() {
        return String.format("MAX(%s)", expression.toSQL());
    }
}