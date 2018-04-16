package com.anshishagua.sqlbuilder.core;

/**
 * User: lixiao
 * Date: 2018/4/15
 * Time: 下午10:46
 */

public class Join {
    private String tableName;
    private JoinType joinType;
    private Predicate onConditions;

    public Join(String tableName, JoinType joinType, Predicate onConditions) {
        this.tableName = tableName;
        this.joinType = joinType;
        this.onConditions = onConditions;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public JoinType getJoinType() {
        return joinType;
    }

    public void setJoinType(JoinType joinType) {
        this.joinType = joinType;
    }

    public Predicate getOnConditions() {
        return onConditions;
    }

    public void setOnConditions(Predicate onConditions) {
        this.onConditions = onConditions;
    }
}