package com.anshishagua.sqlbuilder.core.comparison;

import com.anshishagua.sqlbuilder.core.Predicate;
import com.anshishagua.sqlbuilder.core.PredicatePriority;
import com.anshishagua.sqlbuilder.core.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/15
 * Time: 下午5:44
 */

public class In implements Predicate {
    private String expression;

    private List<String> inList = new ArrayList<>();
    private Select subSelect;

    public In(String expression, Select subSelect) {
        Objects.requireNonNull(expression);
        Objects.requireNonNull(subSelect);

        this.expression = expression;
        this.subSelect = subSelect;
    }

    public In(String expression, List<String> inList) {
        this.expression = expression;
        this.inList = inList;
    }

    @Override
    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        builder.append(expression).append(" IN (");

        if (subSelect != null) {
            builder.append(subSelect.toSQL());
        } else {
            for (int i = 0;i < inList.size(); ++i) {
                if (i == 0) {
                    builder.append(inList.get(i));
                } else {
                    builder.append(", ").append(inList.get(i));
                }
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