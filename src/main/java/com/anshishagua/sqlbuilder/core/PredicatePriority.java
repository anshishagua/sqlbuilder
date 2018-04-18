package com.anshishagua.sqlbuilder.core;

/**
 * User: lixiao
 * Date: 2018/4/15
 * Time: 下午6:24
 */

public class PredicatePriority {
    public static final int EXISTS_PRIORITY = 20;
    public static final int OR_PRIORITY = 20;
    public static final int AND_PRIORITY = 30;
    public static final int NOT_PRIORITY = 40;
    public static final int BETWEEN_PRIORITY = 45;
    public static final int IN_PRIORITY = 50;
    public static final int EQUAL_PRIORITY = 50;
    public static final int NOT_EQUAL_PRIORITY = 50;
    public static final int GREATER_THAN_PRIORITY = 50;
    public static final int GREATER_THAN_EQUAL_PRIORITY = 50;
    public static final int LESS_THAN_PRIORITY = 50;
    public static final int LESS_THAN_EQUAL_PRIORITY = 50;
    public static final int IS_NULL_PRIORITY = 50;
    public static final int IS_NOT_NULL_PRIORITY = 50;
    public static final int LIKE_PRIORITY = 50;
    public static final int NOT_LIKE_PRIORITY = 50;
    public static final int NOT_IN_PRIORITY = 60;
    public static final int BASIC_PRIORITY = 70;
}