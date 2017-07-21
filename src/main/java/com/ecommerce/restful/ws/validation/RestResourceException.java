package com.ecommerce.restful.ws.validation;

/*
 * 
 * Custom exception
 * 
 */
public class RestResourceException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public RestResourceException(String message) {
        super(message);
    }
}
