package com.rallibau.products.shared.domain.criteria;

import java.util.HashMap;

public final class Filter {
    private final FilterField field;
    private final FilterOperator operator;
    private final FilterValue value;
    private final FilterType type;


    public Filter(FilterField field, FilterOperator operator, FilterValue value, FilterType type) {
        this.field = field;
        this.operator = operator;
        this.value = value;
        this.type = type;
    }

    public static Filter create(String field, String operator, String value, String type) {
        return new Filter(
            new FilterField(field),
            FilterOperator.fromValue(operator.toUpperCase()),
            new FilterValue(value),
            new FilterType(type));
    }

    public static Filter fromValues(HashMap<String, String> values) {
        return new Filter(
            new FilterField(values.get("field")),
            FilterOperator.fromValue(values.get("operator")),
            new FilterValue(values.get("value")),
            new FilterType(values.get("type")));
    }

    public FilterField field() {
        return field;
    }

    public FilterOperator operator() {
        return operator;
    }

    public FilterValue value() {
        return value;
    }

    public FilterType type() {
        return type;
    }

    public String serialize() {
        return String.format("%s.%s.%s", field.value(), operator.value(), value.value());
    }
}
