package com.anshishagua.sqlbuilder.core;

/**
 * User: lixiao
 * Date: 2018/4/15
 * Time: 下午5:44
 */

public class And implements Predicate {
    private Predicate left;
    private Predicate right;

    public And(Predicate left, Predicate right) {
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

        builder.append(" AND ");

        if (right.getPriority() < this.getPriority()) {
            builder.append("(").append(right.toSQL()).append(")");
        } else {
            builder.append(right.toSQL());
        }

        return builder.toString();
    }

    @Override
    public int getPriority() {
        return PredicatePriority.AND_PRIORITY;
    }
}