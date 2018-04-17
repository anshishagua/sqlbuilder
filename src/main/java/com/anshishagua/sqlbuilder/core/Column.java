package com.anshishagua.sqlbuilder.core;

import java.sql.JDBCType;

/**
 * User: lixiao
 * Date: 2018/4/16
 * Time: 下午11:22
 */

public class Column {
    private String name;
    private JDBCType type;
    private boolean nullable;
    private boolean primaryKey;
    private String defaultValue;
    private int minLength;
    private int maxLength;

    public Column() {

    }

    public Column(String name, JDBCType type, boolean nullable, boolean primaryKey, String defaultValue, int minLength, int maxLength) {
        this.name = name;
        this.type = type;
        this.nullable = nullable;
        this.primaryKey = primaryKey;
        this.defaultValue = defaultValue;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JDBCType getType() {
        return type;
    }

    public void setType(JDBCType type) {
        this.type = type;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
}