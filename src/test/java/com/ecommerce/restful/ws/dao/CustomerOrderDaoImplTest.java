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

import com.ecommerce.restful.ws.model.CustomerOrder;
import com.ecommerce.restful.ws.validation.RestResourceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testDataSourceContext.xml", "classpath:applicationContext.xml"})
public class CustomerOrderDaoImplTest {
	
	private static final double TOTALCOST = 250.99;
	private static final String DESCRIPTION = "Test Order Description";
	private static final int ITEMS = 4;
	private static final String STATUS = "Active";
	
	@Autowired
	CustomerOrderDao orderDao;
	
	@Autowired
	CustomerDao customerDao;
	
	@Before
	public void setUp() throws Exception {
		
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testCreateNewOrder_Successful() throws RestResourceException {
		
		CustomerOrder order = CustomerOrder.getBuilder(TOTALCOST, ITEMS)
				.description(DESCRIPTION)
				.status(STATUS)
				.customer(customerDao.getCustomerById(1))
				.build();
		
		CustomerOrder result = orderDao.createNewOrder(order);
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getOrderId());
		Assert.assertNotNull(result.getOrderDescription());
		Assert.assertNotNull(result.getOrderStatus());
		Assert.assertNotNull(result.getNumberOfItems());
		Assert.assertNotNull(result.getTotalCost());
		
	}
	
	@Test
	public void testModifyExistingOrder_Successful() throws RestResourceException {
		CustomerOrder order = CustomerOrder.getBuilder(TOTALCOST, ITEMS)
				.id(1)
				.description(DESCRIPTION)
				.status(STATUS)
				.customer(customerDao.getCustomerById(1))
				.build();
		
		CustomerOrder result = orderDao.modifyExistingOrder(order);
		
		Assert.assertNotNull(result);
		Assert.assertNotNull(result.getOrderId());
		Assert.assertNotNull(result.getOrderDescription());
		Assert.assertNotNull(result.getOrderStatus());
		Assert.assertNotNull(result.getNumberOfItems());
		Assert.assertNotNull(result.getTotalCost());   
		Assert.assertEquals(DESCRIPTION, result.getOrderDescription());
		Assert.assertEquals(ITEMS, result.getNumberOfItems());
		Assert.assertEquals(TOTALCOST, result.getTotalCost(), 0);
		Assert.assertEquals(STATUS, result.getOrderStatus());
	}
	
	@Test
	public void testGetOrderById_Successful() throws RestResourceException {
			CustomerOrder result = orderDao.getOrderById(1);
		
			Assert.assertNotNull(result);
			Assert.assertNotNull(result.getOrderId());
			Assert.assertNotNull(result.getOrderDescription());
			Assert.assertNotNull(result.getOrderStatus());
			Assert.assertNotNull(result.getNumberOfItems());
			Assert.assertNotNull(result.getTotalCost());   
				
	}
	
	@Test
	public void testGetPlacedOrders_Successful() throws RestResourceException {
			List<CustomerOrder> orderList = orderDao.getPlacedOrder(1);
			
			Assert.assertNotNull(orderList);
			Assert.assertEquals(3, orderList.size());	
			
				
	}
}
