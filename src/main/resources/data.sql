DROP TABLE IF EXISTS  product;

CREATE TABLE product(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL,
    description VARCHAR(500) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);

INSERT INTO product(name, description, price) VALUES
('product1', 'First Product', 10.50),
('product2', 'Second Product', 5.60),
('product3', 'Third Product', 2.30);