package com.ecommerce.restful.ws.bean;

import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.ecommerce.restful.ws.model.CustomerOrder;

/*
 * 
 * Order Bean class - contains all the data for Order Bean class
 * 
 */

public class CustomerOrderBean {

	private long orderId;
	
	@Length(min = CustomerOrder.MIN_NO_ITEMS)
	private int numberOfItems;
	
	@NotEmpty
	private double totalCost;
	
	@NotEmpty
	private long customerId;
	
	@NotEmpty
	private String orderStatus;
	
	@NotEmpty
	private List<Long> products;
	
	@Length(max = CustomerOrder.MAX_LENGTH_DESCRIPTION)
	private String orderDescription;

	public CustomerOrderBean() {
		// TODO Auto-generated constructor stub
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<Long> getProducts() {
		return products;
	}

	public void setProducts(List<Long> products) {
		this.products = products;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	@Override
	public String toString() {
		return "CustomerOrderBean [orderId=" + orderId + ", numberOfItems=" + numberOfItems + ", totalCost=" + totalCost
				+ ", customerId=" + customerId + ", orderStatus=" + orderStatus + ", products=" + products
				+ ", orderDescription=" + orderDescription + "]";
	}
	
	
}
