package com.ecommerce.restful.ws.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import com.ecommerce.restful.ws.bean.CustomerOrderBean;
import com.ecommerce.restful.ws.dao.CustomerDao;
import com.ecommerce.restful.ws.dao.CustomerOrderDao;
import com.ecommerce.restful.ws.model.CustomerOrder;
import com.ecommerce.restful.ws.validation.RestResourceException;


public class CustomerOrderServiceImplTest {

	private static final long ID = 1;
	private static final double TOTALCOST = 250.99;
	private static final String DESCRIPTION = "Test Order Description";
	private static final int ITEMS = 4;
	private static final String STATUS = "Active";

	private CustomerOrderServiceImpl orderService;

	@Autowired
	private CustomerOrderDao orderDaoMock;

	@Autowired
	private CustomerDao customerDaoMock;
	
	@Before
	public void setUp() throws Exception {
		orderDaoMock = mock(CustomerOrderDao.class);
		customerDaoMock = mock(CustomerDao.class);

		orderService = new CustomerOrderServiceImpl(orderDaoMock, customerDaoMock);        
	}

	@Test
	public void createNewOrder_Successful() throws RestResourceException {
		
		CustomerOrderBean orderBean = new CustomerOrderBean();
		orderBean.setNumberOfItems(ITEMS);
		orderBean.setCustomerId(ID);
		orderBean.setOrderDescription(DESCRIPTION);
		orderBean.setOrderStatus(STATUS);
		orderBean.setTotalCost(TOTALCOST);
		
		orderService.createNewOrder(orderBean);
		
		ArgumentCaptor<CustomerOrder> orderArgument = ArgumentCaptor.forClass(CustomerOrder.class);
		verify(orderDaoMock, times(1)).createNewOrder(orderArgument.capture());
		verifyNoMoreInteractions(orderDaoMock);

		CustomerOrder result = orderArgument.getValue();
		Assert.assertNotNull(result);
		Assert.assertEquals(DESCRIPTION, result.getOrderDescription());
		Assert.assertEquals(ITEMS, result.getNumberOfItems());
		Assert.assertEquals(TOTALCOST, result.getTotalCost(), 0);
		Assert.assertEquals(STATUS, result.getOrderStatus());

	}

	@Test
	public void createNewOrder_SuccessfulOnlyMandatoryParam() throws RestResourceException {
		CustomerOrderBean orderBean = new CustomerOrderBean();
		orderBean.setNumberOfItems(ITEMS);
		orderBean.setTotalCost(TOTALCOST);

		orderService.createNewOrder(orderBean);

		ArgumentCaptor<CustomerOrder> orderArgument = ArgumentCaptor.forClass(CustomerOrder.class);
		verify(orderDaoMock, times(1)).createNewOrder(orderArgument.capture());
		verifyNoMoreInteractions(orderDaoMock);

		CustomerOrder result = orderArgument.getValue();
		Assert.assertNotNull(result);
		Assert.assertNull(DESCRIPTION, result.getOrderDescription());
		Assert.assertNull(STATUS, result.getOrderStatus());
		Assert.assertEquals(ITEMS, result.getNumberOfItems());
		Assert.assertEquals(TOTALCOST, result.getTotalCost(), 0);

	}

	@Test
	public void modifyExistingOrder_Successful() throws RestResourceException {
		CustomerOrderBean orderBean = new CustomerOrderBean();
		orderBean.setOrderId(ID);
		orderBean.setNumberOfItems(ITEMS);
		orderBean.setOrderDescription(DESCRIPTION);
		orderBean.setOrderStatus(STATUS);
		orderBean.setTotalCost(TOTALCOST);

		orderService.modifyExistingOrder(orderBean);

		ArgumentCaptor<CustomerOrder> orderArgument = ArgumentCaptor.forClass(CustomerOrder.class);
		verify(orderDaoMock, times(1)).modifyExistingOrder(orderArgument.capture());
		verifyNoMoreInteractions(orderDaoMock);

		CustomerOrder result = orderArgument.getValue();
		Assert.assertNotNull(result);

		Assert.assertEquals(ID, result.getOrderId());
		Assert.assertEquals(DESCRIPTION, result.getOrderDescription());
		Assert.assertEquals(ITEMS, result.getNumberOfItems());
		Assert.assertEquals(TOTALCOST, result.getTotalCost(), 0);
		Assert.assertEquals(STATUS, result.getOrderStatus());

	}

	@Test
	public void getOrderById_Successful() throws RestResourceException {
		CustomerOrder order = CustomerOrder.getBuilder(TOTALCOST, ITEMS)
				.description(DESCRIPTION)
				.status(STATUS)
				.build();

		when(orderDaoMock.getOrderById(ID)).thenReturn(order);

		CustomerOrder result = orderService.getOrderById(ID);

		Assert.assertNotNull(result);
		Assert.assertEquals(order, result);
	}

	@Test
	public void getOrderById_NoData() throws RestResourceException {

		when(orderDaoMock.getOrderById(ID)).thenReturn(null);

		CustomerOrder result = orderService.getOrderById(ID);

		Assert.assertNull(result);
	}

	@Test
	public void getOrderById_DifferentData() throws RestResourceException {
		CustomerOrder order = CustomerOrder.getBuilder(TOTALCOST, ITEMS)
				.description(DESCRIPTION)
				.status(STATUS)
				.build();

		when(orderDaoMock.getOrderById(ID)).thenReturn(CustomerOrder.getBuilder(60.0, 2).build());

		CustomerOrder result = orderService.getOrderById(ID);

		Assert.assertNotNull(result);
		Assert.assertNotEquals(result, order);
	}

	@Test
	public void getPlacedOrders_Successful() throws RestResourceException {
		List<CustomerOrder> orderList = new ArrayList<CustomerOrder>();
		orderList.add(CustomerOrder.getBuilder(25.90, 2).description("Test Order1").status("Closed").build());
		orderList.add(CustomerOrder.getBuilder(65.50, 4).description("Test Order2").status("Closed").build());
		orderList.add(CustomerOrder.getBuilder(34.40, 1).description("Test Order3").status("Closed").build());
		orderList.add(CustomerOrder.getBuilder(455.45, 6).description("Test Order4").status("Closed").build());
		orderList.add(CustomerOrder.getBuilder(27.99, 3).description("Test Order5").status("Closed").build());


		when(orderDaoMock.getPlacedOrder(1)).thenReturn(orderList);

		List<CustomerOrder> result = orderService.getPlacedOrder(1L);

		Assert.assertNotNull(result);
		Assert.assertEquals(5, result.size());
		Assert.assertEquals(orderList, result);
		for(CustomerOrder order: orderList) {
			Assert.assertNotNull(order.getOrderId());
			Assert.assertNotNull(order.getOrderDescription());
			Assert.assertNotNull(order.getOrderStatus());
			Assert.assertNotNull(order.getNumberOfItems());
			Assert.assertNotNull(order.getTotalCost());        	
		}

	}

	@Test
	public void getPlacedOrders_SuccessfulWithOnlyMandatoryParam() throws RestResourceException {
		List<CustomerOrder> orderList = new ArrayList<CustomerOrder>();
		orderList.add(CustomerOrder.getBuilder(25.90, 2).build());
		orderList.add(CustomerOrder.getBuilder(65.50, 4).build());
		orderList.add(CustomerOrder.getBuilder(34.40, 1).build());
		orderList.add(CustomerOrder.getBuilder(455.45, 6).build());
		orderList.add(CustomerOrder.getBuilder(27.99, 3).build());


		when(orderDaoMock.getPlacedOrder(1)).thenReturn(orderList);

		List<CustomerOrder> result = orderService.getPlacedOrder(1L);

		Assert.assertNotNull(result);
		Assert.assertEquals(5, result.size());
		Assert.assertEquals(orderList, result);
		for(CustomerOrder order: orderList) {
			Assert.assertNotNull(order.getOrderId());
			Assert.assertNotNull(order.getNumberOfItems());
			Assert.assertNotNull(order.getTotalCost());        	
			Assert.assertNull(order.getOrderDescription());
			Assert.assertNull(order.getOrderStatus());
		}

	}

	@Test
	public void getPlacedOrders_NoData() throws RestResourceException {
		when(orderDaoMock.getPlacedOrder(1L)).thenReturn(null);

		List<CustomerOrder> result = orderService.getPlacedOrder(1L);

		Assert.assertNull(result);

	}


}
