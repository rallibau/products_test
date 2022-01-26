package com.rallibau.products.prices.domain;

import com.rallibau.products.shared.domain.IntValueObject;

public class PricePriority extends IntValueObject {
    public PricePriority(int value) {
        super(value);
    }

    public PricePriority() {
        super(null);
    }
}
