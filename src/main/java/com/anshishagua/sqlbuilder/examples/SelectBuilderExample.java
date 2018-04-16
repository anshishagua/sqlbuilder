package com.anshishagua.sqlbuilder.examples;

import com.anshishagua.sqlbuilder.core.JoinType;
import com.anshishagua.sqlbuilder.core.PredicateBuilder;
import com.anshishagua.sqlbuilder.core.SelectBuilder;

/**
 * User: lixiao
 * Date: 2018/4/16
 * Time: 下午9:07
 */

public class SelectBuilderExample {
    public static void main(String [] args) {
        SelectBuilder selectBuilder = new SelectBuilder()
                .select("a.id")
                .select("b")
                .select("c")
                .from("e", "fff").from("f")
                .join("g", JoinType.INNER_JOIN, new PredicateBuilder().basicPredicate("g.id = a.id").and("g.id > 10000").build())
                .where("1 = 1")
                .where("2 = 2")
                .orderBy("a", false)
                .orderBy("b", true);

        System.out.println(selectBuilder.format());
    }
}
