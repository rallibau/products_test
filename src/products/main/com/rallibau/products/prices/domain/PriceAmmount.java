package com.rallibau.products.prices.domain;

import com.rallibau.products.shared.domain.StringValueObject;

public class PriceAmmount extends StringValueObject {
    public PriceAmmount(String value) {
        super(value);
    }
    public PriceAmmount() {
        super("");
    }
}
