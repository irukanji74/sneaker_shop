SET FOREIGN_KEY_CHECKS=0;
delete from customer_authorities;
delete from customers;
delete from shopping_carts;
delete from items;

insert into customers(id, email, name, password, enabled, cart_id) values ('1','nuser@mail.ua','huilo', 'password', 1, '10');

insert into customer_authorities(customer_id, role) values ('1', 'ROLE_USER');

insert into shopping_carts(id) values ('10');

insert into items(id, name, cart_id) values('1', 'huilo', '10');
