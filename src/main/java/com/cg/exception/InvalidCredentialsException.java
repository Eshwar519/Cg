package com.cg.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =HttpStatus.NOT_FOUND)
public class InvalidCredentialsException extends Exception {
	private static final Logger logger =  LoggerFactory.getLogger(InvalidComplaintIdException.class);
	private static final long serialVersionUID = 1L;
	public InvalidCredentialsException(String msg) {
		super(msg);
		logger.info(msg);
	}
}
