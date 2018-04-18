package com.anshishagua.sqlbuilder.core.primitive;

import com.anshishagua.sqlbuilder.core.expression.Expression;
import com.anshishagua.sqlbuilder.core.expression.ExpressionType;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午2:36
 */

public class BooleanValue implements Expression {
    public static final BooleanValue TRUE = new BooleanValue(true);
    public static final BooleanValue FALSE = new BooleanValue(false);

    private final boolean value;

    private BooleanValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toSQL() {
        return String.valueOf(value);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.BOOLEAN;
    }
}