package com.rallibau.products.shared.domain;

import java.io.Serializable;
import java.util.Objects;

public abstract class Identifier implements Serializable {
    final protected String value;

    public Identifier(String value) {

        this.value = value;
    }

    protected Identifier() {
        this.value = null;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Identifier that = (Identifier) o;
        assert value != null;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
