package com.anshishagua.sqlbuilder.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: lixiao
 * Date: 2018/4/16
 * Time: 下午9:05
 */

public class SelectBuilder {
    private static final String SPACES = "           ";

    private List<String> selectFields = new ArrayList<>();
    private List<String> fromTables = new ArrayList<>();
    private List<Join> joinClauses = new ArrayList<>();
    private PredicateBuilder predicateBuilder = new PredicateBuilder();
    private List<String> orderBys = new ArrayList<>();
    private List<String> groupBys = new ArrayList<>();

    public SelectBuilder() {

    }

    public SelectBuilder select(String expression, String alias) {
        selectFields.add(String.format("%s AS %s", expression, alias));

        return this;
    }

    public SelectBuilder select(String expression) {
        selectFields.add(expression);

        return this;
    }

    public SelectBuilder from(String tableName) {
        fromTables.add(tableName);

        return this;
    }

    public SelectBuilder from(String tableName, String alias) {
        fromTables.add(String.format("%s AS %s", tableName, alias));

        return this;
    }

    public SelectBuilder join(String tableName, JoinType joinType, Predicate onConditions) {
        joinClauses.add(new Join(tableName, joinType, onConditions));

        return this;
    }

    public SelectBuilder leftJoin(String tableName, Predicate onConditions) {
        joinClauses.add(new Join(tableName, JoinType.LEFT_JOIN, onConditions));

        return this;
    }

    public SelectBuilder rightJoin(String tableName, Predicate onConditions) {
        joinClauses.add(new Join(tableName, JoinType.RIGHT_JOIN, onConditions));

        return this;
    }

    public SelectBuilder innerJoin(String tableName, Predicate onConditions) {
        joinClauses.add(new Join(tableName, JoinType.INNER_JOIN, onConditions));

        return this;
    }

    public SelectBuilder where(String expression) {
        if (predicateBuilder.getPredicate() == null) {
            predicateBuilder.basicPredicate(expression);
        }
        else {
            predicateBuilder.and(expression);
        }

        return this;
    }

    public SelectBuilder or(String expression) {
        predicateBuilder.or(expression);

        return this;
    }

    public SelectBuilder and(String expression) {
        predicateBuilder.or(expression);

        return this;
    }

    public SelectBuilder groupBy(String expression) {
        groupBys.add(expression);

        return this;
    }

    public SelectBuilder orderBy(String expression, boolean ascending) {
        if (!ascending) {
            orderBys.add(expression + " DESC");
        }
        else {
            orderBys.add(expression);
        }

        return this;
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

        if (predicateBuilder.getPredicate() != null) {
            builder.append(String.format("%-11s", "WHERE"))
                    .append(predicateBuilder.toString())
                    .append("\n");
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

        return builder.toString();
    }

    @Override
    public String toString() {
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
            else {
                builder.append(" ");
            }
        }

        if (!joinClauses.isEmpty()) {
            for (Join joinClause : joinClauses) {
                builder.append(joinClause.getJoinType().toSQL())
                        .append(" ")
                        .append(joinClause.getTableName())
                        .append(" ON (")
                        .append(joinClause.getOnConditions().toSQL()).append(") ");
            }
        }

        if (predicateBuilder.getPredicate() != null) {
            builder.append("WHERE ").append(predicateBuilder.toSQL());
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

        return builder.toString();
    }
}
