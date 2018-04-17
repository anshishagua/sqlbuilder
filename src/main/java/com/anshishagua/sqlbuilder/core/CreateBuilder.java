package com.anshishagua.sqlbuilder.core;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/16
 * Time: 下午10:27
 */

public class CreateBuilder {
    private String tableName;
    private List<Column> columns = new ArrayList<>();
    private boolean createIfNotExists;

    public CreateBuilder() {

    }

    public CreateBuilder table(String tableName) {
        Objects.requireNonNull(tableName);

        this.tableName = tableName;

        return this;
    }

    public CreateBuilder createIfNotExists(boolean createIfNotExists) {
        this.createIfNotExists = createIfNotExists;

        return this;
    }

    public CreateBuilder column(Column column) {
        Objects.requireNonNull(column);

        this.columns.add(column);

        return this;
    }

    public CreateBuilder column(String columnName, JDBCType type) {
        Objects.requireNonNull(columnName);
        Objects.requireNonNull(type);

        Column column = new Column();
        column.setName(columnName);
        column.setType(type);

        this.columns.add(column);

        return this;
    }

    public Create build() {
        Create create = new Create(tableName, columns, createIfNotExists);

        return create;
    }
}