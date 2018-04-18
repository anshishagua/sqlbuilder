package com.anshishagua.sqlbuilder.core.expression;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午2:34
 */

public enum ExpressionType {
    NULL,
    NUMERIC,
    STRING,
    BOOLEAN,
    COLUMN_REF,
    CASE_WHEN,
    AGGREGATION,
    FUNCTION,
    LIMIT
}