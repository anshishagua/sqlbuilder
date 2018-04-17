package com.anshishagua.sqlbuilder.core;

import com.anshishagua.sqlbuilder.utils.AssertUtils;

import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/17
 * Time: 下午5:14
 */

public class Create implements SQL {
    private String tableName;
    private List<Column> columns;
    private boolean createIfNotExists;

    public Create(String tableName, List<Column> columns, boolean createIfNotExists) {
        Objects.requireNonNull(tableName);
        AssertUtils.notEmpty(columns);

        this.tableName = tableName;
        this.columns = new ArrayList<>(columns);
        this.createIfNotExists = createIfNotExists;
    }

    @Override
    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE ");

        if (createIfNotExists) {
            builder.append("IF NOT EXISTS ");
        }

        builder.append(tableName).append("(");

        Iterator<Column> iterator = columns.iterator();

        while (iterator.hasNext()) {
            Column column = iterator.next();

            builder.append(column.getName()).append(" ");

            builder.append(column.getType());

            if (column.getType() == JDBCType.VARCHAR && column.getMinLength() > 0 && column.getMaxLength() > 0) {
                builder.append("(").append(column.getMinLength()).append(", ").append(column.getMaxLength()).append(")");
            }

            if (!column.isNullable()) {
                builder.append(" NOT NULL");
            }

            if (column.getDefaultValue() != null) {
                builder.append(" DEFAULT " + column.getDefaultValue());
            }

            if (column.isPrimaryKey()) {
                builder.append(" PRIMARY KEY");
            }

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