package com.ecommerce.restful.ws.model;

import org.junit.Assert;
import org.junit.Test;

public class ProductTest {
	
	private static final String NAME = "Test Product";
    private static final String DESCRIPTION = "Test Description";
    private static final double PRICE = 60.0;
    private static final String IMAGE = "TestImage.jpg";
    private static final String CATEGORY = "Test";
    
    
    @Test
    public void updateMandatoryFields() {
    	Product product = Product.getBuilder(NAME, PRICE).build();
    	
    	Assert.assertNotNull(product.getProductId());
    	Assert.assertNotNull(product.getProductName());
    	Assert.assertNotNull(product.getProductPrice());
    	Assert.assertNull(product.getProductDescription());
    	Assert.assertNull(product.getProductCategory());
    	Assert.assertNull(product.getProductImage());    	
    }
    
    @Test
    public void updateAllFields() {
    	Product product = Product.getBuilder(NAME, PRICE)
    							.description(DESCRIPTION)
    							.category(CATEGORY)
    							.image(IMAGE)
    							.build();
    	
    	Assert.assertNotNull(product.getProductId());
    	Assert.assertNotNull(product.getProductName());
    	Assert.assertNotNull(product.getProductPrice());
    	Assert.assertNotNull(product.getProductDescription());
    	Assert.assertNotNull(product.getProductCategory());
    	Assert.assertNotNull(product.getProductImage());
    	
    	
    }
    

}
