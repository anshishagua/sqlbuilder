package com.anshishagua.sqlbuilder.utils;

import java.util.Collection;

/**
 * User: lixiao
 * Date: 2018/4/17
 * Time: 下午5:05
 */

public class AssertUtils {
    public static void notEmpty(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("Collection could not be empty");
        }
    }

    public static void equalSize(Collection<?> a, Collection<?> b) {
        int sizeA = a == null ? 0 : a.size();
        int sizeB = b == null ? 0 : b.size();

        if (sizeA != sizeB) {
            throw new IllegalArgumentException();
        }
    }
}
