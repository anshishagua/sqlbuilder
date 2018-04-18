package com.anshishagua.sqlbuilder.core.primitive;

import com.anshishagua.sqlbuilder.core.expression.ExpressionType;
import com.anshishagua.sqlbuilder.core.expression.arithmetic.Arithmetic;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午2:41
 */

public class IntegerValue implements Arithmetic {
    private final int value;

    public IntegerValue(int value) {
        this.value = value;
    }

    public static IntegerValue of(int value) {
        return new IntegerValue(value);
    }

    @Override
    public String toSQL() {
        return String.valueOf(value);
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.NUMERIC;
    }
}