package com.rallibau.products.prices.infrastructure.hibernate;


import com.rallibau.products.prices.domain.Price;
import com.rallibau.products.prices.domain.PriceId;
import com.rallibau.products.shared.domain.Service;
import com.rallibau.products.shared.domain.criteria.Criteria;
import com.rallibau.products.shared.infrastructure.hibernate.HibernateRepository;
import com.rallibau.products.prices.domain.PriceRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("products-transaction_manager")
public class HibernetePriceRepository extends HibernateRepository<Price> implements PriceRepository {
    public HibernetePriceRepository(@Qualifier("products-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory,Price.class);
    }

    @Override
    public void save(Price price) {
        persist(price);
    }

    @Override
    public Optional<Price> search(PriceId id) {
        return byId(id);
    }

    @Override
    public List<Price> matching(Criteria criteria) {
        return byCriteria(criteria);
    }

    @Override
    public List<Price> getAll() {
        return all();
    }

}
