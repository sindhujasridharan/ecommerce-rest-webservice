package com.ecommerce.restful.ws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
/*
 * 
 * Order model
 * 
 */
@Entity
@Table(name = "customerorder")
public class CustomerOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int MAX_LENGTH_DESCRIPTION = 500;
	public static final int MIN_NO_ITEMS = 1;
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long orderId;

	@Column(name = "totalCost", nullable = false)
	private double totalCost;
	
	@Column(name = "numberOfItems", nullable = false)
	private int numberOfItems;
	
	@Column(name = "orderStatus")
	private String orderStatus;

	@Column(name = "orderDescription")
	private String orderDescription;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated")
	private Date updated;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted")
	private Date deleted;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "customerId", nullable = false)
	private Customer customer;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Product> productList;
	
	public CustomerOrder() {
		
	}
	
	public static Builder getBuilder(double cost, int count) {
        return new Builder(cost, count);
    }

	public long getOrderId() {
		return orderId;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public String getOrderDescription() {
		return orderDescription;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public Date getDeleted() {
		return deleted;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public static class Builder {

        private CustomerOrder order;

        public Builder(double cost, int count) {
        	order = new CustomerOrder();
        	order.totalCost = cost;
        	order.numberOfItems = count;        	
        }

        public CustomerOrder build() {
            return order;
        }
        
        public Builder id(long id) {
        	order.orderId = id;
        	return this;
        }
        
        public Builder cost(double cost) {
        	order.totalCost = cost;
            return this;
        }
        
        public Builder numberOfItems(int count) {
        	order.numberOfItems = count;
            return this;
        }
        
        public Builder description(String description) {
        	order.orderDescription = description;
            return this;
        }
        
        public Builder status(String status) {
            order.orderStatus = status;
            return this;
        }
        
        public Builder customer(Customer customer) {
        	order.customer = customer;
        	return this;
        }
                
                
    }


	
	
	
	
}
