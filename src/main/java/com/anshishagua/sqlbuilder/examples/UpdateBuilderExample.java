package com.anshishagua.sqlbuilder.examples;

import com.anshishagua.sqlbuilder.core.UpdateBuilder;

/**
 * User: lixiao
 * Date: 2018/4/16
 * Time: 下午10:19
 */

public class UpdateBuilderExample {
    public static void main(String [] args) {
        UpdateBuilder updateBuilder = new UpdateBuilder()
                .table("person")
                .set("id", "1")
                .set("name", "benben")
                .set("age", "21")
                .where("id = 111")
                .where("age = 222");

        System.out.println(updateBuilder.build().toSQL());
    }
}
