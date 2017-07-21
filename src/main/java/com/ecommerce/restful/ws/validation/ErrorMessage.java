package com.ecommerce.restful.ws.validation;

/*
 * 
 * Error message - object type
 * 
 */
public class ErrorMessage {
	
	private String parameter;
	private String message;
	
	public ErrorMessage(String parameter, String message) {
		super();
		this.parameter = parameter;
		this.message = message;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
