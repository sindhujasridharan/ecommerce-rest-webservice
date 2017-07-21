package com.ecommerce.restful.ws.dao;

import com.ecommerce.restful.ws.model.Customer;
import com.ecommerce.restful.ws.validation.RestResourceException;

/*
 * 
 * Customer Data access object - Interface
 * 
 */

public interface CustomerDao {
	
	public Customer getCustomerById(long id) throws RestResourceException;

}
