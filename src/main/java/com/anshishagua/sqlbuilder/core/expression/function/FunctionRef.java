package com.anshishagua.sqlbuilder.core.expression.function;

import com.anshishagua.sqlbuilder.core.expression.Expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * User: lixiao
 * Date: 2018/4/18
 * Time: 下午3:56
 */

public class FunctionRef implements Function {
    private String name;
    private List<Expression> arguments;

    public FunctionRef(String name) {
        this(name, Collections.emptyList());
    }

    public FunctionRef(String name, List<Expression> arguments) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(arguments);

        this.name = name;
        this.arguments = new ArrayList<>(arguments);
    }

    public static FunctionRef of(String name) {
        return new FunctionRef(name);
    }

    public static FunctionRef of(String name, Expression ... arguments) {
        return new FunctionRef(name, Arrays.asList(arguments));
    }

    public static FunctionRef of(String name, List<Expression> arguments) {
        return new FunctionRef(name, arguments);
    }

    @Override
    public String toSQL() {
        StringBuilder builder = new StringBuilder();

        builder.append(name).append("(");

        Iterator<Expression> iterator = arguments.iterator();

        while (iterator.hasNext()) {
            builder.append(iterator.next().toSQL());

            if (iterator.hasNext()) {
                builder.append(", ");
            }
        }

        builder.append(")");

        return builder.toString();
    }
}