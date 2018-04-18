package com.anshishagua.sqlbuilder.core;

import com.anshishagua.sqlbuilder.core.expression.Limit;

import java.util.ArrayList;
import java.util.List;

/**
 * User: lixiao
 * Date: 2018/4/16
 * Time: 下午9:05
 */

public class SelectBuilder {
    private List<String> selectFields = new ArrayList<>();
    private List<String> fromTables = new ArrayList<>();
    private List<Join> joinClauses = new ArrayList<>();
    private PredicateBuilder predicateBuilder = new PredicateBuilder();
    private List<String> orderBys = new ArrayList<>();
    private List<String> groupBys = new ArrayList<>();
    private PredicateBuilder havingBuilder = new PredicateBuilder();
    private Limit limit = null;

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

    public SelectBuilder selectDistinct(String expression) {
        selectFields.add(String.format("DISTINCT %s", expression));

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

    public SelectBuilder where(Predicate predicate) {
        if (predicateBuilder.getPredicate() == null) {
            predicateBuilder.basicPredicate(predicate);
        }
        else {
            predicateBuilder.and(predicate);
        }

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

    public SelectBuilder or(Predicate predicate) {
        predicateBuilder.or(predicate);

        return this;
    }

    public SelectBuilder or(String expression) {
        predicateBuilder.or(expression);

        return this;
    }

    public SelectBuilder and(Predicate predicate) {
        predicateBuilder.and(predicate);

        return this;
    }

    public SelectBuilder and(String expression) {
        predicateBuilder.and(expression);

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

    public SelectBuilder having(String expression) {
        if (havingBuilder.getPredicate() == null) {
            havingBuilder.basicPredicate(expression);
        } else {
            havingBuilder.and(expression);
        }

        return this;
    }

    public SelectBuilder limit(long limit) {
        this.limit = new Limit(limit);

        return this;
    }

    public SelectBuilder limit(long limit, long offset) {
        this.limit = new Limit(limit, offset);

        return this;
    }

    public Select build() {
        return new Select(selectFields, fromTables, joinClauses, predicateBuilder.getPredicate(), orderBys, groupBys, havingBuilder.getPredicate(), limit);
    }
}