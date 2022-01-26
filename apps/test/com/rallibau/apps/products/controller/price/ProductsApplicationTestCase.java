package com.rallibau.apps.products.controller.price;

import org.springframework.transaction.annotation.Transactional;

@Transactional("products-transaction_manager")
public abstract class ProductsApplicationTestCase extends ApplicationTestCase {
}
