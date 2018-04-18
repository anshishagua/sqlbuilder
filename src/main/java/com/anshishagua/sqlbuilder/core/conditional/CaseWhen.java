package com.anshishagua.sqlbuilder.core.conditional;

import com.anshishagua.sqlbuilder.core.Predicate;
import com.anshishagua.sqlbuilder.core.expression.Expression;
import com.anshishagua.sqlbuilder.core.expression.ExpressionType;
import com.anshishagua.sqlbuilder.utils.AssertUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午3:27
 */

public class CaseWhen implements Expression {
    private final List<Predicate> whenClauses;
    private final List<Expression> thenClauses;
    private final Expression elseClause;

    public CaseWhen(List<Predicate> whenClauses, List<Expression> thenClauses, Expression elseClause) {
        AssertUtils.notEmpty(whenClauses);
        AssertUtils.notEmpty(thenClauses);
        Objects.requireNonNull(elseClause);

        this.whenClauses = new ArrayList<>(whenClauses);
        this.thenClauses = new ArrayList<>(thenClauses);
        this.elseClause = elseClause;
    }

    @Override
    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        builder.append("CASE");

        for (int i = 0; i < whenClauses.size(); ++i) {
            builder.append(" WHEN ").append(whenClauses.get(i).toSQL()).append(" THEN ").append(thenClauses.get(i).toSQL());
        }

        builder.append(" ELSE ").append(elseClause.toSQL()).append(" END");

        return builder.toString();
    }

    @Override
    public ExpressionType getType() {
        return ExpressionType.CASE_WHEN;
    }
}