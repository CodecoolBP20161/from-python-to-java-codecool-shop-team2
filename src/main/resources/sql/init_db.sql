DROP TABLE IF EXISTS supplier, productCategory, product;

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
description TEXT,
department VARCHAR(40)
);

CREATE TABLE product
(
id SERIAL PRIMARY KEY,
name VARCHAR(40),
description TEXT,
defaultPrice FLOAT ,
currencyString VARCHAR(40),
productCategory INTEGER REFERENCES productCategory(id),
supplier INTEGER REFERENCES supplier(id)
);