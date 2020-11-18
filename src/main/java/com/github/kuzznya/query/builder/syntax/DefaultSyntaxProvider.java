package com.github.kuzznya.query.builder.syntax;

import com.github.kuzznya.query.builder.select.model.Join;
import com.github.kuzznya.query.builder.select.model.OrderType;
import com.github.kuzznya.query.builder.select.model.SelectType;

import java.util.List;

public class DefaultSyntaxProvider implements SyntaxProvider {

    @Override
    public String select(SelectType type, List<String> columns) {
        String typeString = "";

        switch (type) {
            case DEFAULT:
                typeString = "";
                break;
            case ALL:
                typeString = "ALL ";
                break;
            case DISTINCT:
                typeString = "DISTINCT ";
                break;
        }

        return "SELECT " + typeString + String.join(", ", columns);
    }

    @Override
    public String from(String table) {
        return "FROM " + table;
    }

    @Override
    public String join(Join join) {

        String prefix = "";

        switch (join.getType()) {
            case DEFAULT:
                prefix = "";
                break;
            case LEFT:
                prefix = "LEFT ";
                break;
            case RIGHT:
                prefix = "RIGHT ";
                break;
            case INNER:
                prefix = "INNER ";
                break;
        }
        return prefix + "JOIN " + join.getTable() + " ON " + join.getOn();
    }

    @Override
    public String where(String condition) {
        return "WHERE " + condition;
    }

    @Override
    public String groupBy(String column) {
        return "GROUP BY " + column;
    }

    @Override
    public String having(String condition) {
        return "HAVING " + condition;
    }

    @Override
    public String orderBy(String column, OrderType orderType) {
        String postfix = "";

        switch (orderType) {
            case DEFAULT:
                postfix = "";
                break;
            case ASCENDING:
                postfix = " ASC";
                break;
            case DESCENDING:
                postfix = " DESC";
                break;
        }

        return "ORDER BY " + column + postfix;
    }

    @Override
    public String limit(int value) {
        return "LIMIT " + value;
    }

    @Override
    public String offset(int value) {
        return "OFFSET " + value;
    }

    @Override
    public String and() {
        return "AND";
    }

    @Override
    public String or() {
        return "OR";
    }

    @Override
    public String alias(String value, String alias) {
        return value + " AS " + alias;
    }

    @Override
    public String delimiter() {
        return " ";
    }
}
