package com.anshishagua.sqlbuilder.core.conditional;

import com.anshishagua.sqlbuilder.core.Predicate;
import com.anshishagua.sqlbuilder.core.expression.Expression;

import java.util.ArrayList;
import java.util.List;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午3:37
 */

public class CaseWhenBuilder {
    private List<Predicate> whenClauses = new ArrayList<>();
    private List<Expression> thenClauses = new ArrayList<>();
    private Expression elseClause;

    public CaseWhenBuilder() {

    }

    public CaseWhenBuilder whenThen(Predicate when, Expression then) {
        whenClauses.add(when);
        thenClauses.add(then);

        return this;
    }

    public CaseWhenBuilder elseEnd(Expression elseClause) {
        this.elseClause = elseClause;

        return this;
    }

    public CaseWhen build() {
        return new CaseWhen(whenClauses, thenClauses, elseClause);
    }
}