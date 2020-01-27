CREATE DATABASE contena;

USE contena;

CREATE TABLE post
(
    id VARCHAR(100) NOT NULL PRIMARY KEY,
    shop_name VARCHAR(100) NOT NULL,
    show_logo_url VARCHAR(100) NOT NULL,
    upload_date VARCHAR(100) NOT NULL
);

CREATE TABLE item
(
    shop_name VARCHAR(100) NOT NULL,
    product_name VARCHAR (100) NOT NULL,
    brand VARCHAR (100) NOT NULL,
    image_url VARCHAR (100) NOT NULL,
    page_url VARCHAR (100) NOT NULL,
    price VARCHAR (100) NOT NULL,
    post_id VARCHAR (100) NOT NULL,
    PRIMARY KEY(shop_name,product_name),
    FOREIGN KEY (post_id) REFERENCES post (id)
);