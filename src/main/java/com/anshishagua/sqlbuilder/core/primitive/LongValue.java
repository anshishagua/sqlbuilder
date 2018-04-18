package com.anshishagua.sqlbuilder.core.primitive;

import com.anshishagua.sqlbuilder.core.expression.ExpressionType;
import com.anshishagua.sqlbuilder.core.expression.arithmetic.Arithmetic;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午2:41
 */

public class LongValue implements Arithmetic {
    private final long value;

    public LongValue(long value) {
        this.value = value;
    }

    public static LongValue of(long value) {
        return new LongValue(value);
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