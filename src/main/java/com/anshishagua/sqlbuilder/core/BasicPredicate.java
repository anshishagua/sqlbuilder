package com.anshishagua.sqlbuilder.core;

/**
 * User: lixiao
 * Date: 2018/4/15
 * Time: 下午5:49
 */

public class BasicPredicate implements Predicate {
    private String expression;

    public BasicPredicate(String expression) {
        this.expression = expression;
    }

    @Override
    public String toSQL() {
        return expression;
    }

    @Override
    public int getPriority() {
        return PredicatePriority.BASIC_PRIORITY;
    }
}