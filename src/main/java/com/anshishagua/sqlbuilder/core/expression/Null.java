package com.anshishagua.sqlbuilder.core.expression;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午4:35
 */

public class Null implements Expression {
    public static final Null NULL = new Null();

    private Null() {

    }

    @Override
    public String toSQL() {
        return "NULL";
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.NULL;
    }
}