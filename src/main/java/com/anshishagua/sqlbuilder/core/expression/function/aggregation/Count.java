package com.anshishagua.sqlbuilder.core.expression.function.aggregation;

import com.anshishagua.sqlbuilder.core.expression.Expression;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午3:47
 */

public class Count implements Aggregation {
    private Expression expression;

    public Count(Expression expression) {
        Objects.requireNonNull(expression);

        this.expression = expression;
    }

    public static Count of(Expression expression) {
        return new Count(expression);
    }

    @Override
    public String toSQL() {
        return String.format("COUNT(%s)", expression.toSQL());
    }
}