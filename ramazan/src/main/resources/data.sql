INSERT INTO users
(username, enabled, password) 
VALUES
('admin', 1, '{noop}admin'),
('cashier', 1, '{noop}12');

INSERT INTO  authorities 
( authority, username) 
VALUES
('ADMIN', 'admin'),
('CASHIER', 'cashier');

INSERT INTO product 
(name, barcode, price, cost, description, register_date, update_date, quantity, percent)
VALUES
('Apple iPhone 13', '111', 1200.0, 800.0, 'The latest iPhone model', '2022-10-01', '2022-10-02', 100, 20.0),
('Samsung Galaxy S22', '222', 1100.0, 700.0, 'The latest Samsung model', '2022-10-01', '2022-10-03', 50, 18.0),
('Sony PlayStation 5', '333', 500.0, 350.0, 'The latest PlayStation console', '2022-10-01', '2022-10-04', 200, 25.0);
