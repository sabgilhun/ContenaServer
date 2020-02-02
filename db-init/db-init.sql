CREATE DATABASE contena;

USE contena;

CREATE TABLE shop
(
    shop_name VARCHAR (100) NOT NULL PRIMARY KEY,
    shop_logo_url VARCHAR (100) NOT NULL,
    subscriber_count INT (11) NOT NULL DEFAULT 0
);

CREATE TABLE post
(
    id INT (11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    upload_date DATETIME NOT NULL,
    shop_name VARCHAR (100) NOT NULL,
    FOREIGN KEY (shop_name) REFERENCES shop (shop_name)
);

CREATE TABLE item
(
    id INT (11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR (100) NOT NULL,
    brand VARCHAR (100) NOT NULL,
    image_url VARCHAR (100) NOT NULL,
    page_url VARCHAR (100) NOT NULL,
    price VARCHAR (100) NOT NULL,
    post_id INT (11) NOT NULL,
    shop_name VARCHAR (100) NOT NULL,
    FOREIGN KEY (post_id) REFERENCES post (id),
    FOREIGN KEY (shop_name) REFERENCES shop (shop_name)
);

CREATE TABLE subscription
(
    id INT (11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR (100) NOT NULL,
    shop_name VARCHAR (100) NOT NULL,
    FOREIGN KEY (shop_name) REFERENCES shop (shop_name)
);