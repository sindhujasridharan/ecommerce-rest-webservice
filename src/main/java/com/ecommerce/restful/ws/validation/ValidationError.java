package com.ecommerce.restful.ws.validation;

import java.util.ArrayList;
import java.util.List;

/*
 * 
 * Validation errors
 * 
 */
public class ValidationError {

	private List<ErrorMessage> validationErrors = new ArrayList<ErrorMessage>();

	public ValidationError() {
		super();
	}
	
	public void setValidationError(String parameter, String message) {
		validationErrors.add(new ErrorMessage(parameter, message));
	}

	public List<ErrorMessage> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(List<ErrorMessage> validationErrors) {
		this.validationErrors = validationErrors;
	}
	
	
	
}
