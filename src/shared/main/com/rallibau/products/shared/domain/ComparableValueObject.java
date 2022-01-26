package com.rallibau.products.shared.domain;

import java.util.Objects;

public class ComparableValueObject {
    private Comparable value;

    public ComparableValueObject(Comparable value) {
        this.value = value;
    }

    public Comparable value() {
        return value;
    }

    @Override
    public String toString() {
        return this.value().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ComparableValueObject)) {
            return false;
        }
        ComparableValueObject that = (ComparableValueObject) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
