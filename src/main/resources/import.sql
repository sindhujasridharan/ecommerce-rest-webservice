INSERT INTO PRODUCT (productName, productprice,productdescription, productCategory, productImage) VALUES ('prod1', 10.99, 'egnjrengrje', 'electronics', 'image1.jpg');
INSERT INTO PRODUCT (productName, productprice,productdescription, productCategory, productImage) VALUES ('prod2', 11.99, 'egnjrengrje1', 'ece', 'image2.jpg');
INSERT INTO PRODUCT (productName, productprice,productdescription, productCategory, productImage) VALUES ('prod3', 12.99, 'egnjrengrje3', 'electronics', 'image3.jpg');
INSERT INTO PRODUCT (productName, productprice,productdescription, productCategory, productImage) VALUES ('prod4', 13.99, 'egnjrengrje4', 'clothes', 'image4.jpg');

INSERT INTO CUSTOMER (customerId, customerFirstName, customerLastName, customerEmail, customerPhoneNo) VALUES (1, 'Test', 'User1', 'testuser1@gmail.com', '(111) 111-1111');
INSERT INTO CUSTOMER (customerId, customerFirstName, customerLastName, customerEmail, customerPhoneNo) VALUES (2, 'Test', 'User2', 'testuser2@gmail.com', '(111) 111-2222');
INSERT INTO CUSTOMER (customerId, customerFirstName, customerLastName, customerEmail, customerPhoneNo) VALUES (3, 'Test', 'User3', 'testuser3@gmail.com', '(111) 111-3333');
INSERT INTO CUSTOMER (customerId, customerFirstName, customerLastName, customerEmail, customerPhoneNo) VALUES (4, 'Test', 'User4', 'testuser4@gmail.com', '(111) 111-4444');
INSERT INTO CUSTOMER (customerId, customerFirstName, customerLastName, customerEmail, customerPhoneNo) VALUES (5, 'Test', 'User5', 'testuser5@gmail.com', '(111) 111-5555');


INSERT INTO CUSTOMERORDER (orderId, orderDescription, numberOfItems, totalCost, orderStatus, customerId) VALUES (1, 'test1', 3, 340, 'Closed',1);
INSERT INTO CUSTOMERORDER (orderId, orderDescription, numberOfItems, totalCost, orderStatus, customerId) VALUES (2, 'test2', 4, 15, 'Active',1);
INSERT INTO CUSTOMERORDER (orderId, orderDescription, numberOfItems, totalCost, orderStatus, customerId) VALUES (3, 'test3', 1, 10, 'Active',1);
INSERT INTO CUSTOMERORDER (orderId, orderDescription, numberOfItems, totalCost, orderStatus, customerId) VALUES (4, 'test4', 6, 170, 'Closed',2);

