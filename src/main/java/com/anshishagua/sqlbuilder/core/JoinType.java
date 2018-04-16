package com.anshishagua.sqlbuilder.core;

/**
 * User: lixiao
 * Date: 2018/4/15
 * Time: 下午10:40
 */

public enum JoinType {
    LEFT_JOIN("LEFT JOIN"),
    RIGHT_JOIN("RIGHT JOIN"),
    INNER_JOIN("INNER JOIN");

    private String sql;

    JoinType(String sql) {
        this.sql = sql;
    }

    public String toSQL() {
        return sql;
    }
}