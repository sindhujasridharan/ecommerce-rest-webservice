package com.ecommerce.restful.ws.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.restful.ws.dao.ProductDao;
import com.ecommerce.restful.ws.model.Product;
import com.ecommerce.restful.ws.validation.RestResourceException;


public class ProductServiceImplTest {
	
	private static final long ID = 1;
	private static final String NAME = "Test Product";
    private static final String DESCRIPTION = "Test Description";
    private static final double PRICE = 60.0;
    private static final String IMAGE = "TestImage.jpg";
    private static final String CATEGORY = "Test";
    
    private ProductServiceImpl productService;
    
    @Autowired
    private ProductDao productDaoMock;
    
    @Before
    public void setUp() {
        productDaoMock = mock(ProductDao.class);

        productService = new ProductServiceImpl(productDaoMock);
    }
    
    @Test
    public void getProductById_Successful() throws RestResourceException {
    	Product product = Product.getBuilder(NAME, PRICE)
				.description(DESCRIPTION)
				.category(CATEGORY)
				.image(IMAGE)
				.build();

        when(productDaoMock.getProductById(ID)).thenReturn(product);

        Product result = productService.getProductById(ID);
        
        Assert.assertNotNull(result);
        Assert.assertEquals(product, result);
    }
    
    @Test
    public void getProductById_NoData() throws RestResourceException {
    	
        when(productDaoMock.getProductById(ID)).thenReturn(null);

        Product result = productService.getProductById(ID);
        
        Assert.assertNull(result);
    }
    
    @Test
    public void getProductById_DifferentData() throws RestResourceException {
    	Product product = Product.getBuilder(NAME, PRICE)
				.description(DESCRIPTION)
				.category(CATEGORY)
				.image(IMAGE)
				.build();
    	
        when(productDaoMock.getProductById(ID)).thenReturn(Product.getBuilder("New Prod", 60.0).build());

        Product result = productService.getProductById(ID);
        
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result, product);
    }
    
    @Test
    public void getProductCatalog_Successful() throws RestResourceException {
    	List<Product> prodList = new ArrayList<Product>();
    	prodList.add(Product.getBuilder("Test Prod1", 50.0).description("Prod1 for testing").category("C1").image("Img1.jpg").build());
    	prodList.add(Product.getBuilder("Test Prod2", 15.99).description("Prod2 for testing").category("C1").image("Img2.jpg").build());
    	prodList.add(Product.getBuilder("Test Prod3", 25.09).description("Prod3 for testing").category("C2").image("Img3.jpg").build());
    	prodList.add(Product.getBuilder("Test Prod4", 11.90).description("Prod4 for testing").category("C2").image("Img4.jpg").build());
    	prodList.add(Product.getBuilder("Test Prod5", 9.80).description("Prod5 for testing").category("C3").image("Img5.jpg").build());
    	prodList.add(Product.getBuilder("Test Prod6", 8.75).description("Prod6 for testing").category("C4").image("Img6.jpg").build());

        when(productDaoMock.getProductCatalog()).thenReturn(prodList);

        List<Product> result = productService.getProductCatalog();
        
        Assert.assertNotNull(result);
        Assert.assertEquals(6, result.size());
        Assert.assertEquals(prodList, result);
        for(Product product: prodList) {
        	Assert.assertNotNull(product.getProductId());
        	Assert.assertNotNull(product.getProductName());
        	Assert.assertNotNull(product.getProductPrice());
        	Assert.assertNotNull(product.getProductDescription());
        	Assert.assertNotNull(product.getProductCategory());
        	Assert.assertNotNull(product.getProductImage());
        }
        
    }
    
    @Test
    public void getProductCatalog_SuccessfulWithOnlyMandatoryParam() throws RestResourceException {
    	List<Product> prodList = new ArrayList<Product>();
    	prodList.add(Product.getBuilder("Test Prod1", 50.0).build());
    	prodList.add(Product.getBuilder("Test Prod2", 15.99).build());
    	prodList.add(Product.getBuilder("Test Prod3", 25.09).build());
    	prodList.add(Product.getBuilder("Test Prod4", 11.90).build());
    	prodList.add(Product.getBuilder("Test Prod5", 9.80).build());
    	prodList.add(Product.getBuilder("Test Prod6", 8.75).build());

        when(productDaoMock.getProductCatalog()).thenReturn(prodList);

        List<Product> result = productService.getProductCatalog();
        
        Assert.assertNotNull(result);
        Assert.assertEquals(6, result.size());
        Assert.assertEquals(prodList, result);
        for(Product product: prodList) {
        	Assert.assertNotNull(product.getProductId());
        	Assert.assertNotNull(product.getProductName());
        	Assert.assertNotNull(product.getProductPrice());
        	Assert.assertNull(product.getProductDescription());
        	Assert.assertNull(product.getProductCategory());
        	Assert.assertNull(product.getProductImage());
        }
        
    }
    
    @Test
    public void getProductCatalog_NoData() throws RestResourceException {
    	 when(productDaoMock.getProductCatalog()).thenReturn(null);

        List<Product> result = productService.getProductCatalog();
        
        Assert.assertNull(result);
           
    }
    

}
