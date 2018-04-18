package com.anshishagua.sqlbuilder.core;

import com.anshishagua.sqlbuilder.core.bool.And;
import com.anshishagua.sqlbuilder.core.bool.Or;
import com.anshishagua.sqlbuilder.core.comparison.Between;
import com.anshishagua.sqlbuilder.core.comparison.Equal;
import com.anshishagua.sqlbuilder.core.comparison.GreaterThan;
import com.anshishagua.sqlbuilder.core.comparison.GreaterThanOrEqual;
import com.anshishagua.sqlbuilder.core.comparison.In;
import com.anshishagua.sqlbuilder.core.comparison.LessThan;
import com.anshishagua.sqlbuilder.core.comparison.LessThanOrEqual;
import com.anshishagua.sqlbuilder.core.comparison.NotEqual;
import com.anshishagua.sqlbuilder.core.comparison.NotIn;
import com.anshishagua.sqlbuilder.core.expression.Expression;

import java.util.Arrays;
import java.util.List;

/**
 * User: lixiao
 * Date: 2018/4/15
 * Time: 下午6:01
 */

public class PredicateBuilder {
    private Predicate predicate;

    public PredicateBuilder() {

    }

    public Predicate getPredicate() {
        return predicate;
    }

    public Predicate build() {
        return predicate;
    }

    public PredicateBuilder basicPredicate(Predicate predicate) {
        this.predicate = predicate;

        return this;
    }

    public PredicateBuilder basicPredicate(String expression) {
        this.predicate = new BasicPredicate(expression);

        return this;
    }

    public PredicateBuilder and(Predicate predicate) {
        this.predicate = new And(this.predicate, predicate);

        return this;
    }

    public PredicateBuilder and(String expression) {
        this.predicate = new And(this.predicate, new BasicPredicate(expression));

        return this;
    }

    public PredicateBuilder or(Predicate predicate) {
        this.predicate = new Or(this.predicate, predicate);

        return this;
    }

    public PredicateBuilder or(String expression) {
        this.predicate = new Or(this.predicate, new BasicPredicate(expression));

        return this;
    }

    public PredicateBuilder notIn(String expression, List<String> notInList) {
        this.predicate = new And(this.predicate, new NotIn(expression, notInList));

        return this;
    }

    public PredicateBuilder in(String expression, List<String> inList) {
        this.predicate = new And(this.predicate, new In(expression, inList));

        return this;
    }

    public PredicateBuilder greaterThan(Expression left, Expression right) {
        this.predicate = new And(this.predicate, new GreaterThan(left, right));

        return this;
    }

    public PredicateBuilder greaterThanOrEqual(Expression left, Expression right) {
        this.predicate = new And(this.predicate, new GreaterThanOrEqual(left, right));

        return this;
    }

    public PredicateBuilder lessThan(Expression left, Expression right) {
        this.predicate = new And(this.predicate, new LessThan(left, right));

        return this;
    }

    public PredicateBuilder lessThanOrEqual(Expression left, Expression right) {
        this.predicate = new And(this.predicate, new LessThanOrEqual(left, right));

        return this;
    }

    public PredicateBuilder equal(Expression left, Expression right) {
        this.predicate = new And(this.predicate, new Equal(left, right));

        return this;
    }

    public PredicateBuilder notEqual(Expression left, Expression right) {
        this.predicate = new And(this.predicate, new NotEqual(left, right));

        return this;
    }

    public PredicateBuilder between(Expression expression, Expression min, Expression max) {
        this.predicate = new Between(expression, min, max);

        return this;
    }

    public String toSQL() {
        return predicate.toSQL();
    }

    @Override
    public String toString() {
        return toSQL();
    }

    public static void main(String [] args) {
        PredicateBuilder builder = new PredicateBuilder();

        builder.basicPredicate("1 = 1").and(new NotIn("a", Arrays.asList("1", "2")));

        System.out.println(builder);
    }
}