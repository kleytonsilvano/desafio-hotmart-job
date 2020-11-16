package com.hotmart.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedRuntimeException;

public class ValidationException extends NestedRuntimeException {

	private static Logger logger = LoggerFactory.getLogger(ValidationException.class);
	
	private static final long serialVersionUID = -2906713668353779395L;

	public ValidationException(String message) {
		super(message);
		logger.error(message);
	}
	
}
