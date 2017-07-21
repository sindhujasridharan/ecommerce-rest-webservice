package com.ecommerce.restful.ws.model;

import java.io.Serializable;
import javax.persistence.*;

/*
 * 
 * Product model
 * 
 */

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final int MAX_LENGTH_DESCRIPTION = 500;
	public static final int MAX_LENGTH_IMAGE = 255;
    public static final int MAX_LENGTH_NAME = 100;
    public static final int MIN_PRICE = 0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productId;
	
	@Column(name = "productName", nullable = false)
	private String productName;
	
	@Column(name = "productPrice", nullable = false)
	private double productPrice;
	
	@Column(name = "productDescription")
	private String productDescription;
	
	@Column(name = "productImage")
	private String productImage;
	
	@Column(name = "productCategory")
	private String productCategory;

	public Product() {
		
	}
	
	public static Builder getBuilder(String name, double price) {
        return new Builder(name, price);
    }
	
	public long getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public String getProductImage() {
		return productImage;
	}

	public String getProductCategory() {
		return productCategory;
	}



	public static class Builder {

        private Product product;

        public Builder(String name, double price) {
        	product = new Product();
        	product.productName = name;
        	product.productPrice = price;
        	
        }

        public Product build() {
            return product;
        }
        
        public Builder id(long id) {
        	product.productId = id;
        	return this;
        }
        
        public Builder name(String name) {
            product.productName = name;
            return this;
        }
        
        public Builder price(double price) {
            product.productPrice = price;
            return this;
        }
        
        public Builder description(String description) {
            product.productDescription = description;
            return this;
        }
        
        public Builder category(String category) {
            product.productCategory = category;
            return this;
        }
        
        public Builder image(String image) {
        	product.productImage = image;
        	return this;
        }
    }



	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productDescription=" + productDescription + ", productImage=" + productImage + ", productCategory="
				+ productCategory + "]";
	}

	

	

	
}
