package com.ecommerce.restful.ws.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.ecommerce.restful.ws.bean.CustomerOrderBean;
import com.ecommerce.restful.ws.dao.CustomerDao;
import com.ecommerce.restful.ws.dao.CustomerOrderDao;
import com.ecommerce.restful.ws.model.Customer;
import com.ecommerce.restful.ws.model.CustomerOrder;
import com.ecommerce.restful.ws.validation.RestResourceException;

/*
 * 
 * Order service - Implementation
 * 
 */
public class CustomerOrderServiceImpl implements CustomerOrderService {

	private	CustomerOrderDao orderDao;
	private CustomerDao customerDao;
	
	@Autowired
	public CustomerOrderServiceImpl(CustomerOrderDao orderDao, CustomerDao customerDao) {
		this.orderDao = orderDao;
		this.customerDao = customerDao;
	}
	
	@Override
	public CustomerOrder createNewOrder(CustomerOrderBean orderBean) throws RestResourceException {
		Customer customer = customerDao.getCustomerById(orderBean.getCustomerId());
		
		CustomerOrder order = CustomerOrder.getBuilder(orderBean.getTotalCost(), orderBean.getNumberOfItems())
										.description(orderBean.getOrderDescription())
										.status(orderBean.getOrderStatus())
										.customer(customer)
										.build();
		
		return orderDao.createNewOrder(order);
	}

	@Override
	public CustomerOrder modifyExistingOrder(CustomerOrderBean orderBean) throws RestResourceException {
		Customer customer = customerDao.getCustomerById(orderBean.getCustomerId());
		
		CustomerOrder order = CustomerOrder.getBuilder(orderBean.getTotalCost(), orderBean.getNumberOfItems())
				.id(orderBean.getOrderId())
				.description(orderBean.getOrderDescription())
				.status(orderBean.getOrderStatus())
				.customer(customer)
				.build();
		return orderDao.modifyExistingOrder(order);
	}
	
	

	@Override
	public List<CustomerOrder> getPlacedOrder(long customerId) throws RestResourceException {
		return orderDao.getPlacedOrder(customerId);
	}

	@Override
	public CustomerOrder getOrderById(long id) throws RestResourceException {
		return orderDao.getOrderById(id);
	}

}
