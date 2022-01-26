package com.rallibau.products.prices.domain;

import com.rallibau.products.shared.domain.StringValueObject;

public class BrandId extends StringValueObject {
    public BrandId(String value) {
        super(value);
    }

    public BrandId() {
        super("");
    }
}
