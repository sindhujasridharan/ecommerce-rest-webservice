package com.ecommerce.restful.ws.dao;

import java.util.List;

import com.ecommerce.restful.ws.model.CustomerOrder;
import com.ecommerce.restful.ws.validation.RestResourceException;

/*
 * 
 * Order Data access object - Interface
 * 
 */

public interface CustomerOrderDao {
	public CustomerOrder createNewOrder(CustomerOrder order) throws RestResourceException;
	
	public CustomerOrder modifyExistingOrder(CustomerOrder order) throws RestResourceException;
	
	public List<CustomerOrder> getPlacedOrder(long customerId) throws RestResourceException;
	
	public CustomerOrder getOrderById(long id) throws RestResourceException;
	
}
