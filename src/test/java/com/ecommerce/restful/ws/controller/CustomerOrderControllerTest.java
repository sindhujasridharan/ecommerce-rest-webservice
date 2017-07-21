package com.ecommerce.restful.ws.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ecommerce.restful.ws.bean.CustomerOrderBean;
import com.ecommerce.restful.ws.model.Customer;
import com.ecommerce.restful.ws.model.CustomerOrder;
import com.ecommerce.restful.ws.service.CustomerOrderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testDataSourceContext.xml", "classpath:testApplicationContext.xml"})
@WebAppConfiguration
public class CustomerOrderControllerTest {
	
	private static final long ID = 1L;
	private static final double TOTALCOST = 250.99;
    private static final String DESCRIPTION = "Test Order Description";
    private static final int ITEMS = 4;
    private static final String STATUS = "Active";
    public static final MediaType MEDIATYPE = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    
    private MockMvc mockMvc;

    @Autowired
    private CustomerOrderService orderServiceMock;
    
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        Mockito.reset(orderServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    
    
    @Test
    public void createNewOrder_Successful() throws Exception {
    	CustomerOrder order = CustomerOrder.getBuilder(TOTALCOST, ITEMS)
    			.id(ID)
				.description(DESCRIPTION)
				.status(STATUS)
				.customer(Customer.getBuilder().id(ID).firstName("Test").lastName("Customer").email("testuser@gmail.com").phoneNo("(910) 333-3030").build())
				.build();
        when(orderServiceMock.createNewOrder(any(CustomerOrderBean.class))).thenReturn(order);

        String jsonParam = "{ \"numberOfItems\": 5, \"totalCost\": 350.00, \"orderDescription\": \"Test\", \"orderStatus\": \"Active\", \"customerId\": 1}";
        
        mockMvc.perform(post("/order/create")
                .contentType(MEDIATYPE)
                .content(jsonParam.getBytes()))
        		.andExpect(status().isOk())
                .andExpect(content().contentType(MEDIATYPE))
                .andExpect(jsonPath("$.orderId").value(1))
                .andExpect(jsonPath("$.numberOfItems").value(ITEMS))
                .andExpect(jsonPath("$.totalCost").value(TOTALCOST))
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.orderStatus").value(STATUS))
                .andExpect(jsonPath("$.orderDescription").value(DESCRIPTION));

        ArgumentCaptor<CustomerOrderBean> orderBeanCaptor = ArgumentCaptor.forClass(CustomerOrderBean.class);
        verify(orderServiceMock, times(1)).createNewOrder(orderBeanCaptor.capture());
        verifyNoMoreInteractions(orderServiceMock);

        CustomerOrderBean result = orderBeanCaptor.getValue();
        Assert.assertNotNull(result);
    }

    
    @Test
    public void modifyExistingOrder_Successful() throws Exception {
    	
    	CustomerOrder order = CustomerOrder.getBuilder(TOTALCOST, ITEMS)
    			.id(ID)
				.description(DESCRIPTION)
				.status(STATUS)
				.customer(Customer.getBuilder().id(ID).firstName("Test").lastName("Customer").email("testuser@gmail.com").phoneNo("(910) 333-3030").build())
				.build();
        when(orderServiceMock.modifyExistingOrder(any(CustomerOrderBean.class))).thenReturn(order);

        String jsonParam = "{ \"orderId\": 1, \"numberOfItems\": 5, \"totalCost\": 350.00, \"orderDescription\": \"Test\", \"orderStatus\": \"Active\", \"customerId\": 1}";
        
        mockMvc.perform(put("/order/modify")
                .contentType(MEDIATYPE)
                .content(jsonParam.getBytes()))
        		.andExpect(status().isOk())
                .andExpect(content().contentType(MEDIATYPE))
                .andExpect(jsonPath("$.orderId").value(1))
                .andExpect(jsonPath("$.numberOfItems").value(ITEMS))
                .andExpect(jsonPath("$.totalCost").value(TOTALCOST))
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.orderStatus").value(STATUS))
                .andExpect(jsonPath("$.orderDescription").value(DESCRIPTION));

        ArgumentCaptor<CustomerOrderBean> orderBeanCaptor = ArgumentCaptor.forClass(CustomerOrderBean.class);
        verify(orderServiceMock, times(1)).modifyExistingOrder(orderBeanCaptor.capture());
        verifyNoMoreInteractions(orderServiceMock);

        CustomerOrderBean result = orderBeanCaptor.getValue();
        Assert.assertNotNull(result);
        
        
    }
    
    @Test
    public void getOrderById_Successful() throws Exception {
    	CustomerOrder order = CustomerOrder.getBuilder(TOTALCOST, ITEMS)
    			.id(ID)
				.description(DESCRIPTION)
				.status(STATUS)
				.customer(Customer.getBuilder().id(ID).firstName("Test").lastName("Customer").email("testuser@gmail.com").phoneNo("(910) 333-3030").build())
				.build();

        when(orderServiceMock.getOrderById(ID)).thenReturn(order);

        mockMvc.perform(get("/order/{id}", 1L))
        		.andExpect(status().isOk())
                .andExpect(content().contentType(MEDIATYPE))
                .andExpect(jsonPath("$.orderId").value(1))
                .andExpect(jsonPath("$.numberOfItems").value(ITEMS))
                .andExpect(jsonPath("$.totalCost").value(TOTALCOST))
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.orderStatus").value(STATUS))
                .andExpect(jsonPath("$.orderDescription").value(DESCRIPTION));

        verify(orderServiceMock, times(1)).getOrderById(ID);
        verifyNoMoreInteractions(orderServiceMock);
    }
    
    @Test
    public void getPlacedOrders_Successful() throws Exception {
    	List<CustomerOrder> orderList = new ArrayList<CustomerOrder>();
		orderList.add(CustomerOrder.getBuilder(25.90, 2)
									.id(1L)
									.description("Test Order1")
									.status("Closed")
									.customer(Customer.getBuilder().id(ID).firstName("Test").lastName("Customer").email("testuser@gmail.com").phoneNo("(910) 333-3030").build())
									.build());
		orderList.add(CustomerOrder.getBuilder(65.50, 4)
									.id(2L)
									.description("Test Order2")
									.status("Closed")
									.customer(Customer.getBuilder().id(ID).firstName("Test").lastName("Customer").email("testuser@gmail.com").phoneNo("(910) 333-3030").build())
									.build());
		orderList.add(CustomerOrder.getBuilder(34.40, 1)
									.id(3L)
									.description("Test Order3")
									.status("Closed")
									.customer(Customer.getBuilder().id(ID).firstName("Test").lastName("Customer").email("testuser@gmail.com").phoneNo("(910) 333-3030").build())
									.build());
		orderList.add(CustomerOrder.getBuilder(455.45, 6)
									.id(4L)
									.description("Test Order4")
									.status("Closed")
									.customer(Customer.getBuilder().id(ID).firstName("Test").lastName("Customer").email("testuser@gmail.com").phoneNo("(910) 333-3030").build())
									.build());
		orderList.add(CustomerOrder.getBuilder(27.99, 3)
									.id(5L)
									.description("Test Order5")
									.status("Closed")
									.customer(Customer.getBuilder().id(ID).firstName("Test").lastName("Customer").email("testuser@gmail.com").phoneNo("(910) 333-3030").build())
									.build());


		when(orderServiceMock.getPlacedOrder(ID)).thenReturn(orderList);
        mockMvc.perform(get("/order/user/{id}", 1L))
        		.andExpect(status().isOk())
                .andExpect(content().contentType(MEDIATYPE))
                .andExpect(jsonPath("$", hasSize(5)))
        		.andExpect(jsonPath("[0]$.orderId").value(1))
        		.andExpect(jsonPath("[1]$.orderId").value(2))
        		.andExpect(jsonPath("[2]$.orderId").value(3))
        		.andExpect(jsonPath("[3]$.orderId").value(4))
        		.andExpect(jsonPath("[4]$.orderId").value(5));

        verify(orderServiceMock, times(1)).getPlacedOrder(ID);
        verifyNoMoreInteractions(orderServiceMock);
    }
}
