package com.rallibau.apps.products.controller.price;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

class PriceGetControllerShould extends ProductsApplicationTestCase{

    @Test
    void check_test_1() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        assertResponse("/price?brand=1&product=35455&fechaAplicacion=2020-06-14-10.00.00",
            200,
            "{\"BRAND_ID\":\"1\",\"PRICE\":\"35.50\",\"END_DATE\":\"2020-12-31-23.59.59\"," +
                "\"START_DATE\":\"2020-06-14-00.00.00\",\"PRODUCT_ID\":\"35455\",\"PRICE_ID\":\"1\"}",
            headers);
    }

    @Test
    void check_test_2() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        assertResponse("/price?brand=1&product=35455&fechaAplicacion=2020-06-14-16.00.00",
            200,
            "{\"BRAND_ID\":\"1\",\"PRICE\":\"25,45\",\"END_DATE\":\"2020-06-14-18.30.00\"," +
                "\"START_DATE\":\"2020-06-14-15.00.00\",\"PRODUCT_ID\":\"35455\",\"PRICE_ID\":\"2\"}",
            headers);
    }

    @Test
    void check_test_3() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        assertResponse("/price?brand=1&product=35455&fechaAplicacion=2020-06-14-21.00.00",
            200,
            "{\"BRAND_ID\":\"1\",\"PRICE\":\"35.50\",\"END_DATE\":\"2020-12-31-23.59.59\"," +
                "\"START_DATE\":\"2020-06-14-00.00.00\",\"PRODUCT_ID\":\"35455\",\"PRICE_ID\":\"1\"}",
            headers);
    }

    @Test
    void check_test_4() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        assertResponse("/price?brand=1&product=35455&fechaAplicacion=2020-06-15-10.00.00",
            200,
            "{\"BRAND_ID\":\"1\",\"PRICE\":\"30,50\",\"END_DATE\":\"2020-06-15-11.00.00\"," +
                "\"START_DATE\":\"2020-06-15-00.00.00\",\"PRODUCT_ID\":\"35455\",\"PRICE_ID\":\"3\"}",
            headers);
    }

    @Test
    void check_test_5() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        assertResponse("/price?brand=1&product=35455&fechaAplicacion=2020-06-16-21.00.00",
            200,
            "{\"BRAND_ID\":\"1\",\"PRICE\":\"38,95\",\"END_DATE\":\"2020-12-31-23.59.59\"," +
                "\"START_DATE\":\"2020-06-15-16.00.00\",\"PRODUCT_ID\":\"35455\",\"PRICE_ID\":\"4\"}",
            headers);
    }

    @Test
    void check_test_fake_brand() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        assertResponse("/price?brand=67&product=35455&fechaAplicacion=2020-06-16-21.00.00",
            200,
            "{\"ERROR_MESSAGE\":\"Don't found price by current parameters\",\"ERROR_CODE\":\"PRICE_NOT_FOUND\"}",
            headers);
    }

    @Test
    void check_test_invalid_date() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        assertResponse("/price?brand=1&product=35455&fechaAplicacion=XXXX-06-35-21.00.00",
            200,
            "{\"ERROR_MESSAGE\":\"unknow error has ocurred\",\"ERROR_CODE\":\"CODE_UNKNOW\"}",
            headers);
    }
}
