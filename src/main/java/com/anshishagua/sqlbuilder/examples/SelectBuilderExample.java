package com.anshishagua.sqlbuilder.examples;

import com.anshishagua.sqlbuilder.core.BasicPredicate;
import com.anshishagua.sqlbuilder.core.JoinType;
import com.anshishagua.sqlbuilder.core.In;
import com.anshishagua.sqlbuilder.core.NotIn;
import com.anshishagua.sqlbuilder.core.Or;
import com.anshishagua.sqlbuilder.core.PredicateBuilder;
import com.anshishagua.sqlbuilder.core.SelectBuilder;

import java.util.Arrays;

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
                .where(new In("1", Arrays.asList("1", "2")))
                .where(new NotIn("1", Arrays.asList("2", "3", "4")))
                .or(new Or(new BasicPredicate("1 > 1"), new BasicPredicate("2 < 1")))
                .orderBy("a", false)
                .orderBy("b", true);

        System.out.println(selectBuilder.format());
    }
}
