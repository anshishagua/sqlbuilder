package com.anshishagua.sqlbuilder.core;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/17
 * Time: 下午5:14
 */

public class Delete implements SQL {
    private String tableName;
    private Predicate predicate;

    public Delete(String tableName) {
        this(tableName, null);
    }

    public Delete(String tableName, Predicate predicate) {
        Objects.requireNonNull(tableName);

        this.tableName = tableName;
        this.predicate = predicate;
    }

    @Override
    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        builder.append("DELETE FROM ").append(tableName);

        if (predicate != null) {
            builder.append(" WHERE ").append(predicate.toSQL());
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        return toSQL();
    }
}