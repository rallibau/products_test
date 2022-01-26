package com.rallibau.products.prices.domain;

import com.rallibau.products.shared.domain.Identifier;

public class PriceId extends Identifier {
    public PriceId(String value) {
        super(value);
    }

    private PriceId() {
    }
}
