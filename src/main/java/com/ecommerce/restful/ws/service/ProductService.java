package com.ecommerce.restful.ws.service;

import java.util.List;

import com.ecommerce.restful.ws.model.Product;
import com.ecommerce.restful.ws.validation.RestResourceException;

/*
 * 
 * Product service - Interface
 * 
 */

public interface ProductService {
	public Product getProductById(long id) throws RestResourceException;
	
	public List<Product> getProductCatalog() throws RestResourceException;
}
