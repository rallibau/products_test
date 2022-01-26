package com.rallibau.products.shared.infrastructure.hibernate;

import com.rallibau.products.shared.domain.criteria.Criteria;
import org.hibernate.SessionFactory;
import com.rallibau.products.shared.domain.Identifier;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public abstract class HibernateRepository<T> {
    protected final SessionFactory             sessionFactory;
    protected final Class<T>                   aggregateClass;
    protected final HibernateCriteriaConverter criteriaConverter;

    public HibernateRepository(SessionFactory sessionFactory, Class<T> aggregateClass) {
        this.sessionFactory    = sessionFactory;
        this.aggregateClass    = aggregateClass;
        this.criteriaConverter = new HibernateCriteriaConverter<T>(sessionFactory.getCriteriaBuilder());
    }

    protected void persist(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().clear();
    }

    protected Optional<T> byId(Identifier id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().byId(aggregateClass).load(id));
    }

    protected List<T> byCriteria(Criteria criteria) {
        CriteriaQuery<T> hibernateCriteria = criteriaConverter.convert(criteria, aggregateClass);

        Query query = sessionFactory.getCurrentSession().createQuery(hibernateCriteria);
        if(!criteria.limit().equals(Optional.empty())){
            query.setMaxResults(criteria.limit().get());
        }
        if(!criteria.offset().equals(Optional.empty())){
            query.setFirstResult(criteria.offset().get());
        }

        return query.getResultList();
    }



    protected List<T> all() {
        CriteriaQuery<T> criteria = sessionFactory.getCriteriaBuilder().createQuery(aggregateClass);

        criteria.from(aggregateClass);

        return sessionFactory.getCurrentSession().createQuery(criteria).getResultList();
    }
}
