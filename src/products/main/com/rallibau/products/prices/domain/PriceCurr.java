package com.rallibau.products.prices.domain;

import com.rallibau.products.shared.domain.StringValueObject;

public class PriceCurr extends StringValueObject {
    public PriceCurr(String value) {
        super(value);
    }
    public PriceCurr() {
        super("");
    }
}
