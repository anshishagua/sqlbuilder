package com.anshishagua.sqlbuilder.core.primitive;

import com.anshishagua.sqlbuilder.core.expression.ExpressionType;
import com.anshishagua.sqlbuilder.core.expression.arithmetic.Arithmetic;

/**
 * User: lixiao
 * Date: 2018/4/17
 * Time: 下午11:20
 */

public class DoubleValue implements Arithmetic {
    private final double value;

    public DoubleValue(double value) {
        this.value = value;
    }

    public static DoubleValue of(double value) {
        return new DoubleValue(value);
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