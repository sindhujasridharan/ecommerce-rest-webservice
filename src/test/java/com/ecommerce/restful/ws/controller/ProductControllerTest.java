package com.ecommerce.restful.ws.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.ecommerce.restful.ws.model.Product;
import com.ecommerce.restful.ws.service.ProductService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testDataSourceContext.xml", "classpath:testApplicationContext.xml"})
@WebAppConfiguration
public class ProductControllerTest {
	
	private static final long ID = 1;
	private static final String NAME = "Test Product";
    private static final String DESCRIPTION = "Test Description";
    private static final double PRICE = 60.0;
    private static final String IMAGE = "TestImage.jpg";
    private static final String CATEGORY = "Test";
    public static final MediaType MEDIATYPE = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
    private MockMvc mockMvc;

    @Autowired
    private ProductService productServiceMock;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        Mockito.reset(productServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    @Test
    public void getProductById_Successful() throws Exception {
    	Product product = Product.getBuilder(NAME, PRICE)
    			.id(1L)
				.description(DESCRIPTION)
				.category(CATEGORY)
				.image(IMAGE)
				.build();

        when(productServiceMock.getProductById(ID)).thenReturn(product);

        mockMvc.perform(get("/product/{id}", 1L))
        		.andExpect(status().isOk())
                .andExpect(content().contentType(MEDIATYPE))
                .andExpect(jsonPath("$.productId").value(1))
                .andExpect(jsonPath("$.productName").value(NAME))
                .andExpect(jsonPath("$.productPrice").value(PRICE))
                .andExpect(jsonPath("$.productDescription").value(DESCRIPTION))
                .andExpect(jsonPath("$.productImage").value(IMAGE))
                .andExpect(jsonPath("$.productCategory").value(CATEGORY));

        verify(productServiceMock, times(1)).getProductById(ID);
        verifyNoMoreInteractions(productServiceMock);
    }
    
    @Test
    public void getProductCatalog_Successful() throws Exception {
    	List<Product> prodList = new ArrayList<Product>();
    	prodList.add(Product.getBuilder("Test Prod1", 50.0).id(1).description("Prod1 for testing").category("C1").image("Img1.jpg").build());
    	prodList.add(Product.getBuilder("Test Prod2", 15.99).id(2).description("Prod2 for testing").category("C1").image("Img2.jpg").build());
    	prodList.add(Product.getBuilder("Test Prod3", 25.09).id(3).description("Prod3 for testing").category("C2").image("Img3.jpg").build());
    	prodList.add(Product.getBuilder("Test Prod4", 11.90).id(4).description("Prod4 for testing").category("C2").image("Img4.jpg").build());
    	prodList.add(Product.getBuilder("Test Prod5", 9.80).id(5).description("Prod5 for testing").category("C3").image("Img5.jpg").build());
    	prodList.add(Product.getBuilder("Test Prod6", 8.75).id(6).description("Prod6 for testing").category("C4").image("Img6.jpg").build());


        when(productServiceMock.getProductCatalog()).thenReturn(prodList);

        mockMvc.perform(get("/product/catalog"))
        		.andExpect(status().isOk())
                .andExpect(content().contentType(MEDIATYPE))
                .andExpect(jsonPath("$", hasSize(6)))
        		.andExpect(jsonPath("[0]$.productId").value(1))
        		.andExpect(jsonPath("[1]$.productId").value(2))
        		.andExpect(jsonPath("[2]$.productId").value(3))
        		.andExpect(jsonPath("[3]$.productId").value(4))
        		.andExpect(jsonPath("[4]$.productId").value(5))
        		.andExpect(jsonPath("[5]$.productId").value(6));

        verify(productServiceMock, times(1)).getProductCatalog();
        verifyNoMoreInteractions(productServiceMock);
    }
}
