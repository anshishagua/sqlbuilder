package com.anshishagua.sqlbuilder.core;

import java.util.List;

/**
 * User: lixiao
 * Date: 2018/4/15
 * Time: 下午5:44
 */

public class In implements Predicate {
    private String expression;

    private List<String> inList;

    public In(String expression, List<String> inList) {
        this.expression = expression;
        this.inList = inList;
    }

    @Override
    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        builder.append(expression).append(" IN(");

        for (int i = 0;i < inList.size(); ++i) {
            if (i == 0) {
                builder.append(inList.get(i));
            } else {
                builder.append(", ").append(inList.get(i));
            }
        }

        builder.append(")");

        return builder.toString();
    }

    @Override
    public int getPriority() {
        return PredicatePriority.IN_PRIORITY;
    }
}