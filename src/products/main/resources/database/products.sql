CREATE TABLE IF NOT EXISTS PRICE (
    id       BIGINT     NOT NULL PRIMARY KEY,
    ammount     VARCHAR(255) NOT NULL,
    curr VARCHAR(255) NOT NULL,
    priceStartDate TIMESTAMP NOT NULL,
    priceEndDate TIMESTAMP NOT NULL,
    priority VARCHAR(255) NOT NULL,
    productId       CHAR(36)     NOT NULL,
    brandId       CHAR(36)     NOT NULL
);
