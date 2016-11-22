DROP TABLE IF EXISTS supplier;

CREATE TABLE supplier
(
id SERIAL PRIMARY KEY ,
name VARCHAR(40),
description TEXT

);

DROP TABLE IF EXISTS productCategory;

CREATE TABLE productCategory
(
id INTEGER PRIMARY KEY,
name VARCHAR(40),
description VARCHAR(10),
department VARCHAR(40)

);

DROP TABLE IF EXISTS product;

CREATE TABLE product
(
id INTEGER PRIMARY KEY,
name VARCHAR(40),
description VARCHAR(10),
defaultPrice FLOAT,
currencyString VARCHAR(40),
productCategory INTEGER REFERENCES productCategory(id),
supplier INTEGER REFERENCES supplier(id)

);






