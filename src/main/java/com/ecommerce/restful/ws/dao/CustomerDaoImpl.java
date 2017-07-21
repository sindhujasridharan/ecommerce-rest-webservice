package com.ecommerce.restful.ws.dao;

/*
 * 
 * Customer Data access object - Implementation
 * 
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import com.ecommerce.restful.ws.model.Customer;
import com.ecommerce.restful.ws.validation.RestResourceException;

public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction transaction = null;
	
	@Override
	public Customer getCustomerById(long id) throws RestResourceException {
		session = sessionFactory.openSession();
		Customer customer = (Customer) session.get(Customer.class, new Long(id));
		transaction = session.getTransaction();
		session.beginTransaction();
		transaction.commit();
		
		return customer;
	}

}
