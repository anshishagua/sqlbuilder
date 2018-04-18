package com.anshishagua.sqlbuilder.core.expression;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午4:38
 */

public class Distinct implements Expression{
    private Expression expression;

    public Distinct(Expression expression) {
        Objects.requireNonNull(expression);

        this.expression = expression;
    }

    public static Distinct of(Expression expression) {
        return new Distinct(expression);
    }

    @Override
    public String toSQL() {
        return String.format("DISTINCT %s", expression.toSQL());
    }

    @Override
    public ExpressionType getType() {
        return expression.getType();
    }
}