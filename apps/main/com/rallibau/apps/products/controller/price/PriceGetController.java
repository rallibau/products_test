package com.rallibau.apps.products.controller.price;

import com.rallibau.products.prices.application.search.SearchPrice;
import com.rallibau.products.prices.domain.Price;
import com.rallibau.products.prices.domain.PriceException;
import com.rallibau.products.shared.infrastructure.persistence.DataLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PriceGetController {
    private final SearchPrice searchPrice;

    public PriceGetController(SearchPrice searchPrice) {
        this.searchPrice = searchPrice;
    }


    @GetMapping("/price")
    public HashMap<String, String> index(@RequestParam(required = false) String fechaAplicacion,
                                         @RequestParam(required = false) Long producto,
                                         @RequestParam(required = false) Long brand,
                                         @RequestParam(required = false) String curr) {

        HashMap<String, String> result = new HashMap<>();
        Price price = null;

        try {
            price = searchPrice.search(fechaAplicacion, producto, brand, curr);
        } catch (PriceException e) {
            result.put("ERROR_CODE", e.errorCode());
            result.put("ERROR_MESSAGE", e.errorMessage());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("ERROR_CODE", "CODE_UNKNOW");
            result.put("ERROR_MESSAGE", "unknow error has ocurred");
            return result;
        }



        result.put("PRODUCT_ID", price.productId().value());
        result.put("BRAND_ID", price.brandId().value());
        result.put("PRICE_ID", price.id().value());
        result.put("START_DATE", DataLoader.DATE_FORMAT.format(price.priceStartDate().value()));
        result.put("END_DATE", DataLoader.DATE_FORMAT.format(price.priceEndDate().value()));
        result.put("PRICE", price.priceAmmount().value());


        return result;
    }

}
