package com.anshishagua.sqlbuilder.core.bool;

import com.anshishagua.sqlbuilder.core.Predicate;
import com.anshishagua.sqlbuilder.core.PredicatePriority;

/**
 * User: lixiao
 * Date: 2018/4/15
 * Time: 下午5:44
 */

public class Or implements Predicate {
    private Predicate left;
    private Predicate right;

    public Or(Predicate left, Predicate right) {
        this.left = left;
        this.right = right;
    }

    public Predicate getLeft() {
        return left;
    }

    public Predicate getRight() {
        return right;
    }

    @Override
    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        if (left.getPriority() < this.getPriority()) {
            builder.append("(").append(left.toSQL()).append(")");
        } else {
            builder.append(left.toSQL());
        }

        builder.append(" OR ");

        if (right.getPriority() < this.getPriority()) {
            builder.append("(").append(right.toSQL()).append(")");
        } else {
            builder.append(right.toSQL());
        }

        return builder.toString();
    }

    @Override
    public int getPriority() {
        return PredicatePriority.OR_PRIORITY;
    }
}