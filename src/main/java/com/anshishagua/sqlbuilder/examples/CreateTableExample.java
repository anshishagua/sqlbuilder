package com.anshishagua.sqlbuilder.examples;

import com.anshishagua.sqlbuilder.core.Column;
import com.anshishagua.sqlbuilder.core.CreateBuilder;

import java.sql.JDBCType;

/**
 * User: lixiao
 * Date: 2018/4/16
 * Time: 下午11:40
 */

public class CreateTableExample {
    public static void main(String [] args) {
        CreateBuilder createBuilder = new CreateBuilder()
                .table("person")
                .createIfNotExists(true)
                .column("id", JDBCType.BIGINT)
                .column("age", JDBCType.INTEGER)
                .column(new Column("name", JDBCType.VARCHAR, true, false, null, 1, 255));

        System.out.println(createBuilder.build().toSQL());
    }
}
