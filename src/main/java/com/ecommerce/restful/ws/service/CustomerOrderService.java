package com.ecommerce.restful.ws.service;

import java.util.List;

import com.ecommerce.restful.ws.bean.CustomerOrderBean;
import com.ecommerce.restful.ws.model.CustomerOrder;
import com.ecommerce.restful.ws.validation.RestResourceException;

/*
 * 
 * Order service - Interface
 * 
 */

public interface CustomerOrderService {
	public CustomerOrder createNewOrder(CustomerOrderBean orderBean) throws RestResourceException;
	
	public CustomerOrder modifyExistingOrder(CustomerOrderBean orderBean) throws RestResourceException;
	
	public List<CustomerOrder> getPlacedOrder(long customerId) throws RestResourceException;
	
	public CustomerOrder getOrderById(long id) throws RestResourceException;
}
