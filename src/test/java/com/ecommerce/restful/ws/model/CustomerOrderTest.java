package com.ecommerce.restful.ws.model;

import org.junit.Assert;
import org.junit.Test;

public class CustomerOrderTest {
	
	private static final double TOTALCOST = 250.99;
    private static final String DESCRIPTION = "Test Order Description";
    private static final int ITEMS = 4;
    private static final String STATUS = "Active";
    
    @Test
    public void updateMandatoryFields() {
    	CustomerOrder order = CustomerOrder.getBuilder(TOTALCOST, ITEMS).build();
    	
    	Assert.assertNotNull(order.getOrderId());
    	Assert.assertNotNull(order.getTotalCost());
    	Assert.assertNotNull(order.getNumberOfItems());
    	Assert.assertNull(order.getOrderDescription());
    	Assert.assertNull(order.getOrderStatus());
    	    	
    }
    
    @Test
    public void updateAllFields() {
    	CustomerOrder order = CustomerOrder.getBuilder(TOTALCOST, ITEMS)
    									.description(DESCRIPTION)
    									.status(STATUS)
    									.build();
    	
    	Assert.assertNotNull(order.getOrderId());
    	Assert.assertNotNull(order.getTotalCost());
    	Assert.assertNotNull(order.getNumberOfItems());
    	Assert.assertNotNull(order.getOrderDescription());
    	Assert.assertNotNull(order.getOrderStatus());
    	
    	
    }
}
