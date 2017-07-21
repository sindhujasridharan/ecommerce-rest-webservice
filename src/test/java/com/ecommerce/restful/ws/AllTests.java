package com.ecommerce.restful.ws;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ecommerce.restful.ws.controller.CustomerOrderControllerTest;
import com.ecommerce.restful.ws.controller.ProductControllerTest;
import com.ecommerce.restful.ws.dao.CustomerOrderDaoImplTest;
import com.ecommerce.restful.ws.dao.ProductDaoImplTest;
import com.ecommerce.restful.ws.model.CustomerOrderTest;
import com.ecommerce.restful.ws.model.ProductTest;
import com.ecommerce.restful.ws.service.CustomerOrderServiceImplTest;
import com.ecommerce.restful.ws.service.ProductServiceImplTest;

@RunWith(Suite.class)
@SuiteClasses({ CustomerOrderTest.class, ProductTest.class,
				CustomerOrderServiceImplTest.class, ProductServiceImplTest.class,
				CustomerOrderDaoImplTest.class, ProductDaoImplTest.class,
				CustomerOrderControllerTest.class, ProductControllerTest.class
			})
public class AllTests {

}
