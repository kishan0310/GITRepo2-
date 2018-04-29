package com.accent.handler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice()
public class GenericExecptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GenericExecptionHandler.class);

	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(IOException.class)
	public void handleIOException(){
		logger.error("IOException handler executed");
	}

	/*@ExceptionHandler(SQLException.class)
	public String handleSQLException(HttpServletRequest request, Exception ex){
		logger.info("SQLException Occured:: URL="+request.getRequestURL());
		return "database_error";
	}*/

	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public String handleRuntimeException(RuntimeException e) {
		logger.error("RuntimeException handler executed");
		return e.getMessage();
	}

	@ExceptionHandler(GenericError.class)
	@ResponseBody
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public String handleGenericErrorException(GenericError e) {
		logger.error("GenericError/Exception handler executed");
		return e.getMessage();
	}
	
	@ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE)
    public String handleNullException(NullPointerException e) {
        logger.error("Null value of ISBN ");
        return e.getMessage();
    }
}
