package com.anshishagua.sqlbuilder.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/16
 * Time: 下午9:05
 */

public class UpdateBuilder {
    private String tableName;
    private List<String> columns = new ArrayList<>();
    private List<String> values = new ArrayList<>();

    private PredicateBuilder predicateBuilder = new PredicateBuilder();

    public UpdateBuilder() {

    }

    public UpdateBuilder table(String tableName) {
        Objects.requireNonNull(tableName);

        this.tableName = tableName;

        return this;
    }

    public UpdateBuilder set(String column, String value) {
        Objects.requireNonNull(column);

        if (value == null) {
            value = "NULL";
        }

        this.columns.add(column);
        this.values.add(value);

        return this;
    }

    public UpdateBuilder where(String expression) {
        if (predicateBuilder.getPredicate() == null) {
            predicateBuilder.basicPredicate(expression);
        } else {
            predicateBuilder.and(expression);
        }

        return this;
    }

    public Update build() {
        return new Update(tableName, columns, values, predicateBuilder.build());
    }

    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        builder.append("UPDATE ").append(tableName);

        builder.append(" SET ");

        for (int i = 0; i < columns.size(); ++i) {
            if (i > 0) {
                builder.append(", ");
            }

            builder.append(columns.get(i)).append(" = ").append(values.get(i));
        }

        if (predicateBuilder.getPredicate() != null) {
            builder.append(" WHERE ").append(predicateBuilder.toSQL());
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        return toSQL();
    }
}