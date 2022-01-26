package com.rallibau.products.shared.infrastructure.persistence;

import com.rallibau.products.shared.infrastructure.hibernate.HibernateConfigurationFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableTransactionManagement
public class ProductsHibernateConfiguration {
    private final HibernateConfigurationFactory factory;
    private final String CONTEXT_NAME = "products";
    @Value("${products.database.host}")
    private String DATABASE_HOST;
    @Value("${products.database.port}")
    private String DATABASE_PORT;
    @Value("${products.database.name}")
    private String DATABASE_NAME;
    @Value("${products.database.user}")
    private String DATABASE_USER;
    @Value("${products.database.password}")
    private String DATABASE_PASSWORD;
    @Value("${products.database.dialect}")
    private String DIALECT;
    @Value("${products.database.driver}")
    private String DRIVER;
    @Value("${products.database.url}")
    private String DATABASE_URL;

    public ProductsHibernateConfiguration(HibernateConfigurationFactory factory) {
        this.factory = factory;
    }

    @Bean("products-transaction_manager")
    public PlatformTransactionManager hibernateTransactionManager() throws IOException {
        return factory.hibernateTransactionManager(sessionFactory());
    }

    @Bean("products-session_factory")
    public LocalSessionFactoryBean sessionFactory() throws IOException {
        return factory.sessionFactory(CONTEXT_NAME, dataSource());
    }

    @Bean("products-data_source")
    public DataSource dataSource() throws IOException {
        return factory.dataSource(
            DATABASE_HOST,
            Integer.parseInt(DATABASE_PORT),
            DATABASE_NAME,
            DATABASE_USER,
            DATABASE_PASSWORD
        );
    }
}
