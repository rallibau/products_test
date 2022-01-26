package com.rallibau.products.prices.domain;

import com.rallibau.products.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface PriceRepository {
    void save(Price price);

    Optional<Price> search(PriceId id);

    List<Price> matching(Criteria criteria);

    List<Price> getAll();
}
