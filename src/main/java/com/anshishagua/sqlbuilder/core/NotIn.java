package com.anshishagua.sqlbuilder.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/15
 * Time: 下午5:44
 */

public class NotIn implements Predicate {
    private String expression;
    private List<String> notInList = new ArrayList<>();
    private Select subSelect;

    public NotIn(String expression, List<String> notInList) {
        Objects.requireNonNull(expression);
        Objects.requireNonNull(notInList);

        this.expression = expression;
        this.notInList = notInList;
    }

    public NotIn(String expression, Select subSelect) {
        Objects.requireNonNull(expression);
        Objects.requireNonNull(subSelect);

        this.expression = expression;
        this.subSelect = subSelect;
    }

    @Override
    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        builder.append(expression).append(" NOT IN (");

        if (subSelect != null) {
            builder.append(subSelect.toSQL());
        } else {
            for (int i = 0;i < notInList.size(); ++i) {
                if (i == 0) {
                    builder.append(notInList.get(i));
                } else {
                    builder.append(", ").append(notInList.get(i));
                }
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