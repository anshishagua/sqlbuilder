package com.anshishagua.sqlbuilder.examples;

import com.anshishagua.sqlbuilder.core.BasicPredicate;
import com.anshishagua.sqlbuilder.core.JoinType;
import com.anshishagua.sqlbuilder.core.comparison.Exists;
import com.anshishagua.sqlbuilder.core.comparison.GreaterThan;
import com.anshishagua.sqlbuilder.core.comparison.In;
import com.anshishagua.sqlbuilder.core.comparison.NotIn;
import com.anshishagua.sqlbuilder.core.bool.Or;
import com.anshishagua.sqlbuilder.core.PredicateBuilder;
import com.anshishagua.sqlbuilder.core.SelectBuilder;
import com.anshishagua.sqlbuilder.core.expression.function.FunctionRef;
import com.anshishagua.sqlbuilder.core.primitive.ColumnRef;
import com.anshishagua.sqlbuilder.core.primitive.IntegerValue;
import com.anshishagua.sqlbuilder.core.primitive.StringValue;

import java.util.Arrays;

/**
 * User: lixiao
 * Date: 2018/4/16
 * Time: 下午9:07
 */

public class SelectBuilderExample {
    public static void main(String [] args) {
        SelectBuilder selectBuilder = new SelectBuilder()
                .selectDistinct("a.id")
                .select("b")
                .select("c")
                .from("e", "fff").from("f")
                .join("g", JoinType.INNER_JOIN, new PredicateBuilder().basicPredicate("g.id = a.id").and("g.id > 10000").build())
                .where("1 = 1")
                .where("2 = 2")
                .where(new In("1", new SelectBuilder().select("id").from("person").build()))
                .where(new NotIn("1", Arrays.asList("2", "3", "4")))
                .or(new Or(new BasicPredicate("1 > 1"), new BasicPredicate("2 < 1")))
                .or(new PredicateBuilder().between(ColumnRef.of("person", "age"), IntegerValue.of(2), IntegerValue.of(1)).build())
                .and(new GreaterThan(FunctionRef.of("LENGTH", ColumnRef.of("person", "name")), StringValue.of("2012-01-01")))
                .and(Exists.of(new SelectBuilder().select("id").from("person").build()))
                .orderBy("a", false)
                .orderBy("b", true)
                .having("COUNT(*) > 1").having("SUM(aaa) < 1")
                .limit(122);

        System.out.println(selectBuilder.build().format());
    }
}