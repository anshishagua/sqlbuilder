package com.anshishagua.sqlbuilder.core.bool;

import com.anshishagua.sqlbuilder.core.Predicate;
import com.anshishagua.sqlbuilder.core.PredicatePriority;

/**
 * User: lixiao
 * Date: 2018/4/15
 * Time: 下午5:44
 */

public class Not implements Predicate {
    private Predicate predicate;

    public Not(Predicate predicate) {
        this.predicate = predicate;
    }

    public static Not of(Predicate predicate) {
        return new Not(predicate);
    }

    @Override
    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        if (predicate.getPriority() < this.getPriority()) {
            builder.append("NOT (").append(predicate.toSQL()).append(")");
        } else {
            builder.append("NOT ").append(predicate.toSQL());
        }

        return builder.toString();
    }

    @Override
    public int getPriority() {
        return PredicatePriority.NOT_PRIORITY;
    }
}