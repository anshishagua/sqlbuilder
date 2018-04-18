package com.anshishagua.sqlbuilder.core.comparison;

import com.anshishagua.sqlbuilder.core.PredicatePriority;
import com.anshishagua.sqlbuilder.core.expression.Expression;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/17
 * Time: 下午11:16
 */

public class LessThanOrEqual implements Comparison {
    private Expression left;
    private Expression right;

    public LessThanOrEqual(Expression left, Expression right) {
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);

        this.left = left;
        this.right = right;
    }

    @Override
    public String toSQL() {
        return String.format("%s %s %s", left.toSQL(), "<=", right.toSQL());
    }

    @Override
    public int getPriority() {
        return PredicatePriority.LESS_THAN_EQUAL_PRIORITY;
    }
}