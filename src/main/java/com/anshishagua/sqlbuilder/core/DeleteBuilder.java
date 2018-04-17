package com.anshishagua.sqlbuilder.core;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/16
 * Time: 下午9:05
 */

public class DeleteBuilder {
    private String tableName;
    private PredicateBuilder predicateBuilder = new PredicateBuilder();

    public DeleteBuilder() {

    }

    public DeleteBuilder table(String tableName) {
        Objects.requireNonNull(tableName);

        this.tableName = tableName;

        return this;
    }

    public DeleteBuilder where(String expression) {
        if (predicateBuilder.getPredicate() == null) {
            predicateBuilder.basicPredicate(expression);
        } else {
            predicateBuilder.and(expression);
        }

        return this;
    }

    public Delete build() {
        Delete delete = new Delete(tableName, predicateBuilder.getPredicate());

        return delete;
    }
}