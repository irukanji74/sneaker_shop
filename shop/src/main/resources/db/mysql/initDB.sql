drop table if exists authorities;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS shopping_carts;
DROP TABLE IF EXISTS items;

CREATE TABLE customers (
    id         INTEGER primary key,
    email      varchar_ignorecase(50) not null,
    name       varchar_ignorecase(50) ,
    lastname   varchar_ignorecase(50) ,
    city       varchar_ignorecase(50) ,
    telephone  varchar_ignorecase(20),
    cart_id    integer(10) UNSIGNED NOT NULL,
    address    varchar_ignorecase(50) ,
    password   varchar_ignorecase(50) not null,
    registered TIMESTAMP DEFAULT now(),
    enabled    boolean default 1,
    FOREIGN KEY (cart_id) REFERENCES shopping_carts(id)
) engine = InnoDb;

CREATE UNIQUE INDEX customers_unique_email_idx on customers (email);

CREATE TABLE authorities (
    auth_email varchar(50) not null,
    authority varchar(50) not null,
    FOREIGN KEY  (auth_email) references customers (email),
    unique index unique_email_idx (auth_email, authority)
) engine = InnoDb;

CREATE TABLE shopping_carts(
     id         INTEGER primary key,
     item_id    Integer(10),
     FOREIGN KEY (item_id) references items(id)
     
)engine = InnoDb;

CREATE TABLE items(
     id                Integer primary key,
     total_quantity    Integer(10),
     item_price        varchar_ignorecase(50),
     sex               varchar_ignorecase(50),
     clothes_type      varchar_ignorecase(50),
     item_size         varchar_ignorecase(50),
     desc              varchar_ignorecase,
     vendor_code       varchar_ignorecase(50)
)engine = InnoDb;


