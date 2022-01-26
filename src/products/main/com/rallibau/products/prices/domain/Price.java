package com.rallibau.products.prices.domain;

import com.rallibau.products.shared.domain.AggregateRoot;

public final class Price extends AggregateRoot {
    private PriceId id;
    private BrandId brandId;
    private PriceStartDate priceStartDate;
    private PriceEndDate priceEndDate;
    private PricePriority pricePriority;
    private PriceAmmount priceAmmount;
    private PriceCurr priceCurr;
    private ProductId productId;

    public Price(PriceId id, BrandId brandId, PriceStartDate priceStartDate, PriceEndDate priceEndDate, PricePriority pricePriority, PriceAmmount priceAmmount, PriceCurr priceCurr, ProductId productId) {
        this.id = id;
        this.brandId = brandId;
        this.priceStartDate = priceStartDate;
        this.priceEndDate = priceEndDate;
        this.pricePriority = pricePriority;
        this.priceAmmount = priceAmmount;
        this.priceCurr = priceCurr;
        this.productId = productId;
    }

    public Price() {
        this.id = null;
        this.brandId = null;
        this.priceStartDate = null;
        this.priceEndDate = null;
        this.pricePriority = null;
        this.priceAmmount = null;
        this.priceCurr = null;
        this.productId = null;
    }


    public PriceId id() {
        return id;
    }

    public BrandId brandId(){
        return brandId;
    }

    public PriceStartDate priceStartDate() {
        return priceStartDate;
    }

    public PriceEndDate priceEndDate() {
        return priceEndDate;
    }

    public ProductId productId() {
        return productId;
    }


    public PriceCurr priceCurr() {
        return priceCurr;
    }

    public PriceAmmount priceAmmount() {
        return priceAmmount;
    }

    public PricePriority pricePriority() {
        return pricePriority;
    }

}
