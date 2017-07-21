package com.ecommerce.restful.ws.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.ecommerce.restful.ws.dao.ProductDao;
import com.ecommerce.restful.ws.model.Product;
import com.ecommerce.restful.ws.validation.RestResourceException;

/*
 * 
 * Product service - Implementation
 * 
 */
public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;
	
	@Autowired
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Override
	public Product getProductById(long id) throws RestResourceException {
		return productDao.getProductById(id);
	}

	@Override
	public List<Product> getProductCatalog() throws RestResourceException {
		return productDao.getProductCatalog();
	}

}
