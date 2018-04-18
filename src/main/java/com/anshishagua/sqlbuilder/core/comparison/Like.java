package com.anshishagua.sqlbuilder.core.comparison;

import com.anshishagua.sqlbuilder.core.PredicatePriority;
import com.anshishagua.sqlbuilder.core.expression.Expression;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午3:18
 */

public class Like implements Comparison {
    private Expression left;
    private Expression right;

    public Like(Expression left, Expression right) {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

    public static Like of(Expression left, Expression right) {
        return new Like(left, right);
    }

    @Override
    public String toSQL() {
        return String.format("%s LIKE %s", left.toSQL(), right.toSQL());
    }

    @Override
    public int getPriority() {
        return PredicatePriority.LIKE_PRIORITY;
    }
}