package com.ecommerce.restful.ws.validation;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

/*
 * 
 * Error handler - validation errors
 * 
 */

@ControllerAdvice
public class ErrorHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationError processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        ValidationError validationErrors = new ValidationError();

        for (FieldError fieldError: fieldErrors) {
            String errorMessage = getErrorMessage(fieldError);
            validationErrors.setValidationError(fieldError.getField(), errorMessage);
        }

        return validationErrors;
    }
	
	private String getErrorMessage(FieldError fieldError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String errorMessage = messageSource.getMessage(fieldError, currentLocale);

        if (errorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            errorMessage = fieldErrorCodes[0];
        }

        return errorMessage;
    }
	
	@ExceptionHandler(ClassNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleResourceNotFoundException(ClassNotFoundException ex) {
        LOGGER.debug("Resource not found - 404 !!");
    }
	
}
