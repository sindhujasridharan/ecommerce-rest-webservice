######################### Description #########################

This project contains the implementation Product and Order Resources as Restful service.

The Product Resource has the following features:

1> Get Product by Id [Get Request --> /product/{id}]

2> Get Product Catalog [Get Request --> /product/catalog]

The Order Resource has the following features:

1> Create new Order [Post Request --> /order/create]

2> Modify existing Order [Put Request --> /order/modify]

3> Get Order By Id [Get Request --> /order/{id}]
	
4> Get Placed Orders [Get Request --> /order/user/{customerId}]
	

Limitations:

1) Does not contain exhaustive features of products and orders 
2) Does not contain user authentication
3) Does not contain exhaustive Error handling
4) Does not contain exhaustive Junit testcases

######################### Pre-requisites #########################
1> Java
2> Eclipse IDE
3> Tomcat7 server
4> H2

######################### Pre-requisites #########################

	
######################### Instructions #########################

1> How to compile and run the application:
-- Git clone the project to local disk.
-- Import the cloned project as maven project to eclipse IDE
-- Run project using RunAs --> Maven clean
-- Run project using RunAs --> Maven install
-- Configure Tomcat server in eclipse
	-- Modify the server.xml file of Tomcat7 server (CATALINA_HOME%/conf/server.xml) to include H2 database in Global JNDI resource
	<!-- Add this line-->	
		<Resource auth="Container" description="Datasource to ecommerce database" driverClassName="org.h2.Driver" maxActive="20" maxIdle="10" maxWait="-1" name="testDS" password="sa" type="javax.sql.DataSource" url="jdbc:h2:mem:ecommerce" username="sa"/>
	<!-- Addline ends -->
	--Modify context.xml file of Tomcat server () to include the testDS resource DataSource
	<!-- Add this line -->
	<ResourceLink name="testDS" global="testDS" type="javax.sql.DataSource"/>
	<!-- Addline ends -->
-- Run project by clicking RunAS-->Run on Server [Choose the configured Tomcat server]
-- Using any Rest Client (e.g., postman), the product and order resource can be accessed as a rest service.
-- Example URL for the implemented features are as follows:
	
	1> Get Product by Id
	Request: http://localhost:8080/ecommerce-rest-webservice/product/3
	
	2> Get Product Catalog 
	Request: http://localhost:8080/ecommerce-rest-webservice/product/catalog

	-- Order 

	1> Create new Order [Post]
	Request: http://localhost:8080/ecommerce-rest-webservice/order/create
	{
    "orderDescription": "Test Description",
    "numberOfItems": 5,
    "totalCost": 360,
    "orderStatus": "Active",
    "customerId": 1
	}
	
	2> Modify existing Order [Put]
	Request: http://localhost:8080/ecommerce-rest-webservice/order/modify
	{
    "orderId": 5,
    "orderDescription": "Changed Description",
    "numberOfItems": 5,
    "totalCost": 360,
    "orderStatus": "Active",
    "customerId": 1
	}
	

	3> Get Order By Id
	Request: http://localhost:8080/ecommerce-rest-webservice/order/1
	
	4> Get Placed Orders
	Request: http://localhost:8080/ecommerce-rest-webservice/order/user/1
	




2> How to run the suite of automated tests:
-- Run project using RunAS-->Junit Tests (AllTests.java in src/java/test is the test suite for all test cases)

######################### End of running Instructions #########################




######################### Examples #########################
Sample Output:
Product:
1> Get Product by Id
	Request: http://localhost:8080/ecommerce-rest-webservice/product/3
	Response:
	{
    "productId": 3,
    "productName": "prod3",
    "productPrice": 12.99,
    "productDescription": "egnjrengrje3",
    "productImage": "image3.jpg",
    "productCategory": "electronics"
	}	

2> Get Product Catalog 
	Request: http://localhost:8080/ecommerce-rest-webservice/product/catalog
	Response:
	[
    {
        "productId": 1,
        "productName": "prod1",
        "productPrice": 10.99,
        "productDescription": "egnjrengrje",
        "productImage": "image1.jpg",
        "productCategory": "electronics"
    },
    {
        "productId": 2,
        "productName": "prod2",
        "productPrice": 11.99,
        "productDescription": "egnjrengrje1",
        "productImage": "image2.jpg",
        "productCategory": "ece"
    },
    {
        "productId": 3,
        "productName": "prod3",
        "productPrice": 12.99,
        "productDescription": "egnjrengrje3",
        "productImage": "image3.jpg",
        "productCategory": "electronics"
    },
    {
        "productId": 4,
        "productName": "prod4",
        "productPrice": 13.99,
        "productDescription": "egnjrengrje4",
        "productImage": "image4.jpg",
        "productCategory": "clothes"
    }
	]

The Order Resource has the following features:

1> Create new Order
	Request: http://localhost:8080/ecommerce-rest-webservice/order/create
	{
    "orderDescription": "Test Description",
    "numberOfItems": 5,
    "totalCost": 360,
    "orderStatus": "Active",
    "customerId": 1
	}
	Response:
	{
    "orderId": 5,
    "numberOfItems": 5,
    "totalCost": 360,
    "customerId": 1,
    "orderStatus": "Active",
    "products": null,
    "orderDescription": "Test Description"
	}

2> Modify existing Order
	Request: http://localhost:8080/ecommerce-rest-webservice/order/modify
	{
    "orderId": 5,
    "orderDescription": "Changed Description",
    "numberOfItems": 5,
    "totalCost": 360,
    "orderStatus": "Active",
    "customerId": 1
	}
	Response:
	{
    "orderId": 5,
    "numberOfItems": 5,
    "totalCost": 360,
    "customerId": 1,
    "orderStatus": "Active",
    "products": null,
    "orderDescription": "Changed Description"
	}


3> Get Order By Id
	Request: http://localhost:8080/ecommerce-rest-webservice/order/1
	Response:
	{
    "orderId": 1,
    "numberOfItems": 3,
    "totalCost": 340,
    "customerId": 1,
    "orderStatus": "Closed",
    "products": null,
    "orderDescription": "test1"
	}
	
4> Get Placed Orders
	Request: http://localhost:8080/ecommerce-rest-webservice/order/user/1
	Response: 
	[
    {
        "orderId": 1,
        "numberOfItems": 3,
        "totalCost": 340,
        "customerId": 1,
        "orderStatus": "Closed",
        "products": null,
        "orderDescription": "test1"
    },
    {
        "orderId": 2,
        "numberOfItems": 4,
        "totalCost": 15,
        "customerId": 1,
        "orderStatus": "Active",
        "products": null,
        "orderDescription": "test2"
    },
    {
        "orderId": 3,
        "numberOfItems": 1,
        "totalCost": 10,
        "customerId": 1,
        "orderStatus": "Active",
        "products": null,
        "orderDescription": "test3"
    }
	]



