package com.anshishagua.sqlbuilder.examples;

import com.anshishagua.sqlbuilder.core.InsertBuilder;

/**
 * User: lixiao
 * Date: 2018/4/16
 * Time: 下午9:53
 */

public class InsertBuilderExample {
    public static void main(String [] args) {
        InsertBuilder insertBuilder = new InsertBuilder()
                .table("person")
                .set("id", "1")
                .set("name", "benben")
                .set("age", "333");

        System.out.println(insertBuilder.build());
    }
}
