package com.rallibau.products.shared.infrastructure.persistence;

import com.rallibau.products.prices.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class DataLoader implements ApplicationRunner {

    private final PriceRepository priceRepository;
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");

    @Autowired
    public DataLoader(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        priceRepository.save(
            new Price(
                new PriceId("1"),
                new BrandId("1"),
                new PriceStartDate(DATE_FORMAT.parse("2020-06-14-00.00.00")),
                new PriceEndDate(DATE_FORMAT.parse("2020-12-31-23.59.59")),
                new PricePriority(0),
                new PriceAmmount("35.50"),
                new PriceCurr("EUR"),
                new ProductId("35455")
            ));

        priceRepository.save(
            new Price(
                new PriceId("2"),
                new BrandId("1"),
                new PriceStartDate(DATE_FORMAT.parse("2020-06-14-15.00.00")),
                new PriceEndDate(DATE_FORMAT.parse("2020-06-14-18.30.00")),
                new PricePriority(1),
                new PriceAmmount("25,45"),
                new PriceCurr("EUR"),
                new ProductId("35455")
            ));

        priceRepository.save(
            new Price(
                new PriceId("3"),
                new BrandId("1"),
                new PriceStartDate(DATE_FORMAT.parse("2020-06-15-00.00.00")),
                new PriceEndDate(DATE_FORMAT.parse("2020-06-15-11.00.00")),
                new PricePriority(1),
                new PriceAmmount("30,50"),
                new PriceCurr("EUR"),
                new ProductId("35455")
            ));

        priceRepository.save(
            new Price(
                new PriceId("4"),
                new BrandId("1"),
                new PriceStartDate(DATE_FORMAT.parse("2020-06-15-16.00.00")),
                new PriceEndDate(DATE_FORMAT.parse("2020-12-31-23.59.59")),
                new PricePriority(1),
                new PriceAmmount("38,95"),
                new PriceCurr("EUR"),
                new ProductId("35455")
            ));
    }
}
