package com.rallibau.products.prices.application.search;

import com.rallibau.products.prices.domain.Price;
import com.rallibau.products.prices.domain.PriceException;
import com.rallibau.products.prices.domain.PriceRepository;
import com.rallibau.products.shared.domain.Service;
import com.rallibau.products.shared.domain.criteria.Criteria;
import com.rallibau.products.shared.domain.criteria.FilterOperator;
import com.rallibau.products.shared.domain.criteria.Filters;
import com.rallibau.products.shared.domain.criteria.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public final class SearchPrice {
    private final PriceRepository priceRepository;


    public SearchPrice(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price search(String fechaAplicacion,
                        Long producto,
                        Long brand,
                        String curr) throws ParseException {


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
        format.parse(fechaAplicacion);

        List<HashMap<String, String>> filters = parseFilters(fechaAplicacion, producto, brand, curr);
        Criteria criteria = new Criteria(
            Filters.fromValues(filters),
            Order.desc("pricePriority"),
            Optional.of(1),
            Optional.of(0)
        );

        List<Price> prices = priceRepository.matching(criteria);
        if (prices.isEmpty()) {
            throw new PriceException("PRICE_NOT_FOUND", "Don't found price by current parameters");
        }
        if (prices.size() > 1) {
            throw new PriceException("PRICE_DUPLICATED", "Find more one price by current parameters");
        }

        return prices.get(0);
    }


    private List<HashMap<String, String>> parseFilters(String fechaAplicacion,
                                                       Long producto,
                                                       Long brand,
                                                       String curr) {

        List<HashMap<String, String>> filters = new ArrayList<>();

        if (fechaAplicacion != null && !fechaAplicacion.isEmpty()) {
            HashMap<String, String> filtroFechaAplicacionMayor = new HashMap<>();
            filtroFechaAplicacionMayor.put("field", "priceStartDate");
            filtroFechaAplicacionMayor.put("operator", FilterOperator.LT.value());
            filtroFechaAplicacionMayor.put("value", fechaAplicacion);
            filtroFechaAplicacionMayor.put("type", "date");
            filters.add(filtroFechaAplicacionMayor);

            HashMap<String, String> filtroFechaAplicacionMenor = new HashMap<>();
            filtroFechaAplicacionMenor.put("field", "priceEndDate");
            filtroFechaAplicacionMenor.put("operator", FilterOperator.GT.value());
            filtroFechaAplicacionMenor.put("value", fechaAplicacion);
            filtroFechaAplicacionMenor.put("type", "date");
            filters.add(filtroFechaAplicacionMenor);
        }


        if (producto != null) {
            HashMap<String, String> filtroProducto = new HashMap<>();
            filtroProducto.put("field", "productId");
            filtroProducto.put("operator", FilterOperator.EQUAL.value());
            filtroProducto.put("value", producto.toString());
            filters.add(filtroProducto);
        }

        if (brand != null) {
            HashMap<String, String> filtroBrand = new HashMap<>();
            filtroBrand.put("field", "brandId");
            filtroBrand.put("operator", FilterOperator.EQUAL.value());
            filtroBrand.put("value", brand.toString());
            filters.add(filtroBrand);
        }

        if (curr != null) {
            HashMap<String, String> filtroBrand = new HashMap<>();
            filtroBrand.put("field", "priceCurr");
            filtroBrand.put("operator", FilterOperator.EQUAL.value());
            filtroBrand.put("value", curr);
            filters.add(filtroBrand);
        }

        return filters;
    }

}
