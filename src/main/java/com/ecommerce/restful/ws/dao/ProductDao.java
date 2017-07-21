package com.ecommerce.restful.ws.dao;

import java.util.List;

import com.ecommerce.restful.ws.model.Product;
import com.ecommerce.restful.ws.validation.RestResourceException;

/*
 * 
 * Product Data access object - Interface
 * 
 */
public interface ProductDao {

	public Product getProductById(long id) throws RestResourceException;
	
	public List<Product> getProductCatalog() throws RestResourceException;
	
}
