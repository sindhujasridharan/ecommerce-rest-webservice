package com.ecommerce.restful.ws.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import com.ecommerce.restful.ws.model.Product;
import com.ecommerce.restful.ws.validation.RestResourceException;

/*
 * 
 * Product Data access object - Implementation
 * 
 */
public class ProductDaoImpl implements ProductDao {

	@Autowired 
	SessionFactory sessionFactory;
	
	Session session = null;
	Transaction transaction = null;
	
	@Override
	public Product getProductById(long id) throws RestResourceException {
		session = sessionFactory.openSession();
		Product product = new Product();
		product = (Product) session.get(Product.class, id);
		transaction = session.getTransaction();
		session.beginTransaction();
		transaction.commit();
			
		return product;
	}
		
	
	@Override
	public List<Product> getProductCatalog() throws RestResourceException {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Product> productList = session.createCriteria(Product.class).list();
		transaction.commit();
		session.close();
		return productList;
	}
	
	
	
}
