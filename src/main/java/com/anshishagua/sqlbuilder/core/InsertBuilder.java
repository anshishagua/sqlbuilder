package com.anshishagua.sqlbuilder.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/16
 * Time: 下午9:05
 */

public class InsertBuilder {
    private String tableName;
    private List<String> columns = new ArrayList<>();
    private List<String> values = new ArrayList<>();

    public InsertBuilder() {

    }

    public InsertBuilder table(String tableName) {
        Objects.requireNonNull(tableName);

        this.tableName = tableName;

        return this;
    }

    public InsertBuilder set(String column, String value) {
        Objects.requireNonNull(column);

        if (value == null) {
            value = "NULL";
        }

        this.columns.add(column);
        this.values.add(value);

        return this;
    }

    public Insert build() {
        return new Insert(tableName, columns, values);
    }
}