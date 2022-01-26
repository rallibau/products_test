package com.rallibau.products.prices.domain;

import com.rallibau.products.shared.domain.StringValueObject;

public class ProductId extends StringValueObject {
    public ProductId(String value) {
        super(value);
    }

    public ProductId() {
        super("");
    }
}
