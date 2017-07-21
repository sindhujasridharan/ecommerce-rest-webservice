package com.ecommerce.restful.ws.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.restful.ws.bean.ProductBean;
import com.ecommerce.restful.ws.model.Product;
import com.ecommerce.restful.ws.service.ProductService;
import com.ecommerce.restful.ws.validation.RestResourceException;

/*
 * 
 * Product Controller class - Product resource for REST service
 * 
 */

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ProductBean getProductById(@PathVariable("id") long id) throws RestResourceException {
		Product product = productService.getProductById(id);
		
		return createBean(product);
	}
	
	@RequestMapping(value = "/catalog", method = RequestMethod.GET)
	public List<ProductBean> getProductCatalog() throws RestResourceException {
		List<Product> productList = productService.getProductCatalog();
		
		return createBeanList(productList);
	}
	
	private ProductBean createBean(Product product) {
		ProductBean bean = new ProductBean();
		bean.setProductId(product.getProductId());
		bean.setProductName(product.getProductName());
		bean.setProductDescription(product.getProductDescription());
		bean.setProductPrice(product.getProductPrice());
		bean.setProductCategory(product.getProductCategory());
		bean.setProductImage(product.getProductImage());
		
		return bean;
	}
	
	private List<ProductBean> createBeanList(List<Product> productList) {
		List<ProductBean> beanList = new ArrayList<ProductBean>();
		for(Product product: productList) {
			beanList.add(createBean(product));
		}
		
		return beanList;
			
	}
}



