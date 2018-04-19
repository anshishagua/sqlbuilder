package com.anshishagua.sqlbuilder.examples;

import com.anshishagua.sqlbuilder.core.Insert;
import com.anshishagua.sqlbuilder.core.InsertBuilder;

/**
 * User: lixiao
 * Date: 2018/4/16
 * Time: 下午9:53
 */

public class InsertBuilderExample {
    public static void main(String [] args) {
        Insert insert = new InsertBuilder()
                .table("person")
                .set("id", "1")
                .set("name", "benben")
                .set("age", "333")
                .build();

        System.out.println(insert.toSQL());
    }
}