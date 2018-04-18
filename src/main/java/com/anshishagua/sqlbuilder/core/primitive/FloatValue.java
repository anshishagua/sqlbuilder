package com.anshishagua.sqlbuilder.core.primitive;

import com.anshishagua.sqlbuilder.core.expression.ExpressionType;
import com.anshishagua.sqlbuilder.core.expression.arithmetic.Arithmetic;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午2:37
 */

public class FloatValue implements Arithmetic {
    private final float value;

    public FloatValue(float value) {
        this.value = value;
    }

    public static FloatValue of(float value) {
        return new FloatValue(value);
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