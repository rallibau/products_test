package com.rallibau.products.prices.domain;

import com.rallibau.products.shared.domain.DomainError;

public class PriceException extends DomainError {
    public PriceException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
