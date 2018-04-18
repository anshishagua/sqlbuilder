package com.anshishagua.sqlbuilder.core.primitive;

import com.anshishagua.sqlbuilder.core.expression.Expression;
import com.anshishagua.sqlbuilder.core.expression.ExpressionType;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午2:56
 */

public class ColumnRef implements Expression {
    private final String tableName;
    private final String column;

    public ColumnRef(String column) {
        Objects.requireNonNull(column);

        this.tableName = null;
        this.column = column;
    }

    public ColumnRef(String tableName, String column) {
        Objects.requireNonNull(tableName);
        Objects.requireNonNull(column);

        this.tableName = tableName;
        this.column = column;
    }

    public static ColumnRef of(String column) {
        return new ColumnRef(column);
    }

    public static ColumnRef of(String tableName, String column) {
        return new ColumnRef(tableName, column);
    }

    @Override
    public String toSQL() {
        if (tableName == null) {
            return String.format("`%s`", column);
        }

        return String.format("`%s`.`%s`", tableName, column);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.COLUMN_REF;
    }
}