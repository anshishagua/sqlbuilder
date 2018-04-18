package com.anshishagua.sqlbuilder.core.expression;

import com.anshishagua.sqlbuilder.core.SQL;

/**
 * User: lixiao
 * Date: 2018/4/17
 * Time: 下午11:23
 */

public interface Expression extends SQL {
    ExpressionType getType();
}