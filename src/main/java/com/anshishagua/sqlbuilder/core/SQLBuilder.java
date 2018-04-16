package com.anshishagua.sqlbuilder.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: lixiao
 * Date: 2018/4/15
 * Time: 上午11:30
 */

public class SQLBuilder {
    private List<String> selectFields = new ArrayList<>();
    private List<String> fromTables = new ArrayList<>();
    private List<Join> joinClauses = new ArrayList<>();
    private PredicateBuilder predicateBuilder = new PredicateBuilder();
    private List<String> orderBys = new ArrayList<>();
    private List<String> groupBys = new ArrayList<>();

    public SQLBuilder() {

    }

    public SQLBuilder select(String expression, String renameTo) {
        selectFields.add(String.format("%s AS %s", expression, renameTo));

        return this;
    }

    public SQLBuilder select(String expression) {
        selectFields.add(expression);

        return this;
    }

    public SQLBuilder from(String tableName) {
        fromTables.add(tableName);

        return this;
    }

    public SQLBuilder join(String tableName, JoinType joinType, Predicate onConditions) {
        joinClauses.add(new Join(tableName, joinType, onConditions));

        return this;
    }

    public SQLBuilder leftJoin(String tableName, Predicate onConditions) {
        joinClauses.add(new Join(tableName, JoinType.LEFT_JOIN, onConditions));

        return this;
    }

    public SQLBuilder rightJoin(String tableName, Predicate onConditions) {
        joinClauses.add(new Join(tableName, JoinType.RIGHT_JOIN, onConditions));

        return this;
    }

    public SQLBuilder innerJoin(String tableName, Predicate onConditions) {
        joinClauses.add(new Join(tableName, JoinType.INNER_JOIN, onConditions));

        return this;
    }


    public SQLBuilder where(String expression) {
        if (predicateBuilder.getPredicate() == null) {
            predicateBuilder.basicPredicate(expression);
        }
        else {
            predicateBuilder.and(expression);
        }

        return this;
    }

    public SQLBuilder or(String expression) {
        predicateBuilder.or(expression);

        return this;
    }

    public SQLBuilder and(String expression) {
        predicateBuilder.or(expression);

        return this;
    }

    public SQLBuilder groupBy(String expression) {
        groupBys.add(expression);

        return this;
    }

    public SQLBuilder orderBy(String expression, boolean ascending) {
        if (!ascending) {
            orderBys.add(expression + " DESC");
        }
        else {
            orderBys.add(expression);
        }

        return this;
    }

    public String format() {
        StringBuilder builder = new StringBuilder();

        return null;
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
            builder.append("WHERE ").append(predicateBuilder.toString());
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