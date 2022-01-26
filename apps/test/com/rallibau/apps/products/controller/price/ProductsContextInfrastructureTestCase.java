package com.rallibau.apps.products.controller.price;

import com.rallibau.apps.products.ProductsApplication;
import com.rallibau.shared.infrastructure.InfrastructureTestCase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = ProductsApplication.class)
@SpringBootTest
public abstract class ProductsContextInfrastructureTestCase extends InfrastructureTestCase {
}
