package com.anshishagua.sqlbuilder.core.expression.arithmetic;

import com.anshishagua.sqlbuilder.core.expression.Expression;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午2:33
 */

public class Multiply implements Arithmetic {
    private Expression left;
    private Expression right;

    public Multiply(Expression left, Expression right) {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);

        this.left = left;
        this.right = right;
    }

    public static Multiply of(Expression left, Expression right) {
        return new Multiply(left, right);
    }

    @Override
    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        builder.append(left.toSQL()).append(" * ").append(right.toSQL());

        return builder.toString();
    }
}