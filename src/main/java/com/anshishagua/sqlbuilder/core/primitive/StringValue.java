package com.anshishagua.sqlbuilder.core.primitive;


import com.anshishagua.sqlbuilder.core.expression.Expression;
import com.anshishagua.sqlbuilder.core.expression.ExpressionType;
import com.anshishagua.sqlbuilder.utils.StringUtils;

/**
 * User: lixiao
 * Date: 2018/4/17
 * Time: 下午11:20
 */

public class StringValue implements Expression {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    public static StringValue of(String value) {
        return new StringValue(value);
    }

    @Override
    public String toSQL() {
        if (value == null) {
            return "NULL";
        }

        return StringUtils.singleQuote(value);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.STRING;
    }
}