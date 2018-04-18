package com.anshishagua.sqlbuilder.core;

/**
 * User: lixiao
 * Date: 2018/4/15
 * Time: 上午11:30
 */

public class SQLBuilder {
    public static SelectBuilder selectBuilder() {
        return new SelectBuilder();
    }

    public static UpdateBuilder updateBuilder() {
        return new UpdateBuilder();
    }

    public static CreateBuilder createBuilder() {
        return new CreateBuilder();
    }

    public static DeleteBuilder deleteBuilder() {
        return new DeleteBuilder();
    }
}