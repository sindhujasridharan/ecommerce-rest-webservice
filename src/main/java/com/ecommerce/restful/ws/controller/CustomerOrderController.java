package com.ecommerce.restful.ws.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.restful.ws.bean.CustomerOrderBean;
import com.ecommerce.restful.ws.model.CustomerOrder;
import com.ecommerce.restful.ws.service.CustomerOrderService;

/*
 * 
 * Order Controller class - Order resource for REST service
 * 
 */

@RestController
@RequestMapping(value = "/order")
public class CustomerOrderController {
	
	@Autowired
	CustomerOrderService orderService;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public CustomerOrderBean createOrder(@RequestBody CustomerOrderBean orderBean) throws Exception {
		
		CustomerOrder order = orderService.createNewOrder(orderBean);
		
		return createBean(order);
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public CustomerOrderBean modifyOrder (@RequestBody CustomerOrderBean orderBean) throws Exception{
		CustomerOrder order = orderService.modifyExistingOrder(orderBean);
		return createBean(order);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public CustomerOrderBean getOrderById(@PathVariable("id") long id) throws Exception {
		CustomerOrder order = orderService.getOrderById(id);
		return createBean(order);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public List<CustomerOrderBean> getPlacedOrders(@PathVariable("id") long customerId) throws Exception {
		List<CustomerOrder> orderList = orderService.getPlacedOrder(customerId); 
		return createBeanList(orderList);
	}

	private CustomerOrderBean createBean(CustomerOrder order) {
		CustomerOrderBean bean = new CustomerOrderBean();
		bean.setOrderId(order.getOrderId());
		bean.setNumberOfItems(order.getNumberOfItems());
		bean.setOrderDescription(order.getOrderDescription());
		bean.setCustomerId(order.getCustomer().getCustomerId());
		bean.setOrderStatus(order.getOrderStatus());
		bean.setTotalCost(order.getTotalCost());
		
		
		return bean;
	}
	
	private List<CustomerOrderBean> createBeanList(List<CustomerOrder> orderList) {
		List<CustomerOrderBean> beanList = new ArrayList<CustomerOrderBean>();
		for(CustomerOrder order: orderList) {
			beanList.add(createBean(order));
		}
		
		return beanList;
			
	}
}
