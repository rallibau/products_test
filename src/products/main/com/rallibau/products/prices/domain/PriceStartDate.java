package com.rallibau.products.prices.domain;

import com.rallibau.products.shared.domain.DateValueObject;

import java.util.Date;

public class PriceStartDate extends DateValueObject {
    public PriceStartDate(Date value) {
        super(value);
    }

    public PriceStartDate() {
        super(null);
    }
}
