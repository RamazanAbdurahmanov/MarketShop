insert into users
(username,enabled,password)
values
('admin',1,'{noop}admin'),
('cashier',1,'{noop}12');

insert into authorities
(username,authority)
values
('admin','ADMIN'),
('cashier','CASHIER');

