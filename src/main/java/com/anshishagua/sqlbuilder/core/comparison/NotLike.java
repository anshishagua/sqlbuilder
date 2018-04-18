package com.anshishagua.sqlbuilder.core.comparison;

import com.anshishagua.sqlbuilder.core.PredicatePriority;
import com.anshishagua.sqlbuilder.core.expression.Expression;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午4:33
 */

public class NotLike implements Comparison {
    private Expression left;
    private Expression right;

    public NotLike(Expression left, Expression right) {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
    }

    public static NotLike of(Expression left, Expression right) {
        return new NotLike(left, right);
    }

    @Override
    public String toSQL() {
        return String.format("%s NOT LIKE %s", left.toSQL(), right.toSQL());
    }

    @Override
    public int getPriority() {
        return PredicatePriority.NOT_LIKE_PRIORITY;
    }
}