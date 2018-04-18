package com.anshishagua.sqlbuilder.examples;

import com.anshishagua.sqlbuilder.core.comparison.GreaterThan;
import com.anshishagua.sqlbuilder.core.comparison.GreaterThanOrEqual;
import com.anshishagua.sqlbuilder.core.SQLBuilder;
import com.anshishagua.sqlbuilder.core.Select;
import com.anshishagua.sqlbuilder.core.conditional.CaseWhenBuilder;
import com.anshishagua.sqlbuilder.core.primitive.ColumnRef;
import com.anshishagua.sqlbuilder.core.primitive.DoubleValue;
import com.anshishagua.sqlbuilder.core.primitive.IntegerValue;
import com.anshishagua.sqlbuilder.core.primitive.StringValue;

/**
 * User: lixiao
 * Date: 2018/4/17
 * Time: 下午10:53
 */

public class SQLBuilderExample {
    public static void main(String [] args) {
        Select select = SQLBuilder.selectBuilder()
                .select("id")
                .select("age")
                .select("name")
                .from("person")
                .where("age > 10")
                .where("age < 20")
                .or(new GreaterThanOrEqual(new StringValue("a"), new DoubleValue(1)))
                .and(new GreaterThan(new CaseWhenBuilder().whenThen(new GreaterThan(ColumnRef.of("person"), IntegerValue.of(1)), IntegerValue.of(1)).elseEnd(IntegerValue.of(2)).build(), IntegerValue.of(1)))
                .groupBy("age")
                .build();

        System.out.println(select.format());
    }
}