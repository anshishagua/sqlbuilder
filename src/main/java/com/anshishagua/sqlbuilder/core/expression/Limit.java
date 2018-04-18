package com.anshishagua.sqlbuilder.core.expression;

import com.anshishagua.sqlbuilder.utils.AssertUtils;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午5:01
 */

public class Limit implements Expression {
    public static final long NO_LIMIT = -1;
    public static final long NO_OFFSET = -1;

    private long limit = NO_LIMIT;
    private long offset = NO_OFFSET;

    public Limit() {

    }

    public Limit(long limit) {
        AssertUtils.assertTrue(limit > 0, "limit要大于0");

        this.limit = limit;
    }

    public Limit(long limit, long offset) {
        AssertUtils.assertTrue(limit > 0, "limit要大于0");
        AssertUtils.assertTrue(offset > 0, "offset要大于0");

        this.limit = limit;
        this.offset = offset;
    }

    @Override
    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        if (limit > 0) {
            builder.append("LIMIT ").append(limit);
        }

        if (offset > 0) {
            builder.append(" OFFSET ").append(offset);
        }

        return builder.toString();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.LIMIT;
    }
}