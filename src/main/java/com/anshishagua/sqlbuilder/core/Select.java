package com.anshishagua.sqlbuilder.core;

import com.anshishagua.sqlbuilder.core.bool.And;
import com.anshishagua.sqlbuilder.core.bool.Or;
import com.anshishagua.sqlbuilder.core.expression.Limit;
import com.anshishagua.sqlbuilder.utils.AssertUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/15
 * Time: 下午5:47
 */

public class Select implements SQL {
    private static final String SPACES = "           ";

    private List<String> selectFields = new ArrayList<>();
    private List<String> fromTables = new ArrayList<>();
    private List<Join> joinClauses = new ArrayList<>();
    private Predicate wherePredicate;
    private List<String> orderBys = new ArrayList<>();
    private List<String> groupBys = new ArrayList<>();
    private Predicate havingPredicate;
    private Limit limit;

    public Select(List<String> selectFields, List<String> fromTables, List<Join> joinClauses, Predicate wherePredicate, List<String> orderBys, List<String> groupBys, Predicate havingPredicate, Limit limit) {
        AssertUtils.notEmpty(selectFields);
        AssertUtils.notEmpty(fromTables);

        this.selectFields = new ArrayList<>(selectFields);
        this.fromTables = new ArrayList<>(fromTables);
        this.joinClauses = new ArrayList<>(joinClauses);
        this.wherePredicate = wherePredicate;
        this.orderBys = new ArrayList<>(orderBys);
        this.groupBys = new ArrayList<>(groupBys);
        this.havingPredicate = havingPredicate;
        this.limit = limit;
    }

    private void iterate(StringBuilder builder, Iterator<String> iterator, String joiner) {
        boolean first = true;

        while (iterator.hasNext()) {
            if (first) {
                builder.append(iterator.next());
                first = false;
            } else {
                builder.append(SPACES).append(iterator.next());
            }

            if (iterator.hasNext()) {
                builder.append(",");
            }

            builder.append("\n");
        }
    }

    private List<String> toPredicates(Predicate predicate) {
        Objects.requireNonNull(predicate);

        List<String> list = new ArrayList<>();

        toPredicates(predicate, list);

        return list;
    }

    private void toPredicates(Predicate predicate, List<String> predicates) {
        if (predicate == null) {
            return;
        }

        if (predicate instanceof And) {
            predicates.add(0, String.format("%-11s%s", "AND", ((And) predicate).getRight().toSQL()));
            toPredicates(((And) predicate).getLeft(), predicates);
        } else if (predicate instanceof Or) {
            predicates.add(0, String.format("%-11s%s", "OR ", ((Or) predicate).getRight().toSQL()));
            toPredicates(((Or) predicate).getLeft(), predicates);
        } else {
            predicates.add(0, predicate.toSQL());

            return;
        }
    }

    public String format() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("%-11s", "SELECT"));

        Iterator<String> iterator = selectFields.iterator();
        iterate(builder, iterator, ",");

        builder.append(String.format("%-11s", "FROM"));

        iterator = fromTables.iterator();

        iterate(builder, iterator, ",");

        if (!joinClauses.isEmpty()) {
            for (Join joinClause : joinClauses) {
                builder.append(String.format("%-11s", joinClause.getJoinType().toSQL()))
                        .append(joinClause.getTableName())
                        .append(" ON (")
                        .append(joinClause.getOnConditions().toSQL()).append(")\n");
            }
        }

        if (wherePredicate != null) {
            List<String> list = toPredicates(wherePredicate);

            builder.append(String.format("%-11s", "WHERE"));

            for (String predicate : list) {
                builder.append(predicate).append("\n");
            }
        }

        if (!groupBys.isEmpty()) {
            builder.append(String.format("%-11s", "GROUP BY"));

            iterator = groupBys.iterator();

            iterate(builder, iterator, ",");
        }

        if (!orderBys.isEmpty()) {
            builder.append(String.format("%-11s", "ORDER BY"));

            iterator = orderBys.iterator();

            iterate(builder, iterator, ",");
        }

        if (havingPredicate != null) {
            List<String> list = toPredicates(havingPredicate);

            builder.append(String.format("%-11s", "HAVING"));

            for (String predicate : list) {
                builder.append(predicate).append("\n");
            }
        }

        if (limit != null) {
            builder.append(limit.toSQL());
        }

        return builder.toString().trim();
    }

    @Override
    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        builder.append("SELECT ");

        Iterator<String> iterator = selectFields.iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next());

            if (iterator.hasNext()) {
                builder.append(", ");
            }
            else {
                builder.append(" ");
            }
        }

        builder.append("FROM ");

        iterator = fromTables.iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next());

            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }

        builder.append(" ");

        if (!joinClauses.isEmpty()) {
            for (Join joinClause : joinClauses) {
                builder.append(joinClause.getJoinType().toSQL())
                        .append(" ")
                        .append(joinClause.getTableName())
                        .append(" ON (")
                        .append(joinClause.getOnConditions().toSQL()).append(") ");
            }
        }

        if (wherePredicate != null) {
            builder.append("WHERE ").append(wherePredicate.toSQL());
            builder.append(" ");
        }

        if (!groupBys.isEmpty()) {
            builder.append("GROUP BY ");

            iterator = groupBys.iterator();

            while (iterator.hasNext()) {
                builder.append(iterator.next());

                if (iterator.hasNext()) {
                    builder.append(", ");
                }
                else {
                    builder.append(" ");
                }
            }
        }

        if (!orderBys.isEmpty()) {
            builder.append("ORDER BY ");

            iterator = orderBys.iterator();

            while (iterator.hasNext()) {
                builder.append(iterator.next());

                if (iterator.hasNext()) {
                    builder.append(", ");
                }
                else {
                    builder.append(" ");
                }
            }
        }

        if (havingPredicate != null) {
            builder.append("HAVING ").append(havingPredicate.toSQL());
            builder.append(" ");
        }

        if (limit != null) {
            builder.append(limit.toSQL());
        }

        return builder.toString().trim();
    }

    @Override
    public String toString() {
        return toSQL();
    }
}