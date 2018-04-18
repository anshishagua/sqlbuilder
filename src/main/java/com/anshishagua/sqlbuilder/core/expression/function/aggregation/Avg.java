package com.anshishagua.sqlbuilder.core.expression.function.aggregation;

import com.anshishagua.sqlbuilder.core.expression.Expression;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午3:47
 */

public class Avg implements Aggregation {
    private Expression expression;

    public Avg(Expression expression) {
        Objects.requireNonNull(expression);

        this.expression = expression;
    }

    public static Avg of(Expression expression) {
        return new Avg(expression);
    }

    @Override
    public String toSQL() {
        return String.format("AVG(%s)", expression.toSQL());
    }
}