package com.ecommerce.restful.ws.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import com.ecommerce.restful.ws.model.CustomerOrder;
import com.ecommerce.restful.ws.validation.RestResourceException;

/*
 * 
 * Order Data access object - Implementation
 * 
 */

public class CustomerOrderDaoImpl implements CustomerOrderDao {

	@Autowired
	SessionFactory sessionFactory;

	Session session = null;
	Transaction transaction = null;
	
	@Override
	public CustomerOrder createNewOrder(CustomerOrder order) throws RestResourceException {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		session.save(order);
		transaction.commit();
		session.close();

		return order;
	}

	@Override
	public CustomerOrder modifyExistingOrder(CustomerOrder order) throws RestResourceException {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		session.update(order);
		transaction.commit();
		session.close();

		return order;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerOrder> getPlacedOrder(long customerId) throws RestResourceException {
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();		
		List<CustomerOrder> orderList = session.createQuery("from CustomerOrder where customerId ="+ customerId).list();
		transaction.commit();
		session.close();
		return orderList;
	}

	@Override
	public CustomerOrder getOrderById(long id) throws RestResourceException {
		session = sessionFactory.openSession();
		CustomerOrder order = (CustomerOrder) session.get(CustomerOrder.class, new Long(id));
		transaction = session.getTransaction();
		session.beginTransaction();
		transaction.commit();
		
		return order;
	}

}
