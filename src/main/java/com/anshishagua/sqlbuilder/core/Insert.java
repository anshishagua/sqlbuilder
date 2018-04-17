package com.anshishagua.sqlbuilder.core;

import com.anshishagua.sqlbuilder.utils.AssertUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/17
 * Time: 下午5:27
 */

public class Insert implements SQL {
    private String tableName;
    private List<String> columns;
    private List<String> values;

    public Insert(String tableName, List<String> columns, List<String> values) {
        Objects.requireNonNull(tableName);
        AssertUtils.notEmpty(columns);
        AssertUtils.notEmpty(values);
        AssertUtils.equalSize(columns, values);

        this.tableName = tableName;
        this.columns = new ArrayList<>(columns);
        this.values = new ArrayList<>(values);
    }

    @Override
    public String toSQL() {
        StringBuilder builder = new StringBuilder();
        builder.append("INSERT INTO ").append(tableName).append("(");

        Iterator<String> iterator = columns.iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next());

            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }

        builder.append(") VALUES(");

        iterator = values.iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next());

            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }

        builder.append(")");

        return builder.toString();
    }

    @Override
    public String toString() {
        return toSQL();
    }
}