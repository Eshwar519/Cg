package com.cg.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =HttpStatus.NOT_FOUND)
public class InvalidEngineerIdException extends Exception{
	private static final Logger logger =  LoggerFactory.getLogger(InvalidEngineerIdException.class);
	private static final long serialVersionUID = 1L;
	public InvalidEngineerIdException(String msg) {
		super(msg);
		logger.info(msg);
	}
}
