package com.anshishagua.sqlbuilder.core;

import java.util.List;

/**
 * User: lixiao
 * Date: 2018/4/15
 * Time: 下午5:44
 */

public class NotIn implements Predicate {
    private String expression;
    private List<String> notInList;

    public NotIn(String expression, List<String> notInList) {
        this.expression = expression;
        this.notInList = notInList;
    }

    @Override
    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        builder.append(expression).append(" NOT IN(");

        for (int i = 0;i < notInList.size(); ++i) {
            if (i == 0) {
                builder.append(notInList.get(i));
            } else {
                builder.append(", ").append(notInList.get(i));
            }
        }

        builder.append(")");

        return builder.toString();
    }

    @Override
    public int getPriority() {
        return PredicatePriority.NOT_IN_PRIORITY;
    }
}