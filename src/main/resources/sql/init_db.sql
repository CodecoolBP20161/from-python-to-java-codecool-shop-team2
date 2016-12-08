DROP TABLE IF EXISTS supplier, productCategory, product, customer;

CREATE TABLE supplier
(
id SERIAL PRIMARY KEY ,
name VARCHAR(40),
description TEXT
);

CREATE TABLE productCategory
(
id SERIAL PRIMARY KEY,
name VARCHAR(40),
department VARCHAR(40),
description TEXT
);

CREATE TABLE product
(
id SERIAL PRIMARY KEY,
name VARCHAR(100),
description TEXT,
defaultPrice FLOAT ,
currencyString VARCHAR(100),
productCategory INTEGER REFERENCES productCategory(id),
supplier INTEGER REFERENCES supplier(id)
);

CREATE TABLE customer
(
id SERIAL PRIMARY KEY,
name VARCHAR(40),
email VARCHAR(40),
hashedPW TEXT,
shippingcountry TEXT DEFAULT NULL,
shippingcity TEXT DEFAULT NULL,
shippingzipcode INTEGER DEFAULT NULL,
shippingaddress TEXT DEFAULT NULL,
billingcountry TEXT DEFAULT NULL,
billingcity TEXT DEFAULT NULL,
billingzipcode INTEGER DEFAULT NULL,
billingaddress TEXT DEFAULT NULL
);