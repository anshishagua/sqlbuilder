package com.anshishagua.sqlbuilder.core;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/16
 * Time: 下午10:37
 */

public class SubSelect implements SQL {
    private Select select;
    private String alias;

    public SubSelect(Select select, String alias) {
        Objects.requireNonNull(select);
        Objects.requireNonNull(alias);

        this.select = select;
        this.alias = alias;
    }

    @Override
    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(select.toSQL()).append(") AS ").append(alias);

        return builder.toString();
    }
}