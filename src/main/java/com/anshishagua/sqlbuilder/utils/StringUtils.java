package com.anshishagua.sqlbuilder.utils;

/**
 * User: lixiao
 * Date: 2018/4/17
 * Time: 下午11:27
 */

public class StringUtils {
    public static String singleQuote(String string) {
        return String.format("'%s'", string);
    }
}