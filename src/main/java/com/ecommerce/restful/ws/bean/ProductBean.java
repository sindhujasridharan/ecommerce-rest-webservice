package com.ecommerce.restful.ws.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import com.ecommerce.restful.ws.model.Product;

/*
 * 
 * Product Bean class - contains all the data for Product Bean class
 * 
 */
public class ProductBean {

	private long productId;
	
	@NotEmpty
	private String productName;
	
	@Length(min = Product.MIN_PRICE)
	private double productPrice;
	
	@Length(max = Product.MAX_LENGTH_DESCRIPTION)
	private String productDescription;
	
	@Length(max = Product.MAX_LENGTH_IMAGE)
	private String productImage;
	
	private String productCategory;

	public ProductBean() {
		
	}
	
	public long getProductId() {
		return productId;
	}



	public void setProductId(long productId) {
		this.productId = productId;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public double getProductPrice() {
		return productPrice;
	}



	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}



	public String getProductDescription() {
		return productDescription;
	}



	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}



	public String getProductImage() {
		return productImage;
	}



	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}



	public String getProductCategory() {
		return productCategory;
	}



	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	@Override
	public String toString() {
		return "ProductBean [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productDescription=" + productDescription + ", productImage=" + productImage + ", productCategory="
				+ productCategory + "]";
	}

	

	
	
}
