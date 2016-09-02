package edu.auctionhsa.advices;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionAdvice {
	
	@ResponseStatus(value=HttpStatus.EXPECTATION_FAILED,   reason="An unknown error happened") 
	@ExceptionHandler(Exception.class)
	//@ResponseBody ErrorInfo //En caso de que quiera retornar en el response un objeto
	public String handleDemoException(Exception ex, HttpServletRequest req) {
		//req.setAttribute("javax.servlet.error.status_code", HttpStatus.BAD_REQUEST.value());
		ex.printStackTrace();
		return "An unknown error happened";
	}

}
