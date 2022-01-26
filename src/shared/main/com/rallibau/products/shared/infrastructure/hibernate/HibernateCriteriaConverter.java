package com.rallibau.products.shared.infrastructure.hibernate;

import com.rallibau.products.shared.domain.DateValueObject;
import com.rallibau.products.shared.domain.StringValueObject;
import com.rallibau.products.shared.domain.criteria.Criteria;
import com.rallibau.products.shared.domain.criteria.Filter;
import com.rallibau.products.shared.domain.criteria.FilterOperator;

import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public final class HibernateCriteriaConverter<T> {
    private final CriteriaBuilder                                                 builder;
    private final HashMap<FilterOperator, BiFunction<Filter, Root<T>, Predicate>> predicateTransformers = new HashMap<FilterOperator, BiFunction<Filter, Root<T>, Predicate>>() {{
        put(FilterOperator.EQUAL, HibernateCriteriaConverter.this::equalsPredicateTransformer);
        put(FilterOperator.NOT_EQUAL, HibernateCriteriaConverter.this::notEqualsPredicateTransformer);
        put(FilterOperator.GT, HibernateCriteriaConverter.this::greaterThanPredicateTransformer);
        put(FilterOperator.LT, HibernateCriteriaConverter.this::lowerThanPredicateTransformer);
        put(FilterOperator.CONTAINS, HibernateCriteriaConverter.this::containsPredicateTransformer);
        put(FilterOperator.NOT_CONTAINS, HibernateCriteriaConverter.this::notContainsPredicateTransformer);
    }};

    public HibernateCriteriaConverter(CriteriaBuilder builder) {
        this.builder = builder;
    }

    public CriteriaQuery<T> convert(Criteria criteria, Class<T> aggregateClass) {
        CriteriaQuery<T> hibernateCriteria = builder.createQuery(aggregateClass);
        Root<T>          root              = hibernateCriteria.from(aggregateClass);

        hibernateCriteria.where(formatPredicates(criteria.filters().filters(), root));

        if (criteria.order().hasOrder()) {
            Path<Object> orderBy = root.get(criteria.order().orderBy().value());
            Order        order   = criteria.order().orderType().isAsc() ? builder.asc(orderBy) : builder.desc(orderBy);



            hibernateCriteria.orderBy(order);
        }


        return hibernateCriteria;
    }

    private Predicate[] formatPredicates(List<Filter> filters, Root<T> root) {
        List<Predicate> predicates = filters.stream().map(filter -> formatPredicate(
            filter,
            root
        )).collect(Collectors.toList());

        Predicate[] predicatesArray = new Predicate[predicates.size()];
        predicatesArray = predicates.toArray(predicatesArray);

        return predicatesArray;
    }

    private Predicate formatPredicate(Filter filter, Root<T> root) {
        BiFunction<Filter, Root<T>, Predicate> transformer = predicateTransformers.get(filter.operator());

        return transformer.apply(filter, root);
    }

    private Predicate equalsPredicateTransformer(Filter filter, Root<T> root) {

        return builder.equal(root.get(filter.field().value()), new StringValueObject(filter.value().value()));
    }

    private Predicate notEqualsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.notEqual(root.get(filter.field().value()), filter.value().value());
    }

    private Predicate greaterThanPredicateTransformer(Filter filter, Root<T> root) {
        if (filter.type() != null && filter.type().value()!= null && filter.type().value().equals("date")) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
            try {
                Date fecha = format.parse(filter.value().value());
                return builder.greaterThan(root.get(filter.field().value()).get("value"), fecha);
            } catch (ParseException e) {
                e.printStackTrace();
                return builder.greaterThan(root.get(filter.field().value()), filter.value().value());
            }
        } else {
            return builder.greaterThan(root.get(filter.field().value()), filter.value().value());
        }

    }

    private Predicate lowerThanPredicateTransformer(Filter filter, Root<T> root) {
        if (filter.type()!= null && filter.type().value()!= null && filter.type().value().equals("date")) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
            try {
                Date fecha = format.parse(filter.value().value());
                return builder.lessThan(root.get(filter.field().value()).get("value"), fecha);
            } catch (ParseException e) {
                e.printStackTrace();
                return builder.lessThan(root.get(filter.field().value()), filter.value().value());
            }
        } else {
            return builder.lessThan(root.get(filter.field().value()), filter.value().value());
        }
    }

    private Predicate containsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.like(root.get(filter.field().value()), String.format("%%%s%%", filter.value().value()));
    }

    private Predicate notContainsPredicateTransformer(Filter filter, Root<T> root) {
        return builder.notLike(root.get(filter.field().value()), String.format("%%%s%%", filter.value().value()));
    }
}
