package com.anshishagua.sqlbuilder.examples;

import com.anshishagua.sqlbuilder.core.Delete;
import com.anshishagua.sqlbuilder.core.DeleteBuilder;

/**
 * User: lixiao
 * Date: 2018/4/17
 * Time: 下午5:21
 */

public class DeleteTableExample {
    public static void main(String [] args) {
        Delete delete = new DeleteBuilder()
                .table("person")
                .where("id = 111")
                .where("age = 222").build();

        System.out.println(delete.toSQL());
    }
}