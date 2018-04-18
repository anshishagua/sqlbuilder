package com.anshishagua.sqlbuilder.core.comparison;

import com.anshishagua.sqlbuilder.core.PredicatePriority;
import com.anshishagua.sqlbuilder.core.Select;

import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午4:44
 */

public class Exists implements Comparison {
    private Select subQuery;

    public Exists(Select subQuery) {
        Objects.requireNonNull(subQuery);

        this.subQuery = subQuery;
    }

    public static Exists of(Select subQuery) {
        return new Exists(subQuery);
    }

    @Override
    public String toSQL() {
        return String.format("EXISTS (%s)", subQuery.toSQL());
    }

    @Override
    public int getPriority() {
        return PredicatePriority.EXISTS_PRIORITY;
    }
}