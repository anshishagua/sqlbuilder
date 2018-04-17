package com.anshishagua.sqlbuilder.core;

import com.anshishagua.sqlbuilder.utils.AssertUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/17
 * Time: 上午9:29
 */

public class Update implements SQL {
    private String tableName;
    private List<String> columns;
    private List<String> values;
    private Predicate predicate;

    public Update(String tableName, List<String> columns, List<String> values) {
        this(tableName, columns, values, null);
    }

    public Update(String tableName, List<String> columns, List<String> values, Predicate predicate) {
        Objects.requireNonNull(tableName);
        AssertUtils.notEmpty(columns);
        AssertUtils.notEmpty(values);
        AssertUtils.equalSize(columns, values);

        this.tableName = tableName;
        this.columns = new ArrayList<>(columns);
        this.values = new ArrayList<>(values);
        this.predicate = predicate;
    }

    @Override
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