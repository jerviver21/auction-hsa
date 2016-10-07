package edu.auctionhsa.advices;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import edu.auctionhsa.model.exception.ErrorInfo;
import edu.auctionhsa.model.exception.InvalidAmountException;


//Intercepta una exception en el controlador para retornar un response de acuerdo a esto.
@ControllerAdvice
public class ExceptionAdvice {
	
	
	
	
	@ResponseStatus(value=HttpStatus.EXPECTATION_FAILED,   reason="Invalid Amount, it has to be bigger than the current maximun") 
	@ExceptionHandler(InvalidAmountException.class)
	@ResponseBody 
	public ErrorInfo handleInvalidAmountException(InvalidAmountException ex, HttpServletRequest req) {
		return new ErrorInfo(ErrorInfo.TYPE.INVALID_AMOUNT, "Invalid Amount, it has to be bigger than the current maximun");
	}
	
	@ResponseStatus(value=HttpStatus.FAILED_DEPENDENCY,   reason="An unknown error happened") 
	@ExceptionHandler(Exception.class)
	//@ResponseBody ErrorInfo //En caso de que quiera retornar en el response un objeto
	public String handleDemoException(Exception ex, HttpServletRequest req) {
		//req.setAttribute("javax.servlet.error.status_code", HttpStatus.BAD_REQUEST.value());
		ex.printStackTrace();
		return "An unknown error happened";
	}
	
	

}
