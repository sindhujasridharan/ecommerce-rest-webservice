package com.ecommerce.restful.ws.dao;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ecommerce.restful.ws.model.*;
import com.ecommerce.restful.ws.validation.RestResourceException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testDataSourceContext.xml", "classpath:applicationContext.xml"})
public class ProductDaoImplTest {

	@Autowired
	ProductDao productDao;

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testGetProductById_Successful() throws RestResourceException{
		Product product = productDao.getProductById(1);

		Assert.assertNotNull(product);
		Assert.assertNotNull(product.getProductId());
		Assert.assertNotNull(product.getProductName());
		Assert.assertNotNull(product.getProductDescription());
		Assert.assertNotNull(product.getProductPrice());
		Assert.assertNotNull(product.getProductImage());
		Assert.assertNotNull(product.getProductImage());


	}

	@Test
	public void testGetProductById_NotFound() throws RestResourceException {

		Product product = productDao.getProductById(10);

		Assert.assertNull(product);	
	}

	@Test
	public void testGetProductCatalog_Successful() throws RestResourceException{

		List<Product> productList = productDao.getProductCatalog();

		Assert.assertNotNull(productList);
		Assert.assertEquals(4, productList.size());	


	}
}
