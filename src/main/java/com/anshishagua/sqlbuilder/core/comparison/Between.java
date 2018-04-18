package com.anshishagua.sqlbuilder.core.comparison;

import com.anshishagua.sqlbuilder.core.PredicatePriority;
import com.anshishagua.sqlbuilder.core.expression.Expression;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/16
 * Time: 下午9:06
 */

public class Between implements Comparison {
    private Expression expression;
    private Expression min;
    private Expression max;

    public Between(Expression expression, Expression min, Expression max) {
        Objects.requireNonNull(expression);
        Objects.requireNonNull(min);
        Objects.requireNonNull(max);

        this.expression = expression;
        this.min = min;
        this.max = max;
    }

    @Override
    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        builder.append(expression.toSQL());

        builder.append(" BETWEEN ").append(min.toSQL());
        builder.append(" AND ").append(max.toSQL());

        return builder.toString();
    }

    @Override
    public int getPriority() {
        return PredicatePriority.BETWEEN_PRIORITY;
    }
}