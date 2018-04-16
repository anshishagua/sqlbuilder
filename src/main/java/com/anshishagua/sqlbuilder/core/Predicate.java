package com.anshishagua.sqlbuilder.core;

/**
 * User: lixiao
 * Date: 2018/4/15
 * Time: 下午5:42
 */

public interface Predicate {
    String toSQL();
    int getPriority();
}