package com.rallibau.products.prices.domain;

import com.rallibau.products.shared.domain.DateValueObject;

import java.util.Date;

public class PriceEndDate extends DateValueObject {
    public PriceEndDate(Date value) {
        super(value);
    }

    public PriceEndDate() {
        super(null);
    }
}
